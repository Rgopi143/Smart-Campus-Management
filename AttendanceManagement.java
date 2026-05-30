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
