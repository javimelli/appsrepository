package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Screenshot;

public interface ScreenshotDao {

	public Screenshot getById(int id);
	public List<Screenshot> getByAppId(int id);
	public int add(Screenshot screenshot);
	public boolean delete(int id);
	public void setConnection(Connection conn);
}
