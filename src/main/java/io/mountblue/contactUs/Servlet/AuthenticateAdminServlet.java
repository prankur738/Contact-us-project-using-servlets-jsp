package io.mountblue.contactUs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.mountblue.contactUs.Dao.AdminDAO;
import io.mountblue.contactUs.POJO.AdminPOJO;

/**
 * Servlet implementation class AuthenticateAdmin
 */
@WebServlet("/AuthenticateAdmin")
public class AuthenticateAdminServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminPOJO admin = new AdminPOJO();
		
		String name = request.getParameter("name");
		
		admin.setName(name);
		admin.setPassword(request.getParameter("password"));
		
		boolean isAccessGranted = AdminDAO.isAdminPresentInDatabase(admin);
		
		PrintWriter out = response.getWriter();
		if(isAccessGranted == true) {
			HttpSession session = request.getSession();
			session.setAttribute("adminName", name);
			response.sendRedirect("/ContactUsPage/admin/contactus/requests/DisplayDataToAdmin.jsp");
			}		
		else {
			response.sendRedirect("/ContactUsPage/admin/login/adminlogin.html");						
		}
		
	}

}
