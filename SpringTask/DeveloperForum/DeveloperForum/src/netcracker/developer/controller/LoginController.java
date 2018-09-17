package netcracker.developer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.service.ForumService;
import netcracker.developer.service.LoginService;
import netcracker.developer.service.QuestionService;
import netcracker.developer.viewBean.ForumBean;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
import netcracker.developer.viewBean.RegBean;

/** 
 * LoginController.java: defined under netcracker.developer.controller package
 * LoginController is a controller class to handle one or multiple requests for loginPage.
 *This controller validate the credentials and then give access to corresponding roles pages.
 *If Already loggedIn user tries to access the login page then it Takes the information from session and then redirect to corresponding roles
 *and also checks whether user is in active state or not. 
 * @author lavp0616
 *
 */
@Controller
public class LoginController {
	
	//Defining logger for LoginController Class
	private Logger logger=Logger.getLogger(LoginController.class);
	
	/**
	 *LoginService, ForumService and QuestionService are autowired 
	 */
	/**
	 *  @Autowired annotation can be used to auto wire bean .
	 *  @Autowired annotation on properties to get rid of the setter methods. 
	 *  When you will pass values of autowired properties using <property> 
	 *  Spring will automatically assign those properties with the passed values or references.
	 *  
	 */
	//since ForumService is using forumDao property , ForumService is autowired
	@Autowired	
	 private ForumService forumService;
	//since QuestionService is using questionDao property ,QuestionService is autowired
	@Autowired
	private QuestionService	questionService;
	//since LoginService is using loginDao property ,LoginService is autowired
	@Autowired
	private LoginService loginService;
	/**
	 * RequestMapping handles login URL  under GET request
	 *  and calls displayLogin method.
	 *  displayLogin method: This method displays the login page to login If they aren't loggedIn .If they are logged in 
	 *  and tries to access login page without logout then from their previous session their roles are fetched and then
	 *  according to the roles their views are  restored
	 *  @param request
	 * 	@param response
	 *  @param loginBean
	 *  @param bindingresult
	 *  @return
	 **/
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,RegBean regBean) {
		ModelAndView model=null;
	
		HttpSession session= request.getSession(false);
		if(session!=null){
			
			String sessionId=(String)session.getAttribute("sessionId");
			if(session.getId()!=sessionId){
			
				model = new ModelAndView("login");
				logger.info("For New Session Login model created");
				model.addObject("loginBean", loginBean);
			   
			}else{
				QuestionBean questionBean = null;
				String user=(String) session.getAttribute("role");
				if(user.equals("admin")){
					//List of Question is created and stored in queList
					List<QuestionBean> queList=new ArrayList<QuestionBean>();
					//List of Forum  is created and  ForumList have been fetched from the database and stored in forumBean .
					List<ForumBean> forumBean = forumService.getForumList();
					
					//questions are retrieved from the database using viewQuestion method and storing in queList
					queList=questionService.viewQuestion(questionBean, loginBean);
					logger.info("Question has been Retrievd from database");
					//New AdminWelcome model is created
					 model=new ModelAndView("adminWelcome");
					 logger.info("Admin model created if logged in admin tries to login again");
					//List of questions have been added to that model
					model.addObject("queList",queList);
					//List of forumNames have been added to that model
					model.addObject("forumBean",forumBean);
					logger.info("Questions and forum list have been added to admin model");
				}else if(user.equals("member")){
					//List of Question is created and stored in queList	
					List<QuestionBean> queList=new ArrayList<QuestionBean>();
					//questions are retrieved from the database using viewQuestion method and storing in queList
					queList=questionService.viewQuestion(questionBean, loginBean);
					logger.info("Question has been Retrievd from database");
					//New AdminWelcome model is created
					model=new ModelAndView("memberWelcome");
					 logger.info("member  model created if logged in user tries to login again");
					//List of questions have been added to that model
					model.addObject("queList",queList);
					logger.info("Questions and forum list have been added to member  model");
					logger.info("member model returned");
					//return newly created model.
					return model;	
					
				}
		}
		}
		else{
			 model = new ModelAndView("login");
			
			logger.info("Invalid credentials so Login model created");
		    model.addObject("loginBean", loginBean);
		    
		}
		return model;
			
			
		}
	
/**
 * 
 * @param request
 * @param response
 * @param loginBean
 * @param bindingresult
 * @return
 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginBean") LoginBean loginBean, BindingResult bindingresult) {
		
		//ModelAndView model = null;
		
		
		if(!bindingresult.hasErrors()){
           
		}
                
		try {
			boolean isValidUser = loginService.isValidUser(loginBean.getUsername(), loginBean.getPassword());
			if (isValidUser) {
				
				
				boolean isActive=loginService.isActive(loginBean.getUsername());
				
				
				if(isActive){
					logger.debug(" Value of isActive (checking whether user is active or not "+isActive);
					
				boolean isAdmin=loginService.isAdmin(loginBean.getUsername());
				logger.debug("Value of isAdmin(checking whether user is admin or not"+isAdmin);
				HttpSession session= request.getSession(true);
				//Set the max time out of session tto 300
				session.setMaxInactiveInterval(300);
				//set the sessionId 
				session.setAttribute("sessionId", session.getId());
				//Set the logged In user name
				session.setAttribute("loggedInUser",loginBean.getUsername());
				logger.info("List of forumName is retrieved");
				List<ForumBean> forumBean = forumService.getForumList();
				
				session.setAttribute("forumBean",forumBean);
				 
				if(isAdmin){
					logger.info("Admin Login Successful");
					session.setAttribute("role","admin");
					return "redirect:/admin";
				}
				else{
					logger.info("Member Login Successful");
					session.setAttribute("role","member");
					return "redirect:/member";
				
				}
			}
				else {
					logger.warn("Deactivated Account");
					String msg="Deactivated Account";
					WarningMsg.showDialog(msg);
					return "redirect:/login";
					
				}
			}
				else {
					logger.warn("Invalid credentials!!");
					String msg="Invalid credentials!!";
					WarningMsg.showDialog(msg);
					return "redirect:/login";
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	logger.info("Redirecting to Login");
	return "redirct:/login";
	
	}
	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	HttpSession session=request.getSession(false);
		if(session!=null){
	//session.getAttribute("loggedInUser");
	//session.removeAttribute("role");
	//session.removeAttribute("froumBean");
	logger.info("Invalidating Session");
	session.invalidate();
		}
		
	logger.info("redirecting to login after invalidating session");
	 return "redirect:/login";
}
	

}

