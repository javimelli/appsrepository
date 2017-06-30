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

import org.javimelli.dao.EstadisticasDao;
import org.javimelli.dao.FiltrosDao;
import org.javimelli.dao.JDBCEstadisticasDao;
import org.javimelli.dao.JDBCFiltrosDao;
import org.javimelli.model.App;
import org.javimelli.model.Dataset;

@Path("/estadisticas")
public class EstadisticasResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getAppsByTitle() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		EstadisticasDao estadisticasDao = new JDBCEstadisticasDao();
		estadisticasDao.setConnection(conn);
		
		List<App> listApp = estadisticasDao.getAppsByTitle();
		
		return listApp;
    }
	
	@GET
	@Path("/datasets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getDatasetsByTitle() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		EstadisticasDao estadisticasDao = new JDBCEstadisticasDao();
		estadisticasDao.setConnection(conn);
		
		List<Dataset> listApp = estadisticasDao.getDatasetsTitle();
		
		return listApp;
    }
}
