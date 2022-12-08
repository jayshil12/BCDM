package mypackage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypackage.model.User;

@WebServlet(description = "login user servlet", urlPatterns = { "/LoginUser.do" })
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @description This method is called when login button is clicked
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			loginUser(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description this method login user
	 */
	public void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		Map<String, User> result = UserDao.loginUser(Integer.valueOf(request.getParameter(Constants.BRONCO_ID)), request.getParameter(Constants.PASSWORD));
		
		if (result.containsKey(Constants.LOGIN_SUCCESSFUL)) {
			HttpSession session  = request.getSession(true);
			session.setAttribute(Constants.BRONCO_ID, request.getParameter(Constants.BRONCO_ID));//setting bronco id as a session attribute
			session.setAttribute(Constants.FIRST_NAME, result.get(Constants.LOGIN_SUCCESSFUL).getFirstName());
			session.setAttribute(Constants.USER_ID, result.get(Constants.LOGIN_SUCCESSFUL).getId());
			
        	request.getRequestDispatcher(Constants.DASHBOARD_JSP).forward(request, response); 
		} else if (result.containsKey(Constants.PASSWORD_INCORRECT)) {
			request.setAttribute(Constants.ERROR_MESSAGE, Constants.PASSWORD_INCORRECT);
        	request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response); 
		} else if (result.containsKey(Constants.USER_NOT_EXISTS)) {
			request.setAttribute(Constants.ERROR_MESSAGE, Constants.USER_NOT_EXISTS);
        	request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response); 
		}
	}
}