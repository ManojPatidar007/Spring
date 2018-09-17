package netcracker.developer.dao;

import java.sql.SQLException;

public interface LoginDao {
	public boolean isValidUser(String username, String password) throws SQLException;

	

	boolean isAdmin(String username) throws SQLException;



	boolean isActive(String username) throws SQLException;
}
