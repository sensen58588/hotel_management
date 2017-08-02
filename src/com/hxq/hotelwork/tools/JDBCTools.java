package com.hxq.hotelwork.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCTools {
	public int update(String sql){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/tf25", "root", "mysql");
			Statement stmt = conn.createStatement();
			
			int rows = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
			return rows;
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		}
		
	}
	public ArrayList<String[]> query(String sql){
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/tf25", "root", "mysql");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()){
				String[] user = new String[rsmd.getColumnCount()];
				for(int i = 1;i<=rsmd.getColumnCount();i++){
					String info = rs.getString(rsmd.getColumnName(i));
					user[i-1] = info;
				}
				rsList.add(user);
			}
			
			stmt.close();
			conn.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		return rsList;
	}
	public ArrayList<String[]> queryForPage(String sql,int page){
		sql = sql+" limit "+((page-1)*10)+",10"; 
		return query(sql);
	}

}
