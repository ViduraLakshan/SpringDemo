package com.example.demo.service;



import com.example.demo.model.Student;
import com.example.demo.exception.StudentNotFoundException;

import java.util.List;

public interface IStudentService {
    void addNewStudent(Student student);
    Student getStudentById(Long studentId);
    List<Student> getStudentList();
    void deleteStudent(Long studentId);
    void studentUpdate(Long studentId, Student student);
    Student findByName(String name);
}
