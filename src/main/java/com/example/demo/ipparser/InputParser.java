package com.example.demo.ipparser;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.demo.exception.StudentException;
import com.example.demo.exception.StudentException.ExceptionType;
import com.example.demo.model.Student;

public class InputParser {
	public enum KEYS {
		ROLL_NO("rollno"),
		NAME("name"),
		AGE("age");
		
		private String keyName;
		KEYS(String keyName) {
			this.keyName= keyName;
		}
		
		public String getKeyName() {
			return this.keyName;
		}
	}
	
	public static Student getStudentObject(String pStudent, KEYS ...keys) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		Student student = new Student();
		try {
			json = (JSONObject) parser.parse(pStudent);
			
			JSONObject studentJson = (JSONObject) parser.parse(JSONValue.toJSONString(json.get("student")));
			for(int i=0; i<keys.length; i++) {
				if(studentJson.get(keys[i].keyName)==null)
					throw new StudentException("Fields '"+ keys[i].keyName +"' missing", ExceptionType.INVALID_INPUT);
				
				if(keys[i].equals(KEYS.ROLL_NO)) {
					student.setRollNo(((Long)studentJson.get(keys[i].keyName)).intValue());
				}
				if(keys[i].equals(KEYS.AGE)) {
					student.setAge(((Long)studentJson.get(keys[i].keyName)).intValue());
				}
				if(keys[i].equals(KEYS.NAME)) {
					student.setName((String)studentJson.get(keys[i]));
				}
			}
			
		} catch (ParseException e) {
			//logger.error("Parsing exception: " + e);
			e.printStackTrace();
		}
		return student;
	}

}
