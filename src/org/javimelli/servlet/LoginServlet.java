package org.javimelli.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.javimelli.dao.JDBCUserDao;
import org.javimelli.dao.UserDao;
import org.javimelli.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Estamos en Ligin Servet Get");
		System.out.println("Estamos en Ligin Servet Post");
		BufferedReader buffer = request.getReader();
		int cont = 0;
		String username = "";
		String password = "";
		while(buffer.ready()){
			if(cont == 1){
				username = buffer.readLine().split(":")[1].replaceAll("[\", ]", "");
			}
			else if(cont == 2){
				password = buffer.readLine().split(":")[1].replaceAll("[\", ]", "");
			}else{
				buffer.readLine();		
			}
			
			cont++;
		}
		buffer.close();
		System.out.println("User: "+username+" Password: "+password);
		User userSession = null;
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDao userDao = new JDBCUserDao();
		userDao.setConnection(conn);
		
		User userDB = userDao.get((String)username);
		
		if(userDB.getUsername() != null){
			if(userDB.getUsername().equals(username) && userDB.getPassword().equals(password)){
				
				HttpSession session = (HttpSession) request.getSession(true);
				session.setAttribute("user",userDB);
				userSession = (User) session.getAttribute("user");
				System.out.println("Get ID Session: "+session.getId()+" User: "+userSession.getUsername()+" Password: "+userSession.getPassword());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Estamos en Ligin Servet Post");
		BufferedReader buffer = request.getReader();
		int cont = 0;
		String username = "";
		String password = "";
		while(buffer.ready()){
			if(cont == 1){
				username = buffer.readLine().split(":")[1].replaceAll("[\", ]", "");
			}
			else if(cont == 2){
				password = buffer.readLine().split(":")[1].replaceAll("[\", ]", "");
			}else{
				buffer.readLine();		
			}
			
			cont++;
		}
		buffer.close();
		System.out.println("User: "+username+" Password: "+password);
		User userSession = null;
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDao userDao = new JDBCUserDao();
		userDao.setConnection(conn);
		
		User userDB = userDao.get((String)username);
		
		if(userDB.getUsername() != null){
			if(userDB.getUsername().equals(username) && userDB.getPassword().equals(password)){
				
				HttpSession session = (HttpSession) request.getSession(true);
				session.setAttribute("user",userDB);
				userSession = (User) session.getAttribute("user");
				System.out.println("Get ID Session: "+session.getId()+" User: "+userSession.getUsername()+" Password: "+userSession.getPassword());
			}
		}
		
	}

}
