package org.javimelli.resources;

import org.javimelli.dao.JDBCUserDao;
import org.javimelli.dao.UserDao;
import org.javimelli.model.User;



import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/users")
public class UsersResouce {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
    
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsersJSON() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDao userDao = new JDBCUserDao();
		userDao.setConnection(conn);
		
		//Guardamos en listaUsuarios la lista de usuarios de la base de daros
		List<User> listaUsuarios = userDao.getUsersAll();
		
		//Devolvemos la listaUusarios
		return listaUsuarios;
    }
}
