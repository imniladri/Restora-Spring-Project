# Restora: Restaurant Management System (Spring Boot) 🍽️

Restora is a full-stack **Restaurant Management Web Application** built using **Java and Spring Boot**.  
The project follows **MVC architecture** and provides role-based access for **Users** and **Admins**.

🔗 **GitHub Repository:**  https://github.com/imniladri/Restora-Spring-Project

🔗 **Live Preview:**  https://restora-app.onrender.com/

---

## 👨‍💻 Developer

**Niladri Mondal** <br>
_Java & Spring Boot Developer_

🔗 GitHub: https://github.com/imniladri

---

## 📌 Project Overview

Restora is designed to manage restaurant operations through a simple and user-friendly web interface.  
It demonstrates real-world usage of **Spring Boot**, **Spring MVC**, **Spring Data JPA**, and relational databases.

The application currently supports two main roles:
- **User**
- **Admin**

---

## 🚀 Features

### 👤 User
- User registration and login
- View restaurant information
- Browse available services
- Interactive UI using JSP and HTML/CSS/JavaScript

### 🔐 Admin
- Admin authentication
- Manage restaurant-related data
- Role-based access control
- Secure backend operations

---

## 🛠️ Tech Stack

| Backend            | Frontend      | DevOps & Database        |
|--------------------|---------------|--------------------------|
| Java               | HTML5         | Docker                   |
| Spring Boot        | CSS3          | Render                   |
| Spring MVC         | JavaScript    | Oracle SQL               |
| Spring Data JPA    | JSP           | PostgreSQL               |
| Hibernate          | —             | —                        |
| Servlet / JSP      | —             | —                        |

---

## 🏗️ Architecture

- MVC Architecture
  - Controller Layer
  - Service Layer
  - Repository Layer
- Database-driven persistence using JPA
- Modular and scalable structure

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+ (or compatible version)
- Maven
- Oracle SQL or PostgreSQL
- Docker (optional)

### Steps

```bash
# Clone the repository
git clone https://github.com/imniladri/Restora-Spring-Project.git

# Navigate to the project directory
cd Restora-Spring-Project

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Application will be available at:
http://localhost:8080
```

---

## 🐳 Docker Support

Docker is used to build the application image for deployment on **Render**.

```bash
# Build Docker image
docker build -t restora-app .

# Run Docker container
docker run -p 8080:8080 restora-app
```
---

## 📂 Project Structure

```
Restora-Spring-Project
│── src/main/java
│   ├── controller
│   ├── service
│   ├── repository
│   └── model
│
│── src/main/resources
│   ├── application.properties
│   └── templates
│
│── src/main/webapp
│   └── JSP files
│
│── Dockerfile
│── pom.xml
│── README.md
```
---

## 🗄️ Database Schema

The application uses a **relational database design** implemented with **Oracle SQL / PostgreSQL** and managed using **Spring Data JPA & Hibernate**.

<img width="800" alt="schema-restora" src="https://github.com/imniladri/Restora-Spring-Project/blob/main/restora-schema.svg" />

> ⚠️ **Note:**  
> The schema may evolve as new features such as online ordering, payments, and security enhancements are added.

---

## 📈 Future Enhancements

- Spring Security integration
- JWT-based authentication
- Online ordering and payments
- Swagger API documentation
- Improved UI with modern frontend frameworks

---

## 📜 License

This project is licensed under the **MIT License**. Feel free to use, modify, and distribute it for learning and development purposes.

---

### ⭐ If you find this project helpful, consider giving it a star on GitHub!

