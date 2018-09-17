/**
 * QuestionServiceImpl.java    defined under netcracker.developer.service.impl package
 *        This java  class deals  with posting ,viewing ,filtering questions
 *        by likes and latest time by different roles like admin,member,guest
 *       
 *         Only Members and Administrator can post a question under forum.  
 *         Administrator, Member and Guest can view a question and answer
 *         
 *         this Java class  calls dao methods  for further processing.        
 */
//package
package netcracker.developer.service.impl;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.dao.QuestionDao;
import netcracker.developer.service.QuestionService;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
/**
 * @author kaas0616
 */
public class QuestionServiceImpl implements QuestionService{


	/**
	 * QuestionDao class used here to send parameters or calling the Forum DAO  methods to operate DAO implementations 
	 */
	QuestionDao questionDao;
	
	

	public QuestionDao getQuestionDao() {
		return questionDao;
	}



	public void setQuestionDao(QuestionDao questionAnswerDao) {
		this.questionDao = questionAnswerDao;
	}

	/**
	 * insertQuestion method calls questionDao methods for further processing
	 * 
	 * @param questionBean needed to take query Values to insert into database
	 * @param username  used to show which user posted and to store in database
	 * @return nothing
	 */

	@Override
	public void insertQuestion(QuestionBean questionBean,String username) {
		questionDao.insertQuestion(questionBean,username);
		
	}
	/**
	 * viewQuestion method calls questionDao methods for further processing
	 * 
	 * @param questionBean needed fetched queries from database to store in list of QuestionBean objects
	 * @param forumName used to fetch queries related to given forumName 
	 * @return List Of QuestionBean objects
	 * @throws SQLException 
	 */


	@Override
	public List<QuestionBean> viewQuestion(QuestionBean questionBean, String forumName) throws SQLException {
		return questionDao.viewQuestion(questionBean,forumName);
	}

	/**
	 * viewQuestion method calls questionDao methods for further processing
	 * 
	 * @param questionBean needed fetched queries from database to store in list of QuestionBean objects
	 * @param loginBean used to fetch all queries
	 * @return List Of QuestionBean objects
	 */

	@Override
	public List<QuestionBean> viewQuestion(QuestionBean questionBean, LoginBean loginBean) {
		
		return questionDao.viewQuestion(questionBean, loginBean);
	}

	 /**
     * filterByLikeQuestion calls questionDao methods for further processing
     * 
     * @param questionBean needed fetched queries from database to store in list of QuestionBean objects  in sorted format of likes
     * @param forumName used to fetch queries related to given forumName 
     * @return List Of QuestionBean objects
     */

	@Override
	public List<QuestionBean> filterByLikeQuestion(QuestionBean questionBean, String forumName) {
		return questionDao.filterByLikeQuestion(questionBean, forumName);
	}
	
	 /**
     * filterByLatestQuestion  calls questionDao methods for further processing
     * 
     * @param questionBean needed fetched queries from database to store in list of QuestionBean objects  in sorted format of latest time
     * @param forumName used to fetch queries related to given forumName
     * @return  List Of QuestionBean objects
     */


	@Override
	public List<QuestionBean> filterByLatestQuestion(QuestionBean questionBean, String forumName) {
		return questionDao.filterByLatestQuestion(questionBean,forumName);
	}


}
