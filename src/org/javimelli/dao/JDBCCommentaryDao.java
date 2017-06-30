package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.javimelli.model.Commentary;

public class JDBCCommentaryDao implements CommentaryDao{
	
	private Connection conn;

	//CONTANTES DE TABLA
	private static final String tblCommentary = "commentary";
	//CONSTANTE DE ATRIBUSTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrUser_id = "user_id";
	private static final String atrApp_id = "app_id";
	private static final String atrDate = "date";
	private static final String atrTime = "time";
	private static final String atrText = "text";
	private static final String atrId_padre = "id_padre";
	
	@Override
	public List<Commentary> getAll() {
		
		if (conn == null) return null;
		
		ArrayList<Commentary> commentarys = new ArrayList<Commentary>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblCommentary;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Commentary commentary = new Commentary();
				commentary.setId(rs.getInt(atrId));
				commentary.setUser_id(rs.getInt(atrUser_id));
				commentary.setApp_id(rs.getInt(atrApp_id));
				commentary.setDate(rs.getString(atrDate));
				commentary.setTime(rs.getString(atrTime));
				commentary.setText(rs.getString(atrText));
				commentary.setId_padre(rs.getInt(atrId_padre));
		
				commentarys.add(commentary);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentarys;
	}

	@Override
	public List<Commentary> getAllByOwner(int owner) {
		
		if (conn == null) return null;
		
		ArrayList<Commentary> commentarys = new ArrayList<Commentary>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblCommentary+" WHERE "+atrUser_id+"="+owner;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Commentary commentary = new Commentary();
				commentary.setId(rs.getInt(atrId));
				commentary.setUser_id(rs.getInt(atrUser_id));
				commentary.setApp_id(rs.getInt(atrApp_id));
				commentary.setDate(rs.getString(atrDate));
				commentary.setTime(rs.getString(atrTime));
				commentary.setText(rs.getString(atrText));
				commentary.setId_padre(rs.getInt(atrId_padre));
		
				commentarys.add(commentary);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentarys;
	}

	@Override
	public List<Commentary> getAllByApps(int app) {
		
if (conn == null) return null;
		
		ArrayList<Commentary> commentarys = new ArrayList<Commentary>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblCommentary+" WHERE "+atrApp_id+"="+app;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Commentary commentary = new Commentary();
				commentary.setId(rs.getInt(atrId));
				commentary.setUser_id(rs.getInt(atrUser_id));
				commentary.setApp_id(rs.getInt(atrApp_id));
				commentary.setDate(rs.getString(atrDate));
				commentary.setTime(rs.getString(atrTime));
				commentary.setText(rs.getString(atrText));
				commentary.setId_padre(rs.getInt(atrId_padre));
		
				commentarys.add(commentary);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentarys;
	}

	@Override
	public Commentary get(int id) {
		
		Commentary commentary = new Commentary();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblCommentary+" WHERE "+atrId+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					commentary.setId(rs.getInt(atrId));
					commentary.setUser_id(rs.getInt(atrUser_id));
					commentary.setApp_id(rs.getInt(atrApp_id));
					commentary.setDate(rs.getString(atrDate));
					commentary.setTime(rs.getString(atrTime));
					commentary.setText(rs.getString(atrText));
					commentary.setId_padre(rs.getInt(atrId_padre));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return commentary;
	}

	@Override
	public int add(Commentary commentary) {
		
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
				String sql = "INSERT INTO "+tblCommentary +" ("+atrId+","+atrId_padre+","+atrUser_id+","+atrApp_id+","+atrDate+","+atrTime+","+atrText+") VALUES("
						+commentary.getId()+","
						+commentary.getId_padre()+","
						+commentary.getUser_id()+","
						+commentary.getApp_id()+",'"
						+dateFormat.format(date)+"','"
						+timeFormat.format(date)+"','"
						+commentary.getText()+"')";
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
	public boolean save(Commentary commentary) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblCommentary +" SET "+atrId+"="+commentary.getId()+","
												+atrId_padre+"="+commentary.getId_padre()+","
												+atrUser_id+"="+commentary.getUser_id()+","
												+atrApp_id+"="+commentary.getApp_id()+","
												+atrText+"='"+commentary.getText()+"'"
												+" WHERE "+atrId+"="+commentary.getId();
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
				String sql = "DELETE FROM "+tblCommentary+" WHERE "+atrId+"="+id;
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
