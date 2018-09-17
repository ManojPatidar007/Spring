/**
 * RolesService.java 
 * 
 *      This interface defines methods like getting  particular user and  changing old password with updating his details
 */
package netcracker.developer.service;

import netcracker.developer.viewBean.RegBean;

public interface RolesService {

	/**
	 * getUser
	 * 
	 * @param emailId used to get user details
	 * @return Object RegBean
	 */
	public RegBean getUser(String emailId);
	/**
	 * updateProfile
	 * 
	 * @param regBean used to update new user details 
	 */
	public void updateProfile(RegBean regBean);
	/**
	 * changePassword
	 * 
	 * @param regBean to get users old password
	 * @param password to update this new password 
	 */
	public void changePassword(RegBean regBean, String newpassword);

/**
 * increaseLike
 * 
 * @param queryId used to increase no of likes  for that particular question Id 
 */

	public void increaseLike(int queryId);

}
