package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.Dataset;
import org.javimelli.model.Dataset_app;

public interface Dataset_appDao {

	public List<Dataset> getDatasetByApp(int app);
	public List<App> getAppByDataset(int dataset);
	public int add(Dataset_app dataset_app);
	public boolean delete(Dataset_app dataset_app);
	public void setConnection(Connection conn);
}
