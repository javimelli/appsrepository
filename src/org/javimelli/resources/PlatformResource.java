package org.javimelli.resources;

import java.sql.Connection;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.javimelli.dao.JDBCPlatformDao;
import org.javimelli.dao.PlatformDao;
import org.javimelli.model.Platform;

@Path("/platforms")
public class PlatformResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Platform> getPlatform() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		PlatformDao platformDao = new JDBCPlatformDao();
		platformDao.setConnection(conn);
		
		List<Platform> listPlatforms = platformDao.getAlls();
		
		return listPlatforms;
    }
    
    @GET
    @Path("/{platfromId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Platform getPlatformById(@PathParam("platfromId") int id){
    	
    	Platform platform = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		PlatformDao platformDao = new JDBCPlatformDao();
		platformDao.setConnection(conn);
		
		platform = platformDao.getById(id);
    	
    	return platform;
    }
}
