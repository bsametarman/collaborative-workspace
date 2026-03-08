# Collaborative Workspace (Backend)

Distributed, collaborative text editing. Backend built with a microservices architecture. 

This system allows multiple users to connect via WebSockets and edit documents concurrently. It features JWT authentication, event driven message routing, and asynchronous data persistence to ensure high availability and fast synchronization.

## 🏗️ Architecture & Microservices

The backend is decoupled into three independent Spring Boot microservices, communicating via standard REST APIs and Apache Kafka events.

### 1. User Service
The dedicated authentication authority for the system.
* **Responsibilities:** Handles user registration and login.
* **Security:** Secures passwords using BCrypt.
* **Token Factory:** Generates and signs JSON Web Tokens (JWT) upon successful authentication, allowing for stateless authorization across the network.

### 2. Workspace Gateway (API Gateway)
The primary entry point for all client traffic and real time connections.
* **Responsibilities:** Manages persistent WebSocket (STOMP/SockJS) tunnels with connected users.
* **Security Interceptor:** intercepts initial WebSocket handshakes, extracts the JWT, and validates the cryptographic signature statelessly (without querying the database).
* **Event Publisher:** Receives real time document edits (keystrokes) and instantly publishes them to Kafka topics for asynchronous processing.

### 3. Snapshot Worker (Processing Engine)
The background service that ensures data integrity.
* **Responsibilities:** Operates as a Kafka consumer, constantly listening for stream events from the Gateway.
* **Reconstruction:** Catches individual keystrokes and reconstructs the document's state.
* **Persistence:** Safely commits the finalized document snapshots to the PostgreSQL database. 

## 🚀 Tech Stack

* **Core:** Java, Spring Boot
* **Real-Time Communication:** WebSockets, STOMP, SockJS
* **Message Broker:** Apache Kafka, Zookeeper
* **Security:** JWT, BCrypt
* **Database:** PostgreSQL

## ⚙️ How to Run Locally

1. **Start the Infrastructure:**
   Ensure Zookeeper and Apache Kafka are running locally.
2. **Start the Microservices:**
   Boot up `user-service`, `workspace-gateway`, and `snapshot-worker` via your IDE or Maven (`mvn spring-boot:run`).
3. **Authenticate:**
   Send a `POST` request to `http://localhost:8082/api/auth/register` to create a user, then to `/login` to receive your Bearer Token.
4. **Connect:**
   Pass the JWT in the `Authorization` header during the SockJS connection handshake to establish the WebSocket tunnel with the Gateway.