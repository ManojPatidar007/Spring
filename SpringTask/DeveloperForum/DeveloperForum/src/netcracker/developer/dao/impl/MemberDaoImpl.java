package netcracker.developer.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import netcracker.developer.dao.MemberDao;
import netcracker.developer.viewBean.RegBean;

public class MemberDaoImpl extends RolesDaoImpl implements MemberDao {
	
private DataSource dataSource;
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	@Override
	public void updateState(RegBean regBean) {
	
		String sql = "UPDATE user set userState=? where emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] {"inactive",regBean.getEmailId()}
				);
	}


}
