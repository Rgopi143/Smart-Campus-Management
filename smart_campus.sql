Step 1: Create Database
CREATE DATABASE smart_campus;
USE smart_campus;
________________________________________
Step 2: Create Tables
Students Table
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    course VARCHAR(50),
    year INT,
    email VARCHAR(100)
);
Faculty Table
CREATE TABLE faculty (
    faculty_id INT PRIMARY KEY,
    faculty_name VARCHAR(100),
    department VARCHAR(50),
    email VARCHAR(100)
);
Attendance Table
CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    subject_name VARCHAR(50),
    attendance_date DATE,
    status VARCHAR(10),
    FOREIGN KEY(student_id) REFERENCES students(student_id)
);
Complaints Table
CREATE TABLE complaints (
    complaint_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(100),
    complaint_text VARCHAR(500),
    status VARCHAR(30)
);
Events Table
CREATE TABLE events (
    event_id INT PRIMARY KEY,
    event_name VARCHAR(100),
    event_date DATE,
    location VARCHAR(100)
);
Users Table
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    role VARCHAR(20)
);
