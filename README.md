# 📚 Library Management System (Spring Boot)

A RESTful Library Management System built using Spring Boot and MySQL.

## 🚀 Features
- Add books
- Add students
- Issue book to student
- Return book
- Track available books
- Input validation
- Pagination & sorting
- DTO + ModelMapper
- Global exception handling

## 🛠 Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## 📌 API Endpoints
- POST /api/library/books
- GET /api/library/books
- POST /api/library/issue
- PUT /api/library/return/{id}
- POST /api/students
- GET /api/students

## 🗄 Database
- books
- students
- issued_books

## ▶ How to Run
1. Clone the repo  
2. Create database: library_db  
3. Update application.properties  
4. Run the application  