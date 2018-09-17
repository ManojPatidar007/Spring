package netcracker.developer.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.service.ForumService;
import netcracker.developer.service.QuestionService;
import netcracker.developer.service.RolesService;
import netcracker.developer.viewBean.ForumBean;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
import netcracker.developer.viewBean.RegBean;


/**
 * 
 * RolesController.java: This  is a controller class to handle one or multiple requests for Both Admin and Member Page.
 *@author lavp0616
 */
@Controller
public class RolesController {

	// Declaring  RolesService methods to call the RolesDaoImplementation. 
	@Autowired
	RolesService rolesService;
	// Declaring QuestionService methods to call the QuestionDaoImplementation.
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ForumService forumService;
	
	
	/**
	 *changePassowrd(Get Method):Both admin and member can change password using this method.
	 * @param request
	 * @param response
	 * @param regBean
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value="/changePassword",method=RequestMethod.GET)
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response, RegBean regBean, LoginBean loginBean) {
		// Gets a previous session
		HttpSession session = request.getSession(false);
		//Getting the attribute value of role from previous session and storing in user
		String user=(String)session.getAttribute("role");
		/*If user is a null means  role attribute has not been set in previous session.Because for guest, Session is not created and 
		 * if a guest tries to access this page then he is redirected to login page.
		*/
		if((user==null)||((user!=("admin"))&&(user!=("member")))){

			ModelAndView model=new ModelAndView("login");
			String msg="InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);
			model.addObject("loginBean", loginBean);
			return model;


		}else{
			//new model for changePassword has been created.
			ModelAndView model= new ModelAndView("changePassword");
			//regBean attribute is added to model.
			model.addObject("regBean",regBean);
			//return new model
			return model;
		}
	}
	
	/*  ChangPassword(post method):In this method New passowrd an old password have been  
	 * @param password
	 * @param newpassword
	 * @param request
	 * @param response
	 * @param regBean
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public ModelAndView changePassword(@RequestParam("password") String password,
			@RequestParam("firstName") String newpassword,HttpServletRequest request,HttpServletResponse response, RegBean regBean, LoginBean loginBean) {
		HttpSession session = request.getSession(false);
		String user=(String)session.getAttribute("role");
		if((user==null)||((user!=("admin"))&&(user!=("member")))){

			ModelAndView model=new ModelAndView("login");
			String msg="InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);
			model.addObject("loginBean", loginBean);
			return model;


		}else{

			
			String  user1= (String) session.getAttribute("loggedInUser");
		regBean=rolesService.getUser(user1);
		String pwd=regBean.getPassword();
		if(pwd.equals(password)){
		rolesService.changePassword(regBean,newpassword);
		ModelAndView model=new ModelAndView("login");
		model.addObject("loginBean",loginBean);
		String msg1="Password Changed Successfully ";
		
		session.invalidate();
		WarningMsg.showMessage(msg1);
		String msg="Please Login to Continue";
		WarningMsg.showDialog(msg);
		return model;
		

	}
		else{
			ModelAndView model=new ModelAndView("changePassword");
			String msg="Password doesn't match";
			WarningMsg.showDialog(msg);
			model.addObject("regBean",regBean);
			return model;
			
		}
	}
	}
	
	
	
	/**
	 * 
	 * @param emailId
	 * @param regBean
	 * @param request
	 * @param response
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value="/editProfile")
	public ModelAndView editProfile(@ModelAttribute RegBean regBean, HttpServletRequest request, HttpServletResponse response,LoginBean loginBean) {
		HttpSession session = request.getSession(false);
		String user=(String)session.getAttribute("role");
		if((user==null)||((user!=("admin"))&&(user!=("member")))){

			ModelAndView model=new ModelAndView("login");
			request.setAttribute("message","InValid Access Please Log In To Continue");
			model.addObject("loginBean", loginBean);
			return model;


		}else{

			String emailId=(String) session.getAttribute("loggedInUser");
			System.out.println(emailId);
			regBean = rolesService.getUser(emailId);

			ModelAndView model= new ModelAndView("editProfile");
			model.addObject("regBean",regBean);

			return model;
		}
	}


	@RequestMapping("/eupdate")
	public String updateProfile(@ModelAttribute RegBean regBean) {
		rolesService.updateProfile(regBean);
		String msg= "Profile updated successfully";
		WarningMsg.showMessage(msg);
		return "redirect:/view";

	}


	@RequestMapping("/view")
public String resolvingView(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession(false);
		String role=(String) session.getAttribute("role");
		if(role.equals("admin")){
			return "redirect:/admin";
		}
		else if(role.equals("member")){
			return "redirect:/member";
		}
		else if(role.equals("guest")){
			return "redirect:/guest";
		}
		else{
			
			String msg="Please Login To Continue OR Click on Guest to continue as Guest";
			WarningMsg.showDialog(msg);
			return"redirect:/login";
		}
		
		
	}

	@RequestMapping(value="/increaseLike")
	public String increaseLike(@RequestParam("queryId") int queryId,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String user=(String)session.getAttribute("role");
		
			rolesService.increaseLike(queryId);
			if(user.equals("admin")){
			return "redirect:/viewAns?queryId="+queryId;
			}
			else
			{
				return "redirect:/viewAns?queryId="+queryId;
			}
		
	}

	@RequestMapping(value= "/guestWelcome", method = RequestMethod.GET)
	public ModelAndView viewQuestionsGuest(HttpServletRequest request,@ModelAttribute QuestionBean questionBean,
			@ModelAttribute LoginBean loginBean) {
		List<QuestionBean> queList=new ArrayList<QuestionBean>();
		
		queList=questionService.viewQuestion(questionBean, loginBean);
		
		
		
		ModelAndView model=new ModelAndView("guestWelcome");
		List<ForumBean> forumBean = forumService.getForumList();
		    model.addObject("forumBean",forumBean);
			model.addObject("queList",queList);
		
		return model;		
	}
	
	
}
