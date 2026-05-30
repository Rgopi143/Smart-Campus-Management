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
