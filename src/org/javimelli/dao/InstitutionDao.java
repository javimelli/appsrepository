package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Institution;;

public interface InstitutionDao {

	public List<Institution> getAlls();
	public Institution getById(int id);
	public int add(Institution institution);
	public boolean save(Institution institution);
	public boolean delete(int id);
	public void setConnection(Connection conn);
}
