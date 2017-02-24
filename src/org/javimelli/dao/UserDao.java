package org.javimelli.dao;

import org.javimelli.model.User;
import java.sql.Connection;
import java.util.List;

public interface UserDao {

	public User get(int id);
	public User get(String name);
	public List<User> getUsersAll();
	public int addUser(User user);
	public boolean save(User user);
	public boolean delete(int id);
	public void setConnection(Connection conn);
}
