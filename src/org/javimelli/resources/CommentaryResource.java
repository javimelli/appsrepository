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

import org.javimelli.dao.CommentaryDao;
import org.javimelli.dao.JDBCCommentaryDao;
import org.javimelli.model.Commentary;

@Path("/commentarys")
public class CommentaryResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commentary> getCommentarys() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
		
		List<Commentary> listCommentarys = commentaryDao.getAll();
		
		return listCommentarys;
    }
    
    @GET
    @Path("/owner/{ownerId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commentary> getCommentarysByOwner(@PathParam("ownerId") int owner) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
		
		List<Commentary> listCommentarys = commentaryDao.getAllByOwner(owner);
		
		return listCommentarys;
    }
    
    @GET
    @Path("/app/{appId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commentary> getCommentarysByApp(@PathParam("appId") int app) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
		
		List<Commentary> listCommentarys = commentaryDao.getAllByApps(app);
		
		return listCommentarys;
    }
    
    @GET
	@Path("/{comentaryId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Commentary getAppId(@PathParam("comentaryId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
		
		Commentary commentary = null;
		commentary = commentaryDao.get(id);
		
		return commentary;
	}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCommentarys(Commentary commentary, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
    	
		int id = commentaryDao.add(commentary);
    	
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
    @Path("/{commentaryId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("commentaryId") int id, Commentary commentaryEdit){
    	
    	Response res = null;
    	
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
		
		Commentary commentary = commentaryDao.get(commentaryEdit.getId());
		
		if(commentary == null){
			//Lazamos EXCEPTION
		}else{
			if(commentary.getId() == commentaryEdit.getId()){
				commentaryDao.save(commentaryEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
    
    @DELETE
    @Path("/{commentaryId: [0-9]+}")
    public void deleteUserId(@PathParam("commentaryId") int id){
    	
		Connection conn = (Connection) sc.getAttribute("dbConn");
		CommentaryDao commentaryDao = new JDBCCommentaryDao();
		commentaryDao.setConnection(conn);
		
		Commentary commentary = commentaryDao.get(id);
		
		if(commentary == null){
			//Lazamos EXCEPTION
		}else{
			commentaryDao.delete(id);
		}
    }
	
}
