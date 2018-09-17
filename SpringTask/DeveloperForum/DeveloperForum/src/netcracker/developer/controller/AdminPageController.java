package netcracker.developer.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.service.AdminService;
import netcracker.developer.service.ForumService;
import netcracker.developer.service.QuestionService;
import netcracker.developer.viewBean.ForumBean;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
import netcracker.developer.viewBean.RegBean;

/**
 * AdminPageController.java: defined under netcracker.developer.controller package
 * AdminPageController is a controller class to handle one or multiple requests for adminPage.
 * Here administrator Can view his page and get the list of all users and update information of selected user and delete user or question or answer.
 * @author lavp0616
 *
 */
@Controller
public class AdminPageController  {

	
	//Defining logger for administrator page 
	private Logger logger=Logger.getLogger(AdminPageController.class);
	
	/**
	 *  @Autowired annotation can be used to auto wire bean .
	 *  @Autowired annotation on properties to get rid of the setter methods. 
	 *  When you will pass values of autowired properties using <property> 
	 *  Spring will automatically assign those properties with the passed values or references.
	 *  
	 */

	/**
	 * ForumService and QuestionService are autowired 
	 */
	
	
	 /**
	  * since AdminService is using adminDao property , AdminService is autowired
	  */
	 @Autowired
	 private AdminService adminService;
	
	 /**
	  * since ForumService is using forumDao property , ForumService is autowired
	  */
	 @Autowired 
	 private ForumService forumService;
	

	/**
	 * since QuestionService is using questionDao property ,QuestionService is autowired
	 */
	 @Autowired 
	 private QuestionService questionService;
	
	
	
	  /**
	   *  RequestMapping handles admin URL  under GET request
	  *  and calls viewQuestionsAdmin method.
	  * 
	  *  viewQuestionAdmin method : This method is executed when a administrator makes a request for AdminPage .After successful validation 
	  * AdminPage model is created and returned.  
	  * @param request on HttpServletRequest used to get HttpSession
	  * @param questionBean   used to send  updated list of questions
	  * @param loginBean  used when someone  Entered URL illegally in web browser
	  * @return ModelAndView depends on type of role ,the appropriate models with corresponding views allotted for the role
	  */
	@RequestMapping(value= "/admin", method = RequestMethod.GET)
	public ModelAndView viewQuestionsAdmin(HttpServletRequest request,@ModelAttribute QuestionBean questionBean,
			@ModelAttribute LoginBean loginBean) {
		//PropertyConfigurator.configure("log4j.properties");
		// session object is created here and request.getSession(false) returns current session if it is there and doesn't create a new session 
		HttpSession session = request.getSession(false);
		
		//Getting the attribute value of role from previous session and storing in user . 
		String user=(String)session.getAttribute("role");
		
		/*If user is a null means  role attribute has not been set in previous session.Because for guest Session is not created and 
		 * if a guest tries to access this page then he is redirected to login page.If a member tries to access then his session is
		 * invalidated and redirected to login. 
		*/
		if(user==null||(user!=("admin"))||(user.equals("member"))){
			ModelAndView model=new ModelAndView("login");
			String msg="InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);
			model.addObject("loginBean", loginBean);
			logger.warn("InValid Access For Admin Page");
			return model;
		}
		else{
			//List of Questions is created and stored in queList
			List<QuestionBean> queList=new ArrayList<QuestionBean>();
			//List of Forum  is created and  ForumList have been fetched from the database and stored in forumBean .
			List<ForumBean> forumBean = forumService.getForumList();
			//questions are retrieved from the database using viewQuestion method and storing in queList
			queList=questionService.viewQuestion(questionBean, loginBean);
			if(queList.isEmpty()){
		        session.setAttribute("Questions","No Questions Found");
		      }
		      else{
		      
		       session.setAttribute("Questions","");
		      }
			//New AdminWelcome model is created
			ModelAndView model=new ModelAndView("adminWelcome");
			//List of questions have been added to that model
			model.addObject("queList",queList);
			//List of forumNames have been added to that model
			model.addObject("forumBean",forumBean);
		logger.info("Admin model created and returned");
		
		return model;		
		}
	}


	@RequestMapping("/register")
	public String registerUser(@ModelAttribute RegBean regBean) {
		return "redirect:/registration";

	}

	/**
	 * 
	 * RequestMapping handles getList URL  under GET request
	  *  and calls getUserList method.
	 * getUserList method: This method is used to get UserList from the database but accessed only by administrator.
	 * @param request on HttpServletRequest used to get HttpSession
	 * @param response on HttpServletresponse
	 * @param loginBean  used when someone  Entered URL illegally in web browser
	 * @return ModelAndView  which consists of all user information for administrator
	 */
	@RequestMapping("/getList")
	public ModelAndView getUserList(HttpServletRequest request,HttpServletResponse response,LoginBean loginBean) {
				// session object is created here and request.getSession(false) returns current session if it is there and doesn't create a new session
				HttpSession session = request.getSession(false);
				
				//Getting the attribute value of role from previous session and storing in user . 
				String user=(String)session.getAttribute("role");
				
				/*If user is a null means   role attribute has not been set in previous session.Because for guest Session is not created and 
				 * if a guest tries to access this page then he is redirected to login page.If a member tries to access then his session is
				 * invalidated and redirected to login. 
				 */
				if(user==null||(user!=("admin"))||(user.equals("member"))){
					ModelAndView model=new ModelAndView("login");
					String msg="InValid Access Please Log In To Continue";
					WarningMsg.showDialog(msg);
					model.addObject("loginBean", loginBean);
					logger.warn("InValid Access For Admin getList Page");
					return model;
				}
				else{
					//Information of all members and admin have been fetched from database and stored in regBean which is list.
					List<RegBean> regBean = adminService.getUserList();
					//New model of adminUserList have been created and information of all users have been added to that model.
					logger.info("Admin user List model is created with all the user information and returned");
					return new ModelAndView("adminUserList", "regBean", regBean);
				}
	}
	
	
	/**
	 *  RequestMapping handles adminEdit URL  under GET request
	 *  and calls editUser method.
	 * editUser:Admin Can edit all the details of the user.and he can  deactivate the member account and change the
	 * role from administrator to member or from member to administrator. 
	 * @param emailId To access the details of the user through emailId 
	 * @param regBean
	 * @param request on HttpServletRequest used to get HttpSession
	 * @param response on HttpServletresponse
	 * @param loginBean  used when someone  Entered URL illegally in web browser
	 * @return ModelAndView  which consists of  user information a particular user for an  administrator
	 * @throws SQLException 
	 */
	@RequestMapping("/adminEdit")
	public ModelAndView editUser(@RequestParam("emailId") String emailId,
			@ModelAttribute RegBean regBean,HttpServletRequest request,HttpServletResponse response,LoginBean loginBean) throws SQLException {
		// session object is created here and request.getSession(false) returns current session if it is there and doesn't create a new session
		HttpSession session = request.getSession(false);
		
		//Getting the attribute value of role from previous session and storing in user . 
		String user=(String)session.getAttribute("role");
		
		/*If user is a null means   role attribute has not been set in previous session.Because for guest Session is not created and 
		 * if a guest tries to access this page then he is redirected to login page.If a member tries to access then his session is
		 * invalidated and redirected to login. 
		 */
		if(user==null||(user!=("admin"))||(user.equals("member"))){
			ModelAndView model=new ModelAndView("login");
			String msg="InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);
			logger.warn("InValid Access For Admin Edit Page");
			model.addObject("loginBean", loginBean);
			return model;
		}
		else{
			boolean isValidEmail=adminService.isValidEmailUser(emailId);
			//To retrieve all the user details from the database and storing in regbBean.
			if(isValidEmail){
			regBean = adminService.getUser(emailId);
			//New model of adminEdit is created
			ModelAndView model= new ModelAndView("adminEdit");
			//Adding all the user information to model
			model.addObject("regBean",regBean);
			logger.info("A complete information of single user is extracted to make changes in Admin Edit paage ");
			//returning the newly created model
			return model;
			}
			else{
				
				String msg="Invalid Email Kindly see the list of users and Then Edit";
				WarningMsg.showDialog(msg);
				//Information of all members and admin have been fetched from database and stored in regBean which is list.
				List<RegBean> regBean1 = adminService.getUserList();
				//New model of adminUserList have been created and information of all users have been added to that model.
				logger.info("Admin user List model is created with all the user information and returned");
				return new ModelAndView("adminUserList", "regBean", regBean1);
				
			}
		}
	}
	
	
	
	

	/**
	 * RequestMapping handles update URL  under GET request
	 *  and calls updateUser method.
	 * updateUser method:If administrator  makes any changes to the users then it is updated in
	 * database through this method.
	 * @param regBean consists of all Information about a user  
	 * @return Redirecting to getList to view the updated information
	 */
	@RequestMapping("/update")
	public String updateUser(@ModelAttribute RegBean regBean) {
		//calling updateData method present in administrator service to update the user details.
		adminService.updateData(regBean);
		logger.info("information of single user is updated");
		//after updating redirecting to getList to see the updated user information.
		String msg="information of a user is updated";
		WarningMsg.showMessage(msg);
		return "redirect:/getList";

	}

	/**
	 * RequestMapping handles deleteUser URL  under GET request
	 * and calls deleteUser method.
	 * deleteUser method:This method is used to delete  the user and to remove his credentials.
	 * @param emailId  To access the  emailId for deletion 
	 * @return Redirecting to getList to view The updated list
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam String emailId) {
		//Confirm dialog box is used to ask for confirmation of deletion.
		int option = JOptionPane.showConfirmDialog(null, "Press Ok to delete", "Confirm", JOptionPane.YES_NO_OPTION);
		logger.info("Confirm Dailog is poped and waiting  for admin decision for deleting user");
		//If answer is yes then deleting the user
		  if (option == 0) { //The ISSUE is here
		    logger.info("admin entered yes and deleting a user");
		     logger.info("emailId = " +emailId );
		     adminService.deleteData(emailId);
		     //redirecting to see the new List after deletion.
		     logger.info("Redirecting to admin page after deltion of user");
		     String msg="Deleted user Successfully";
		     WarningMsg.showMessage(msg);
		     return "redirect:/getList";
		  } else {
		    
		     logger.info("admin entered NO for deletion");
		     //redirecting to see the new List after deletion.
		     logger.info("Redirecting to admin page without deletion As admin pressed No for deletion");
		     String msg="You selected No for deletion So,user is not deleted";
		     WarningMsg.showMessage(msg);
		     return "redirect:/getList";
		  }
		
	
	}


	/**
	 * RequestMapping handles deleteQue URL  under GET request
	 * and calls deleteQue method.
	 *  deleteQue : this method is used to delete question from the Forum
	 * @param queryId : Id of the Question for deletion
	 * @return Redirecting to admin page after deletion or without deletion.
	 */
	@RequestMapping("/deleteQue")
	public String deleteQue(@RequestParam("queryId") int queryId) {
		//Confirm dialog box is used to ask for confirmation of deletion.
		int option = JOptionPane.showConfirmDialog(null, "Press Ok to delete", "Confirm", JOptionPane.YES_NO_OPTION);
		logger.info("Confirm Dailog is poped and waiting  for admin decision for deleting a question ");
		//If answer is yes then deleting the question
		  if (option == 0) { //The ISSUE is here
			  logger.info("admin entered yes for  deleting a question");
			  logger.debug("queryId = " +queryId);
		     //calling service method to delete the question
		     adminService.deleteQue(queryId);
		     String msg="Question Deleted successfully";
		     WarningMsg.showMessage(msg);
		     //redirecting to admin page.
		     logger.info("Redirecting to admin page after deltion");
		     return "redirect:/admin";
		  } else {
			  String msg="You selected No for deletion So,Question is not deleted";
			     WarningMsg.showMessage(msg);
		   //redirecting to admin page.
		     logger.info("Redirecting to admin page without deletion As admin pressed No for deletion");
		     return "redirect:/viewAns?queryId="+queryId;
		  }
		
	
	}
	

	
	/**
	 *  RequestMapping handles deleteAns URL  under GET request
	 * and calls deleteAns method.
	 * deleteAns: this method is used to delete answers from the Forum.
	 * @param solutionId : Id of the Answer for deletion
	 * @param queryId: Id of the Question for which the solution belongs 
	 * @return Redirecting to admin page after deletion or without deletion
	 */
	@RequestMapping("/deleteAns")
	public String deleteAns(@RequestParam("solutionId") int  solutionId,@RequestParam("queryId") int  queryId) {
		//Confirm dialog box is used to ask for confirmation of deletion.
		int option = JOptionPane.showConfirmDialog(null, "Press Ok to delete", "Confirm", JOptionPane.YES_NO_OPTION);
		//If answer is yes then deleting the answer.
		  if (option == 0) { 
			  logger.info("admin entered yes for  deleting a solution");
			  logger.debug("solutionId  = " +solutionId); 
		   //calling service method to delete the answer
		     adminService.deleteAns(solutionId,queryId);
		     String msg="Answer Deleted successfully";
		     WarningMsg.showMessage(msg);
		   //redirecting to admin page.
		     logger.info("Redirecting to admin page after deletion");
		     return "redirect:/viewAns?queryId="+queryId;
		  } else {
			  String msg="You selected No for deletion So,Answer is not deleted";
			     WarningMsg.showMessage(msg);
		     //redirecting to admin page.
		     logger.info("Redirecting to admin page without deletion As admin pressed No for deletion");

		     return "redirect:/viewAns?queryId="+queryId;
		  }
		
	
	}



}
