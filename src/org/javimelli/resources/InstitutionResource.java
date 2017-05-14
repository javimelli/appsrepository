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

import org.javimelli.dao.InstitutionDao;
import org.javimelli.dao.JDBCInstitutionDao;
import org.javimelli.model.Institution;

@Path("/institutions")
public class InstitutionResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Institution> getInstitutions() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		InstitutionDao institutionDao = new JDBCInstitutionDao();
		institutionDao.setConnection(conn);
		
		List<Institution> listInstitutions = institutionDao.getAlls();
		
		return listInstitutions;
    }
    
    @GET
	@Path("/{instituionId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Institution getInstitutionId(@PathParam("instituionId") int id, @Context HttpServletRequest request){
		
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		InstitutionDao institutionDao = new JDBCInstitutionDao();
		institutionDao.setConnection(conn);
		
		Institution institution = null;
		institution = institutionDao.getById(id);
		
		return institution;
	}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postInstitution(Institution institution, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		InstitutionDao institutionDao = new JDBCInstitutionDao();
		institutionDao.setConnection(conn);
    	
		int id = institutionDao.add(institution);
    	
		//Creamos una respuesta
		//return 201 y la localizacion del nuevo recurso
		res = Response.ok(Integer.toString(id))
			.contentLocation(
					uriInfo
					.getAbsolutePathBuilder()
					.path(Integer.toString(id))
					.build())
			.build();
		
		return res;
    }
    
    @PUT
    @Path("/{institutionId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putInstitution(@PathParam("institutionId") int id, Institution institutionEdit){
    	
    	Response res = null;
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		InstitutionDao institutionDao = new JDBCInstitutionDao();
		institutionDao.setConnection(conn);
		
		Institution institution = institutionDao.getById(institutionEdit.getId());
		
		if(institution == null){
			//Lazamos EXCEPTION
		}else{
			if(institution.getId() == institutionEdit.getId()){
				institutionDao.save(institutionEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
    
    @DELETE
    @Path("/{institutionId: [0-9]+}")
    public void deleteUserId(@PathParam("institutionId") int id){
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		InstitutionDao institutionDao = new JDBCInstitutionDao();
		institutionDao.setConnection(conn);
		
		Institution institution = institutionDao.getById(id);
		
		if(institution == null){
			//Lazamos EXCEPTION
		}else{
			institutionDao.delete(id);
		}
    }
}
