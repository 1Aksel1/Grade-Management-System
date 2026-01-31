# 📚️ Grade Management System

A distributed grade management system built using a microservice architecture, enabling administrators, professors and students to track and manage the academic workflow - from exam periods and registrations to final grades. 

---

##  Features 

- **Multiple Client Applications**
    - Student Web Client
    - Professor Web Client
    - Admin Desktop Client


- **Client-Specific Backend Support (BFF Pattern)**
    - Each client communicates through its own Backend-for-Frontend service
    - API responses optimized to the needs of each client application


- **Key Role-Based Workflows**
    - Administrators manage exam periods, system configuration and notifications
    - Professors enter and manage exam results 
    - Students register for exams and track grades


- **Asynchronous Notifications** - Email notifications are triggered on key system events (e.g. exam period activation, grade entry)


- **Resilience & Fault Tolerance** - Retry mechanisms for inter-service communication


## How to run 

All backend microservices and web client applications are started using Docker Compose.

**Terminal:** 

```bash
docker-compose up 
```

The Admin Client is a JavaFX desktop application and is not included in Docker Compose.
It is designed to be run locally from an IDE (e.g. IntelliJ IDEA).

For demo purposes, exam periods (JANUARY and FEBRUARY) are pre-activated, allowing full system testing without the admin client.

Note: Email notifications are configured for a demo/local SMTP setup and will not actually be sent.

### Access the application


**Student Web App:** http://localhost:8085

**Professor Web App:** http://localhost:80

### Demo users

**Admin**

- Email: admin@demo.com
- Password: admin123

**Student**

- Email: student@demo.com
- Password: student123

**Professor**

- Email: professor@demo.com
- Password: professor123


## System Architecture

The system consists of eight microservices and three client applications.

- Microservices: Admin BFF, Professor BFF, Student BFF, User Service, Grade Management Service, Notification Service, Eureka and ActiveMQ
- Client applications: Admin Desktop Application, Professor Web Application and Student Web Application

The architecture diagram illustrates the main components and their communication flow.

Clients communicate only with their dedicated BFF services. Backend services contain business logic and are not aware of specific client needs. Service discovery and asynchronous messaging are handled via Eureka and ActiveMQ.


![System-Architecture](./screenshots/system-architecture-diagram.jpeg)

## Technical Overview

**Backend**

The backend is implemented as a set of **Spring Boot** microservices, each responsible for a specific business domain.

Core backend services include:

- User Service — authentication, authorization and **JWT** token generation

- Grade Management Service — exam periods, exam registration and grade calculation

- Notification Service — asynchronous notification handling

Each core backend microservice has its own **MySQL** database, ensuring loose coupling.

BFF backend services include:

- Admin BFF
- Professor BFF
- Student BFF


Each BFF service acts as a client-specific gateway, routing and adapting data without maintaining its own database.


**Clients**

- Admin Client — **JavaFX** desktop application
- Professor Client — **Angular** web application
- Student Client — Server-side rendered web application using **Thymeleaf**


**Security, Messaging & Communication**

- **JWT**-based authentication and role-based authorization (Admin, Professor, Student)

- **DTO**-based communication to reduce coupling between services

- **Netflix Eureka** for service registration and lookup

- **ActiveMQ** for asynchronous inter-service messaging

- **Resilience4j** retry mechanisms for improved fault tolerance

- **Spring Email** integration for notification delivery (demo setup)

