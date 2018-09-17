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
import netcracker.developer.service.MemberService;
import netcracker.developer.service.QuestionService;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
import netcracker.developer.viewBean.RegBean;
/**
 * 
 * MemberPageController.java: MemberPageController  is a controller class to handle one or multiple requests for MemberPage.
 * @author lavp0616
 *
 */
@Controller
public class MemberPageController {
	
	// Declaring  MemberService methods to call the MemberDaoImplementation. 
	@Autowired
	private MemberService memberService;
	
	// Declaring QuestionService methods to call the QuestionDaoImplementation. 
	@Autowired 
	private QuestionService questionService;


	/**
	 * viewQuestionMember method : This method is executed when a member makes a request for memberPage .After successfull validation 
	 * member model is created and returned. 
	 * @param request
	 * @param questionBean
	 * @param loginBean
	 * @return
	 */
	@RequestMapping(value= "/member", method = RequestMethod.GET)
	public ModelAndView viewQuestionsMember(HttpServletRequest request,@ModelAttribute QuestionBean questionBean,
			@ModelAttribute LoginBean loginBean) {
			// Gets a previous session
			HttpSession session = request.getSession(false);
			//Getting the attribute value of role from previous session and storing in user . 
			String user=(String)session.getAttribute("role");
			/*If user is a null means  role attribute has not been set in previous session.Because for guest, Session is not created and 
			 * if a guest tries to access this page then he is redirected to login page.If a admin tries to access then his session is
			 * invalidated and redirected to login. 
			*/
		if(user==null||(user!=("member"))||(user.equals("admin"))){

			ModelAndView model=new ModelAndView("login");
			String msg="InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);
		
			model.addObject("loginBean", loginBean);
			return model;
		}
		else {
		//List of Question is created and stored in queList	
		List<QuestionBean> queList=new ArrayList<QuestionBean>();
		//questions are retrieved from the database using viewQuestion method and storing in queList
		queList=questionService.viewQuestion(questionBean, loginBean);
		if(queList.isEmpty()){
	        session.setAttribute("Questions","No Questions Found");
	      }
	      else{
	      
	       session.setAttribute("Questions","");
	      }
		//New AdminWelcome model is created
		ModelAndView model=new ModelAndView("memberWelcome");
		//List of questions have been added to that model
		model.addObject("queList",queList);
		//return newly created model.
		return model;		
	}
	
	
	}
	
	
	/**
	 * viewQuestionMember method : This method is executed when a member makes a request for memberPage .After successfull validation 
	 * member model is created and returned. 
	 * @param emailId
	 * @param request
	 * @param response
	 * @param regBean
	 * @return
	 */
	@RequestMapping(value="/editState", method = RequestMethod.GET)
	public String updateState(@RequestParam("emailId") String emailId,HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute RegBean regBean) {
		//member service calling getUser method to retrieve all the information of that user
		regBean=memberService.getUser(emailId);
		//Calling updateState for deactivation. 
		 memberService.updateState(regBean);
		 //redirecting to logout.
		return "redirect:/logout";

	}
	
	

	

}
