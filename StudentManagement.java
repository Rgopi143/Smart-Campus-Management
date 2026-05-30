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
