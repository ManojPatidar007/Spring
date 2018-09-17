/**
 * ForumService.java    defined under netcracker.developer.service package
 *        This java interface class deals with creating forum,deleting forum ,getting list of forums
 *       
 *         Only  Administrator can create a forum and delete it.         
 */
//package
package netcracker.developer.service;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.viewBean.ForumBean;

public interface ForumService {
	/**
	 * getForumList
	 * 
	 * @return List of forums 
	 */
	List<ForumBean> getForumList();
	/**
	 * deleteForum 
	 * 
	 * @param forumName used to delete forum from database
	 * @return nothing
	 */
	void deleteForum(String forumName);
	/**
	 * createForum 
	 * 	  
	 * @param forumName used to insert forum in database
	 * @return nothing
	 * @throws SQLException 
	 */
	boolean createForum(String forumName) throws SQLException;
	
	List<String> getForumNames();
	ForumBean getForumBean(String forumName);
	
	
	
   
}
