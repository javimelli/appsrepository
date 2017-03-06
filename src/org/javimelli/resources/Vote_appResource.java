package org.javimelli.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import org.javimelli.model.Vote_app;
import org.javimelli.dao.Vote_appDao;
import org.javimelli.dao.JDBCVote_appDao;

@Path("/votes_apps")
public class Vote_appResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
    @Path("/user/{userId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vote_app> getAllsByUser(@PathParam("userId") int id){
    	
    	List<Vote_app> listVotes = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
		
		listVotes = vote_appDao.getByUser_id(id);
    	
    	return listVotes;
    }
    
    @GET
    @Path("/app/{appId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vote_app> getAllsByApp(@PathParam("appId") int id){
    	
    	List<Vote_app> listVotes = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
		
		listVotes = vote_appDao.getByApp_id(id);
    	
    	return listVotes;
    }
    
    @GET
    @Path("/{userId: [0-9]+}/{appId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vote_app getAllsByUserAndApp(@PathParam("userId") int id, @PathParam("appId") int app){
    	
    	Vote_app vote = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
		
		vote = vote_appDao.getByUserAndApp(id,app);
    	
    	return vote;
    }
    
    @GET
    @Path("/countComplaint/{apptId: [0-9]+}")
    public int getCountComplaint(@PathParam("apptId") int app){
    	
    	int denuncias = 0;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
		
		denuncias = vote_appDao.numComplaint(app);
    	
    	return denuncias;
    }
    
    @GET
    @Path("/averageVotes/{appId: [0-9]+}")
    public int getCountVotePositive(@PathParam("appId") int app){
    	
    	int denuncias = 0;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
		
		denuncias = vote_appDao.AverageVotes(app);
    	
    	return denuncias;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postVote_commenet(Vote_app vote, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	/*
 			{
				"user_id": 1,
				"app_id": 1,
				"value": 1,
				"complaint": false
			}
    	 */
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
    	
		int id = vote_appDao.add(vote);
    	
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
    @Path("/{userId: [0-9]+}/{apptId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("appId") int id, @PathParam("apptId") int app, Vote_app vote_appEdit){
    	
    	Response res = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_appDao vote_appDao = new JDBCVote_appDao();
		vote_appDao.setConnection(conn);
		
		Vote_app vote = vote_appDao.getByUserAndApp(vote_appEdit.getUser_id(), vote_appEdit.getApp_id());
		
		if(vote == null){
			//Lazamos EXCEPTION
		}else{
			if(vote.getUser_id() == vote_appEdit.getUser_id() && vote.getApp_id() == vote_appEdit.getApp_id()){
				vote_appDao.save(vote_appEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
}
