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
