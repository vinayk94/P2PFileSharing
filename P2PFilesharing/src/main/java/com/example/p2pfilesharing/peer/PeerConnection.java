package com.example.p2pfilesharing.peer;

import com.example.p2pfilesharing.util.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Handles the peer-to-peer connection for file transfer.
 * This is a placeholder class and needs to be implemented with actual WebRTC functionality.
 */
public class PeerConnection {
    private static final Logger logger = LogManager.getLogger(PeerConnection.class);
    private final String remotePeerId;
    private boolean isConnected;

    public PeerConnection(String remotePeerId) {
        this.remotePeerId = remotePeerId;
        this.isConnected = false;
    }

    /**
     * Establishes a connection with the remote peer.
     * This method should implement WebRTC connection logic.
     */
    public void connect() {
        logger.info("Connecting to peer: {}", remotePeerId);
        // Implement WebRTC connection logic here
        // For now, we'll just set isConnected to true
        isConnected = true;
        logger.info("Connected to peer: {}", remotePeerId);
    }

    /**
     * Sends a file to the connected peer.
     *
     * @param file The file to send
     * @throws IOException If an I/O error occurs
     */
    public void sendFile(File file) throws IOException {
        if (!isConnected) {
            throw new IllegalStateException("Not connected to peer");
        }
        logger.info("Sending file {} to peer: {}", file.getName(), remotePeerId);
        byte[] fileData = FileUtils.readFileToByteArray(file);
        // Implement file sending logic here
        // For now, we'll just log the file size
        logger.info("File size: {} bytes", fileData.length);
    }

    /**
     * Receives a file from the connected peer.
     *
     * @param fileName The name of the file to receive
     * @param fileData The byte array containing the file data
     * @throws IOException If an I/O error occurs
     */
    public void receiveFile(String fileName, byte[] fileData) throws IOException {
        if (!isConnected) {
            throw new IllegalStateException("Not connected to peer");
        }
        logger.info("Receiving file {} from peer: {}", fileName, remotePeerId);
        File receivedFile = new File(fileName);
        FileUtils.writeByteArrayToFile(receivedFile, fileData);
        logger.info("File received and saved: {}", receivedFile.getAbsolutePath());
    }

    /**
     * Closes the connection with the remote peer.
     */
    public void disconnect() {
        if (isConnected) {
            // Implement WebRTC disconnection logic here
            isConnected = false;
            logger.info("Disconnected from peer: {}", remotePeerId);
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getRemotePeerId() {
        return remotePeerId;
    }
}