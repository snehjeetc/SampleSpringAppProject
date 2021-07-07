package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SpringDemoApplication;
import com.example.demo.service.IStudentService;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/studentRecords")
public class StudentController {
	private static final Logger logger = LogManager.getLogger(SpringDemoApplication.class);
	@Autowired
	private IStudentService lStudentService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object addStudent(@RequestBody(required = true) String pStudent) {
		
	
		Integer rollNo = lStudentService.addStudentData(pStudent);
		JSONObject response = new JSONObject();
		if(rollNo == null) {
			response.put("message ", "failed to add student");
			response.put("status", 500);
			return response;
		}
		response.put("roll_no", rollNo);
		response.put("message", "added successfully");
		response.put("status", 202);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getAllStudents() {
		JSONObject response = new JSONObject();
		response.put("list", lStudentService.fetchAllStudentRecords());
		response.put("message", "data fetched successfully");
		response.put("status", 202);
		return response;
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getStudent(@RequestParam (required = true) Integer rollno) {
		JSONObject response = new JSONObject();
		response.put("student", lStudentService.fetchStudent(rollno));
		response.put("message", "data fetched successfully");
		response.put("status", 202);
		return response;
	}
	
	@RequestMapping(value = "/testLogger", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object testLogger(@RequestParam (required = true) Integer rollno) {
		JSONObject response = new JSONObject();
		response.put("student", rollno);
		response.put("message", "data fetched successfully");
		response.put("status", 202);
		logger.warn("testing  ");
		return response;
	}
}
