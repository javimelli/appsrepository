package org.javimelli.listener;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


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
			conn = DriverManager.getConnection(HOST,USER,PASSWORD);
			
			//Guardamos la conexión en el contexto de la aplicación
			sc.setAttribute("dbConn", conn);

			logger.info("-------------------------Base de datos creada------------------------------");
		}catch(SQLException e) {
			e.printStackTrace();
		}
    }
	
}
