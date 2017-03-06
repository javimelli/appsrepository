package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Vote_app;

public interface Vote_appDao {
	
	public List<Vote_app> getByUser_id(int user);
	public List<Vote_app> getByApp_id(int app);
	public Vote_app getByUserAndApp(int user, int app);
	public int numComplaint(int app);
	public int AverageVotes(int app);
	public int add(Vote_app vote);
	public boolean save(Vote_app vote);
	public void setConnection(Connection conn);
}
