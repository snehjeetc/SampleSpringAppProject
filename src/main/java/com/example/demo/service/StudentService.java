package com.example.demo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.SpringDemoApplication;
import com.example.demo.exception.StudentException;
import com.example.demo.exception.StudentException.ExceptionType;
import com.example.demo.ipparser.InputParser;
import com.example.demo.ipparser.InputParser.KEYS;
import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService implements IStudentService{
	
	private static final Logger logger = LogManager.getLogger(SpringDemoApplication.class);
	
	@Autowired
	private StudentRepo repo;
	
	@Override
	public Integer addStudentData(String pStudent) {
			Student stud = InputParser.getStudentObject(pStudent, KEYS.NAME, KEYS.AGE);
			Integer roll_no = repo.addStudent(stud);
			stud.setRollNo(roll_no);
			logger.info("Student added : " + stud.toString());
			return roll_no;
		
	}

	@Override
	public List<Student> fetchAllStudentRecords() {
		return repo.fetchAllRecords();
	}

	@Override
	public Student fetchStudent(Integer rollno) {
		Student student = repo.fetchStudent(rollno);
		if(student == null)
			throw new StudentException("Roll no : " + rollno + " not found", ExceptionType.RECORD_NOT_FOUND);
		return student;
	}
}
