package io.mountblue.contactUs.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.mountblue.contactUs.POJO.AdminPOJO;

public class AdminDAO {
	
	public static boolean isAdminPresentInDatabase(AdminPOJO admin) {
		
		String url = "jdbc:postgresql://localhost:5432/ContactUs";
		String username = "postgres";
		String password = "12345";
		
		String adminName = admin.getName();
		String adminPassword = admin.getPassword();
		
		String sqlQuery = "SELECT * FROM admins WHERE name = ? AND password = ?";
		
		boolean isAdminPresent = false;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			
			statement.setString(1, adminName);
			statement.setString(2, adminPassword);
		
			ResultSet line = statement.executeQuery();
			
			if(line.next()) {
				isAdminPresent = true;//line.getString("password").equals(adminPassword);
			}			
//			System.out.println();
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isAdminPresent;

		
	}

}
