package com.example.p2pfilesharing.signaling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.tyrus.server.Server;
import com.example.p2pfilesharing.signaling.SignalingServer;

/**
 * Configuration class for the WebSocket server.
 */
public class WebSocketConfig {
    private static final Logger logger = LogManager.getLogger(WebSocketConfig.class);
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String PATH = "/ws";

    /**
     * Starts the WebSocket server.
     *
     * @throws Exception if the server fails to start
     */
    public static void startServer() throws Exception {
        Server server = new Server(HOST, PORT, PATH, null, SignalingServer.class);
        try {
            server.start();
            logger.info("WebSocket server started at ws://{}:{}{}", HOST, PORT, PATH);
        } catch (Exception e) {
            logger.error("Failed to start WebSocket server", e);
            throw e;
        }
    }
}