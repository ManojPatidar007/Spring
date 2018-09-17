package netcracker.developer.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import netcracker.developer.dao.AdminDao;
import netcracker.developer.jdbc.UserRowMapper;
import netcracker.developer.viewBean.RegBean;
/**
 * 
 * @author yogs0616
 * Class AdminDaoImpl.java : this is the implementation of interface AdminDao also it extends the RolesDaoImpl 
 * 							 
 *
 */
public class AdminDaoImpl extends RolesDaoImpl  implements AdminDao{

	
	
	private DataSource dataSource;
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
	
	@Override
	public void insertData(RegBean regBean) {
		
		
		
		
		
		String sql = "INSERT INTO user  VALUES (?, ?, ? ,? ,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] {regBean.getEmailId(), regBean.getFirstName(), regBean.getLastName(),
						regBean.getPassword(),regBean.getMobileNumber(),regBean.getUserTypeId(),regBean.getUserState()});

		
	}

	@Override
	public List<RegBean> getUserList() {
		
		List<RegBean> userList = new ArrayList<RegBean>();

		String sql = "select * from user";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		return userList;
	}

	@Override
	public void deleteData(String emailId) {
		
		String sql = "delete from user where emailId='" + emailId+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	@Override
	public void updateData(RegBean regBean) {
		
		String sql = "UPDATE user set firstName = ?,lastName = ? , mobileNumber = ? ,password= ?,userTypeId= ? , userState= ? where emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] {regBean.getFirstName(),regBean.getLastName(),regBean.getMobileNumber(),regBean.getPassword(),regBean.getUserTypeId(),regBean.getUserState(),regBean.getEmailId()}
				);

}

	@Override
	public void deleteQue(int queryId) {
		String sql = "delete from queries where queryId='" +queryId+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
		
	}

	@Override
	public void deleteAns(int solutionId,int queryId ) {
		String sql = "delete from solutions where solutionId='" +solutionId+"'";
		
		String query= "update queries set no_of_answers=no_of_answers-1 where queryId="+queryId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
		jdbcTemplate.execute(query);
	}

	@Override
	public boolean isValidEmailUser(String emailId) throws SQLException {
			String query = "Select count(1) from user where emailId = ? ";
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, emailId);
		
			
			
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next())
				return (resultSet.getInt(1) > 0);
			else
				return false;
		}
	}

	
	


