
/**
 * RolesDaoImpl.java 
 * 
 *     This class implements RolesDao interface methods like  getting  particular user and  changing old password with updating his details
 */
package netcracker.developer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;

import netcracker.developer.dao.RolesDao;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.jdbc.UserRowMapper;
import netcracker.developer.viewBean.RegBean;

public class RolesDaoImpl implements RolesDao {

private DataSource dataSource;
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * getUser
	 * 
	 * @param emailId used to get user details from database
	 * @return Object RegBean
	 */
	public RegBean getUser(String emailId) {
		List<RegBean> userList = new ArrayList<RegBean>();
		try{
			
		
		
		String sql = "select * from user where emailId =?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//gets  userList with that emailId
		userList = jdbcTemplate.query(sql,new Object[] { emailId }, new UserRowMapper());
		System.out.println(userList.size());
		
		}
		catch(CannotGetJdbcConnectionException e){
			String msg="JDBC Connection problem";
			WarningMsg.showDialog(msg);
			
		}catch (Exception e) {
			e.getMessage();
			}
		return userList.get(0);
	}
	/**
	 * updateProfile
	 * 
	 * @param regBean used to update new user details in database 
	 */
	@Override
	public void updateProfile(RegBean regBean) {
		try{
		String sql = "UPDATE user set firstName = ?,lastName = ? , mobileNumber = ?  where emailId = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] {regBean.getFirstName(),regBean.getLastName(),regBean.getMobileNumber(),regBean.getEmailId()}
				);

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	/**
	 * changePassword
	 * 
	 * @param regBean to get users old password
	 * @param password to update this new password in database
	 */

	@Override
	public void changePassword(RegBean regBean,String password) {
		
		try{
			String sql = "UPDATE user set password= ? where emailId = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] {password,regBean.getEmailId()}
				);
		}
		catch(CannotGetJdbcConnectionException e){
			e.getMessage();
		}
		
	}
	/**
	 * increaseLike
	 * 
	 * @param queryId used to increase no of likes in database for that particular question Id 
	 */
	@Override
	public void increaseLike(int queryId) {
		//increasing likes by count 1
		try{
		String query= "update queries set no_of_likes=no_of_likes+1 where queryId="+queryId;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.execute(query);
	}catch(CannotGetJdbcConnectionException e){
		
		e.getMessage();
	}
	}
}//RolesDaoImpl block ends
