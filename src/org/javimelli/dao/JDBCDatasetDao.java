package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.Dataset;

public class JDBCDatasetDao implements DatasetDao{

	//CONSTANTES DE TABLA
	private static final String tblDataset = "dataset";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrCategory_id = "category_id";
	private static final String atrUser_id = "user_id";
	private static final String atrDescription = "description";
	private static final String atrUri_dataset = "uri_dataset";
	private static final String atrInstitution = "institution_id";
	
	private Connection conn;
	
	@Override
	public List<Dataset> getAlls() {
		
		if (conn == null) return null;
		
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblDataset;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Dataset dataset = new Dataset();
				dataset.setId(rs.getInt(atrId));
				dataset.setCategory_id(rs.getInt(atrCategory_id));
				dataset.setUser_id(rs.getInt(atrUser_id));
				dataset.setDescription(rs.getString(atrDescription));
				dataset.setUri_dataset(rs.getString(atrUri_dataset));
				dataset.setInstitution_id(rs.getInt(atrInstitution));
		
				datasets.add(dataset);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasets;
	}

	@Override
	public Dataset getById(int id) {
		
		Dataset dataset = new Dataset();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblDataset+" WHERE "+atrId+"="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					dataset.setId(rs.getInt(atrId));
					dataset.setCategory_id(rs.getInt(atrCategory_id));
					dataset.setUser_id(rs.getInt(atrUser_id));
					dataset.setDescription(rs.getString(atrDescription));
					dataset.setUri_dataset(rs.getString(atrUri_dataset));
					dataset.setInstitution_id(rs.getInt(atrInstitution));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return dataset;
	}

	@Override
	public List<Dataset> getByUserId(int id) {
		
		if (conn == null) return null;
		
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblDataset+" WHERE "+atrUser_id+"="+id;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Dataset dataset = new Dataset();
				dataset.setId(rs.getInt(atrId));
				dataset.setCategory_id(rs.getInt(atrCategory_id));
				dataset.setUser_id(rs.getInt(atrUser_id));
				dataset.setDescription(rs.getString(atrDescription));
				dataset.setUri_dataset(rs.getString(atrUri_dataset));
				dataset.setInstitution_id(rs.getInt(atrInstitution));
		
				datasets.add(dataset);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasets;
	}

	@Override
	public List<Dataset> getByCategoryId(int id) {
		
		if (conn == null) return null;
		
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblDataset+" WHERE "+atrCategory_id+"="+id;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Dataset dataset = new Dataset();
				dataset.setId(rs.getInt(atrId));
				dataset.setCategory_id(rs.getInt(atrCategory_id));
				dataset.setUser_id(rs.getInt(atrUser_id));
				dataset.setDescription(rs.getString(atrDescription));
				dataset.setUri_dataset(rs.getString(atrUri_dataset));
				dataset.setInstitution_id(rs.getInt(atrInstitution));
		
				datasets.add(dataset);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasets;
	}

	@Override
	public List<Dataset> getByInstitutionId(int id) {
		
		if (conn == null) return null;
		
		ArrayList<Dataset> datasets = new ArrayList<Dataset>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblDataset+" WHERE "+atrInstitution+"="+id;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Dataset dataset = new Dataset();
				dataset.setId(rs.getInt(atrId));
				dataset.setCategory_id(rs.getInt(atrCategory_id));
				dataset.setUser_id(rs.getInt(atrUser_id));
				dataset.setDescription(rs.getString(atrDescription));
				dataset.setUri_dataset(rs.getString(atrUri_dataset));
				dataset.setInstitution_id(rs.getInt(atrInstitution));
		
				datasets.add(dataset);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasets;
	}

	@Override
	public int add(Dataset dataset) {
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblDataset +" ("+atrId+","+atrCategory_id+","+atrUser_id+","+atrDescription+","+atrUri_dataset+","+atrInstitution+") VALUES("
						+dataset.getId()+","
						+dataset.getCategory_id()+","
						+dataset.getUser_id()+",'"
						+dataset.getDescription()+"','"
						+dataset.getUri_dataset()+"',"
						+dataset.getInstitution_id()+")";
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
	public boolean save(Dataset dataset) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblDataset +" SET "+atrId+"="+dataset.getId()+","
												+atrCategory_id+"="+dataset.getCategory_id()+","
												+atrUser_id+"="+dataset.getUser_id()+","
												+atrDescription+"='"+dataset.getDescription()+"',"
												+atrUri_dataset+"='"+dataset.getUri_dataset()+"',"
												+atrInstitution+"="+dataset.getInstitution_id()
												+" WHERE "+atrId+"="+dataset.getId();
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
				String sql = "DELETE FROM "+tblDataset+" WHERE "+atrId+"="+id;
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
