
/**
 * RolesServiceImpl.java 
 * 
 *     This class implements RolesDao interface methods like  getting  particular user and  changing old password with updating his details
 */
package netcracker.developer.service.impl;

import netcracker.developer.dao.RolesDao;
import netcracker.developer.service.RolesService;
import netcracker.developer.viewBean.RegBean;

public class RolesServiceImpl implements RolesService {
	
		RolesDao rolesDao;
	
	
	
	public RolesDao getRolesDao() {
			return rolesDao;
		}


		public void setRolesDao(RolesDao rolesDao) {
			this.rolesDao = rolesDao;
		}

		/**
		 * getUser
		 * 
		 * @param emailId used to get user details
		 * @return Object RegBean
		 */
	public RegBean getUser(String emailId) {
		return rolesDao.getUser(emailId);
		}
	/**
	 * updateProfile
	 * 
	 * @param regBean used to update new user details 
	 */
		@Override
		public void updateProfile(RegBean regBean) {
			rolesDao.updateProfile(regBean);
		}
		/**
		 * changePassword
		 * 
		 * @param regBean to get users old password
		 * @param password to update this new password 
		 */
		@Override
		public void changePassword(RegBean regBean, String password) {
		 rolesDao.changePassword(regBean, password);
			
		}
		/**
		 * increaseLike
		 * 
		 * @param queryId used to increase no of likes  for that particular question Id 
		 */


		@Override
		public void increaseLike(int queryId) {
		rolesDao.increaseLike(queryId);
			
		}




}
