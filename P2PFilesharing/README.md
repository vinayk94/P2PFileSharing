# P2P File Sharing Application

## Project Overview

This Java-based peer-to-peer (P2P) file-sharing application enables direct file transfers between users over the internet. It utilizes WebSocket for signaling and aims to implement WebRTC for peer-to-peer connections.

## Key Components

### 1. Main.java
- **Purpose**: Application entry point
- **Functionality**: Initializes the SignalingServer and sets up the application environment

### 2. SignalingServer.java
- **Purpose**: Facilitates communication between peers
- **Functionality**:
  - Manages WebSocket connections
  - Handles peer join/leave events
  - Relays messages between peers for connection establishment
- **Key Methods**:
  - `onOpen()`: Handles new peer connections
  - `onMessage()`: Processes and relays messages between peers
  - `onClose()`: Manages peer disconnections

### 3. Peer.java
- **Purpose**: Represents a user in the P2P network
- **Functionality**:
  - Connects to the SignalingServer
  - Sends and receives messages for peer discovery and connection setup
- **Key Methods**:
  - `connect()`: Establishes connection to the SignalingServer
  - `sendMessage()`: Sends messages through the WebSocket connection

### 4. PeerConnection.java
- **Purpose**: Manages direct P2P connections for file transfer
- **Functionality**:
  - Establishes direct connections between peers (to be implemented with WebRTC)
  - Handles file transfer operations
- **Key Methods**:
  - `connect()`: Initiates a P2P connection
  - `sendFile()`: Transfers a file to a connected peer
  - `receiveFile()`: Receives a file from a connected peer

### 5. FileUtils.java
- **Purpose**: Provides utility methods for file operations
- **Functionality**:
  - Reads files into byte arrays
  - Writes byte arrays to files
- **Key Methods**:
  - `readFileToByteArray()`: Converts a file to a byte array
  - `writeByteArrayToFile()`: Saves a byte array as a file

## How It Works

1. The application starts with `Main.java`, which initializes the `SignalingServer`.
2. Peers connect to the `SignalingServer` using `Peer.java`.
3. When a peer wants to share a file:
   a. It sends a message through the `SignalingServer` to discover available peers.
   b. Once a target peer is identified, they exchange connection information via the `SignalingServer`.
   c. `PeerConnection` uses this information to establish a direct P2P connection.
   d. The file is transferred directly between peers using `PeerConnection` and `FileUtils`.

## Setup and Running

1. Clone the repository:
   ```
   git clone https://github.com/vinayk94/P2PFileSharing.git
   cd p2pfilesharing
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn exec:java -Dexec.mainClass="com.example.p2pfilesharing.Main"
   ```

## Development Notes

- The project uses Maven for dependency management and build automation.
- JUnit is used for unit testing. Run tests with `mvn test`.
- Log4j is used for logging. Configure logging in `src/main/resources/log4j2.xml`.
- The WebSocket implementation uses the Java WebSocket API (javax.websocket).

## Current Limitations and TODOs

- WebRTC implementation for P2P connections is not yet complete.
- File transfer functionality needs to be fully implemented in `PeerConnection`.
- User interface is not yet developed.
- Error handling and edge cases need to be addressed in all components.

## Contributing

Contributions are welcome! Here are some areas where you can help:
- Implementing WebRTC for P2P connections
- Developing a user interface
- Improving error handling and adding more unit tests
- Enhancing the file transfer mechanism for larger files

Please fork the repository, create a feature branch, and submit a pull request for review.

## License

This project is licensed under the MIT License.