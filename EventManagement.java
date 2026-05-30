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
