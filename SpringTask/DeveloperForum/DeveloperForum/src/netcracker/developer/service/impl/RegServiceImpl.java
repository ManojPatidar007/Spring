package netcracker.developer.service.impl;

import java.sql.SQLException;

import netcracker.developer.dao.RegDao;
import netcracker.developer.service.RegService;
import netcracker.developer.viewBean.RegBean;

public class RegServiceImpl implements RegService {
	private RegDao regDao;

	public RegDao getRegDao() {
		return regDao;
	}

	public void setRegDao(RegDao regDao) {
		this.regDao = regDao;
	}

	@Override
	public boolean isRegSuccesful( RegBean bean) throws SQLException {

		return regDao.isRegSuccesful(bean);
	}

	

}
