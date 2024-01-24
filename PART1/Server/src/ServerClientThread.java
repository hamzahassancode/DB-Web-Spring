import Repository.AdminRepository;
import Repository.InstructorRepository;
import Repository.StudentRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.*;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;
    Connection connection;
    ServerClientThread(Socket inSocket, int counter,Connection connection){
        serverClient = inSocket;
        clientNo=counter;
        this.connection=connection;
    }

    public void run(){

        try{
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());

            String clientMessage="", serverMessage="";


            outStream.writeUTF("Welcome to the Student Grading System  "+
                        "\nTO LOGIN :  "+
                        "\nSTUDENTS ENTER (1)  "+
                        "\nINSTRUCTOR ENTER (2)  "+
                        "\nADMIN ENTER (3)  "+
                "\nlogout enter (0)  ");
            String whoIsTheClient = inStream.readUTF();
                String username,password;
                switch (whoIsTheClient){
                    case "1":
                        System.out.print("case 1: ");

                        while(!clientMessage.equals("0")) {


                                                 System.out.println("get username ...");
                            username = inStream.readUTF();
                                                 System.out.println("get password ...");

                            password = inStream.readUTF();
                                                System.out.println("try to find student " + username);
                                                StudentRepository studentRepository=new StudentRepository(username,password);
                                    if (studentRepository.checkLogin(username,password)) {
                                    outStream.writeUTF("\n");

                                    clientMessage=inStream.readUTF();
                                    switch (clientMessage){
                                        case "1":
                                            serverMessage=studentRepository.display();
                                            outStream.writeUTF(serverMessage);
                                        case "0":
                                            clientMessage="0";
                                            break;
                                        default:
                                            serverMessage= studentRepository.display();
                                            outStream.writeUTF(serverMessage);
                                    }
                                }
                                else{
                                    outStream.writeUTF("\nInvalid username or password!"+"\ntry again..");
                                }
                        }
                            outStream.flush();


                        break;

                    case "2":
                        while(!clientMessage.equals("0")) {

                                System.out.println("get username ...");
                                username = inStream.readUTF();
                                System.out.println("get password ...");

                                password = inStream.readUTF();
                                System.out.println("try to find instructor " + username);
                                InstructorRepository instructorRepository=new InstructorRepository(username,password);

                                if (instructorRepository.checkLogin(username,password)) {

                                    System.out.println("login successful to " + username);

                                    while(!clientMessage.equals("0")){
                                    clientMessage=inStream.readUTF();
                                    switch (clientMessage){
                                        case "1":
                                            serverMessage=instructorRepository.display();
                                            outStream.writeUTF(serverMessage);
                                            break;
                                        case "2":
                                            //outStream.writeUTF("Enter course ID: ");
                                            int courseId = Integer.parseInt(inStream.readUTF());
                                            System.out.println("course id : "+courseId);

                                            // Check if the instructor teaches this course
                                            if (instructorRepository.isTeachingCourse(courseId)) {

                                                int studentId = Integer.parseInt(inStream.readUTF());
                                                System.out.println("STUDETN id : "+studentId);
                                                //Enter grade:
                                                double grade = Double.parseDouble(inStream.readUTF());
                                                System.out.println("GRADE : "+grade);

                                                instructorRepository.insertGrades(courseId, studentId, grade);
                                                System.out.println("Grade entered successfully.");
                                            } else {
                                                System.out.println("You are not teaching this course.");
                                            }

                                            outStream.writeUTF(serverMessage);
                                            break;
                                        default:
                                            instructorRepository.display();
                                            outStream.writeUTF(serverMessage);
                                            break;
                                    }
                                    }
                                }
                                else{
                                    System.out.println("\nInvalid username or password!"+"\ntry again..");
                                    outStream.writeUTF("\nInvalid username or password!"+"\ntry again..");
                                }
                            outStream.flush();

                        }
                        break;
                    case "3":
                        while(!clientMessage.equals("0")) {

                                System.out.println("get username ...");
                                username = inStream.readUTF();
                                System.out.println("get password ...");

                                password = inStream.readUTF();
                                System.out.println("try to find admin " + username);
                                AdminRepository adminRepository=new AdminRepository(username,password);
                                if (adminRepository.checkLogin(username,password)) {
                                    System.out.println("successful login " + username);



                                    while(!clientMessage.equals("0")){
                                        clientMessage=inStream.readUTF();
                                                switch (clientMessage) {
                                                    case "1":
                                                        System.out.println("IN CASE1");
                                                        // Code to handle adding a new student
                                                        //outStream.writeUTF("Enter student full name:");
                                                        String studentFullName = inStream.readUTF();
                                                        System.out.println("GET FULL NAME ");

                                                        // outStream.writeUTF("Enter student username:");
                                                        String studentUsername = inStream.readUTF();
                                                        System.out.println("GET STUDENT USERNAME ");
                                                        // outStream.writeUTF("Enter student password:");
                                                        String studentPassword = inStream.readUTF();
                                                        adminRepository.addStudent(studentFullName, studentUsername, studentPassword);
                                                        outStream.writeUTF("Student added successfully.");
                                                        break;

                                                    case "2":
                                                        //Enter student ID to delete:
                                                        int studentIdToDelete = Integer.parseInt(inStream.readUTF());
                                                        adminRepository.deleteStudent(studentIdToDelete);
                                                        outStream.writeUTF("Student deleted successfully.");
                                                        break;

                                                    case "3":
                                                        //Enter instructor full name:"
                                                        String instructorFullName = inStream.readUTF();
                                                        //Enter instructor username:
                                                        String instructorUsername = inStream.readUTF();
                                                        //Enter instructor password:"
                                                        String instructorPassword = inStream.readUTF();
                                                        adminRepository.addInstructor(instructorFullName, instructorUsername, instructorPassword);
                                                        outStream.writeUTF("Instructor added successfully.");
                                                        break;

                                                    case "4":
                                                       //Enter instructor ID to delete:
                                                        int instructorIdToDelete = Integer.parseInt(inStream.readUTF());
                                                        adminRepository.deleteInstructor(instructorIdToDelete);
                                                        outStream.writeUTF("Instructor deleted successfully.");
                                                        break;

                                                    case "5":
                                                       //Enter course name:"
                                                        String courseName = inStream.readUTF();
                                                        //Enter instructor ID for the course:
                                                        int instructorIdForCourse = Integer.parseInt(inStream.readUTF());
                                                        adminRepository.addCourse(courseName, instructorIdForCourse);
                                                        outStream.writeUTF("Course added successfully.");
                                                        break;

                                                    case "6":
                                                       // Enter course ID to delete:
                                                        int courseIdToDelete = Integer.parseInt(inStream.readUTF());
                                                        adminRepository.deleteCourse(courseIdToDelete);
                                                        outStream.writeUTF("Course deleted successfully.");
                                                        break;

                                                    case "7":
                                                       // oEnter student ID:
                                                        int studentId = Integer.parseInt(inStream.readUTF());
                                                        //Enter course ID:
                                                        int course_Id = Integer.parseInt(inStream.readUTF());

                                                        // Call the appropriate method to register the student for the course
                                                        adminRepository.registerStudentForCourse(studentId, course_Id);
                                                        outStream.writeUTF("Student registered for course.");
                                                        break;

                                                    case "8":
                                                        //Enter instructor ID:
                                                        int instructorId = Integer.parseInt(inStream.readUTF());
                                                        //Enter course ID:
                                                        int courseToAppointId = Integer.parseInt(inStream.readUTF());
                                                        // Call the appropriate method to appoint the instructor for the course
                                                        adminRepository.appointTeacherForCourse(instructorId, courseToAppointId);
                                                        outStream.writeUTF("Instructor appointed for course.");
                                                        break;

                                                    case "9":
                                                        //Enter instructor ID:
                                                        int instructor_Id = Integer.parseInt(inStream.readUTF());
                                                       //Enter course ID:
                                                        int courseIdToAppoint = Integer.parseInt(inStream.readUTF());

                                                        // Call the appropriate method to appoint the instructor for the course
                                                        adminRepository.appointTeacherForCourse(instructor_Id, courseIdToAppoint);

                                                        // Send success message to client
                                                        outStream.writeUTF("Instructor appointed for the course.");
                                                        break;

                                                    case "0":
                                                        // Logout
                                                        outStream.writeUTF("Logged out.");
                                                        break;

                                                    default:
                                                        outStream.writeUTF("Invalid choice.");
                                                        break;
                                                }

                                                // Flush the output stream
                                                outStream.flush();
                                    }
                                }
                                else{
                                    System.out.println("\nInvalid username or password!"+"\ntry again..");
                                    outStream.writeUTF("\nInvalid username or password!"+"\ntry again..");
                                }
                            outStream.flush();
                        }
                        break;
                    default:
                        break;
                }
            outStream.flush();
            inStream.close();
            outStream.close();
            serverClient.close();
        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

  
}