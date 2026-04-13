# 🚀 Spring Boot Quiz Application – Microservices Architecture

A **scalable Quiz Application** built using **Spring Boot Microservices**, demonstrating real-world backend architecture including **Service Discovery, API Gateway, Inter-service Communication, and Database Management**.

---

## 🧠 Project Overview

This project follows a **distributed microservices architecture**, where each service is independently deployable and responsible for a specific domain.

🔹 The system enables:

* Managing quiz questions
* Creating dynamic quizzes
* Submitting quizzes and calculating results

---

## 🏗️ Architecture

```
Client → API Gateway → Microservices → Database
                     ↓
               Service Registry (Eureka)
```

### 🔹 Core Components

| Component            | Description                                 |
| -------------------- | ------------------------------------------- |
| **Service Registry** | Eureka Server for service discovery         |
| **API Gateway**      | Single entry point for routing requests     |
| **Question Service** | Handles CRUD operations for questions       |
| **Quiz Service**     | Manages quiz creation, submission & scoring |
| **PostgreSQL**       | Persistent storage for application data     |

---

## ⚙️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Cloud (Eureka, Gateway, OpenFeign)**
* **Spring Data JPA (Hibernate)**
* **PostgreSQL**
* **Maven**

---

## 📦 Microservices Breakdown

### 🟢 1. Service Registry (Eureka Server)

* Registers all microservices
* Enables dynamic service discovery
* Dashboard: `http://localhost:8761`

---

### 🔵 2. API Gateway

* Acts as **entry point**
* Routes requests to appropriate services
* Can be extended with:

  * Authentication (JWT)
  * Rate limiting
  * Logging

---

### 🟡 3. Question Service

* Manages question bank
* Supports CRUD operations

#### APIs:

```
GET    /questions
POST   /questions
PUT    /questions/{id}
DELETE /questions/{id}
```

---

### 🔴 4. Quiz Service

* Creates quizzes dynamically
* Fetches questions via Feign Client
* Evaluates quiz submissions

#### APIs:

```
POST   /quiz/create
GET    /quiz/{id}
POST   /quiz/submit/{id}
```

---

## 🔗 Inter-Service Communication

* Implemented using **OpenFeign Client**
* Quiz Service communicates with Question Service via REST APIs

👉 Enables:

* Loose coupling
* Scalability
* Maintainability

---

## 🗄️ Database Configuration

Each service can use its own database/schema (recommended for microservices).

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/quiz_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## ▶️ How to Run the Project

### 1️⃣ Clone the repository

```
git clone https://github.com/catMansCodes/springboot-quiz-app-microservices.git
cd springboot-quiz-app-microservices
```

---

### 2️⃣ Start Services in Order

✅ Step 1: Start Service Registry

```
cd service-registry
mvn spring-boot:run
```

✅ Step 2: Start API Gateway

```
cd api-gateway
mvn spring-boot:run
```

✅ Step 3: Start Question Service

```
cd question-service
mvn spring-boot:run
```

✅ Step 4: Start Quiz Service

```
cd quiz-service
mvn spring-boot:run
```

---

## 📡 API Flow Example

### 🔹 Create Quiz

```
POST /quiz/create?category=Java&numOfQuestions=5
```

### 🔹 Get Quiz

```
GET /quiz/{id}
```

### 🔹 Submit Quiz

```
POST /quiz/submit/{id}
```

---

## 🧪 Sample Question JSON

```json
{
  "questionText": "What is Java?",
  "optionA": "OOP Language",
  "optionB": "Database language",
  "optionC": "Frontend language",
  "optionD": "Text editor",
  "correctAnswer": "OOP Language",
  "category": "Java",
  "difficulty": "easy"
}
```

---

## ✅ Key Features

* ✔️ Microservices-based architecture
* ✔️ Service Discovery using Eureka
* ✔️ API Gateway routing
* ✔️ Feign Client for inter-service communication
* ✔️ PostgreSQL integration
* ✔️ RESTful APIs for Quiz & Question management
* ✔️ Scalable & loosely coupled system

---

## 🧠 Learning Outcomes

This project demonstrates:

* Microservices architecture design
* Service-to-service communication
* API Gateway pattern
* Eureka-based service discovery
* Real-world backend system design

---

## 🚀 Future Enhancements

* 🔐 Spring Security + JWT Authentication
* 🐳 Docker & Kubernetes deployment
* 📊 Quiz analytics & leaderboard
* ⚡ Resilience4j (Circuit Breaker)
* 📄 Swagger / OpenAPI documentation
* 🧠 AI-based question generation (Spring AI)

---

## 🤝 Contributing

Contributions are welcome!
Feel free to fork the repo and raise a PR.

---

## 👨‍💻 Author

**Vimal Maru**
Java | Spring Boot | Microservices | AI Enthusiast

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and share it with others!
