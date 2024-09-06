package com.example.p2pfilesharing.signaling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.websocket.Session;
import java.io.IOException;
import com.example.p2pfilesharing.signaling.SignalingServer;

import static org.mockito.Mockito.*;

class SignalingServerTest {

    private SignalingServer signalingServer;

    @Mock
    private Session session1;

    @Mock
    private Session session2;

    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        signalingServer = new SignalingServer();
        when(session1.getId()).thenReturn("session1");
        when(session2.getId()).thenReturn("session2");
    }

    @Test
    void testOnOpen() {
        signalingServer.onOpen(session1);
        verify(session1).getId();
    }

    @Test
    void testOnClose() {
        signalingServer.onOpen(session1);
        signalingServer.onClose(session1);
        verify(session1, times(2)).getId();
    }

    @Test
    void testOnMessage() throws IOException {
        signalingServer.onOpen(session1);
        signalingServer.onOpen(session2);

        String message = "Test message";
        signalingServer.onMessage(message, session1);

        verify(session2).getBasicRemote();
        verify(session2.getBasicRemote()).sendText(message);
        verify(session1, never().description("Sender should not receive their own message")).getBasicRemote();
    }

    @Test
    void testOnError() {
        Throwable throwable = new RuntimeException("Test exception");
        signalingServer.onError(session1, throwable);
        verify(session1).getId();
    }
}