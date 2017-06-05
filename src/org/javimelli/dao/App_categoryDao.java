package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.App_category;
import org.javimelli.model.Category;

public interface App_categoryDao {

	public List<Category> getCategoryByApp(int appId);
	public List<App> getAppByCategory(int category);
	public List<App> getAppByCategory(int category, String country);
	public int add(App_category app_category);
	public boolean save(App_category app_category, int idCategoryOld);
	public boolean delete(App_category app_category);
	public boolean delete(int app);
	public void setConnection(Connection conn);
}
