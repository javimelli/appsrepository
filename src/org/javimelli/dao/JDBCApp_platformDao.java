package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.App_platform;
import org.javimelli.model.Platform;

public class JDBCApp_platformDao implements App_platformDao{

	//CONSTANTES DE TABLA
	private static final String tblApp_platform = "app_platform";
	private static final String tblApp = "app";
	private static final String tblPlatform = "platform";
	
	//CONTANTES DE ATRIBUTOS DE TABLA
	private static final String atrApp_id = "app_id";
	private static final String atrPlatform_id = "platform_id";
	private static final String atrUrl_download = "url_download";
	
	//CONSTANTE DE ATRIBUTOS DE TABLA app
	private static final String atrAppId = "id";
	private static final String atrAppUser_id = "user_id";
	private static final String atrAppUrl_web = "url_web";
	private static final String atrAppTitle = "title";
	private static final String atrAppDescription = "description";
	private static final String atrAppUrl_icon = "url_icon";
	private static final String atrAppPrice = "price";
	private static final String atrAppVersion = "version";
	private static final String atrAppUrl_video = "url_video";
	private static final String atrAppLanguage = "language";
	private static final String atrAppCountry = "country";
	private static final String atrAppId_fotos = "id_fotos";
	private static final String atrAppDate = "date";
	private static final String atrAppTime = "time";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA platform
	private static final String atrPlatformId = "id";
	private static final String atrPlatformName = "name";
	private static final String atrPlatformVersion = "version";
	
	private Connection conn;

	@Override
	public List<App> getAppsByplatform(int id) {
		
		List<App> ListApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblApp_platform+" a1 INNER JOIN "+tblApp+" a2 ON a1."+atrApp_id+"=a2."+atrAppId+" WHERE a1."+atrPlatform_id+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					App app = new App();
					app.setId(rs.getInt(atrAppId));
					app.setUser_id(rs.getInt(atrAppUser_id));
					app.setUrl_web(rs.getString(atrAppUrl_web));
					app.setTitle(rs.getString(atrAppTitle));
					app.setDescription(rs.getString(atrAppDescription));
					app.setUrl_icon(rs.getString(atrAppUrl_icon));
					app.setPrice(rs.getInt(atrAppPrice));
					app.setVersion(rs.getInt(atrAppVersion));
					app.setUrl_video(rs.getString(atrAppUrl_video));
					app.setLanguage(rs.getString(atrAppLanguage));
					app.setCountry(rs.getString(atrAppCountry));
					app.setId_fotos(rs.getString(atrAppId_fotos));
					app.setTime(rs.getString(atrAppTime));
					app.setDate(rs.getString(atrAppDate));
					
					ListApps.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListApps;
	}

	@Override
	public List<Platform> getPlatformsByApps(int id) {
		
		List<Platform> ListPlatforms = new ArrayList<Platform>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblApp_platform+" a1 INNER JOIN "+tblPlatform+" a2 ON a1."+atrPlatform_id+"=a2."+atrPlatformId+" WHERE a1."+atrApp_id+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Platform platform = new Platform();
					platform.setId(rs.getInt(atrPlatformId));
					platform.setName(rs.getString(atrPlatformName));
					platform.setVersion(rs.getInt(atrPlatformVersion));
					
					ListPlatforms.add(platform);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListPlatforms;
	}

	@Override
	public int add(App_platform app_platform) {
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblApp_platform +" ("+atrApp_id+","+atrPlatform_id+","+atrUrl_download+") VALUES("
						+app_platform.getApp_id()+","
						+app_platform.getPlatform_id()+",'"
						+app_platform.getUrl_download()+"')";
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
	public boolean delete(App_platform app_platform) {
		
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM "+tblApp_platform+" WHERE "+atrApp_id+"="+app_platform.getApp_id()+" AND "+atrPlatform_id+"="+app_platform.getPlatform_id();
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
	public boolean delete(int app) {
		
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM "+tblApp_platform+" WHERE "+atrApp_id+"="+app;
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
