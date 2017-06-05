package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.App;
import org.javimelli.model.Dataset;
import org.javimelli.model.Filtro;
import org.javimelli.model.FiltroDataset;
import org.javimelli.model.Order;

public interface FiltrosDao {
	
	public List<App> filtrosApp(Filtro filtros);
	public List<App> BusqApp(String busq);
	public List<Dataset> filtrosDataset(FiltroDataset filtros);
	public List<Dataset> BusqDataset(String busq);
	public void setConnection(Connection conn);
}
