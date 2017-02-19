package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.javimelli.model.User;

public class JDBCUserDao  implements UserDao{

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCUserDao.class.getName());
	
	@Override
	public List<User> getUsersAll() {
		
		if (conn == null) return null;
		
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  rs = stmt.executeQuery("select * from user");
			}
			while ( rs.next() ) {
				User user = new User();
				user.setId_user(rs.getInt("id"));
				user.setNombre(rs.getString("name"));
				user.setApellido1(rs.getString("Last_name1"));
				user.setApellido2(rs.getString("Last_name2"));
				user.setNombre_desarrollador(rs.getString("username"));
				user.setTelefono(rs.getString("telefono"));
				user.setUrl_web(rs.getString("url_web"));
				user.setEmail(rs.getString("email"));
				user.setPais(rs.getString("country"));
				user.setUrl_foto(rs.getString("url_foto"));
				user.setPassword(rs.getString("password"));
				
				
				users.add(user);
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
