package org.javimelli.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.javimelli.model.Vote_comment;

public class JDBCVote_commentDao implements Vote_commentDao{

	private Connection conn;
	
	//CONSTANTES TABLAS
	private static final String tblVote_comment = "vote_comment";
	//CONSTANTE ATRIBUTOS DE TABLA
	private static final String atrUser_id = "User_id";
	private static final String atrCommentary_id = "Commentary_id";
	private static final String atrValue = "value";
	private static final String atrComplaint = "complaint";
	
	@Override
	public List<Vote_comment> getByUser_id(int user) {
		List<Vote_comment> listVotes = new ArrayList<Vote_comment>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblVote_comment+" WHERE "+atrUser_id+"="+user;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Vote_comment vote = new Vote_comment();
					vote.setUser_id(rs.getInt(atrUser_id));
					vote.setCommentary_id(rs.getInt(atrCommentary_id));
					vote.setValue(rs.getBoolean(atrValue));
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
	public List<Vote_comment> getByCommentary_id(int commentary) {
		List<Vote_comment> listVotes = new ArrayList<Vote_comment>();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblVote_comment+" WHERE "+atrCommentary_id+"="+commentary;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					Vote_comment vote = new Vote_comment();
					vote.setUser_id(rs.getInt(atrUser_id));
					vote.setCommentary_id(rs.getInt(atrCommentary_id));
					vote.setValue(rs.getBoolean(atrValue));
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
	public Vote_comment getByUserAndCommentary(int user, int comment){
		Vote_comment vote = new Vote_comment();
		
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "SELECT * FROM "+tblVote_comment+" WHERE "+atrCommentary_id+"="+comment+" AND "+atrUser_id+"="+user;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					vote.setUser_id(rs.getInt(atrUser_id));
					vote.setCommentary_id(rs.getInt(atrCommentary_id));
					vote.setValue(rs.getBoolean(atrValue));
					vote.setComplaint(rs.getBoolean(atrComplaint));
									}
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return vote;
	}

	@Override
	public int numComplaint(int comment) {
		int result = 0;
		if(conn != null){
			Statement stmt;
			try{
				stmt = conn.createStatement();
				String sql ="SELECT COUNT(*) as total FROM "+tblVote_comment+" WHERE "+atrComplaint+"=true AND "+atrCommentary_id+"="+comment;
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
	public int add(Vote_comment vote) {
		int id=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				String sql = "INSERT INTO "+tblVote_comment +" ("+atrUser_id+","+atrCommentary_id+","+atrValue+","+atrComplaint+") VALUES("
						+vote.getUser_id()+","
						+vote.getCommentary_id()+","
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
	public boolean save(Vote_comment vote) {
		
		boolean save = false;
		if(conn != null){
			Statement stmt;
			
			try{
				stmt = conn.createStatement();
				String sql = "UPDATE "+ tblVote_comment +" SET "+atrUser_id+"="+vote.getUser_id()+","
												+atrCommentary_id+"="+vote.getCommentary_id()+","
												+atrValue+"="+vote.getValue()+","
												+atrComplaint+"="+vote.isComplaint()
												+" WHERE "+atrUser_id+"="+vote.getUser_id()+" AND "+atrCommentary_id+"="+vote.getCommentary_id();
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
	public void setConnection(Connection conn){
		this.conn = conn;
	}

	@Override
	public int numVotePositeve(int comment) {
		int result = 0;
		if(conn != null){
			Statement stmt;
			try{
				stmt = conn.createStatement();
				String sql ="SELECT COUNT(*) as total FROM "+tblVote_comment+" WHERE "+atrValue+"=true AND "+atrCommentary_id+"="+comment;
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
	public int numVoteNegative(int comment) {
		int result = 0;
		if(conn != null){
			Statement stmt;
			try{
				stmt = conn.createStatement();
				String sql ="SELECT COUNT(*) as total FROM "+tblVote_comment+" WHERE "+atrValue+"=false AND "+atrCommentary_id+"="+comment;
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

}
