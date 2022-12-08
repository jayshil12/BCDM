package mypackage.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import mypackage.model.User;

@WebServlet(description = "Create new user servlet", urlPatterns = { "/CreateUser.do" })
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @description this method is used to get user data from database
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			getUser(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description This method is called when user clicks Register button on the UI
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();

		try {
			switch (action) {
				case "/CreateUser.do":
					insertUser(request, response);
                    break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	/**
	 * @description This method insert user into database
	 */
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String firstname = request.getParameter(Constants.FIRST_NAME);
        String lastname = request.getParameter(Constants.LAST_NAME);
        String password = request.getParameter(Constants.PASSWORD);
        String phone = request.getParameter(Constants.PHONE).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)$2-$3");
        String address = request.getParameter(Constants.ADDRESS);
        String user_type = request.getParameter(Constants.USER_TYPE);
        String department = request.getParameter(Constants.DEPARTMENT);
        String office = request.getParameter(Constants.OFFICE);
        String research = request.getParameter(Constants.RESEARCH);
        String major = request.getParameter(Constants.MAJOR);
        String minor = request.getParameter(Constants.MINOR);
        Integer bronco_id = Integer.valueOf(request.getParameter(Constants.BRONCO_ID));
        
        SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy", Locale.US);
		Date start_date = null;
		try {
			start_date = formatter.parse(request.getParameter(Constants.START_DATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date grad_date = null;
		try {
			grad_date = formatter.parse(request.getParameter(Constants.GRAD_DATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        User newUser = new User(firstname, lastname, password, bronco_id, phone, address, user_type, department, office, research, major, minor, start_date, grad_date);
        String message = UserDao.saveUser(newUser);
        
        if (message == Constants.SUCCESS) {
        	RequestDispatcher view = request.getRequestDispatcher(Constants.USERADD_JSP);
    		view.forward(request, response);
        } else {
        	System.out.println(message);
        }
    }
	
	/**
	 * @description This method checks if user exists
	 */
	private void getUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String result = UserDao.checkIfUserExists(Integer.valueOf(request.getParameter(Constants.BRONCO_ID)));
        
        if (result == Constants.SUCCESS) {
        	doPost(request, response);
        } else if (result == Constants.USER_EXISTS) {
        	System.out.println(result);
        	request.setAttribute(Constants.ERROR_MESSAGE, result);
        	request.getRequestDispatcher(Constants.REGISTRATION_JSP).forward(request, response); 
        }
    }
}