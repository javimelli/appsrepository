package org.javimelli.dao;

import org.javimelli.model.User;
import java.sql.Connection;
import java.util.List;

public interface UserDao {

	public List<User> getUsersAll();
	public void setConnection(Connection conn);
}
