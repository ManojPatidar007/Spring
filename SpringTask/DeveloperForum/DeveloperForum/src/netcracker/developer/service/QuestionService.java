/**
 * QuestionService.java    defined under netcracker.developer.service package
 *        This java  class deals  with posting ,viewing ,filtering questions
 *        by likes and latest time by different roles like admin,member,guest
 *       
 *         Only Members and Administrator can post a question under forum.  
 *         Administrator, Member and Guest can view a question and answer
 *         
 *         this Java  interface class is implemented by QuestionServiceImpl class      
 */
//package
package netcracker.developer.service;


import java.sql.SQLException;
import java.util.List;

import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
/**
 * 
 * @author kaas0616
 *
 */
public interface QuestionService {
	
	/**
	 * viewQuestion method
	 * 
	 * @param questionBean needed fetched queries from database to store in list of QuestionBean objects
	 * @param forumName used to fetch queries related to given forumName 
	 * @return List Of QuestionBean objects
	 * @throws SQLException 
	 */
	public List<QuestionBean> viewQuestion(QuestionBean questionBean, String forumName) throws SQLException;
	/**
	 * viewQuestion method
	 * 
	 * @param questionBean needed fetched queries from database to store in list of QuestionBean objects
	 * @param loginBean used to fetch all queries
	 * @return List Of QuestionBean objects
	 */

	public List<QuestionBean> viewQuestion(QuestionBean questionBean, LoginBean loginBean);
	/**
	 * insertQuestion method
	 * 
	 * @param questionBean needed to take query Values to insert into database
	 * @param username  used to show which user posted and to store in database
	 * @return nothing
	 */
	void insertQuestion(QuestionBean questionBean, String username);
	 /**
     * filterByLikeQuestion
     * 
     * @param questionBean needed fetched queries from database to store in list of QuestionBean objects  in sorted format of likes
     * @param forumName used to fetch queries related to given forumName 
     * @return List Of QuestionBean objects
     */

	public List<QuestionBean> filterByLikeQuestion(QuestionBean questionBean, String forumName);
	/**
     * filterByLatestQuestion 
     * 
     * @param questionBean needed fetched queries from database to store in list of QuestionBean objects  in sorted format of latest time
     * @param forumName used to fetch queries related to given forumName
     * @return  List Of QuestionBean objects
     */

	public List<QuestionBean> filterByLatestQuestion(QuestionBean questionBean, String forumName);



	
	

	
}
