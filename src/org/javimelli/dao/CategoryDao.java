package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Category;

public interface CategoryDao {

	public List<Category> getAlls();
	public Category getById(int categoryId);
	public void setConnection(Connection conn);
}
