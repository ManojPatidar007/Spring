/**
 * UserRowMapper.java 
 *  
 *     It fetches user records from database and all columns of each row is  bind to one user object
 *     Thus returns List  of user objects
 *     
 */
package netcracker.developer.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import netcracker.developer.viewBean.RegBean;
/**
 *  RowMapper interface allows to map a row of the relations with the instance of RegBean class.
 *  It iterates the ResultSet internally and adds it into the collection.
 *  RowMapper adds the data of ResultSet into the RegBean collection.
 *  It defines only one method mapRow that accepts ResultSet instance and int as the parameter list
 */
/**
 * @author kaas0616
 */
public class UserRowMapper implements RowMapper<RegBean> {

	@Override
	public RegBean mapRow(ResultSet resultSet, int line) throws SQLException {
		UserExtractor userExtract = new UserExtractor();
		return userExtract.extractData(resultSet);
	}
	
}
