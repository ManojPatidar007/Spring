/**
 * UserExtractor.java 
 *  
 *       It is user defined class which implements ResultsetExtractor to fetch records 
 *       according to user 
 */
package netcracker.developer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import netcracker.developer.viewBean.RegBean;
/**
 * @author kaas0616
 */
public class UserExtractor implements ResultSetExtractor<RegBean> {

	public RegBean extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		//RegBean 
		RegBean regBean = new RegBean();
		
		//adding result set data to RegBean collection
		regBean.setEmailId(resultSet.getString(1));
		regBean.setFirstName(resultSet.getString(2));
		regBean.setLastName(resultSet.getString(3));
		regBean.setMobileNumber(resultSet.getString(4));
		regBean.setPassword(resultSet.getString(5));
		regBean.setUserTypeId(resultSet.getInt(6));
		regBean.setUserState(resultSet.getString(7));
		return regBean;
	}

}//UserExtractor ends here