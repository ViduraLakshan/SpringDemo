package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired


    @GetMapping("/getAll")
    public List<Student> getStudent()
    {
        return studentService.getStudent();
    }
    @PostMapping("/Add")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "/delete/{studentId}")
    public void deleteStudent(@PathVariable ("studentId") Long studentId)
    {
        studentService.deleteStudent(studentId);
    }
    @PutMapping(path = "/update/{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String email) {
        studentService.studentUpdate(studentId,name,email);
    }
    @PutMapping(path = "/updateStudent/{studentId}")
    public  void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody Student student) {
        Student studentFromDb=studentService.getStudentById(studentId);
        studentFromDb.setName(student.getName());
        studentFromDb.setEmail(student.getEmail());
        studentFromDb.setDob(student.getDob());
        studentService.studentUpdate(studentId,studentFromDb);
    }
}
