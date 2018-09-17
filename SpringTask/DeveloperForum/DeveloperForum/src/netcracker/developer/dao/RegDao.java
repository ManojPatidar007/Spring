package netcracker.developer.dao;

import java.sql.SQLException;

import netcracker.developer.viewBean.RegBean;

public interface RegDao {

		public boolean isRegSuccesful(RegBean bean)throws SQLException;
}
