package netcracker.developer.service.impl;

import java.sql.SQLException;

import netcracker.developer.dao.LoginDao;
import netcracker.developer.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;

	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		return loginDao.isValidUser(username, password);
	}

	@Override
	public boolean isAdmin(String username) throws SQLException {
				return loginDao.isAdmin(username);
	}

	@Override
	public boolean isActive(String username) throws SQLException {
		
		return loginDao.isActive(username);
	}

}
