package com.example.school.repository;

import com.example.school.model.Student;
import java.util.*;

public interface StudentRepository{

    ArrayList<Student> getStudents(); //GET ALL

    Student addStudent(Student student); //POST A STUDENT

    String addMultipleStudents(ArrayList<Student> givenMultipleStudents); //POST MULTIPLE STUDENTS

    Student getStudentById(int studentId); //GET A STUDENT

    Student updateStudent(int studentId, Student student); //UPDATE A STUDENT

    void deleteStudent(int studentId); //DELETE A STUDENT

}