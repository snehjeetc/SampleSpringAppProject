package com.example.demo.repo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.example.demo.config.BeanUtil;

@Component
public class DataBaseUtil {
	
	private DataSource lDataSource = BeanUtil.getBean(DataSource.class);
	public Connection getConnection() {
		Connection con = null;
		
		try {
			con = lDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection(Connection pCon) {
		if(pCon == null)
			return;
		try {
			pCon.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
