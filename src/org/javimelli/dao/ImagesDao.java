package org.javimelli.dao;

import java.sql.Connection;
import java.util.List;

import org.javimelli.model.Images;

public interface ImagesDao {
	public List<Images> getAllImages();
	public List<Images> getById_fotos(String id_fotos);
	public Images getById(int id);
	public int addImages(Images img);
	public boolean save(Images img);
	public boolean delete(int id);
	public void setConnection(Connection conn);
	
}
