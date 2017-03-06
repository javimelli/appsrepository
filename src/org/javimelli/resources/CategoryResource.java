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

import org.javimelli.dao.AppDao;
import org.javimelli.dao.CategoryDao;
import org.javimelli.dao.JDBCAppDao;
import org.javimelli.dao.JDBCCategoryDao;
import org.javimelli.model.App;
import org.javimelli.model.Category;

@Path("/categorys")
public class CategoryResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategorys() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CategoryDao categoryDao = new JDBCCategoryDao();
		categoryDao.setConnection(conn);
		
		List<Category> listCategory = categoryDao.getAlls();
		
		return listCategory;
    }
    
    @GET
    @Path("/{categoryId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategorysById(@PathParam("categoryId") int id){
    	
    	Category category = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CategoryDao categoryDao = new JDBCCategoryDao();
		categoryDao.setConnection(conn);
		
		category = categoryDao.getById(id);
    	
    	return category;
    }
}
