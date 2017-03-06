package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.Platform;

public class JDBCPlatformDao implements PlatformDao{

	//CONSTANTES DE TABLA
	private static final String tblPlatform = "platform";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrName = "name";
	private static final String atrVersion = "version";
	
	private Connection conn;
	
	@Override
	public List<Platform> getAlls() {
		
		if (conn == null) return null;
		
		ArrayList<Platform> platforms = new ArrayList<Platform>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblPlatform;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Platform platform = new Platform();
				platform.setId(rs.getInt(atrId));
				platform.setName(rs.getString(atrName));
				platform.setVersion(rs.getInt(atrVersion));

		
				platforms.add(platform);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return platforms;
	}

	@Override
	public Platform getById(int id) {
		
		if (conn == null) return null;
		
		Platform platform = new Platform();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblPlatform+" WHERE "+atrId+"="+id;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			if ( rs.next() ) {
				platform.setId(rs.getInt(atrId));
				platform.setName(rs.getString(atrName));
				platform.setVersion(rs.getInt(atrVersion));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return platform;
	}

	@Override
	public void setConnection(Connection conn) {
		
		this.conn = conn;
	}

}
