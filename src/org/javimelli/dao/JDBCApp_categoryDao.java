package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.App_category;
import org.javimelli.model.Category;

public class JDBCApp_categoryDao implements App_categoryDao {

	//CONSTANTES DE TABLA
	private static final String tblApp_category = "app_category";
	private static final String tblCategory = "category";
	private static final String tblApp = "app";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA app_category
	private static final String atrApp_id = "app_id";
	private static final String atrCategory_id = "category_id";
	private static final String atrCategory_description = "description";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA category
	private static final String atrId = "id";
	private static final String atrName = "name";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA app
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
	private static final String atrAppVisitas = "visits";
	
	private Connection conn;
	
	@Override
	public List<Category> getCategoryByApp(int appId) {
		List<Category> ListCategorys = new ArrayList<Category>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblApp_category+" a INNER JOIN "+tblCategory+" c ON a."+atrCategory_id+"=c."+atrId+" WHERE a."+atrApp_id+"="+appId;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Category category = new Category();
					category.setId(rs.getInt(atrId));
					category.setName(rs.getString(atrName));
					category.setDescription(rs.getString(atrCategory_description));
					
					ListCategorys.add(category);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListCategorys;
	}

	@Override
	public int add(App_category app_category) {
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblApp_category +" ("+atrApp_id+","+atrCategory_id+") VALUES("
						+app_category.getApp_id()+","
						+app_category.getCategory_id()+")";
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
	public boolean save(App_category app_category, int idCategoryOld) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblApp_category +" SET "+atrApp_id+"="+app_category.getApp_id()+","
												+atrCategory_id+"="+app_category.getCategory_id()
												+" WHERE "+atrApp_id+"="+app_category.getApp_id()+" AND "+atrCategory_id+"="+idCategoryOld;	
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
	public void setConnection(Connection conn) {
		
		this.conn = conn;
	}

	@Override
	public boolean delete(App_category app_category) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM "+tblApp_category+" WHERE "+atrApp_id+"="+app_category.getApp_id()+" AND "+atrCategory_id+"="+app_category.getCategory_id();
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
				String sql = "DELETE FROM "+tblApp_category+" WHERE "+atrApp_id+"="+app;
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
	public List<App> getAppByCategory(int category) {
		
		List<App> ListApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblApp_category+" a INNER JOIN "+tblApp+" app ON a."+atrApp_id+"=app."+atrAppId+" WHERE a."+atrCategory_id+"="+category;
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
					app.setVisitas(rs.getInt(atrAppVisitas));
					
					ListApps.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListApps;
	}

	@Override
	public List<App> getAppByCategory(int category, String country) {
		List<App> ListApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblApp_category+" a INNER JOIN "+tblApp+" app ON a."+atrApp_id+"=app."+atrAppId+" WHERE a."+atrCategory_id+"="+category+" AND app."+atrAppCountry+"="+country;
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
					app.setVisitas(rs.getInt(atrAppVisitas));
					
					ListApps.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListApps;
	}

}
