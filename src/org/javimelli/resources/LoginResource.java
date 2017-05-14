package org.javimelli.resources;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.javimelli.model.User;
import org.javimelli.dao.UserDao;
import org.javimelli.dao.JDBCUserDao;

@Path("session")
public class LoginResource {
	
	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	@Context
	HttpServletResponse response;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserSession(@Context HttpServletRequest request, @Context HttpServletResponse response){
		
		User userSession = null;
		
//		NewCookie cookie = new NewCookie("name", "123", "/", "", "comment", 100, false);
//		return Response.ok("ok").cookie(cookie).build();
		
		HttpSession session = (HttpSession) request.getSession();
		if(session != null){
			System.out.println("Id en get User: "+session.getId());
			userSession = (User) session.getAttribute("user");
		}
		
		return userSession;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User starSession(User user, @Context HttpServletRequest request){
		
		User userSession = null;
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDao userDao = new JDBCUserDao();
		userDao.setConnection(conn);
		
		User userDB = userDao.get(user.getUsername());
		
		//Compobamos que el usuario exite en la base de datos y evitamos un nullpointerexception a la hora
		//de acceder al username delk userDB
		if(userDB.getUsername() != null){
			if(userDB.getUsername().equals(user.getUsername()) && userDB.getPassword().equals(user.getPassword())){
				
				HttpSession session = (HttpSession) request.getSession(true);
				session.setAttribute("user",userDB);
				userSession = (User) session.getAttribute("user");
				System.out.println("Get ID Session: "+session.getId()+" User: "+userSession.getUsername()+" Password: "+userSession.getPassword());
			}
		}
		
		return userSession;
	}
	
	@GET
	@Path("/logout")
	public boolean logout(@Context HttpServletRequest request){
		boolean logout = false;
		
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
			logout = true;
		}
		
		return logout;
	}
}
