package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

import org.javimelli.model.User;

public class JDBCUserDao  implements UserDao{

	private Connection conn;
	//private static final Logger logger = Logger.getLogger(JDBCUserDao.class.getName());
	
	//CONSTANTES DE TABLAS
	private static final String tblUser = "user";
	//CONSTANTES DE ATRIBUTOS
	private static final String atrId = "id";
	private static final String atrName = "name";
	private static final String atrLast_name1 = "last_name1";
	private static final String atrLast_name2 = "last_name2";
	private static final String atrUsername = "username";
	private static final String atrTlf = "tlf";
	private static final String atrUrl_web = "url_web";
	private static final String atrEmail = "email";
	private static final String atrCountry = "country";
	private static final String atrUrl_foto = "url_foto";
	private static final String atrPassword = "password";
	
	
	@Override
	public List<User> getUsersAll() {
		
		if (conn == null) return null;
		
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement stmt;
			ResultSet rs;
			synchronized(conn){
			  stmt = conn.createStatement();
			  String sql = "select * from "+tblUser;
			  System.out.println(sql);
			  rs = stmt.executeQuery(sql);
			}
			while ( rs.next() ) {
				User user = new User();
				user.setId(rs.getInt(atrId));
				user.setName(rs.getString(atrName));
				user.setLast_name1(rs.getString(atrLast_name1));
				user.setLast_name2(rs.getString(atrLast_name2));
				user.setUsername(rs.getString(atrUsername));
				user.setTlf(rs.getString(atrTlf));
				user.setUrl_web(rs.getString(atrUrl_web));
				user.setEmail(rs.getString(atrEmail));
				user.setCountry(rs.getString(atrCountry));
				user.setUrl_foto(rs.getString(atrUrl_foto));
				user.setPassword(rs.getString(atrPassword));
				
				
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
				String sql = "INSERT INTO "+tblUser +" ("+atrName+","+atrLast_name1+","+atrLast_name2+","+atrUsername+","+atrTlf+","+atrUrl_web+","+atrEmail+","+atrCountry+","+atrUrl_foto+","+atrPassword+") VALUES('"
						+user.getName()+"','"
						+user.getLast_name1()+"','"
						+user.getLast_name2()+"','"
						+user.getUsername()+"','"
						+user.getTlf()+"','"
						+user.getUrl_web()+"','"
						+user.getEmail()+"','"
						+user.getCountry()+"','"
						+user.getUrl_foto()+"','"
						+user.getPassword()+"')";
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
	public User get(int id) {
		
		User user = new User();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblUser+" WHERE id="+id;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					user.setId(rs.getInt(atrId));
					user.setName(rs.getString(atrName));
					user.setLast_name1(rs.getString(atrLast_name1));
					user.setLast_name2(rs.getString(atrLast_name2));
					user.setUsername(rs.getString(atrUsername));
					user.setTlf(rs.getString(atrTlf));
					user.setUrl_web(rs.getString(atrUrl_web));
					user.setEmail(rs.getString(atrEmail));
					user.setCountry(rs.getString(atrCountry));
					user.setUrl_foto(rs.getString(atrUrl_foto));
					user.setPassword(rs.getString(atrPassword));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return user;
	}

	@Override
	public User get(String name) {
		
		User user = new User();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblUser+" WHERE username='"+name+"'";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					user.setId(rs.getInt(atrId));
					user.setName(rs.getString(atrName));
					user.setLast_name1(rs.getString(atrLast_name1));
					user.setLast_name2(rs.getString(atrLast_name2));
					user.setUsername(rs.getString(atrUsername));
					user.setTlf(rs.getString(atrTlf));
					user.setUrl_web(rs.getString(atrUrl_web));
					user.setEmail(rs.getString(atrEmail));
					user.setCountry(rs.getString(atrCountry));
					user.setUrl_foto(rs.getString(atrUrl_foto));
					user.setPassword(rs.getString(atrPassword));
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean save(User user) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE user SET "+atrId+"="+user.getId()+","
												+atrName+"='"+user.getName()+"',"
												+atrLast_name1+"='"+user.getLast_name1()+"',"
												+atrLast_name2+"='"+user.getLast_name2()+"',"
												+atrUsername+"='"+user.getUsername()+"',"
												+atrTlf+"='"+user.getTlf()+"',"
												+atrUrl_web+"='"+user.getUrl_web()+"',"
												+atrEmail+"='"+user.getEmail()+"',"
												+atrCountry+"='"+user.getCountry()+"',"
												+atrUrl_foto+"='"+user.getUrl_foto()+"',"
												+atrPassword+"='"+user.getPassword()+"'"
												+" WHERE id="+user.getId();
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
				String sql = "DELETE FROM user WHERE id ="+id;
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
