package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.Screenshot;

public class JDBCScreenshotDao implements ScreenshotDao{

	//CONSTANTES DE TABLA
	private static final String tblScreenshot = "screenshot";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrUrl_capture = "url_capture";
	private static final String atrApp_id = "app_id";
	
	private Connection conn;
	
	@Override
	public Screenshot getById(int id) {
		
		Screenshot screenshot = new Screenshot();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblScreenshot+" WHERE "+atrId+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					screenshot.setId(rs.getInt(atrId));
					screenshot.setUrl_capture(rs.getString(atrUrl_capture));
					screenshot.setApp_id(rs.getInt(atrApp_id));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return screenshot;
	}

	@Override
	public List<Screenshot> getByAppId(int id) {
		
		List<Screenshot> listScreenshots = new ArrayList<Screenshot>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblScreenshot+" WHERE "+atrApp_id+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Screenshot screenshot = new Screenshot();
					screenshot.setId(rs.getInt(atrId));
					screenshot.setUrl_capture(rs.getString(atrUrl_capture));
					screenshot.setApp_id(rs.getInt(atrApp_id));
					
					listScreenshots.add(screenshot);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return listScreenshots;
	}

	@Override
	public int add(Screenshot screenshot) {
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblScreenshot +" ("+atrId+","+atrUrl_capture+","+atrApp_id+") VALUES("
						+screenshot.getId()+",'"
						+screenshot.getUrl_capture()+"',"
						+screenshot.getApp_id()+")";
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
	public boolean delete(int id) {
		
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM "+tblScreenshot+" WHERE "+atrId+"="+id;
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

}
