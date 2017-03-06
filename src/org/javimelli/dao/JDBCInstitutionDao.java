package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.Institution;

public class JDBCInstitutionDao implements InstitutionDao{

	//CONSTANTES DE TABLAS
	private static final String tblInstitution = "institution";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrName = "name";
	private static final String atrCountry = "country";
	private static final String atrCity = "city";
	private static final String atrProvince = "province";
	
	private Connection conn;
	
	
	@Override
	public List<Institution> getAlls() {
		
		if (conn == null) return null;
		
		ArrayList<Institution> institutions = new ArrayList<Institution>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblInstitution;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Institution institution = new Institution();
				institution.setId(rs.getInt(atrId));
				institution.setName(rs.getString(atrName));
				institution.setCountry(rs.getString(atrCountry));
				institution.setCity(rs.getString(atrCity));
				institution.setProvince(rs.getString(atrProvince));
		
				institutions.add(institution);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return institutions;
	}

	@Override
	public Institution getById(int id) {
		Institution institution = new Institution();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblInstitution+" WHERE id="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					institution.setId(rs.getInt(atrId));
					institution.setName(rs.getString(atrName));
					institution.setCountry(rs.getString(atrCountry));
					institution.setCity(rs.getString(atrCity));
					institution.setProvince(rs.getString(atrProvince));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return institution;
	}

	@Override
	public int add(Institution institution) {
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblInstitution +" ("+atrId+","+atrName+","+atrCountry+","+atrCity+","+atrProvince+") VALUES("
						+institution.getId()+",'"
						+institution.getName()+"','"
						+institution.getCountry()+"','"
						+institution.getCity()+"','"
						+institution.getProvince()+"')";
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
	public boolean save(Institution institution) {
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblInstitution +" SET "+atrId+"="+institution.getId()+","
												+atrName+"='"+institution.getName()+"',"
												+atrCountry+"='"+institution.getCountry()+"',"
												+atrCity+"='"+institution.getCity()+"',"
												+atrProvince+"='"+institution.getProvince()+"'"
												+" WHERE id="+institution.getId();
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
				String sql = "DELETE FROM "+tblInstitution+" WHERE id="+id;
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
