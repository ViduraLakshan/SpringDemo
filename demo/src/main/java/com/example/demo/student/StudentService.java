package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
        {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public List<Student> getStudent()
    {
        return studentRepository.findAll();
    }

    public void deleteStudent(Long studentId) {
        boolean exists=studentRepository.existsById(studentId);
        if (!exists)
        {
            throw new IllegalStateException("student with Id"+studentId+"dose not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void studentUpdate(Long studentId, String name, String email) {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "\"student with Id\"+studentId+\"dose not exist\""));
        if (name!=null&&name.length()>0&&!Objects.equals(student.getName(),name))
        {
            student.setName(name);
        }
        if (email!=null&&email.length()>0&&!Objects.equals(student.getEmail(),email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent())
            {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }

    public void studentUpdate(Long studentId, Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.getById(studentId);
    }
}
