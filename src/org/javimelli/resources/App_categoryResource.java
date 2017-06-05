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

import org.javimelli.dao.App_categoryDao;
import org.javimelli.dao.JDBCApp_categoryDao;
import org.javimelli.model.App;
import org.javimelli.model.App_category;
import org.javimelli.model.Category;

@Path("/apps_categorys")
public class App_categoryResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
	@Path("/{appId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategoryId(@PathParam("appId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
		
		List<Category> category = null;
		category = app_categoryDao.getCategoryByApp(id);
		
		return category;
	}
	
	@GET
	@Path("/category/{categoryId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getApps(@PathParam("categoryId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
		
		List<App> apps = null;
		apps = app_categoryDao.getAppByCategory(id);
		
		return apps;
	}
	
	@GET
	@Path("/category/{categoryId: [0-9]+}/pais/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getApps(@PathParam("categoryId") int id,@PathParam("categoryId") String country, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
		
		List<App> apps = null;
		apps = app_categoryDao.getAppByCategory(id, country);
		
		return apps;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCategory(App_category app_category, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
    	
		int id = app_categoryDao.add(app_category);
    	
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
    @Path("/{categoryOldId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("categoryOldId") int id, App_category app_categoryEdit){
    	
    	Response res = null;
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
		
		if(app_categoryEdit == null){
			//Lazamos EXCEPTION
		}else{
			app_categoryDao.save(app_categoryEdit, id);
		}
		
		return res;
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUserId(App_category app_categoryEdit){
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
		
		app_categoryDao.delete(app_categoryEdit);
    }
    
    @DELETE
    @Path("/{appId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUserId(@PathParam("appId") int app){
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		App_categoryDao app_categoryDao = new JDBCApp_categoryDao();
		app_categoryDao.setConnection(conn);
		
		app_categoryDao.delete(app);
    }
}
