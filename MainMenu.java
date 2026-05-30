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

            System.out.println("\n===== SMART CAMPUS MANAGEMENT SYSTEM =====");
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
