package netcracker.developer.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import netcracker.developer.dao.LoginDao;
import netcracker.developer.jdbc.UserRowMapper;
import netcracker.developer.viewBean.RegBean;

public class LoginDaoImpl implements LoginDao {

	DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		String query = "Select count(1) from user where emailId = ? and password = ? ";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next())
			return (resultSet.getInt(1) > 0);
		else
			return false;
	}
	
	public RegBean getUser(String username) {
	List<RegBean> userList = new ArrayList<RegBean>();
	String sql = "select * from user where emailId =?";
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	userList = jdbcTemplate.query(sql,new Object[] { username }, new UserRowMapper());
	return userList.get(0);
	}

	

	@Override
	public boolean isAdmin(String username) throws SQLException {
		
			
		
		RegBean reg=getUser(username);
		 int state=reg.getUserTypeId();
			if(state==1){
				return true;
			}
			 
				return false;
			
		
}	
		

	@Override
	public boolean isActive(String username) throws SQLException {
		
			
		
		RegBean reg=getUser(username);
		 String state=reg.getUserState();
			if(state.equals("active")){
				return true;
			}
			 
				return false;
			
		
}	
		
		

	

}