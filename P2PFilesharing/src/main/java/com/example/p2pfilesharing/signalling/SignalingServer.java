package com.example.p2pfilesharing.signaling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * WebSocket server for handling signaling between peers.
 */
@ServerEndpoint("/signaling")
public class SignalingServer {
    private static final Logger logger = LogManager.getLogger(SignalingServer.class);
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        logger.info("New WebSocket connection: {}", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        logger.info("WebSocket connection closed: {}", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("Received message from {}: {}", session.getId(), message);
        broadcastMessage(message, session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("Error in WebSocket session {}", session.getId(), throwable);
    }

    private void broadcastMessage(String message, Session senderSession) {
        synchronized (sessions) {
            for (Session session : sessions) {
                if (!session.equals(senderSession)) {
                    try {
                        session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        logger.error("Error sending message to session {}", session.getId(), e);
                    }
                }
            }
        }
    }
}