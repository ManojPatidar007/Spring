package netcracker.developer.service.impl;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.dao.AdminDao;
import netcracker.developer.service.AdminService;

import netcracker.developer.viewBean.RegBean;

public class AdminServiceImpl extends  RolesServiceImpl implements AdminService{

	
	private AdminDao ado;
	
	
	
	
	public AdminDao getAdo() {
		return ado;
	}

	public void setAdo(AdminDao ado) {
		super.setRolesDao(ado);
		this.ado = ado;
	}

	@Override
	public void insertData(RegBean regBean) {
	ado.insertData(regBean);
		
	}
	
	@Override
	public List<RegBean> getUserList() {
		return ado.getUserList();
	}

	@Override
	public void deleteData(String emailId) {
	 ado.deleteData(emailId);
		
	}

	

	@Override
	public void updateData(RegBean regBean) {
		ado.updateData(regBean);	
	}

	@Override
	public void deleteQue(int queryId) {
		ado.deleteQue(queryId);
	}

	@Override
	public void deleteAns(int solutionId,int queryId) {
		ado.deleteAns(solutionId,queryId);
		
	}

	@Override
	public boolean isValidEmailUser(String emailId) throws SQLException {
		
		return ado.isValidEmailUser(emailId);
	}

	




}
