# P2P File Sharing

## Project Overview

This project is a peer-to-peer (P2P) file-sharing application implemented in Java. It allows users in different locations to share files directly over the internet, ensuring secure and efficient file transfers.

## Setup Instructions

### Prerequisites

- JDK 17 or later
- IntelliJ IDEA (latest edition)
- Maven

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/P2PFileSharing.git
cd P2PFileSharing
```

### Step 2: Open the Project in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the cloned repository directory.
3. Click "OK" to open the project.

### Step 3: Build the Project

1. Open the `pom.xml` file.
2. Click "Load Maven Changes" to download the dependencies and build the project.

### Step 4: Run the Signaling Server

1. Navigate to `src/main/java/com/example/signaling/WebSocketServer.java`.
2. Right-click the file and select "Run 'WebSocketServer.main()'".
3. The WebSocket server will start and listen on `ws://localhost:8080/ws`.

## Project Structure

* `src/main/java/com/example/signaling`
    * `SignalingServer.java`: Handles WebSocket connections and messaging between peers.
    * `WebSocketServer.java`: Configures and starts the WebSocket server.

## Dependencies

* `javax.websocket`: WebSocket API for Java.
* `org.glassfish.tyrus`: WebSocket implementation for Java.
* `org.slf4j`: Logging framework for Java.
* `org.openjfx`: JavaFX library for building user interfaces.

## Next Steps

1. Implement WebRTC connection setup.
2. Add encryption for secure file transfers.
3. Develop the file transfer mechanism.
4. Build the user interface with JavaFX.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
