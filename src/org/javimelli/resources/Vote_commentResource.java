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

import org.javimelli.dao.JDBCVote_commentDao;
import org.javimelli.dao.Vote_commentDao;
import org.javimelli.model.Vote_comment;

@Path("/Votes_comments")
public class Vote_commentResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
    @Path("/user/{userId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vote_comment> getAllsByUser(@PathParam("userId") int id){
    	
    	List<Vote_comment> listVotes = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		listVotes = vote_commentDao.getByUser_id(id);
    	
    	return listVotes;
    }
    
    @GET
    @Path("/comment/{userId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vote_comment> getAllsByComment(@PathParam("userId") int id){
    	
    	List<Vote_comment> listVotes = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		listVotes = vote_commentDao.getByCommentary_id(id);
    	
    	return listVotes;
    }
    
    @GET
    @Path("/{userId: [0-9]+}/{commentId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vote_comment getAllsByUserAndCommen(@PathParam("userId") int id, @PathParam("commentId") int commen){
    	
    	Vote_comment votes = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		votes = vote_commentDao.getByUserAndCommentary(id,commen);
    	
    	return votes;
    }
    
    @GET
    @Path("/countComplaint/{commentId: [0-9]+}")
    public int getCountComplaint(@PathParam("commentId") int commen){
    	
    	int denuncias = 0;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		denuncias = vote_commentDao.numComplaint(commen);
    	
    	return denuncias;
    }
    
    @GET
    @Path("/countVotePositive/{commentId: [0-9]+}")
    public int getCountVotePositive(@PathParam("commentId") int commen){
    	
    	int denuncias = 0;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		denuncias = vote_commentDao.numVotePositeve(commen);
    	
    	return denuncias;
    }
    
    @GET
    @Path("/countVoteNegative/{commentId: [0-9]+}")
    public int getCountVoteNegative(@PathParam("commentId") int commen){
    	
    	int denuncias = 0;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		denuncias = vote_commentDao.numVoteNegative(commen);
    	
    	return denuncias;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postVote_commenet(Vote_comment vote, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	/*
 			{
				"user_id": 1,
				"commentary_id": 1,
				"value": true,
				"complaint": false
			}
    	 */
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
    	
		int id = vote_commentDao.add(vote);
    	
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
    @Path("/{userId: [0-9]+}/{commentId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("appId") int id, @PathParam("commentId") int comment, Vote_comment commentEdit){
    	
    	Response res = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		Vote_commentDao vote_commentDao = new JDBCVote_commentDao();
		vote_commentDao.setConnection(conn);
		
		Vote_comment vote = vote_commentDao.getByUserAndCommentary(commentEdit.getUser_id(), commentEdit.getCommentary_id());
		
		if(vote == null){
			//Lazamos EXCEPTION
		}else{
			if(vote.getUser_id() == commentEdit.getUser_id() && vote.getCommentary_id() == commentEdit.getCommentary_id()){
				vote_commentDao.save(commentEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
}
