package org.javimelli.resources;

import java.sql.Connection;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.javimelli.model.App;
import org.javimelli.dao.AppDao;
import org.javimelli.dao.JDBCAppDao;

@Path("/apps")
public class AppResource {
	
	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getApps() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		List<App> listApp = appDao.getAppsAll();
		
		return listApp;
    }
    
    @GET
    @Path("/owner/{appId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getAllsByOwner(@PathParam("appId") int id){
    	
    	List<App> listApps = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		listApps = appDao.getAllByOwner(id);
    	
    	return listApps;
    }
    
    @GET
	@Path("/{appId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public App getAppId(@PathParam("appId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		App app = null;
		app = appDao.getById(id);
		
		return app;
	}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postApp(App app, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
    	
		int id = appDao.addApp(app);
    	
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
    
    @PUT
    @Path("/{appId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("appId") int id, App appEdit){
    	
    	Response res = null;
    	
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		App app = appDao.getById(appEdit.getId());
		
		if(app == null){
			//Lazamos EXCEPTION
		}else{
			if(app.getId() == appEdit.getId()){
				appDao.save(appEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
    
    @DELETE
    @Path("/{appId: [0-9]+}")
    public void deleteUserId(@PathParam("appId") int id){
    	
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		App app = appDao.getById(id);
		
		if(app == null){
			//Lazamos EXCEPTION
		}else{
			appDao.delete(id);
		}
    }
}
