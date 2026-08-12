# Lab 7 - Spring Database

## 📌 Project Description

โปรเจกต์นี้เป็นการพัฒนา Web Application ด้วย Spring Boot สำหรับจัดการข้อมูลเกม (Game Management) โดยเชื่อมต่อกับ PostgreSQL Database และใช้ Thymeleaf สำหรับแสดงผลหน้าเว็บ

ภายในระบบมีการประยุกต์ใช้แนวคิด Software Design เช่น Layered Architecture, GRASP, SOLID และ Strategy Pattern

---

## 🛠️ Technologies

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- PostgreSQL
- Maven

---

## 📁 Project Structure

```text
src/main/java/com/example/
├── model/
│   └── Game.java
│
├── repository/
│   └── GameRepository.java
│
├── strategy/
│   ├── DiscountStrategy.java
│   ├── NoDiscountStrategy.java
│   ├── StudentDiscountStrategy.java
│   ├── SeasonalSaleStrategy.java
│   └── DiscountContext.java
│
├── service/
│   └── GameService.java
│
└── controller/
    └── GameController.java