package com.example.demo90.repository;

import com.example.demo90.models.Student;

import java.util.List;

public interface StudentDao {
    Student add(Student student);

    List<Student> getAll();

    Student findById(int student_id);

    // true if item exists and is updated
    boolean update(Student student);

    // true if item exists and is deleted
    boolean deleteById(int student_id);
}
