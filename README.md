# 🏢 EmployeeApp — Backend (Spring Boot REST API)

A secure, production-structured REST API for managing employee records. Built with **Java 21**, **Spring Boot 4.0**, **Spring Security**, **JWT Authentication**, and **MySQL**.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.8 |
| Security | Spring Security + JWT (jjwt 0.12.6) |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Validation | Jakarta Bean Validation |
| Boilerplate Reduction | Lombok |
| Build Tool | Maven |

---

## ✨ Features

- 🔐 **JWT-based Authentication** — Stateless login with signed JWT tokens (24-hour expiry)
- 👤 **Role-based Access Control** — All endpoints protected under `ADMIN` role
- 👥 **Employee CRUD** — Create, Read, Update, and Delete employees
- 🗂️ **Department Filtering** — Filter employees by department
- 🔍 **Search by Name** — Case-insensitive partial name search
- 🗑️ **Soft Delete** — Deleted employees are flagged (`isDeleted=true`), not permanently removed from DB
- ✅ **Input Validation** — Request DTOs validated with `@NotBlank`, `@Email`, `@Min`
- ⚠️ **Global Exception Handling** — Centralized error responses via `@ControllerAdvice`
- 🌐 **CORS Configured** — Allows cross-origin requests from the frontend origin
- 🌱 **Auto Admin Seeding** — A default admin account is created on first startup via environment variable

---

## 📁 Project Structure

```
src/main/java/com/employeeapp/
│
├── controller/
│   ├── AuthController.java              # Login & Admin registration
│   ├── EmployeeController.java          # Employee CRUD + search
│   └── DepartmentController.java        # List all departments
│
├── service/
│   ├── AuthService.java                 # Auth logic (register, login, JWT)
│   └── EmployeeService.java             # Employee business logic + DTO mapping
│
├── repository/
│   ├── EmployeeRepository.java          # JPA queries for employees
│   └── UserRepository.java              # JPA queries for users
│
├── entity/
│   ├── Employee.java                    # Employee DB entity
│   └── User.java                        # User (admin) DB entity
│
├── dto/
│   ├── request/
│   │   ├── AuthRequestDTO.java
│   │   └── EmployeeRequestDTO.java
│   └── response/
│       ├── AuthResponseDTO.java
│       └── EmployeeResponseDTO.java
│
├── security/
│   ├── SecurityConfig.java              # Spring Security filter chain + CORS
│   ├── JwtUtil.java                     # JWT generation & validation
│   ├── JwtFilter.java                   # JWT request filter (OncePerRequestFilter)
│   └── CustomUserDetailsService.java    # UserDetailsService implementation
│
├── enums/
│   ├── Department.java                  # ENGINEERING, FINANCE, MARKETING, HUMAN_RESOURCES
│   └── Role.java                        # ADMIN
│
├── exceptions/
│   ├── GlobalExceptionHandler.java
│   ├── EmployeeNotFoundException.java
│   ├── UserNotFoundException.java
│   └── DuplicateUserException.java
│
└── DataInitializer.java                 # Seeds default admin on startup
```

---

## 🔌 API Endpoints

> **Base URL:** `http://localhost:8080`
>
> All endpoints except `/auth/login` require a valid JWT in the `Authorization: Bearer <token>` header.

### 🔐 Auth

| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/auth/login` | Public | Login and receive a JWT token |
| `POST` | `/auth/register-admin` | ADMIN | Register a new admin user |

**Login — Request Body:**
```json
{
  "username": "your_admin_username",
  "password": "your_admin_password"
}
```

**Login — Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "your_admin_username",
  "role": "ADMIN"
}
```

---

### 👥 Employee

| Method | Endpoint | Access | Description |
|---|---|---|---|
| `POST` | `/employee` | ADMIN | Add a single employee |
| `POST` | `/employee/saveAll` | ADMIN | Add multiple employees at once |
| `GET` | `/employee` | ADMIN | Get all active employees |
| `GET` | `/employee/{id}` | ADMIN | Get employee by ID |
| `PUT` | `/employee/{id}` | ADMIN | Update employee by ID |
| `DELETE` | `/employee/{id}` | ADMIN | Soft delete employee by ID |
| `GET` | `/employee/dept/{dept}` | ADMIN | Filter employees by department |
| `GET` | `/employee/search?name=` | ADMIN | Search employees by name (case-insensitive) |

**Employee — Request Body:**
```json
{
  "name": "Vijaykumar",
  "email": "vijay@example.com",
  "department": "ENGINEERING",
  "salary": 50000
}
```

**Available department values:** `ENGINEERING` · `FINANCE` · `MARKETING` · `HUMAN_RESOURCES`

---

### 🗂️ Department

| Method | Endpoint | Access | Description |
|---|---|---|---|
| `GET` | `/department` | ADMIN | Get list of all departments |

**Response:**
```json
["ENGINEERING", "FINANCE", "MARKETING", "HUMAN_RESOURCES"]
```

---

## ⚙️ Setup & Running Locally

### Prerequisites

- Java 21+
- Maven 3.8+
- MySQL 8.0+

### 1. Clone the Repository

```bash
git clone https://github.com/vijaykumar-dev28/EmployeeApp.git
cd EmployeeApp
```

### 2. Create the Database

```sql
CREATE DATABASE employee_db;
```

> The app auto-creates all tables via `spring.jpa.hibernate.ddl-auto=update` — no SQL scripts needed.

### 3. Configure Environment Variables

This project uses environment variables to keep all sensitive values out of the codebase. Create a `.env` file or export them in your terminal before running.

Refer to `.env.example` for the full list:

```env
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_jwt_secret_key_minimum_32_characters_long
ADMIN_DEFAULT_PASSWORD=choose_a_strong_admin_password
```

> ⚠️ Never commit your actual `.env` file. It is already listed in `.gitignore`.

**Setting variables — Windows (Command Prompt):**
```cmd
set DB_USERNAME=root
set DB_PASSWORD=yourpassword
set JWT_SECRET=mysupersecretkey1234567890123456
set ADMIN_DEFAULT_PASSWORD=yourAdminPassword
```

**Setting variables — macOS / Linux:**
```bash
export DB_USERNAME=root
export DB_PASSWORD=yourpassword
export JWT_SECRET=mysupersecretkey1234567890123456
export ADMIN_DEFAULT_PASSWORD=yourAdminPassword
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

The server starts at: **`http://localhost:8080`**

### 5. Default Admin Account

On first startup, a default admin is automatically created using the `ADMIN_DEFAULT_PASSWORD` environment variable you set above. Use those credentials to log in via `/auth/login`.

---

## 🧪 Testing with Postman

1. **Login** — `POST /auth/login` with your admin credentials
2. **Copy the token** from the response body
3. For all protected requests — go to the **Authorization** tab → select **Bearer Token** → paste the token

---

## 🔒 Security Design

| Concern | Approach |
|---|---|
| Password storage | BCrypt hashing |
| Authentication | Stateless JWT (no sessions) |
| Token expiry | 24 hours (86400000 ms) |
| Secret management | Environment variables only — never hardcoded |
| CORS | Restricted to the configured frontend origin (`http://localhost:5173`) |
| Protected routes | All routes require ADMIN role except `/auth/login` |

---

## 🔗 Related Repository

**Frontend (React + Vite):** [EmployeeApp-FrontEnd](https://github.com/vijaykumar-dev28/EmployeeApp-FrontEnd.git)

---

## 👨‍💻 Author

**Vijaykumar** — Java Full Stack Developer
[GitHub](https://github.com/vijaykumar-dev28)
[LinkedIN](www.linkedin.com/in/vijaykumar-dev28)
