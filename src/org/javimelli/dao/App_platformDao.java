package org.javimelli.dao;
import org.javimelli.model.App;

import java.sql.Connection;
import java.util.List;
import org.javimelli.model.Platform;
import org.javimelli.model.App_platform;

public interface App_platformDao {

	public List<App> getAppsByplatform(int id);
	public List<Platform> getPlatformsByApps(int id);
	public int add(App_platform app_platform);
	public boolean delete(App_platform app_platform);
	public void setConnection(Connection conn);
}
