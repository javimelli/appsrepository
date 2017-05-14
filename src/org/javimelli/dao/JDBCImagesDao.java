package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.Images;

public class JDBCImagesDao implements ImagesDao{

	private Connection conn;
	
	//CONSTANTES DE TABLA
	private static final String tblImages = "images";
	//CONSTATES DE ATRIBUTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrId_fotos = "id_fotos";
	private static final String atrUrl = "url";
	private static final String atrType = "type";
	@Override
	public List<Images> getAllImages() {
		
		if (conn == null) return null;
		
		ArrayList<Images> images = new ArrayList<Images>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblImages;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Images image = new Images();
				image.setId(rs.getInt(atrId));
				image.setId_fotos(rs.getString(atrId_fotos));
				image.setUrl(rs.getString(atrUrl));
				image.setType(rs.getString(atrType));
		
				images.add(image);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}
	@Override
	public List<Images> getById_fotos(String id_fotos) {
		
		List<Images> images = new ArrayList<Images>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblImages+" WHERE "+atrId_fotos+"='"+id_fotos+"'";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Images image = new Images();
					image.setId(rs.getInt(atrId));
					image.setId_fotos(rs.getString(atrId_fotos));
					image.setUrl(rs.getString(atrUrl));
					image.setType(rs.getString(atrType));
			
					images.add(image);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return images;
	}
	@Override
	public int addImages(Images img) {
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblImages +" ("+atrId+","+atrId_fotos+","+atrUrl+","+atrType+") VALUES("
						+img.getId()+",'"
						+img.getId_fotos()+"','"
						+img.getUrl()+"','"
						+img.getType()+"')";
				System.out.println(sql);
				stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
				
				ResultSet genKeys = stmt.getGeneratedKeys();
				
				if (genKeys.next())
				    id = genKeys.getInt(1);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return id;
	}
	@Override
	public boolean save(Images img) {
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblImages +" SET "+atrId+"="+img.getId()+","
												+atrId_fotos+"='"+img.getId_fotos()+"',"
												+atrUrl+"='"+img.getUrl()+"',"
												+atrType+"='"+img.getType()+"'"
												+" WHERE "+atrId+"="+img.getId();
				System.out.println(sql);
				stmt.executeUpdate(sql);
				save = true;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return save;
	}
	@Override
	public boolean delete(int id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM "+tblImages+" WHERE "+atrId+"="+id;
				System.out.println(sql);
				stmt.executeUpdate(sql);
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
		
	}
	@Override
	public Images getById(int id) {
		
		Images image = new Images();
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblImages+" WHERE "+atrId+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					image.setId(rs.getInt(atrId));
					image.setId_fotos(rs.getString(atrId_fotos));
					image.setUrl(rs.getString(atrUrl));
					image.setType(rs.getString(atrType));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return image;
	}
	
}
