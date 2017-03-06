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

import org.javimelli.dao.DatasetDao;
import org.javimelli.dao.JDBCDatasetDao;
import org.javimelli.model.Dataset;

@Path("/datasets")
public class DatasetResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getApps() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		DatasetDao datasetDao = new JDBCDatasetDao();
		datasetDao.setConnection(conn);
		
		List<Dataset> listDataset = datasetDao.getAlls();
		
		return listDataset;
    }
    
    @GET
    @Path("{datasetId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Dataset getDatasetById(@PathParam("datasetId") int id) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		DatasetDao datasetDao = new JDBCDatasetDao();
		datasetDao.setConnection(conn);
		
		Dataset dataset = datasetDao.getById(id);
		
		return dataset;
    }
    
    @GET
    @Path("/user/{datasetId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getDatasetByUser_id(@PathParam("datasetId") int id) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		DatasetDao datasetDao = new JDBCDatasetDao();
		datasetDao.setConnection(conn);
		
		List<Dataset> datasets = datasetDao.getByUserId(id);
		
		return datasets;
    }
    
    @GET
    @Path("/category/{datasetId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getDatasetByCategory_id(@PathParam("datasetId") int id) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		DatasetDao datasetDao = new JDBCDatasetDao();
		datasetDao.setConnection(conn);
		
		List<Dataset> datasets = datasetDao.getByCategoryId(id);
		
		return datasets;
    }
    
    @GET
    @Path("/institution/{datasetId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getDatasetByInstitution_id(@PathParam("datasetId") int id) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		DatasetDao datasetDao = new JDBCDatasetDao();
		datasetDao.setConnection(conn);
		
		List<Dataset> datasets = datasetDao.getByInstitutionId(id);
		
		return datasets;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postDataset(Dataset dataset, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
    	DatasetDao datasetDao = new JDBCDatasetDao();
    	datasetDao.setConnection(conn);
    	
		int id = datasetDao.add(dataset);
    	
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
    @Path("/{datasetId: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putDataset(@PathParam("datasetId") int id, Dataset datasetEdit){
    	
    	Response res = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
    	DatasetDao datasetDao = new JDBCDatasetDao();
    	datasetDao.setConnection(conn);
		
		Dataset dataset = datasetDao.getById(datasetEdit.getId());
		
		if(dataset == null){
			//Lazamos EXCEPTION
		}else{
			if(dataset.getId() == datasetEdit.getId()){
				datasetDao.save(datasetEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
    
    @DELETE
    @Path("/{datasetId: [0-9]+}")
    public void deleteUserId(@PathParam("datasetId") int id){
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
    	DatasetDao datasetDao = new JDBCDatasetDao();
    	datasetDao.setConnection(conn);
		
		Dataset dataset = datasetDao.getById(id);
		
		if(dataset == null){
			//Lazamos EXCEPTION
		}else{
			datasetDao.delete(id);
		}
    }
}
