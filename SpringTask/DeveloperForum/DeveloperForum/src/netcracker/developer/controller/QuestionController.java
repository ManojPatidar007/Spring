/**
 * QuestionController.java    defined under netcracker.developer.controller package
 *        This java controller class deals with posting ,viewing ,filtering questions
 *        by likes and latest time by different roles like admin,member,guest
 *       
 *         Only Members and Administrator can post a question under forum.  
 *         Administrator, Member and Guest can view a question and answer
 *         
 */


// package 
package netcracker.developer.controller;
/**
 *   import : different needed packages are imported 
 *   
 * */

import java.sql.SQLException;
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
import netcracker.developer.viewBean.ForumBean;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
/** 
 * @author kaas0616
 */
@Controller
public class QuestionController {

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
	 * since ForumService is using forumDao property , ForumService is autowired
	 */
		@Autowired
		private ForumService forumService;

		/**
		 * since QuestionService is using questionDao property ,QuestionService is autowired
		 */
		
		@Autowired
		private QuestionService questionService;
		
		
	
		/*@RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
		  */
	
	/**
	 *  RequestMapping handles postQue URL  under GET request
	 *  and calls adminPostQuestions1 method.
	 * 
	 * @param request  on HttpServletRequest used to get HttpSession
	 * @param response on HttpServletResponse
	 * @param questionBean  used to send  updated list of questions
	 * @param loginBean   used when someone  Entered URL illegally in web browser
	 * @return ModelAndView depends on type of role ,the appropriate models with corresponding views allotted for the role 
	 */
		
		@RequestMapping(value= "/postQue",method=RequestMethod.GET)
		public ModelAndView adminpostQuestions1(HttpServletRequest request,HttpServletResponse response,@ModelAttribute QuestionBean questionBean, LoginBean loginBean ) {
			
			/**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
			HttpSession session = request.getSession(false);
			/**
			 * user type stores role attribute
			 * user gets null value indicates session is not created yet and shows it is an invalid access 
			 */
			String user=(String)session.getAttribute("role");
			if(user==null){
				ModelAndView model=new ModelAndView("login");
				String msg="InValid Access Please Log In To Continue";
				WarningMsg.showDialog(msg);
				model.addObject("loginBean", loginBean);
				return model;
			}
			/**
			 * user is not null now
			 * and if user is admin ,we are giving user a adminPostQue View
			 */
			else{
				ModelAndView model=null;
				if(user.equals("admin")){
					
					model=new ModelAndView("adminPostQue");
				}
				/**
				 * user is not null now
				 * and if user is member ,we are giving user a memberPostQue View
				 */
				else{
					model=new ModelAndView("memberPostQ");
				}
			/**
			 * we need to update the forumList before passing the model with appropriate view
			 * 
			 *  forumBean uses forumService	to get updated forumList
			 */
				
			List<ForumBean> forumBean = forumService.getForumList();
			/**
			 * mes attribute is set  to inform user ,question posting was successful
			 */
			
			/**
			 * model is getting added  object questionBean  to retrieve queryId,queryDesc,queryHeading,no_of_likes,no_of_answers  in view page
			 *  model is getting added  object forumBean to retrieve forumNames in view page
			 */
			model.addObject("questionBean", questionBean);
			model.addObject("forumBean",forumBean);	
			return model;		//returns a model
		}
		}
		
		
		/**
		 *  RequestMapping handles postQue URL under POST method 
	     *  and calls adminPostQuestions method.
		 * @param request on HttpServletRequest used to get HttpSession
		 * @param response on HttpServlet Response
		 * @param questionBean used to send  updated list of questions
		 * @param loginBean used when someone  Entered URL illegally in web browser
		 * @return String depends on type of role ,the appropriate URLs with corresponding redirections allotted for the role
		 */
		@RequestMapping(value= "/postQue",method=RequestMethod.POST)
		public String adminpostQuestions(HttpServletRequest request,HttpServletResponse response,@ModelAttribute QuestionBean questionBean, LoginBean loginBean) {
			
			/**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
			HttpSession session = request.getSession(false);
			/**
			 * user type stores role attribute
			 * user gets null value indicates session is not created yet and shows it is an invalid access 
			 */
			String user=(String)session.getAttribute("role");
			
			if(user==null){
				
				return "redirect:/login";
			}
			else{
					String username=(String) session.getAttribute("loggedInUser");
					
					questionService.insertQuestion(questionBean, username);
					String msg= "Posted Question successfully";
					WarningMsg.showMessage(msg);
			
					request.setAttribute("mes", "member Posted successfull");
					/**
					 * user is not null now
					 * and if user is admin ,we are redirecting him to a admin URL
					 */
						if(user.equals("admin")){
							
							//System.out.println("admin");
							return "redirect:/admin";	
						}
						/**
						 * user is not null now
						 * and if user is member ,we are redirecting him to a member URL
						 */
						else {
							//System.out.println("member");
							return "redirect:/member";	
						}
												
		}
		}
		/**
		 *  RequestMapping handles viewQue URL  
	     *  and calls adminviewQue method.
		 * @param request on HttpServletRequest used to get HttpSession
		 * @param response
		 * @param questionBean used to send  updated list of questions
		 * @param loginBean used when someone  Entered URL illegally in web browser
		 * @param forumName used to list questions from that particular forum
		 * @return  ModelAndView depends on type of role ,the appropriate models with corresponding views allotted for that role 
		 * @throws SQLException 
		 */
		@RequestMapping("/viewQue")
		public ModelAndView adminviewQue(HttpServletRequest request, HttpServletResponse response, @ModelAttribute  QuestionBean questionBean,@ModelAttribute LoginBean loginBean,@RequestParam("forumName") String forumName) throws SQLException {
			
			/**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
			HttpSession session = request.getSession(false);
			ModelAndView model=null;
			/**
			 * user type stores role attribute
			 */
			String user=(String)session.getAttribute("role");
			if(user==null){
				model = new ModelAndView("guestViewQue");
			}
			else{
				/**
				 * user is not null now
				 * and if user is admin ,we are giving user a adminViewQue View
				 */
				
				if(user.equals("admin")){
					model = new ModelAndView("adminViewQue");
				}
				/**
				 * user is not null now
				 * and if user is member ,we are giving user a memberViewQue View
				 */
				else{
					model = new ModelAndView("memberViewQue");
				}
			}
			
			/**
			 * we need to update the forumList,questions before passing the model with appropriate view
			 * 
			 *  forumBean uses forumService	to get updated forumList
			 *  QuestionBean uses questionService to get updated question List
			 */
				
				List<QuestionBean> queList=new ArrayList<QuestionBean>();
				List<ForumBean> forumBean =forumService.getForumList();
				
				List<String> forumNames=forumService.getForumNames();
				boolean i1=forumNames.contains(forumName);
				if(i1){
			    queList=questionService.viewQuestion(questionBean,forumName);
			    request.setAttribute("mes", "view successfull");
				}else{
					String msg="Forum Name Doesn't exist .Please Select the Existing Forum";
					WarningMsg.showDialog(msg);
					
					queList=questionService.viewQuestion(questionBean,loginBean);
				}
				session.setAttribute("ForumName",forumName);
				if(queList.isEmpty()){
			        session.setAttribute("Questions","No Questions Found");
			      }
			      else{
			      
			       session.setAttribute("Questions","");
			      }
				
			    /**
				 * model is getting added the list of objects questionBean to retrieve queryId,queryDesc,queryHeading,no_of_likes,no_of_answers  in view page
				 * model is getting added  the list of objects forumBean to retrieve forumNames in view page
				 */
				model.addObject("queList",queList);
				model.addObject("forumBean",forumBean);
				model.addObject("forumName",forumName);
				return model;//returns a model
		
		}
		
		




		/**
	     *  RequestMapping handles filterBylikeQue URL  
	     *  and calls filterByLikeQue method.
		 * @param request on HttpServletRequest used to get HttpSession
		 * @param response
		 * @param questionBean used to send  updated list of questions
		 * @param loginBean used when someone  Entered URL illegally in web browser
		 * @param forumName  used to list questions from that particular forum in sorted format by likes
		 * @return  ModelAndView depends on type of role ,the appropriate models with corresponding views allotted for that role 
		 */
		@RequestMapping("/filterByLikeQue")
		public ModelAndView filterByLikeQue(HttpServletRequest request, HttpServletResponse response, @ModelAttribute QuestionBean questionBean,@ModelAttribute LoginBean loginBean,@RequestParam("forumName") String forumName) {
			
			/**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
			HttpSession session = request.getSession(false);
			/**
			 * user type stores role attribute
			 */
			String user=(String)session.getAttribute("role");
			
				ModelAndView model =null;
				if(user==null){
					model = new ModelAndView("guestViewQue");
					
				}
				/**
				 * user is not null now
				 * and if user is admin ,we are giving him a adminViewQue View
				 */
				
				else if(user.equals("admin")){
					model = new ModelAndView("adminViewQue");
				}
				/**
				 * user is not null now
				 * and if user is member ,we are giving user a memberViewQue View
				 */
				
				else{
					model = new ModelAndView("memberViewQue");
				}
				/**
				 * we need to update the forumList,questions before passing the model with appropriate view
				 * 
				 *  forumBean uses forumService	to get updated forumList
				 *  QuestionBean uses questionService to get updated question List in sorted format by likes
				 */
					
	            List<QuestionBean> queList=new ArrayList<QuestionBean>();
	            List<ForumBean> forumBean = forumService.getForumList();
	            List<String> forumNames=forumService.getForumNames();
				boolean i1=forumNames.contains(forumName);
				
				if(i1){
					 queList=questionService.filterByLikeQuestion(questionBean,forumName);
					    request.setAttribute("mes", "filter by likesuccessfull");
				}else{
					String msg="Forum Name Doesn't exist .Please Select the Existing Forum";
					WarningMsg.showDialog(msg);
					
					queList=questionService.viewQuestion(questionBean,loginBean);
				}
				session.setAttribute("ForumName",forumName);
				if(queList.isEmpty()){
			        session.setAttribute("Questions","No Questions Found");
			      }
			      else{
			      
			       session.setAttribute("Questions","");
			      }
			   
			    /**
				 * model is getting added the list of objects questionBean to retrieve queryId,queryDesc,queryHeading,no_of_likes,no_of_answers  in view page
				 * model is getting added  the list of objects forumBean to retrieve forumNames in view page
				 */
				model.addObject("queList",queList);
				model.addObject("forumBean",forumBean);
				model.addObject("forumName",forumName);
			return model;//returns a model 
		
		}
		
		/**
	     *  RequestMapping handles filterByLatestQue URL  
	     *  and calls filterByLatestQue method.
		 * @param request on HttpServletRequest used to get HttpSession
		 * @param response
		 * @param questionBean used to send  updated list of questions
		 * @param loginBean used when someone  Entered URL illegally in web browser
		 * @param forumName  used to list questions from that particular forum in sorted format by latest
		 * @return  ModelAndView depends on type of role ,the appropriate models with corresponding views allotted for that role 
		 */
		@RequestMapping("/filterByLatestQue")
		public ModelAndView filterByLatestQue(HttpServletRequest request, HttpServletResponse response, @ModelAttribute QuestionBean questionBean,@ModelAttribute LoginBean loginBean,@RequestParam("forumName") String forumName) {
			
			/**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
			HttpSession session = request.getSession(false);
			/**
			 * user type stores role attribute
			 */
			String user=(String)session.getAttribute("role");
			
				ModelAndView model =null;
				
				if(user==null){
					model = new ModelAndView("guestViewQue");
					
				}
				/**
				 * user is not null now
				 * and if user is admin ,we are giving him a adminViewQue View
				 */
				else if(user.equals("admin")){
					model = new ModelAndView("adminViewQue");
				}
				/**
				 * user is not null now
				 * and if user is member ,we are giving him a memberViewQue View
				 */
				else{
					model = new ModelAndView("memberViewQue");
				}
				/**
				 * we need to update the forumList,questions before passing the model with appropriate view
				 * 
				 *  forumBean uses forumService	to get updated forumList
				 *  QuestionBean uses questionService to get updated question List in sorted format by likes
				 */
	           List<QuestionBean> queList=new ArrayList<QuestionBean>();
	           List<ForumBean> forumBean = forumService.getForumList();
	         
			
			 List<String> forumNames=forumService.getForumNames();
				boolean i1=forumNames.contains(forumName);
				
				if(i1){
					  queList=questionService.filterByLatestQuestion(questionBean,forumName);
						request.setAttribute("mes", "filter by likesuccessfull");
				}else{
					String msg="Forum Name Doesn't exist .Please Select the Existing Forum";
					WarningMsg.showDialog(msg);
					
					queList=questionService.viewQuestion(questionBean,loginBean);
				}
				session.setAttribute("ForumName",forumName);
				if(queList.isEmpty()){
			        session.setAttribute("Questions","No Questions Found");
			      }
			      else{
			      
			       session.setAttribute("Questions","");
			      }
			   

			/**
			 * model is getting added the list of objects questionBean to retrieve queryId,queryDesc,queryHeading,no_of_likes,no_of_answers  in view page
			 * model is getting added  the list of objects forumBean to retrieve forumNames in view page
			 */
				model.addObject("queList",queList);
				model.addObject("forumBean",forumBean);
				model.addObject("forumName",forumName);
			return model;//returns a model
	
		}	
		}
