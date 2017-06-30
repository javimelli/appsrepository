package org.javimelli.resources;

import java.sql.Connection;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.javimelli.model.User;
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
    
    @GET
	@Path("/limit/{num: [0-9]+}/{init: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAppByLimit(@PathParam("num") int num, @PathParam("init") int init,  @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		List<App> app = null;
		app = appDao.getAppsByLmit(num, init);
		
		return app;
	}
    
    @GET
	@Path("/country/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAppByCountry(@PathParam("country") String country,  @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		List<App> app = null;
		app = appDao.getAppsByCountry(country);
		
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
		
		HttpSession session = (HttpSession) request.getSession();
    	User user = (User) session.getAttribute("user");
    	
    	int id = 9999;
    	
    	if(user != null){
    		app.setUser_id(user.getId());
    		id = appDao.addApp(app);
    	}
    	
		
    	
		//Creamos una respuesta
		res = Response //return 201 y la localizacion del nuevo recurso
			.ok(Integer.toString(id))
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
    public Response putUser(@PathParam("appId") int id, App appEdit,  @Context HttpServletRequest request){
    	
    	Response res = null;
    	
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AppDao appDao = new JDBCAppDao();
		appDao.setConnection(conn);
		
		HttpSession session = (HttpSession) request.getSession();
    	User user = (User) session.getAttribute("user");
    	
    	boolean save = false;
    	
    	App app = appDao.getById(appEdit.getId());
    	
    	if(user != null || (app != null && (app.getVisitas() != appEdit.getVisitas()))){
			
			if(app == null){
				//Lazamos EXCEPTION
			}else{
				if(app.getId() == appEdit.getId()){
					save = appDao.save(appEdit);
				}
				else{
					//LANZAMOS EXCEPTION DE ID
				}
			}
    	}
    	
    	res = Response.ok(save).build();
		
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
