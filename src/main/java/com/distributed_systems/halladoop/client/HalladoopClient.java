package com.distributed_systems.halladoop.client;

import spark.Spark;

import java.io.File;

import static spark.Spark.*;

/**
 * Created by devin on 12/3/15.
 */
public class HalladoopClient {
    private static final String WRITE_ENDPOINT = "/write";
    private static final String CONFIRM_PACKET = "/confirmPacket";
    private static final String WRITE_PIPELINE = "/getWritePipeline";
    private static final String READ_MANIFEST = "/getReadManifest";

    private final String NAME_NODE_ADDRESS;
    private static HalladoopClient instance;

    private HalladoopClient(String NAME_NODE_ADDRESS) {
        this.NAME_NODE_ADDRESS = NAME_NODE_ADDRESS;
        initializeEndPoints();
    }

    private void initializeEndPoints() {
        post(WRITE_ENDPOINT, (request, response) -> {
            return response.body();
        });

        post(CONFIRM_PACKET, ((request, response) -> {
            return response.body();
        }));
    }

    public void write(File file) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost postRequest = new HttpPost("http://localhost:4567/write");
    }

    public File read(String fileName) {
        return null;
    }

    /***
     * This method is provided by to create a single instance of the Halladoop client.
     * Only one instance of the client is allowed due to a single instance of the Spark server.
     *
     * @param address The address to the Halladoop name server.
     * @return The created instance of the Halladoop client.
     */
    public static HalladoopClient getClient(String address) {
        if (instance == null) {
            instance = new HalladoopClient(address);
        }

        return instance;
    }
}