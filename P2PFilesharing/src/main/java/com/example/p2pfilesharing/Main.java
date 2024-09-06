package com.example.p2pfilesharing;

import com.example.p2pfilesharing.signaling.WebSocketConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main entry point for the P2P File Sharing application.
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting P2P File Sharing application");

        try {
            WebSocketConfig.startServer();
            logger.info("WebSocket server started successfully");

            // Keep the application running
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.error("Error starting the application", e);
        }
    }
}