package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Dataset;

public interface DatasetDao {

	public List<Dataset> getAlls();
	public Dataset getById(int id);
	public List<Dataset> getByUserId(int id);
	public List<Dataset> getByCategoryId(int id);
	public List<Dataset> getByInstitutionId(int id);
	public int add(Dataset dataset);
	public boolean save(Dataset dataset);
	public boolean delete(int id);
	public void setConnection(Connection conn);
	
}
