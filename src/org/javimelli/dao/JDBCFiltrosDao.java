package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.Dataset;
import org.javimelli.model.Filtro;
import org.javimelli.model.FiltroDataset;
import org.javimelli.model.Order;



public class JDBCFiltrosDao  implements FiltrosDao{

	private Connection conn;
	
	//CONSTANTES TABLAS
	private static final String tblApp = "app";
	private static final String tblApp_category = "app_category";
	private static final String tblVote_comment = "vote_comment";
	
	//CONSTANTES ATRIBUTOS TABLA
	private static final String atrId = "id";
	private static final String atrUser_id = "user_id";
	private static final String atrUrl_web = "url_web";
	private static final String atrTitle = "title";
	private static final String atrDescription = "description";
	private static final String atrUrl_icon = "url_icon";
	private static final String atrPrice = "price";
	private static final String atrVersion = "version";
	private static final String atrUrl_video = "url_video";
	private static final String atrLanguage = "language";
	private static final String atrCountry = "country";
	private static final String atrId_fotos = "id_fotos";
	private static final String atrDate = "date";
	private static final String atrTime = "time";
	private static final String atrVisitas = "visits";
	
	//CONSTANTES DE TABLA APP_CATEGORY
	private static final String atrtblApp_categoryApp_id = "app_id";
	private static final String atrtblApp_categoryCategory_id = "category_id";
	private static final String atrtblApp_categoryCategory_description = "description";
	
	//CONSTANTES DE TABLE VOTE_COMMENT
	private static final String atrvote_commentUser_id = "User_id";
	private static final String atrvote_commentCommentary_id = "Commentary_id";
	private static final String atrvote_commentValue = "value";
	private static final String atrvote_commentComplaint = "complaint";
	
	//-------------------------Filtros Datasets---------------------------------------------
	
	//CONSTANTES DE TABLA
	private static final String tblDataset = "dataset";
	private static final String tblInstitution = "institution";
		
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrDatasetId = "id";
	private static final String atrDatasetCategory_id = "category_id";
	private static final String atrDatasetUser_id = "user_id";
	private static final String atrDatasetDescription = "description";
	private static final String atrDatasetUri_dataset = "uri_dataset";
	private static final String atrDatasetInstitution = "institution_id";
	private static final String atrDatasetTitle = "title";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrInstitutionId = "id";
	private static final String atrInstitutionName = "name";
	private static final String atrInstitutionCountry = "country";
	private static final String atrInstitutionCity = "city";
	private static final String atrInstitutionProvince = "province";
	
	
	
	
	@Override
	public List<App> filtrosApp(Filtro filtros) {
		
		List<App> apps = new ArrayList<App>();
		
		String atributos = "SELECT *";
		String tables = " FROM " + tblApp + " a ";
		String where = "";
		String orderBy = "";
		String limit = "LIMIT " + filtros.getInit() + ", " + filtros.getNum();
		
		boolean hayWhere = false;
		
		//Recorremos los filtros
		if(filtros.getCategoria() != 9999){
			tables += "INNER JOIN "+tblApp_category+" appc ON a."+atrId+"=appc."+atrtblApp_categoryApp_id+" ";
			where += "WHERE appc."+atrtblApp_categoryCategory_id+"="+filtros.getCategoria()+" ";
			hayWhere = true;
		}

		if(!filtros.getPais().equals("")){
			if(hayWhere)
				where += "AND a.country='"+filtros.getPais()+"' ";
			else
				where += "WHERE a.country='"+filtros.getPais()+"' ";
		}

		//Recorremos los orders
		if(filtros.isVistas()){
			orderBy = "ORDER BY visits "+filtros.getOrder()+" ";
		}

		if(filtros.isPrecio()){
			orderBy = "ORDER BY price "+filtros.getOrder()+" ";
		}
		if(filtros.isMedia()){
			atributos += ", (SELECT AVG("+atrvote_commentValue+") FROM vote_app WHERE app_id=a.id) as average";
			orderBy += "ORDER BY average "+filtros.getOrder()+" ";
		}
		
		String sql = null;
		if(hayWhere)
			sql = atributos + tables + where + orderBy;
		else
			sql = atributos + tables + where + orderBy + limit;
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				//String sql = "SELECT * FROM "+tblApp+ " LIMIT " + init + ", " + numRegs;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					App app = new App();
					app.setId(rs.getInt(atrId));
					app.setUser_id(rs.getInt(atrUser_id));
					app.setUrl_web(rs.getString(atrUrl_web));
					app.setTitle(rs.getString(atrTitle));
					app.setDescription(rs.getString(atrDescription));
					app.setUrl_icon(rs.getString(atrUrl_icon));
					app.setPrice(rs.getInt(atrPrice));
					app.setVersion(rs.getInt(atrVersion));
					app.setUrl_video(rs.getString(atrUrl_video));
					app.setLanguage(rs.getString(atrLanguage));
					app.setCountry(rs.getString(atrCountry));
					app.setId_fotos(rs.getString(atrId_fotos));
					app.setTime(rs.getString(atrTime));
					app.setDate(rs.getString(atrDate));
					app.setVisitas(rs.getInt(atrVisitas));
					
					apps.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return apps;
	}

	@Override
	public List<App> BusqApp(String busq) {
		
		List<App> listApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblApp+" WHERE MATCH("+atrTitle+","+atrDescription+") AGAINST('"+busq+"')"; 
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					App app = new App();
					app.setId(rs.getInt(atrId));
					app.setUser_id(rs.getInt(atrUser_id));
					app.setUrl_web(rs.getString(atrUrl_web));
					app.setTitle(rs.getString(atrTitle));
					app.setDescription(rs.getString(atrDescription));
					app.setUrl_icon(rs.getString(atrUrl_icon));
					app.setPrice(rs.getInt(atrPrice));
					app.setVersion(rs.getInt(atrVersion));
					app.setUrl_video(rs.getString(atrUrl_video));
					app.setLanguage(rs.getString(atrLanguage));
					app.setCountry(rs.getString(atrCountry));
					app.setId_fotos(rs.getString(atrId_fotos));
					app.setTime(rs.getString(atrTime));
					app.setDate(rs.getString(atrDate));
					app.setVisitas(rs.getInt(atrVisitas));
					
					listApps.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return listApps;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Dataset> filtrosDataset(FiltroDataset filtros) {
		
		if (conn == null) return null;
		
		List<Dataset> datasets = new ArrayList<Dataset>();
		
		String atributos = "SELECT *";
		String tables = " FROM " + tblDataset + " d ";
		String where = "";
		
		boolean hayWhere = false;
		boolean hayJoin = false;
		
		if(filtros.getCategoria() != 9999){
			where += "WHERE d." + atrDatasetCategory_id +"="+filtros.getCategoria();
			hayWhere = true;
		}
		
		if(!filtros.getCiudad().equals("")){
			tables += " INNER JOIN " + tblInstitution + " i ON d." + atrDatasetInstitution + "=i." + atrInstitutionId + " ";
			hayJoin = true;
			if(hayWhere){
				where += " AND i." + atrInstitutionCity +" = '" + filtros.getCiudad() + "'";
			}else{
				where += "WHERE i." + atrInstitutionCity +" = '" + filtros.getCiudad() + "'";
			}
			hayWhere = true;
		}
		
		if(!filtros.getPais().equals("")){
			if(!hayJoin){
				hayJoin = true;
				tables += " INNER JOIN " + tblInstitution + " i ON d." + atrDatasetInstitution + "=i." + atrInstitutionId + " ";
			}
			if(hayWhere){
				where += " AND i." + atrInstitutionCountry + " = '" + filtros.getPais() + "'"; 
			}else{
				where += "WHERE i." + atrInstitutionCountry + " = '" + filtros.getPais() + "'"; 
			}
		}
		
		String sql = atributos + tables + where;
		System.out.println(sql);
		
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Dataset dataset = new Dataset();
				dataset.setId(rs.getInt(atrDatasetId));
				dataset.setCategory_id(rs.getInt(atrDatasetCategory_id));
				dataset.setUser_id(rs.getInt(atrDatasetUser_id));
				dataset.setDescription(rs.getString(atrDatasetDescription));
				dataset.setUri_dataset(rs.getString(atrDatasetUri_dataset));
				dataset.setInstitution_id(rs.getInt(atrDatasetInstitution));
				dataset.setTitle(rs.getString(atrDatasetTitle));
		
				datasets.add(dataset);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return datasets;

	}

	@Override
	public List<Dataset> BusqDataset(String busq) {
		
		if (conn == null) return null;
		
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblDataset+" WHERE MATCH("+atrTitle+","+atrDescription+") AGAINST('"+busq+"')"; 
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Dataset dataset = new Dataset();
				dataset.setId(rs.getInt(atrDatasetId));
				dataset.setCategory_id(rs.getInt(atrDatasetCategory_id));
				dataset.setUser_id(rs.getInt(atrDatasetUser_id));
				dataset.setDescription(rs.getString(atrDatasetDescription));
				dataset.setUri_dataset(rs.getString(atrDatasetUri_dataset));
				dataset.setInstitution_id(rs.getInt(atrDatasetInstitution));
				dataset.setTitle(rs.getString(atrDatasetTitle));
		
				datasets.add(dataset);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasets;
	}

}
