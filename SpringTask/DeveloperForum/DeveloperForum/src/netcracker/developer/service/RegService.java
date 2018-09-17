package netcracker.developer.service;

import java.sql.SQLException;

import netcracker.developer.viewBean.RegBean;

public interface RegService {

	public boolean isRegSuccesful(RegBean regBean)throws SQLException;

	
}
