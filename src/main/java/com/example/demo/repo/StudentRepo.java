package com.example.demo.repo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;

@Component
public class StudentRepo {
	@Autowired
	private DataBaseUtil dataBaseUtil;

	public Integer addStudent(Student pStudent) {
		Connection con = dataBaseUtil.getConnection();
		Integer roll_no = null;
		String insertQuery = "INSERT INTO student_record (name, age) VALUES( ?, ?) RETURNING roll_no";
		if(con != null) {
			try(PreparedStatement pStmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)){
				pStmt.setString(1, pStudent.getName());
				pStmt.setInt(2,  pStudent.getAge());
				pStmt.execute();
				ResultSet rs = pStmt.getGeneratedKeys();
				if(rs!=null && rs.next()) {
					roll_no = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dataBaseUtil.closeConnection(con);
		return roll_no;
	}
	
	public List<Student> fetchAllRecords(){
		Connection con = dataBaseUtil.getConnection();
		String selectQuery = "SELECT * FROM student_record";
		List<Student> studentList = new ArrayList<>();
		if(con!=null) {
			try(Statement stmt = con.createStatement()){
				ResultSet rs = stmt.executeQuery(selectQuery);
				while(rs!=null && rs.next()) {
					Integer rollNo = rs.getInt("roll_no");
					String name = rs.getString("name");
					Integer age = rs.getInt("age");
					studentList.add(new Student(rollNo, name, age));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return studentList;
	}
	
	public Student fetchStudent(Integer rollno) {
		Connection con = dataBaseUtil.getConnection();
		String selectQuery = String.format("SELECT * FROM student_record WHERE roll_no = %s", rollno);
		if(con!=null) {
			try(Statement stmt = con.createStatement()){
				ResultSet rs = stmt.executeQuery(selectQuery);
				if(rs!=null && rs.next()) {
					Integer rollNo = rs.getInt("roll_no");
					String name = rs.getString("name");
					Integer age = rs.getInt("age");
					return new Student(rollNo, name, age);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dataBaseUtil.closeConnection(con);
		return null;
	}
}
