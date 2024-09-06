package com.example.p2pfilesharing.peer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.websocket.Session;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PeerTest {

    private Peer peer;

    @Mock
    private Session session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        peer = new Peer("testPeer");
    }

    @Test
    void testOnOpen() {
        peer.onOpen(session);
        verify(session).getId();
    }

    @Test
    void testOnMessage() {
        String message = "Test message";
        peer.onMessage(message);
        // Since onMessage only logs the message, we can't easily verify its behavior
        // In a real scenario, we might want to add some testable behavior here
    }

    @Test
    void testOnClose() {
        peer.onClose(session, null);
        verify(session).getId();
    }

    @Test
    void testSendMessage() throws IOException {
        peer.onOpen(session);
        when(session.isOpen()).thenReturn(true);

        String message = "Test message";
        peer.sendMessage(message);

        verify(session).getBasicRemote();
        verify(session.getBasicRemote()).sendText(message);
    }

    @Test
    void testSendMessageClosedSession() throws IOException {
        peer.onOpen(session);
        when(session.isOpen()).thenReturn(false);

        String message = "Test message";
        peer.sendMessage(message);

        verify(session, never()).getBasicRemote();
    }

    @Test
    void testGetPeerId() {
        assertEquals("testPeer", peer.getPeerId());
    }
}