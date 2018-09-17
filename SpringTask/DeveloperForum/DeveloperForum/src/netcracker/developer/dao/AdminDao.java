package netcracker.developer.dao;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.viewBean.RegBean;
/**
 * 
 * @author yogs0616
 * 
 * interface AdminDao which extends RolesDao
 *
 */
public interface AdminDao extends RolesDao {

	
	
	public void insertData(RegBean regBean);

	public List<RegBean> getUserList();

	public void deleteData(String emailId);

	void updateData(RegBean regBean);

	public void deleteQue(int queryId);

	public void deleteAns(int solutionId,int queryId);

	public boolean isValidEmailUser(String emailId) throws SQLException;

	
	

}
