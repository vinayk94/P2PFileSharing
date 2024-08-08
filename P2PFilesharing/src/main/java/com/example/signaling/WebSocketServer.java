package com.example.signaling;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;

public class WebSocketServer {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/ws", SignalingServer.class);

        try {
            server.start();
            System.out.println("WebSocket server started at ws://localhost:8080/ws");
            // Keep the server running
            new java.util.Scanner(System.in).nextLine();
        } catch (DeploymentException e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
