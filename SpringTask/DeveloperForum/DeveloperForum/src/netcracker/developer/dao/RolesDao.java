
/**
 * RolesDao.java 
 * 
 *      This interface defines methods like getting  particular user and  changing old password with updating his details
 */
package netcracker.developer.dao;

import netcracker.developer.viewBean.RegBean;

public interface RolesDao {
	/**
	 * getUser
	 * 
	 * @param emailId used to get user details from database
	 * @return Object RegBean
	 */
	public RegBean getUser(String emailId);

	/**
	 * changePassword
	 * 
	 * @param regBean to get users old password
	 * @param password to update this new password in database
	 */


	void changePassword(RegBean regBean, String password);
/**
 * updateProfile
 * 
 * @param regBean used to update new user details in database 
 */
	public void updateProfile(RegBean regBean);

/**
 * increaseLike
 * 
 * @param queryId used to increase no of likes in database for that particular question Id 
 */

	public void increaseLike(int queryId);


}//RolesDao block  ends
