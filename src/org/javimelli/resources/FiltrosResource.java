package org.javimelli.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.javimelli.dao.AppDao;
import org.javimelli.dao.FiltrosDao;
import org.javimelli.dao.JDBCAppDao;
import org.javimelli.dao.JDBCFiltrosDao;
import org.javimelli.model.App;
import org.javimelli.model.Dataset;
import org.javimelli.model.Filtro;
import org.javimelli.model.FiltroDataset;

@Path("/filtros")
public class FiltrosResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	@GET
	@Path("/{busq}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getAppsByBusq(@PathParam("busq") String busq) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		FiltrosDao filtrosDao = new JDBCFiltrosDao();
		filtrosDao.setConnection(conn);
		
		List<App> listApp = filtrosDao.BusqApp(busq);
		
		return listApp;
    }
	
	@GET
	@Path("/dataset/{busq}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getDatasetByBusq(@PathParam("busq") String busq) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		FiltrosDao filtrosDao = new JDBCFiltrosDao();
		filtrosDao.setConnection(conn);
		
		List<Dataset> listApp = filtrosDao.BusqDataset(busq);
		
		return listApp;
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<App> getApps(Filtro filtros) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		FiltrosDao filtrosDao = new JDBCFiltrosDao();
		filtrosDao.setConnection(conn);
		
		List<App> listApp = filtrosDao.filtrosApp(filtros);
		
		return listApp;
    }
	
	@POST
	@Path("/datasets")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dataset> getDatasets(FiltroDataset filtros) {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		FiltrosDao filtrosDao = new JDBCFiltrosDao();
		filtrosDao.setConnection(conn);
		
		List<Dataset> listDatasets = filtrosDao.filtrosDataset(filtros);
		
		return listDatasets;
    }
}
