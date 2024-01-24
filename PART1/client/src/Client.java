
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            Socket socket=new Socket("127.0.0.1",8888);
            DataInputStream inStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // Read and display the welcome message from the server


            String serverMessage = inStream.readUTF();
            System.out.println(serverMessage);

            System.out.print("enter number : ");
            String userChoice = scanner.nextLine();
            outStream.writeUTF(userChoice);

            while (!userChoice.equals("0")) {
                switch (userChoice) {
                    case "1" -> {
                        // Handle student login
                        System.out.print("Enter your username: ");
                        String studentUsername = scanner.nextLine();
                        outStream.writeUTF(studentUsername);
                        System.out.print("Enter your password: ");
                        String studentPassword = scanner.nextLine();
                        outStream.writeUTF(studentPassword);

                        // Receive and display the server's response
                        String loginResponse = inStream.readUTF();
                        System.out.println(loginResponse);


                        // Student logged in successfully, now handle student actions
                        while (true) {
                            System.out.println("1. View Grades");
                            System.out.println("0. Logout");
                            System.out.print("Enter your choice: ");
                            String studentAction = scanner.nextLine();
                            outStream.writeUTF(studentAction);

                            if (studentAction.equals("1")) {
                                // Request to view grades
                                // You can read and display the grades received from the server
                                String grades = inStream.readUTF();
                                System.out.println(grades);
                            } else if (studentAction.equals("0")) {
                                break; // Exit the student action loop
                            }
                        }
                        break;
                    }
                    case "2" -> {
                        // Handle instructor login
                        while (true){
                            System.out.print("welcome to instructor page  ");
                            System.out.print("\nEnter your username: ");
                            String instructorUsername = scanner.nextLine();
                            outStream.writeUTF(instructorUsername);
                            System.out.print("Enter your password: ");
                            String instructorPassword = scanner.nextLine();
                            outStream.writeUTF(instructorPassword);




                                // Instructor logged in successfully, now handle instructor actions
                                label:
                                while (true) {
                                    System.out.println("1. View Grades");
                                    System.out.println("2. Enter Grades");
                                    System.out.println("0. Logout");
                                    System.out.print("Enter your choice: ");
                                    String instructorAction = scanner.nextLine();
                                    outStream.writeUTF(instructorAction);

                                    switch (instructorAction) {
                                        case "1":
                                            // Request to view grades
                                            // You can read and display the grades received from the server
                                            String grades = inStream.readUTF();
                                            System.out.println("Your students' grades:");
                                            System.out.println(grades);
                                            break;
                                        case "2":
                                            // Request to enter grades
                                            System.out.print("Enter course ID: ");
                                            int courseId = Integer.parseInt(scanner.nextLine());
                                            outStream.writeUTF(String.valueOf(courseId));

                                            System.out.print("Enter student ID: ");
                                            int studentId = Integer.parseInt(scanner.nextLine());
                                            outStream.writeUTF(String.valueOf(studentId));

                                            System.out.print("Enter grade: ");
                                            double grade = Double.parseDouble(scanner.nextLine());
                                            outStream.writeUTF(String.valueOf(grade));

                                            // Send grade information to the server
//                                        outStream.writeInt(courseId);
//                                        outStream.writeInt(studentId);
//                                        outStream.writeDouble(grade);
                                            outStream.flush();

                                            // Receive and display the server's response
                                            String gradeEntryResponse = inStream.readUTF();
                                            System.out.println(gradeEntryResponse);
                                            break;
                                        case "0":
                                            break ; // Exit the instructor action loop
                                    }
                                }

                        }



                    }
                    case "3" -> {
                        // Handle admin login
                        System.out.print("Enter your username: ");
                        String adminUsername = scanner.nextLine();
                        outStream.writeUTF(adminUsername);
                        System.out.print("Enter your password: ");
                        String adminPassword = scanner.nextLine();
                        outStream.writeUTF(adminPassword);


                            // Admin logged in successfully, now handle admin actions
                            label1:
                            while (true) {
                                System.out.println("1. Add Student");
                                System.out.println("2. Delete Student");
                                System.out.println("3. Add Instructor");
                                System.out.println("4. Delete Instructor");
                                System.out.println("5. Add Course");
                                System.out.println("6. Delete Course");
                                System.out.println("7. Register Student for Course");
                                System.out.println("8. Appoint Instructor for Course");
                                System.out.println("9. View Grades");
                                System.out.println("0. Logout");
                                System.out.print("Enter your choice: ");
                                String adminAction = scanner.nextLine();
                                outStream.writeUTF(adminAction);

                                switch (adminAction) {
                                    case "1":
                                        // Request to add a student
                                        System.out.print("Enter student's full name: ");
                                        String studentFullName = scanner.nextLine();
                                        outStream.writeUTF(studentFullName);

                                        System.out.print("Enter student's username: ");
                                        String studentUsername_s = scanner.nextLine();
                                        outStream.writeUTF(studentUsername_s);

                                        System.out.print("Enter student's password: ");
                                        String studentPassword_s = scanner.nextLine();
                                        outStream.writeUTF(studentPassword_s);

                                        // Receive and display the server's response
                                        String addStudentResponse = inStream.readUTF();
                                        System.out.println(addStudentResponse);
                                        outStream.flush();
                                        break;
                                    case "2":
                                        // Request to delete a student
                                        System.out.print("Enter student ID to delete: ");
                                        String studentIdToDelete = scanner.nextLine();
                                        System.out.println(studentIdToDelete);

                                        // Send student ID to the server
                                        outStream.writeUTF(studentIdToDelete);


                                        // Receive and display the server's response
                                        String deleteStudentResponse = inStream.readUTF();
                                        System.out.println(deleteStudentResponse);
                                        outStream.flush();
                                        break;
                                    case "3":
                                        // Request to add an instructor
                                        System.out.print("Enter instructor's full name: ");
                                        String instructorFullName = scanner.nextLine();
                                        System.out.print("Enter instructor's username: ");
                                        String instructor_Username = scanner.nextLine();
                                        System.out.print("Enter instructor's password: ");
                                        String instructor_Password = scanner.nextLine();

                                        // Send instructor information to the server
                                        outStream.writeUTF(instructorFullName);
                                        outStream.writeUTF(instructor_Username);
                                        outStream.writeUTF(instructor_Password);
                                        outStream.flush();

                                        // Receive and display the server's response
                                        String addInstructorResponse = inStream.readUTF();
                                        System.out.println(addInstructorResponse);
                                        break;
                                    case "4":
                                        // Request to delete an instructor
                                        System.out.print("Enter instructor ID to delete: ");
                                        String instructorIdToDelete = scanner.nextLine();

                                        // Send instructor ID to the server
                                        outStream.writeUTF(instructorIdToDelete);
                                        outStream.flush();

                                        // Receive and display the server's response
                                        String deleteInstructorResponse = inStream.readUTF();
                                        System.out.println(deleteInstructorResponse);
                                        break;
                                    case "5":
                                        // Request to add a course
                                        System.out.print("Enter course name: ");
                                        String courseName = scanner.nextLine();

                                        // Send course name to the server
                                        outStream.writeUTF(courseName);

                                        // Receive and display the server's response
                                        String addCourseResponse = inStream.readUTF();
                                        System.out.println(addCourseResponse);
                                        outStream.flush();
                                        break;
                                    case "6":
                                        // Request to delete a course
                                        System.out.print("Enter course ID to delete: ");
                                        int courseIdToDelete = Integer.parseInt(scanner.nextLine());

                                        // Send course ID to the server
                                        outStream.writeInt(courseIdToDelete);
                                        outStream.flush();

                                        // Receive and display the server's response
                                        String deleteCourseResponse = inStream.readUTF();
                                        System.out.println(deleteCourseResponse);
                                        break;
                                    case "7":
                                        // Request to register a student for a course
                                        System.out.print("Enter student ID: ");
                                        String studentId = scanner.nextLine();
                                        System.out.print("Enter course ID: ");
                                        String courseId = scanner.nextLine();

                                        // Send student and course IDs to the server
                                        outStream.writeUTF(studentId);
                                        outStream.writeUTF(courseId);
                                        outStream.flush();

                                        // Receive and display the server's response
                                        String registerStudentResponse = inStream.readUTF();
                                        System.out.println(registerStudentResponse);
                                        break;
                                    case "8": {
                                        // Request to appoint an instructor for a course
                                        System.out.print("Enter instructor ID: ");
                                        String instructorId = scanner.nextLine();
                                        System.out.print("Enter course ID: ");
                                        String courseIdToAppoint = scanner.nextLine();

                                        // Send instructor and course IDs to the server
                                        outStream.writeUTF(instructorId);
                                        outStream.writeUTF(courseIdToAppoint);
                                        outStream.flush();

                                        // Receive and display the server's response
                                        String appointInstructorResponse = inStream.readUTF();
                                        System.out.println(appointInstructorResponse);
                                        break;
                                    }
                                    case "9": {
                                        // Request to view grades
                                        System.out.print("Enter instructor ID: ");
                                        String instructorId = scanner.nextLine();
                                        System.out.print("Enter course ID: ");
                                        String courseIdToView = scanner.nextLine();

                                        // Send instructor and course IDs to the server
                                        outStream.writeUTF(instructorId);
                                        outStream.writeUTF(courseIdToView);
                                        outStream.flush();

                                        // Receive and display the server's response
                                        String viewGradesResponse = inStream.readUTF();
                                        System.out.println(viewGradesResponse);
                                        break;
                                    }
                                    case "0":
                                        // Request to exit the admin action loop
                                        break label1;
                                }

                            }

                    }
                    default -> System.out.println("Invalid choice. Please enter a valid option.");
                }

                // Read and display the next message from the server
                serverMessage = inStream.readUTF();
                System.out.println(serverMessage);

                // Get user choice for the next iteration
                userChoice = scanner.nextLine();
                outStream.writeUTF(userChoice);
            }

            System.out.println("Logged out. Goodbye!");

            inStream.close();
            outStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
