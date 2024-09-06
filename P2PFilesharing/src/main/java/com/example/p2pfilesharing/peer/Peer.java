package com.example.p2pfilesharing.peer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

/**
 * Represents a peer in the P2P network.
 */
@ClientEndpoint
public class Peer {
    private static final Logger logger = LogManager.getLogger(Peer.class);
    private Session session;
    private final String peerId;
    private final CountDownLatch latch;

    public Peer(String peerId) {
        this.peerId = peerId;
        this.latch = new CountDownLatch(1);
    }

    /**
     * Connects to the signaling server.
     *
     * @param uri the WebSocket URI of the signaling server
     * @throws Exception if connection fails
     */
    public void connect(URI uri) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, uri);
        latch.await();
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        logger.info("Connected to signaling server: {}", session.getId());
        latch.countDown();
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("Received message: {}", message);
        // Handle incoming messages (e.g., signaling data for WebRTC)
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Disconnected from signaling server: {}", closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("Error in WebSocket session", throwable);
    }

    /**
     * Sends a message to the signaling server.
     *
     * @param message the message to send
     * @throws IOException if sending fails
     */
    public void sendMessage(String message) throws IOException {
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(message);
        } else {
            logger.warn("Cannot send message: WebSocket session is not open");
        }
    }

    public String getPeerId() {
        return peerId;
    }
}