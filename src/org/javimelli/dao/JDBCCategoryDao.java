package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.Category;

public class JDBCCategoryDao implements CategoryDao{

	//CONSTANTES DE TABLA
	private static final String tblCategory = "category";
	
	//CONSTANTES DE ATRIBUTOS DE TABLA
	private static final String atrId = "id";
	private static final String atrName = "name";
	private static final String atrDescription = "description";
	
	private Connection conn;
	
	@Override
	public List<Category> getAlls() {
		
		if (conn == null) return null;
		
		ArrayList<Category> categorys = new ArrayList<Category>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblCategory;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				Category category = new Category();
				category.setId(rs.getInt(atrId));
				category.setName(rs.getString(atrName));
				category.setDescription(rs.getString(atrDescription));
		
				categorys.add(category);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorys;
	}

	@Override
	public Category getById(int categoryId) {
		if (conn == null) return null;
		
		Category category = new Category();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblCategory+" WHERE "+atrId+"="+categoryId;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			if ( rs.next() ) {
				category.setId(rs.getInt(atrId));
				category.setName(rs.getString(atrName));
				category.setDescription(rs.getString(atrDescription));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public void setConnection(Connection conn) {
		
		this.conn = conn;
	}

	
}
