package com.example.school.service;

import java.util.*;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import com.example.school.model.StudentRowMapper;

import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class StudentH2Service implements StudentRepository{
    @Autowired 
    private JdbcTemplate db;

    
    @Override
    public ArrayList<Student> getStudents(){
        List<Student> studentList = db.query("SELECT * FROM STUDENT", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentList);

        return students;
    }

    @Override
    public Student addStudent(Student student){
        db.update("INSERT INTO STUDENT(studentName,gender,standard) values(?,?,?)", student.getStudentName(), student.getGender(), student.getStandard());

        Student addedStudent = db.queryForObject("SELECT * FROM STUDENT WHERE studentName =? and gender = ? and standard = ?", new StudentRowMapper(),
        student.getStudentName(), student.getGender(), student.getStandard());

        return addedStudent;
    }

    @Override
    public String addMultipleStudents(ArrayList<Student> givenMultipleStudents){
        int count =givenMultipleStudents.size();
        for(Student eachStudent: givenMultipleStudents){
            db.update("INSERT INTO STUDENT(studentName,gender,standard) values(?,?,?)", eachStudent.getStudentName(), eachStudent.getGender(),
            eachStudent.getStandard());
        }

        String totalStudents = "Successfully added " + count +" students ";

        return totalStudents;
    }

    @Override 
    public Student getStudentById(int studentId){
        try{
            Student requestedStudent = db.queryForObject("SELECT * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(), studentId);

            return requestedStudent;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudent(int studentId, Student student){
        try{
            Student existingStudent = db.queryForObject("SELECT * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(), studentId);
            if(existingStudent != null){
                if(student.getStudentName() != null){
                    db.update("UPDATE STUDENT SET studentName = ? WHERE studentId = ?", student.getStudentName(), studentId);
                }
                if(student.getGender() != null){
                    db.update("UPDATE STUDENT SET gender = ? WHERE studentId = ?", student.getGender(), studentId);
                }
                if(student.getStandard() != 0){
                    db.update("UPDATE STUDENT SET standard = ? WHERE studentId = ?", student.getStandard(), studentId);
                }
            }
            Student updatedStudent = db.queryForObject("SELECT * FROM STUDENT WHERE studentId = ?", new StudentRowMapper(), studentId);

            return updatedStudent;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    
    public void deleteStudent(int studentId){
        db.update("DELETE FROM STUDENT WHERE studentId = ?", studentId);
    }
}