package org.javimelli.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import org.javimelli.dao.AppDao;
import org.javimelli.dao.ImagesDao;
import org.javimelli.dao.JDBCAppDao;
import org.javimelli.dao.JDBCImagesDao;
import org.javimelli.model.Images;

@Path("/images")
public class ImagesResource {

	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Images> getApps() {
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ImagesDao imagesDao = new JDBCImagesDao();
		imagesDao.setConnection(conn);
		
		List<Images> listImages = imagesDao.getAllImages();
		
		return listImages;
    }
    
    @GET
    @Path("/id_fotos/{Id_fotos}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Images> getAllsByOwner(@PathParam("Id_fotos") String id){
    	
    	List<Images> listImages = null;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ImagesDao imagesDao = new JDBCImagesDao();
		imagesDao.setConnection(conn);
		
		listImages = imagesDao.getById_fotos(id);
    	
    	return listImages;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postApp(Images img, @Context HttpServletRequest request){
    	
    	Response res;
    	
    	//Obtenemos la conexion a la base de datos del contexto de la aplicacion
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ImagesDao imagesDao = new JDBCImagesDao();
		imagesDao.setConnection(conn);
    	
    	int id = 9999;
    	
		id = imagesDao.addImages(img);
    	
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
    @Path("/{Id: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("Id") int id, Images imgEdit){
    	
    	Response res = null;
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		ImagesDao imagesDao = new JDBCImagesDao();
		imagesDao.setConnection(conn);
		
		Images imgs = imagesDao.getById(id);
		
		if(imgs == null){
			//Lazamos EXCEPTION
		}else{
			if(imgs.getId() == imgEdit.getId()){
				imagesDao.save(imgEdit);
			}
			else{
				//LANZAMOS EXCEPTION DE ID
			}
		}
		
		return res;
    }
    
    @DELETE
    @Path("/{imgId: [0-9]+}")
    public void deleteUserId(@PathParam("imgId") int id){
    	
    	Connection conn = (Connection) sc.getAttribute("dbConn");
		ImagesDao imagesDao = new JDBCImagesDao();
		imagesDao.setConnection(conn);
		
		Images img = imagesDao.getById(id);
		
		if(img == null){
			//Lazamos EXCEPTION
		}else{
			imagesDao.delete(id);
		}
    }
}
