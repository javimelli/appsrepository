package org.javimelli.resources;

import org.javimelli.dao.JDBCUserDao;

import org.javimelli.dao.UserDao;
import org.javimelli.model.User;



import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    
    //Método para el post de un user
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(User user){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDao userDao = new JDBCUserDao();
		userDao.setConnection(conn);
    	
		int id = userDao.addUser(user);
    	
		//Creamos una respuesta
		res = Response //return 201 y la localizacion del nuevo recurso
			.created(
					uriInfo
					.getAbsolutePathBuilder()
					.path(Integer.toString(id))
					.build())
			.contentLocation(
					uriInfo
					.getAbsolutePathBuilder()
					.path(Integer.toString(id))
					.build())
			.build();
		
		return res;
    }
    
    
    
}
