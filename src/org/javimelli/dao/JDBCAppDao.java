package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.javimelli.model.App;

public class JDBCAppDao implements AppDao{

	private Connection conn;
	
	//CONSTANTES TABLAS
	private static final String tblApp = "app";
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
	
	@Override
	public List<App> getAppsAll() {
		
		if (conn == null) return null;
		
		ArrayList<App> apps = new ArrayList<App>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblApp;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apps;
	}

	@Override
	public List<App> getAllByOwner(int owner) {
		
		List<App> listApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblApp+" WHERE "+atrUser_id+"="+owner;
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
	public App getById(int id) {
		
		App app = new App();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblApp+" WHERE "+atrId+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
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
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return app;
	}

	@Override
	public int addApp(App app) {
		
		int id=-1;
		if (conn != null){
			
			//CREACIÓN DE LA HORA Y LA FECHA
			Date date = new Date();
	    	System.out.println(date.getTime());
	    	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	System.out.println("Hora: "+timeFormat.format(date));
	    	System.out.println("Fecha: "+dateFormat.format(date));

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblApp +" ("+atrId+","+atrUser_id+","+atrUrl_web+","+atrTitle+","+atrDescription+","+atrUrl_icon+","+atrPrice+","+atrVersion+","+atrUrl_video+","+atrLanguage+","+atrCountry+","+atrId_fotos+","+atrTime+","+atrDate+","+atrVisitas+") VALUES("
						+app.getId()+","
						+app.getUser_id()+",'"
						+app.getUrl_web()+"','"
						+app.getTitle()+"','"
						+app.getDescription()+"','"
						+app.getUrl_icon()+"',"
						+app.getPrice()+","
						+app.getVersion()+",'"
						+app.getUrl_video()+"','"
						+app.getLanguage()+"','"
						+app.getCountry()+"','"
						+app.getId_fotos()+"','"
						+timeFormat.format(date)+"','"
						+dateFormat.format(date)+"',"
						+app.getVisitas()+")";
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
	public boolean save(App app) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblApp +" SET "+atrId+"="+app.getId()+","
												+atrUser_id+"="+app.getUser_id()+","
												+atrUrl_web+"='"+app.getUrl_web()+"',"
												+atrTitle+"='"+app.getTitle()+"',"
												+atrDescription+"='"+app.getDescription()+"',"
												+atrUrl_icon+"='"+app.getUrl_icon()+"',"
												+atrPrice+"="+app.getPrice()+","
												+atrVersion+"="+app.getVersion()+","
												+atrUrl_video+"='"+app.getUrl_video()+"',"
												+atrLanguage+"='"+app.getLanguage()+"',"
												+atrCountry+"='"+app.getCountry()+"',"
												+atrVisitas+"="+app.getVisitas()
												+" WHERE "+atrId+"="+app.getId();
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
				String sql = "DELETE FROM "+tblApp+" WHERE "+atrId+"="+id;
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
	public List<App> getAppsByLmit(int numRegs, int init) {
List<App> listApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblApp+ " LIMIT " + init + ", " + numRegs;
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
	public List<App> getAppsByCountry(String country) {
List<App> listApps = new ArrayList<App>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblApp+" WHERE "+atrCountry+"= '"+country+"'";
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
					
					listApps.add(app);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return listApps;
	}

}
