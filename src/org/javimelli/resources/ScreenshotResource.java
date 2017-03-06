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

import org.javimelli.dao.AppDao;
import org.javimelli.dao.JDBCAppDao;
import org.javimelli.dao.JDBCScreenshotDao;
import org.javimelli.dao.ScreenshotDao;
import org.javimelli.model.App;
import org.javimelli.model.Screenshot;

@Path("/screenshots")
public class ScreenshotResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Path("/{screenshotId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Screenshot getScreenshotId(@PathParam("screenshotId") int id) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ScreenshotDao screenshotDao = new JDBCScreenshotDao();
		screenshotDao.setConnection(conn);
		
		Screenshot screenshot = null;
		screenshot = screenshotDao.getById(id);
		
		return screenshot;
    }
    
    @GET
    @Path("/app/{appId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Screenshot> getScreenshotByAppId(@PathParam("appId") int id) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ScreenshotDao screenshotDao = new JDBCScreenshotDao();
		screenshotDao.setConnection(conn);
		
		List<Screenshot> screenshots = screenshotDao.getByAppId(id);
		
		return screenshots;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postScreenshot(Screenshot screenshot, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ScreenshotDao screenshotDao = new JDBCScreenshotDao();
		screenshotDao.setConnection(conn);
    	
		int id = screenshotDao.add(screenshot);
    	
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
    @Path("/{screenshotId: [0-9]+}")
    public void deleteScreenshotId(@PathParam("screenshotId") int id){
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ScreenshotDao screenshotDao = new JDBCScreenshotDao();
		screenshotDao.setConnection(conn);
		
		Screenshot screenshot = screenshotDao.getById(id);
		
		if(screenshot == null){
			//Lazamos EXCEPTION
		}else{
			screenshotDao.delete(id);
		}
    }
}
