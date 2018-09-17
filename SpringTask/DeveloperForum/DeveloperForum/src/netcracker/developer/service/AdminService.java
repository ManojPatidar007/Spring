package netcracker.developer.service;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.viewBean.RegBean;

public interface AdminService  extends RolesService{
	
	public void insertData(RegBean regBean);

	public List<RegBean> getUserList();

	public void deleteData(String emailId);

	
	public void changePassword(RegBean regBean,String password);

	

	
	public void updateData(RegBean regBean);

	public void deleteQue(int queryId);

	public void deleteAns(int solutionId,int queryId);

	public boolean isValidEmailUser(String emailId) throws SQLException;


}
