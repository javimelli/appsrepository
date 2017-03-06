package org.javimelli.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.javimelli.dao.App_categoryDao;
import org.javimelli.dao.App_platformDao;
import org.javimelli.dao.JDBCApp_categoryDao;
import org.javimelli.dao.JDBCApp_platformDao;
import org.javimelli.model.App;
import org.javimelli.model.App_category;
import org.javimelli.model.App_platform;
import org.javimelli.model.Category;
import org.javimelli.model.Platform;

@Path("/apps_platforms")
public class App_platformResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
	@Path("/app/{appId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Platform> getPlatformByAppId(@PathParam("appId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		App_platformDao app_platformDao = new JDBCApp_platformDao();
		app_platformDao.setConnection(conn);
		
		List<Platform> platform = null;
		platform = app_platformDao.getPlatformsByApps(id);
		
		return platform;
	}
	
	@GET
	@Path("/platform/{platformId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAppByPlatformId(@PathParam("platformId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		App_platformDao app_platformDao = new JDBCApp_platformDao();
		app_platformDao.setConnection(conn);
		
		List<App> app = null;
		app = app_platformDao.getAppsByplatform(id);
		
		return app;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postApp_platform(App_platform app_platform, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		App_platformDao app_platformDao = new JDBCApp_platformDao();
		app_platformDao.setConnection(conn);
    	
		int id = app_platformDao.add(app_platform);
    	
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
	
	@DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteApp_platform(App_platform app_platformEdit){
    	
		Connection conn = (Connection) sc.getAttribute("dbConn");
		App_platformDao app_platformDao = new JDBCApp_platformDao();
		app_platformDao.setConnection(conn);
		
		app_platformDao.delete(app_platformEdit);
    }
}
