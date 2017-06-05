package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;
import org.javimelli.model.App;

public interface AppDao {

	public List<App> getAppsAll();
	public List<App> getAppsByLmit(int numRegs,int init);
	public List<App> getAppsByCountry(String country);
	public List<App> getAllByOwner(int owner);
	public App getById(int id);	
	public int addApp(App app);
	public boolean save(App app);
	public boolean delete(int id);
	public void setConnection(Connection conn);
}
