/**
 *
 */
package netcracker.developer.service;

import java.sql.SQLException;

public interface LoginService {
	public boolean isValidUser(String username, String password) throws SQLException;

	public boolean isAdmin(String username) throws SQLException;

	public boolean isActive(String username) throws SQLException ;
}
