package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public List<Student> getStudentList()
    {
        return studentService.getStudentList();
    }
    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentById(studentId);
    }
    @GetMapping("name/{name}")
    public Student findByName(@PathVariable("name") String name)
    {
        return studentService.findByName(name);
    }
    @PostMapping("")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable ("studentId") Long studentId)
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public  void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody Student student) {
        studentService.studentUpdate(studentId,student);
    }
}
