package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface IStudentService {
	Integer addStudentData(String pStudent);
	List<Student> fetchAllStudentRecords();
	Student fetchStudent(Integer rollno);
}
