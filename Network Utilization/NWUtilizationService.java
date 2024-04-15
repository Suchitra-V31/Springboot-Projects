package com.example.NetworkUtilization;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Service;


@Service
public class NWUtilizationService {
	PreparedStatement ps;
	Connection conn;
	String sql;
	public String getCity() {
		PreparedStatement ps;
		ResultSet result;
		JSONArray cityList = new JSONArray(); // to store city list
		try {
			// Connect to mysql database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkUtilization","root","Suji@1234567");
			sql = "Select cityCode,cityName from city";
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cityCode", result.getString("cityCode").toString().trim());
				obj.put("cityName", result.getString("cityName").toString().trim());
				cityList.put(obj);
			}
			close(conn,ps,result);
		}
		catch(Exception e) {
			System.out.println("Error Occured!!! "+e); 
		}
		return cityList.toString();
		
	}
	public String getType() {
		ResultSet result;
		JSONArray typeList = new JSONArray();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkUtilization","root","Suji@1234567");
			sql = "select typeCode , typeName from type";
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()) {
				JSONObject obj = new JSONObject();
				obj.put("typeCode",result.getString("typeCode").toString().trim());
				obj.put("typeName",result.getString("typeName").toString().trim());
				typeList.put(obj);
			}
			close(conn,ps,result);
		}
		catch(Exception e) {
			System.out.println("Error Occured!!! " + e);
		}
		return typeList.toString();
		
	}
	public String getValue(int cityCode,int typeCode) {
		ResultSet result ;	
		int c = cityCode;
		int t = typeCode;
		JSONArray valueList = new JSONArray();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkUtilization","root","Suji@1234567");
			if(t==1) {
				sql = "select value from dataValue where cityCode=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, c);
			}
			else {
				sql = "select value from dataValue where typeCode=? and cityCode=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, t);
				ps.setInt(2,c);
			}


			result = ps.executeQuery();
			while(result.next()) {
				JSONObject obj = new JSONObject();
				obj.put("Value",result.getString("value").toString().trim());
				valueList.put(obj);
			}
			close(conn,ps,result);
			
		}
		catch(Exception e) {
			System.out.println("Error Occured!!! " + e);
		}
		return valueList.toString();

		
	}
	public String getUtilization(int city_code,int type_code,String value) {
		ResultSet result;
		JSONArray utilization = new JSONArray();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkUtilization","root","Suji@1234567");
			if(type_code==1) {
				sql="select * from capacity where city_code=? and city=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,city_code);
				ps.setString(2, value);
			
			}
			else {
				sql="select * from capacity where city_code=? and type_code=? and city=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,city_code);
				ps.setInt(2, type_code);
				ps.setString(3, value);
			}
			result = ps.executeQuery();
			while(result.next()) {
				JSONObject obj = new JSONObject();
				obj.put("Network ID ",result.getString("city").toString().trim());
				obj.put("City Code",result.getString("city_code").toString().trim());
				obj.put("Type Code",result.getString("type_code").toString().trim());
				obj.put("Type",result.getString("type").toString().trim());
				obj.put("Value",result.getString("value").toString().trim());
				obj.put("Ring Capacity",result.getString("ring_capacity").toString().trim());
				obj.put("Utilization",result.getString("utilization").toString().trim());
				utilization.put(obj);
				
			}
			close(conn,ps,result);	
		}
		catch(Exception e) {
			System.out.println("Error Occured!!! " + e);
		}
		return utilization.toString();
		
	}
	private static void close(Connection myConn,PreparedStatement myStmt,ResultSet myRs)
	{
		try {
			if (myRs != null) {
			 myRs.close();
			}
			if (myStmt != null) {
			 myStmt.close();
			}
			if (myConn != null) {
			 myConn.close();
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
