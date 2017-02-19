package org.javimelli.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.javimelli.model.User;


@WebListener
public class ListenerApplication implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(ServletContextListener.class.getName());
	//CONSTANTES PARA LA CONEXIÓN CON LA BASE DE DATOS
	private static final String HOST = "jdbc:mysql://db4free.net/appsrepository";
	private static final String USER = "javimelli8";
	private static final String PASSWORD = "corchado";
	
    public ListenerApplication() {
        
    }

    public void contextDestroyed(ServletContextEvent event)  { 
         
    }
	
    public void contextInitialized(ServletContextEvent event)  { 
         
    	logger.info("-------------------------------------Creating DB-----------------------------------");
		Connection conn = null;
		
		//Cargamos el driver de mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			//Cogemos el contexto de la aplicación
			ServletContext sc = event.getServletContext();
			
			//Creamos la conecxión con los datos de la base de datos
			conn = DriverManager.getConnection(this.HOST,this.USER,this.PASSWORD);
			
			//Guardamos la conexión en el contexto de la aplicación
			sc.setAttribute("dbConn", conn);
			
			/*
			ArrayList<User> users = new ArrayList<User>();
			Statement stmt = conn.createStatement();	
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery("select * from user");
			}
			while ( rs.next() ) {
				User user = new User();
				user.setId_user(rs.getInt("id_user"));
				user.setNombre(rs.getString("nombre"));
				user.setEmail(rs.getString("apellido1"));
				user.setEmail(rs.getString("apellido2"));
				user.setNombre_desarrollador(rs.getString("nombre_desarrollador"));
				user.setTelefono(rs.getString("telefono"));
				user.setUrl_web(rs.getString("url_web"));
				user.setEmail(rs.getString("email"));
				user.setPais(rs.getString("pais"));
				
				
				users.add(user);
				
								
			}
			*/

			logger.info("-------------------------Base de datos creada------------------------------");
		}catch(SQLException e) {
			e.printStackTrace();
		}
    }
	
}
