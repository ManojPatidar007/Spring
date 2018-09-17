package netcracker.developer.service.impl;

import netcracker.developer.dao.MemberDao;
import netcracker.developer.service.MemberService;
import netcracker.developer.viewBean.RegBean;

public class MemberServiceImpl extends RolesServiceImpl implements MemberService {
	
	private MemberDao memberDao;

	public MemberDao getmemberDao() {
		return memberDao;
	}

	public void setmemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public void updateState(RegBean regBean) {
		memberDao.updateState(regBean);
		
	}

}
