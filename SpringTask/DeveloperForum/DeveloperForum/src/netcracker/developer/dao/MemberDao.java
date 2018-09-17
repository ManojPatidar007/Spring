package netcracker.developer.dao;

import netcracker.developer.viewBean.RegBean;

public interface MemberDao extends RolesDao {

	public void updateState(RegBean regBean);
	
}
