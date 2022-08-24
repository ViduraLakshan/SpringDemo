package com.example.demo.service;

import com.example.demo.exception.EmailTekanException;
import com.example.demo.model.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
        {
            throw new EmailTekanException("Email taken");
        }

        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent())
        {
            throw new StudentNotFoundException("Student not found");
        }
        return student.get();
    }

    @Override
    public List<Student> getStudentList()
    {
        return studentRepository.findAll();
    }
    @Override
    public void deleteStudent(Long studentId) {
        boolean exists=studentRepository.existsById(studentId);
        if (!exists)
        {
            throw new StudentNotFoundException("student with Id"+studentId+"dose not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Override
    public void studentUpdate(Long studentId, Student student) {
        Student studentFromDb = studentRepository.findById(studentId).get();
        if (Objects.nonNull(student.getName())&&
                !"".equalsIgnoreCase(student.getName()))
        {
            studentFromDb.setName(student.getName());
        }
        if (Objects.nonNull(student.getEmail())&&
                !"".equalsIgnoreCase(student.getEmail()))
        {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent())
            {
                throw new EmailTekanException("Email taken");
            }else {
                studentFromDb.setEmail(student.getEmail());
            }

        }
        if (Objects.nonNull(student.getDob()))
        {
            studentFromDb.setDob(student.getDob());
        }
        studentRepository.save(studentFromDb);
    }

    @Override
    public Student findByName(String name) {
        Optional<Student> student=studentRepository.findByName(name);
        if (!student.isPresent())
        {
            throw new StudentNotFoundException("Student not found");
        }
        return student.get();
    }

}
