package io.mountblue.contactUs.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import io.mountblue.contactUs.POJO.UserPOJO;

public class UserDAO {
	
	public static boolean addUserInDatabase(UserPOJO newUser) {
		
		String url = "jdbc:postgresql://localhost:5432/ContactUs";
		String username = "postgres";
		String password = "12345";
		
		String sqlQuery = "INSERT INTO requests (name, email, message) values(?,?,?)";
		
		boolean isAddSuccess = false;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			
			statement.setString(1, newUser.getName());
			statement.setString(2, newUser.getEmail());
			statement.setString(3, newUser.getMessage());
			
			isAddSuccess = statement.executeUpdate() > 0 ? true : false;
			
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isAddSuccess;
	}
	
	public static void toggleStatus(int userId, String userStatus) {
		

		String url = "jdbc:postgresql://localhost:5432/ContactUs";
		String username = "postgres";
		String password = "12345";
		
		String sqlQuery = "UPDATE requests SET status = ? WHERE id = ?";
		String setStatus = userStatus.equals("active") ? "archived" : "active";
		boolean isToggleSuccess = false;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			
			statement.setInt(2,userId);
			statement.setString(1,setStatus );
			
			isToggleSuccess = statement.executeUpdate() > 0 ? true : false;
			
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(isToggleSuccess);
	
		
	}

}
