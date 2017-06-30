package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.Dataset;
import org.javimelli.model.Dataset_app;

public class JDBCDataset_appDao implements Dataset_appDao{
	
	//CONSTANTES DE TABLA
	private static final String tblDataset_app = "dataset_app";
	private static final String tblDataset = "dataset";
	private static final String tblApp = "app";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrApp_id = "app_id";
	private static final String atrDataset_id = "dataset_id";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA dataset
	private static final String atrDatasetId = "id";
	private static final String atrDatasetCategory_id = "category_id";
	private static final String atrDatasetUser_id = "user_id";
	private static final String atrDatasetDescription = "description";
	private static final String atrDatasetUri_dataset = "uri_dataset";
	private static final String atrDatasetInstitution = "institution_id";
	private static final String atrDatasetTitle = "title";
	
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
	public List<Dataset> getDatasetByApp(int app) {
		
		List<Dataset> ListDataset = new ArrayList<Dataset>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblDataset_app+" d1 INNER JOIN "+tblDataset+" d2 ON d1."+atrDataset_id+"=d2."+atrDatasetId+" WHERE d1."+atrApp_id+"="+app;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Dataset dataset = new Dataset();
					dataset.setId(rs.getInt(atrDatasetId));
					dataset.setCategory_id(rs.getInt(atrDatasetCategory_id));
					dataset.setUser_id(rs.getInt(atrDatasetUser_id));
					dataset.setDescription(rs.getString(atrDatasetDescription));
					dataset.setUri_dataset(rs.getString(atrDatasetUri_dataset));
					dataset.setInstitution_id(rs.getInt(atrDatasetInstitution));
					dataset.setTitle(rs.getString(atrDatasetTitle));
					
					ListDataset.add(dataset);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListDataset;
	}

	@Override
	public List<App> getAppByDataset(int dataset) {
		
		List<App> ListApp = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//SELECT * FROM app_category a INNER JOIN category c ON a.Category_id=c.id WHERE a.App_id=
				String sql = "SELECT * FROM "+tblDataset_app+" d1 INNER JOIN "+tblApp+" a2 ON d1."+atrApp_id+"=a2."+atrAppId+" WHERE d1."+atrDataset_id+"="+dataset;
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
					
					ListApp.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ListApp;
	}

	@Override
	public int add(Dataset_app dataset_app) {
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblDataset_app +" ("+atrApp_id+","+atrDataset_id+") VALUES("
						+dataset_app.getApp_id()+","
						+dataset_app.getDataset_id()+")";
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
	public boolean delete(Dataset_app dataset_app) {
		
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "DELETE FROM "+tblDataset_app+" WHERE "+atrApp_id+"="+dataset_app.getApp_id()+" AND "+atrDataset_id+"="+dataset_app.getDataset_id();
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
				String sql = "DELETE FROM "+tblDataset_app+" WHERE "+atrApp_id+"="+app;
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
