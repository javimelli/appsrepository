package org.javimelli.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.dao.Vote_appDao;
import org.javimelli.model.Vote_app;

public class JDBCVote_appDao implements Vote_appDao{

	private Connection conn;
	
	//CONSTANTES TABLA
	private static final String tblVote_app = "vote_app";
	
	//CONSTANTES ATRIBUTOS DE TABLA
	private static final String atrUser_id = "user_id";
	private static final String atrApp_id = "app_id";
	private static final String atrValue = "value";
	private static final String atrComplaint = "complaint";
	
	@Override
	public List<Vote_app> getByUser_id(int user) {
		
		List<Vote_app> listVotes = new ArrayList<Vote_app>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblVote_app+" WHERE "+atrUser_id+"="+user;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Vote_app vote = new Vote_app();
					vote.setUser_id(rs.getInt(atrUser_id));
					vote.setApp_id(rs.getInt(atrApp_id));
					vote.setValue(rs.getInt(atrValue));
					vote.setComplaint(rs.getBoolean(atrComplaint));
					
					listVotes.add(vote);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return listVotes;
	}

	@Override
	public List<Vote_app> getByApp_id(int app) {
		
		List<Vote_app> listVotes = new ArrayList<Vote_app>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblVote_app+" WHERE "+atrApp_id+"="+app;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Vote_app vote = new Vote_app();
					vote.setUser_id(rs.getInt(atrUser_id));
					vote.setApp_id(rs.getInt(atrApp_id));
					vote.setValue(rs.getInt(atrValue));
					vote.setComplaint(rs.getBoolean(atrComplaint));
					
					listVotes.add(vote);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return listVotes;
	}

	@Override
	public Vote_app getByUserAndApp(int user, int app) {
		
		Vote_app vote = new Vote_app();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblVote_app+" WHERE "+atrApp_id+"="+app+" AND "+atrUser_id+"="+user;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					vote.setUser_id(rs.getInt(atrUser_id));
					vote.setApp_id(rs.getInt(atrApp_id));
					vote.setValue(rs.getInt(atrValue));
					vote.setComplaint(rs.getBoolean(atrComplaint));
					
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return vote;
	}

	@Override
	public int numComplaint(int app) {
		int result = 0;
		if(conn != null){
			Statement stmt;
			try{
				stmt = conn.createStatement();
				String sql ="SELECT COUNT(*) as total FROM "+tblVote_app+" WHERE "+atrComplaint+"=true AND "+atrApp_id+"="+app;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					result = rs.getInt("total");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int AverageVotes(int app) {
		int result = 0;
		if(conn != null){
			Statement stmt;
			try{
				stmt = conn.createStatement();
				String sql ="SELECT AVG(value) as average FROM "+tblVote_app+" WHERE "+atrApp_id+"="+app;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					result = rs.getInt("average");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int add(Vote_app vote) {
		
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblVote_app +" ("+atrUser_id+","+atrApp_id+","+atrValue+","+atrComplaint+") VALUES("
						+vote.getUser_id()+","
						+vote.getApp_id()+","
						+vote.getValue()+","
						+vote.isComplaint()+")";
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
	public boolean save(Vote_app vote) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblVote_app +" SET "+atrUser_id+"="+vote.getUser_id()+","
												+atrApp_id+"="+vote.getApp_id()+","
												+atrValue+"="+vote.getValue()+","
												+atrComplaint+"="+vote.isComplaint()
												+" WHERE "+atrUser_id+"="+vote.getUser_id()+" AND "+atrApp_id+"="+vote.getApp_id();
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
	public void setConnection(Connection conn) {
		
		this.conn = conn;
	}

}
