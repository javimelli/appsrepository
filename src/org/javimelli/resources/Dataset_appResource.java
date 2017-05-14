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

import org.javimelli.dao.Dataset_appDao;
import org.javimelli.dao.JDBCDataset_appDao;
import org.javimelli.model.App;
import org.javimelli.model.Dataset;
import org.javimelli.model.Dataset_app;

@Path("/datasets_apps")
public class Dataset_appResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
	@Path("/dataset/{datasetId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<App> getAppsByDatasets(@PathParam("datasetId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Dataset_appDao dataset_appDao = new JDBCDataset_appDao();
		dataset_appDao.setConnection(conn);
		
		List<App> apps = null;
		apps = dataset_appDao.getAppByDataset(id);
		
		return apps;
	}
	
	@GET
	@Path("/app/{appId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dataset> getDatasetByApps(@PathParam("appId") int id, @Context HttpServletRequest request){
		
		Connection conn = (Connection) sc.getAttribute("dbConn");
		Dataset_appDao dataset_appDao = new JDBCDataset_appDao();
		dataset_appDao.setConnection(conn);
		
		List<Dataset> datasets = null;
		datasets = dataset_appDao.getDatasetByApp(id);
		
		return datasets;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postDataset_app(Dataset_app dataset_app, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		Dataset_appDao dataset_appDao = new JDBCDataset_appDao();
		dataset_appDao.setConnection(conn);
    	
		int id = dataset_appDao.add(dataset_app);
    	
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
	
	@DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUserId(Dataset_app dataset_appEdit){
    	
		//Obtenemos la conexion a la base de datos del contexto de la aplicacion
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		Dataset_appDao dataset_appDao = new JDBCDataset_appDao();
		dataset_appDao.setConnection(conn);
		
		dataset_appDao.delete(dataset_appEdit);
    }
}
