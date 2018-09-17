/**
 * ForumServiceImpl.java    defined under netcracker.developer.service.Impl package
 *        This java interface class deals with creating forum,deleting forum ,getting list of forums
 *       
 *         Only  Administrator can create a forum and delete it.         
 */
//package
package netcracker.developer.service.impl;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.dao.ForumDao;
import netcracker.developer.service.ForumService;
import netcracker.developer.viewBean.ForumBean;

public class ForumServiceImpl  implements ForumService{

	/**
	 * ForumDao class used here to send parameters or calling the Forum DAO  methods to operate DAO implementations 
	 */
	ForumDao forumDao;
	
	public ForumDao getForumDao() {
		return forumDao;
	}

	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}

	/**
	 * getForumList is gets list of forums from forumDao
	 * 
	 * @return List of forums 
	 */
	@Override
	public List<ForumBean> getForumList() {
		
		return forumDao.getForumList();
	}

	
	/**
	 * deleteForum is passing parameter to forumDao's deleteForum
	 * 
	 * @param forumName used to delete forum from database
	 * @return nothing
	 */

	@Override
	public void deleteForum(String forumName) {
		forumDao.deleteForum(forumName);
		
	}
	/**
	 * createForum method is passing parameter to forumDao's createForum
	 * 	  
	 * @param forumName used to insert forum in database
	 * @return nothing
	 * @throws SQLException 
	 */
	@Override
	public boolean createForum(String forumName) throws SQLException {
		
		return forumDao.createForum(forumName);
	}

	@Override
	public List<String> getForumNames() {
		return forumDao.getForumNames();
	}

	@Override
	public ForumBean getForumBean(String forumName) {
		return forumDao.getForumBean(forumName);
	}

	
}
