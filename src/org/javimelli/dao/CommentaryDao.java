package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Commentary;

public interface CommentaryDao {

	public List<Commentary> getAll();
	public List<Commentary>  getAllByOwner(int owner);
	public List<Commentary> getAllByApps(int app);
	public Commentary get(int id);	
	public int add(Commentary commentary);
	public boolean save(Commentary commentary);
	public boolean delete(int id);
	public void setConnection(Connection conn);
}
