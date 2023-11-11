package io.mountblue.contactUs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.mountblue.contactUs.Dao.UserDAO;
import io.mountblue.contactUs.POJO.UserPOJO;

/**
 * Servlet implementation class AddRequest
 */
@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			UserPOJO user = new UserPOJO();
			
			user.setName(request.getParameter("name"));
			user.setEmail(request.getParameter("email"));
			user.setMessage(request.getParameter("message"));
			
			boolean isUserAdded = UserDAO.addUserInDatabase(user);
			
			PrintWriter out = response.getWriter();
			if(isUserAdded == true) {
				out.println("Thank You!");
				out.println("Your details have been successfully added in the Database");
			}
			else {
				out.println("Error! Try again");
			}	
		
	}

}
