package com.example.demo90.service;

import com.example.demo90.repository.StudentDatabaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {

   // private final StudentRepository studentRepository;
private final StudentDatabaseDao studentDatabaseDao;

    @Autowired
    public StudentService( StudentDatabaseDao studentDatabaseDao) {
        this.studentDatabaseDao = studentDatabaseDao;
    }

//    public boolean checkLogin(String username, String password) {
//        Student student = studentRepository.findByUsernameAndPassword(username, password);
//        return student != null;
//    }
//    public Integer findStudentIdByUsername(String username) {
//        Optional<Student> studentOptional = Optional.ofNullable(studentRepository.findByUsername(username));
//        return studentOptional.map(student -> Math.toIntExact(student.getStudent_id())).orElse(null);
//    }
//
//
//    public String welcomeMessage(String username) {
//        Student student = studentRepository.findByUsername(username);
//        if (student != null) {
//            return "Welcome " + student.getFullName();
//        } else {
//            return "Invalid username or password!";
//        }
//    }

    public String welcomeMessage(String username) {
        String fullName = studentDatabaseDao.getStudentFullName(username);
        if (fullName != null) {
            return "Welcome " + fullName;
        } else {
            return "Invalid username or password!";
        }
    }

    public boolean authenticate(String username, String password) {
        return studentDatabaseDao.checkLogin(username, password);
    }

        public String display(String username) {
            List<Map<String, Object>> grades = studentDatabaseDao.getStudentGrades(username);

            if (!grades.isEmpty()) {
                StringBuilder message = new StringBuilder();
                message.append("Welcome ").append(username).append("\n");
                message.append("Your grades:\n");

                for (Map<String, Object> row : grades) {
                    String courseName = (String) row.get("course_name");
                    double grade = (double) row.get("grade");
                    message.append(courseName).append("\t").append("Grade: ").append(grade).append("\n");
                }

                return message.toString();
            } else {
                return "No grades found for the user.";
            }
        }

    public String getCourseInformation(int course_id) {
        Map<String, Object> courseInfo = studentDatabaseDao.getCourseInformation(course_id);

        String courseName = (String) courseInfo.get("courseName");
        int studentCount = (int) courseInfo.get("studentCount");
        double maxGrade = (double) courseInfo.get("maxGrade");
        double minGrade = (double) courseInfo.get("minGrade");
        double avgGrade = (double) courseInfo.get("avgGrade");

        StringBuilder message = new StringBuilder();
        message.append("Course: ").append(courseName).append("\n");
        message.append("Number of students who took the exam: ").append(studentCount).append("\n");
        message.append("Max grade: ").append(maxGrade).append("\n");
        message.append("Min grade: ").append(minGrade).append("\n");
        message.append("Mean of grades: ").append(avgGrade).append("\n");

        return message.toString();
    }


}
