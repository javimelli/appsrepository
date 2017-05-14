package org.javimelli.resources;

import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/image")
public class ImageResource {
	
	@Context//Obtenemos el contexto de la aplicacion
	ServletContext sc;
	@Context//Obtenemos el URI completo de la solicitud
	UriInfo uriInfo;
	
	//CAMBIAR LA DIRECCION PARA EL SERVIDOR REAL CUANDO SE PASE A PRODUION, AHORA ESTA EN EL DE PRUEBAS DE ECLPSE
	private static final String DIR_SERVER = "C:\\Users\\Javi\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\webapps\\";
	private static final String FILE_LOCATION = "images";
	
	
	/**
	 * 
	 * @param uploadedInputStream Flujo de entrada al programa del fichero que se está subiendo (Stream de bytes de lectura)
	 * @param fileDetail Clase con detalles del fichero transferido
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
								@Context HttpServletRequest request
			){
		String url = null;//String que enviaremos en la respuesta, para añadirle el domino del servidor
		String img = request.getParameter("img");
		String id_form = request.getParameter("id_form");
		String url_location = null;
		try{
			if(img.equals("capture")){
				url = this.FILE_LOCATION.concat("\\apps\\" + id_form + "\\capturas\\");
				url_location = this.DIR_SERVER + url;
			}else{
				url = this.FILE_LOCATION.concat("\\apps\\" + id_form + "\\iconos\\");
				url_location = this.DIR_SERVER + url;
			}
			File file = new File(url_location);
			if(file.mkdirs()){
				System.out.println("Directorio creado");
			}
			//Creamos un fichero con un flujo de salida del programa con la location correcta
			FileOutputStream out = new FileOutputStream(url_location + fileDetail.getFileName());
			int read = 0;
			//Buffer para almacenar los Bytes leídos del InputStream pasado como parámetro
			byte[] buffer = new byte[1024];
			//Leemos los Bytes del InputStream pasado por parámetro de un MB en un MB hasta que termine  de leerse el fichero.
			while((read = uploadedInputStream.read(buffer)) != -1){
				out.write(buffer, 0, read);
			}
			//Escribe los datos intermedios
			out.flush();
			//Cerramos definitivamente el flujo de salida
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Response res;
		res = Response.ok(url + fileDetail.getFileName()).build();
		
		return res;
	}
}
