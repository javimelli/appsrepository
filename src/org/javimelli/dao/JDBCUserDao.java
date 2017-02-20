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
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLast_name1(rs.getString("Last_name1"));
				user.setLast_name2(rs.getString("Last_name2"));
				user.setUsername(rs.getString("username"));
				user.setTlf(rs.getString("tlf"));
				user.setUrl_web(rs.getString("url_web"));
				user.setEmail(rs.getString("email"));
				user.setCountry(rs.getString("country"));
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
	public int addUser(User user){
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO User (name,Last_name1,Last_name2,username,tlf,url_web,email,country,url_foto,password) VALUES('"
									+user.getName()+"','"
									+user.getLast_name1()+","
									+user.getLast_name2()+","
									+user.getUsername()+","
									+user.getTlf()+","
									+user.getUrl_web()+","
									+user.getEmail()+"','"
									+user.getCountry()+","
									+user.getUrl_foto()+","
									+user.getPassword()+"')",Statement.RETURN_GENERATED_KEYS);
				
				ResultSet genKeys = stmt.getGeneratedKeys();
				
				if (genKeys.next())
				    id = genKeys.getInt(1);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
