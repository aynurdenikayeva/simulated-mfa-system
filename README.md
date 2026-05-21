# 🔐 MFA Security System (Spring Boot)

A secure backend system built with Spring Boot that implements **JWT Authentication + Multi-Factor Authentication (MFA)** using OTP (One-Time Password) for sensitive operations.

This project simulates **enterprise-level security architecture** with step-up authentication for actions like password changes and payment processing.

---

# 🚀 Features

- 👤 User Registration & Login
- 🔑 JWT Authentication (Stateless Security)
- 🛡 Role-based Access Control (USER / ADMIN)
- 🔐 Multi-Factor Authentication (OTP-based)
- ⏱ OTP Expiration (5 minutes)
- 🚫 OTP Replay Protection (single-use)
- 🔁 Attempt Limiting (max 5 tries)
- 📩 OTP Simulation (console-based email/SMS)
- 👤 Get Current User Profile
- ⚙ Clean layered architecture (Controller → Service → Repository)

---

# 🏗 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

---

# 📁 Project Structure
```text
mfa_security_system
│
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── mapper
├── repository
├── security
├── service
├── util
└── MfaSecuritySystemApplication.java
```

---

# 🔄 System Architecture Flow
User Register
↓
User Login (JWT generated)
↓
Access Sensitive Endpoint
↓
OTP Generated (/mfa/generate)
↓
OTP Sent (Console Simulation)
↓
OTP Verification (/mfa/verify)
↓
Access Granted

---

# 🔐 Authentication Flow

## 📌 Register

**POST**
/auth/register
<img width="901" height="770" alt="Register" src="https://github.com/user-attachments/assets/6c1b246d-3f74-49eb-a0a1-4c025d2757d9" />
## 📌 Login
**POST**
/auth/login
<img width="877" height="751" alt="LoginJwt" src="https://github.com/user-attachments/assets/5731280b-185a-4b98-8fa3-2c11c5f43a61" />

# 🔐 MFA (OTP) FLOW
## 📌 Generate OTP
**POST**
/mfa/generate
<img width="887" height="780" alt="OtpGenerate" src="https://github.com/user-attachments/assets/ace27faa-5030-4831-87f1-911047db2b38" />
<img width="1690" height="153" alt="otp code pg admin" src="https://github.com/user-attachments/assets/bd954e7f-085d-4bbc-b59c-972091b23023" />
## 📌 Verify OTP
**POST**
/mfa/verify
<img width="886" height="695" alt="OtpVerify2" src="https://github.com/user-attachments/assets/042a2cc5-0a6d-4b98-a583-511a0e330436" />
<img width="880" height="707" alt="OtpVerify" src="https://github.com/user-attachments/assets/9bf5a9f1-81df-4b62-8c8d-81ad0f0a992d" />

# 👤 User Endpoint
## Get Current User
**Get**
/users/me<img width="906" height="703" alt="UserEndpoint" src="https://github.com/user-attachments/assets/3303318f-b2f0-4360-a557-151c768c193b" />

## 🧪 Testing Flow (Postman)
Register user → /auth/register
Login → /auth/login → get JWT
Call /mfa/generate
Check console OTP
Call /mfa/verify
Access /users/me
## 👨‍💻 Author
Aynur D.

Backend Developer focused on:

Spring Boot
Security Systems
JWT Authentication
Enterprise Backend Design


