# 🏦 Banking System – Microservices Architecture

A scalable, event-driven **banking backend** built with a **microservices architecture**. It supports account management, secure transactions, online payments via **Razorpay**, real-time **fraud detection**, and user **notifications**, all routed through a central **API Gateway** and accelerated with **Redis** caching.

---

## ✨ Features

- 👤 **Account Management** – create and manage customer bank accounts, check balances
- 💸 **Transactions** – deposits, withdrawals and fund transfers with a full transaction history
- 💳 **Razorpay Payments** – order creation, payment verification and secure checkout flow
- 🛡️ **Fraud Detection** – flags suspicious or abnormal transactions
- 🔔 **Notifications** – alerts to users for payments and account activity
- 🚪 **API Gateway** – single entry point with request routing to all services
- ⚡ **Redis Caching** – faster reads and reduced database load
- 🐳 **Dockerized** – entire system runs with a single `docker-compose` command

---

## 🧩 Microservices Overview

| Service | Responsibility |
|---|---|
| `api-gateway-service` | Single entry point; routes client requests to the right service |
| `account-service` | Customer accounts, balances and account details |
| `transaction-service` | Money transfers, transaction records and history |
| `payment-service` | Razorpay integration (order creation and payment verification) |
| `fraud-detection-service` | Analyses transactions and flags suspicious activity |
| `notification-service` | Sends notifications on transactions and payments |

---

## 🏗️ Architecture

```
                     ┌──────────────────────┐
   Client / App ───▶ │   API Gateway        │
                     └─────────┬────────────┘
        ┌───────────┬──────────┼───────────┬───────────────┐
        ▼           ▼          ▼           ▼               ▼
   ┌─────────┐ ┌───────────┐ ┌─────────┐ ┌──────────────┐ ┌───────────────┐
   │ Account │ │Transaction│ │ Payment │ │    Fraud     │ │ Notification  │
   │ Service │ │  Service  │ │ Service │ │  Detection   │ │   Service     │
   └────┬────┘ └─────┬─────┘ └────┬────┘ └──────────────┘ └───────────────┘
        │            │            │
        └────────────┴─────┬──────┴──────────┐
                           ▼                 ▼
                       Database            Redis
                                        (Caching)
                                      Razorpay API
```

---

## 🛠️ Tech Stack

- **Architecture:** Microservices, API Gateway pattern
- **Payments:** Razorpay
- **Caching:** Redis
- **Containerization:** Docker & Docker Compose
- **Backend:** <!-- e.g. Java, Spring Boot, Spring Cloud Gateway -->
- **Database:** <!-- e.g. MySQL / PostgreSQL / MongoDB -->
- **Messaging (if used):** <!-- e.g. Kafka / RabbitMQ -->

---

## 📁 Project Structure

```
Banking_System/
├── account-service/
├── api-gateway-service/
├── fraud-detection-service/
├── notification-service/
├── payment-service/
├── transaction-service/
└── docker-compose.yml
```

---

## 🚀 Getting Started

### Prerequisites

- Docker & Docker Compose
- A Razorpay account (test mode keys)
- <!-- JDK 17+ and Maven/Gradle, if running services without Docker -->

### 1. Clone the repository

```bash
git clone https://github.com/Yashabes/Banking_System.git
cd Banking_System
```

### 2. Configure environment variables

Add your Razorpay test credentials (never commit real keys):

```properties
RAZORPAY_KEY_ID=your_key_id
RAZORPAY_KEY_SECRET=your_key_secret
```

### 3. Run with Docker Compose

```bash
docker-compose up --build
```

All services, Redis and the gateway will start together. Access the system through the API Gateway.

---

## 🔌 Sample API Flow (Payments)

1. Client calls the gateway → `payment-service` to **create a Razorpay order**
2. User completes payment on the Razorpay checkout
3. Client sends payment details for **signature verification**
4. `transaction-service` records the transaction
5. `fraud-detection-service` evaluates it
6. `notification-service` informs the user

> Update the endpoints below with your actual routes.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/accounts` | Create an account |
| `GET` | `/api/accounts/{id}` | Get account details |
| `POST` | `/api/transactions/transfer` | Transfer funds |
| `POST` | `/api/payments/create-order` | Create Razorpay order |
| `POST` | `/api/payments/verify` | Verify Razorpay payment |

---

## 🔐 Security Notes

- Razorpay payment signatures are verified server-side
- Secrets are supplied through environment variables, not hard-coded
- All external traffic passes through the API Gateway

---

## 🔮 Future Improvements

- JWT-based authentication & role-based access
- Service discovery and centralized configuration
- Distributed tracing and monitoring (Prometheus, Grafana)
- CI/CD pipeline and Kubernetes deployment
- Unit and integration test coverage

---

## 🤝 Contributing

Contributions, issues and feature requests are welcome. Feel free to open an issue or submit a pull request.

---

## 👨‍💻 Author

**Yash** – [GitHub](https://github.com/Yashabes) · [LinkedIn](https://www.linkedin.com/in/taliyanyash)

⭐ If you found this project useful, consider giving it a star!
