Smart Campus Management System – Java + SQL Complete Mini Project
Technologies Used
•	Core Java
•	JDBC
•	MySQL
•	OOP Concepts
•	Exception Handling
•	Collections
Modules
1.	Student Management
2.	Faculty Management
3.	Attendance Management
4.	Complaint Management
5.	Event Management
6.	Login System
________________________________________
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
________________________________________
Step 3: JDBC Driver Setup
Download:
•	MySQL Connector JAR
Recommended Tools:
•	Eclipse IDE
•	IntelliJ IDEA
•	MySQL Workbench
________________________________________
Step 4: Project Structure
SmartCampusManagementSystem/
│
├── DBConnection.java
├── Login.java
├── MainMenu.java
├── StudentManagement.java
├── FacultyManagement.java
├── AttendanceManagement.java
├── ComplaintManagement.java
├── EventManagement.java
└── mysql-connector-j.jar
________________________________________
Step 5: DBConnection.java
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con = null;

    public static Connection getConnection() {

        try {

            if (con == null || con.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/smart_campus",
                        "root",
                        "root");

                System.out.println("Database Connected Successfully");
            }

        } catch (Exception e) {
            System.out.println("Database Connection Error : " + e.getMessage());
        }

        return con;
    }
}
________________________________________
Step 6: Login.java
import java.sql.*;
import java.util.Scanner;

public class Login {

    public static boolean loginSystem() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful");
                return true;
            } else {
                System.out.println("Invalid Credentials");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
________________________________________
Step 7: StudentManagement.java
import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    Scanner sc = new Scanner(System.in);

    public void addStudent() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Year: ");
            int year = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            String query = "INSERT INTO students VALUES(?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, course);
            pst.setInt(4, year);
            pst.setString(5, email);

            pst.executeUpdate();

            System.out.println("Student Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewStudents() {

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3) + " " +
                        rs.getInt(4) + " " +
                        rs.getString(5));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteStudent() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student ID to Delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE student_id=?";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);

            pst.executeUpdate();

            System.out.println("Student Deleted Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
________________________________________
Step 8: FacultyManagement.java
import java.sql.*;
import java.util.Scanner;

public class FacultyManagement {

    Scanner sc = new Scanner(System.in);

    public void addFaculty() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Faculty ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Faculty Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            String query = "INSERT INTO faculty VALUES(?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, department);
            pst.setString(4, email);

            pst.executeUpdate();

            System.out.println("Faculty Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
________________________________________
Step 9: AttendanceManagement.java
import java.sql.*;
import java.util.Scanner;

public class AttendanceManagement {

    Scanner sc = new Scanner(System.in);

    public void markAttendance() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Subject Name: ");
            String subject = sc.nextLine();

            System.out.print("Enter Attendance Status (Present/Absent): ");
            String status = sc.nextLine();

            String query = "INSERT INTO attendance(student_id,subject_name,attendance_date,status) VALUES(?,?,CURDATE(),?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, studentId);
            pst.setString(2, subject);
            pst.setString(3, status);

            pst.executeUpdate();

            System.out.println("Attendance Marked Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
________________________________________
Step 10: ComplaintManagement.java
import java.sql.*;
import java.util.Scanner;

public class ComplaintManagement {

    Scanner sc = new Scanner(System.in);

    public void addComplaint() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Complaint: ");
            String complaint = sc.nextLine();

            String query = "INSERT INTO complaints(student_name,complaint_text,status) VALUES(?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, complaint);
            pst.setString(3, "Pending");

            pst.executeUpdate();

            System.out.println("Complaint Submitted Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
________________________________________
Step 11: EventManagement.java
import java.sql.*;
import java.util.Scanner;

public class EventManagement {

    Scanner sc = new Scanner(System.in);

    public void addEvent() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Event ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Event Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Event Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            System.out.print("Enter Location: ");
            String location = sc.nextLine();

            String query = "INSERT INTO events VALUES(?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, date);
            pst.setString(4, location);

            pst.executeUpdate();

            System.out.println("Event Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
________________________________________
Step 12: MainMenu.java
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

        boolean login = Login.loginSystem();

        if (login == false) {
            System.out.println("Program Closed");
            return;
        }

        Scanner sc = new Scanner(System.in);

        StudentManagement student = new StudentManagement();
        FacultyManagement faculty = new FacultyManagement();
        AttendanceManagement attendance = new AttendanceManagement();
        ComplaintManagement complaint = new ComplaintManagement();
        EventManagement event = new EventManagement();

        int choice;

        while (true) {

            System.out.println("
===== SMART CAMPUS MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Add Faculty");
            System.out.println("5. Mark Attendance");
            System.out.println("6. Add Complaint");
            System.out.println("7. Add Event");
            System.out.println("8. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    student.addStudent();
                    break;

                case 2:
                    student.viewStudents();
                    break;

                case 3:
                    student.deleteStudent();
                    break;

                case 4:
                    faculty.addFaculty();
                    break;

                case 5:
                    attendance.markAttendance();
                    break;

                case 6:
                    complaint.addComplaint();
                    break;

                case 7:
                    event.addEvent();
                    break;

                case 8:
                    System.out.println("Thank You");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
________________________________________
Step 13: Insert Login Data
INSERT INTO users VALUES('admin','admin123','ADMIN');
________________________________________
Step 14: Compile and Run
Compile
Windows Command Prompt
javac -cp ".;mysql-connector-j.jar" *.java
Run
java -cp ".;mysql-connector-j.jar" MainMenu
Linux / Mac
javac -cp ".:mysql-connector-j.jar" *.java
java -cp ".:mysql-connector-j.jar" MainMenu
________________________________________
Sample Output
===== SMART CAMPUS MANAGEMENT SYSTEM =====
1. Add Student
2. View Students
3. Delete Student
4. Add Faculty
5. Mark Attendance
6. Add Complaint
7. Add Event
8. Exit

________________________________________
Spring Boot Module
A new Spring Boot starter app has been added under the spring-boot-app folder. It includes a Maven configuration, MySQL datasource settings, a Student entity, repository, and REST controller.

Run with:
mvn spring-boot:run

Database setup:
- Use the existing smart_campus.sql file to create the database and tables.
- Update the datasource credentials in spring-boot-app/src/main/resources/application.properties as needed.

