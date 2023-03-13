package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

@RestController
public class StudentController{
    
    @Autowired
    private StudentH2Service studentH2Service;

    @GetMapping("/students")

    public ArrayList<Student> getStudents(){
        return studentH2Service.getStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentH2Service.addStudent(student);
    }

    @PostMapping("/students/bulk")
    public String addMultipleStudents(@RequestBody ArrayList<Student> givenMultipleStudents){
        return studentH2Service.addMultipleStudents(givenMultipleStudents);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        return studentH2Service.getStudentById(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable int studentId, @RequestBody Student student){
        return studentH2Service.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable int studentId){
        studentH2Service.deleteStudent(studentId);
    }


}