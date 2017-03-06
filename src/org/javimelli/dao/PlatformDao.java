package org.javimelli.dao;

import org.javimelli.model.Platform;
import java.util.List;
import java.sql.Connection;

public interface PlatformDao {

	public List<Platform> getAlls();
	public Platform getById(int id);
	public void setConnection(Connection conn);
}
