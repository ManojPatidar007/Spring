/**
 * ForumController.java    defined under netcracker.developer.controller package
 *        This java controller class deals with creating forum,deleting forum 
 *       
 *         Only  Administrator can create a forum and delete it.         
 */
//package
package netcracker.developer.controller;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import netcracker.developer.imp.WarningMsg;
import netcracker.developer.service.ForumService;
import netcracker.developer.viewBean.ForumBean;
import netcracker.developer.viewBean.LoginBean;
/**
 * @author kaas0616
 */
@Controller
public class ForumController {
	
	
	private Logger logger=Logger.getLogger(ForumController.class);
	
	/**
	 *  @Autowired annotation can be used to auto wire bean .
	 *  @Autowired annotation on properties to get rid of the setter methods. 
	 *  When you will pass values of autowired properties using <property> 
	 *  Spring will automatically assign those properties with the passed values or references.
	 *  
	 */
	
	/**
	 * since ForumService is using forumDao property , ForumService is autowired
	 */

	@Autowired
	ForumService forumService;
	

	/*@RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
	 * */

/**
 *  RequestMapping handles createForum URL  
 *  and calls createForum method.
 * 
 * @param request  on HttpServletRequest used to get HttpSession
 * @param loginBean   used when someone  Entered URL illegally in web browser
 * @param forumBean used to send updated list of forums
 * @return String  redirects user to admin URL
 * @throws SQLException 
 */
	
	 @RequestMapping("/createForum")
	 public String createForum(HttpServletRequest request,LoginBean loginBean,ForumBean forumBean) throws SQLException {
		 /**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
		 
	  HttpSession session = request.getSession(false);
	  /**
		 * user type stores role attribute
		 * user gets null value or if he is not admin  indicates session is not created yet and shows it is an invalid access 
		 */
	  
	  String user=(String)session.getAttribute("role");
	  if(user==null&&user!="admin"){
	   return "redirect:/logout";
	   
	  }else{
		  /**
		   * new forum name for creating  is taken from admin by using JOptionPane
		   */

	   /**
	    * forumService calls createForum method to insert new forumName in database
	    */
	 
	 
	   
	   //JOptionPane inpOption = new JOptionPane();

	    //Shows a inputdialog
	    String forumName = JOptionPane.showInputDialog("Enter a Forum Name: "); 

	    //if OK is pushed then (if not forumName is null)
	    if (forumName != null){
	    	
	    	      /**
	    	       * isCreatedForumSuccessful  boolean datatype checks if forum Name already exists or not in database
	    	       */
	    				
	    		 if(forumName.equals("")){
	    			 String msg="Forum Name should be filled by value";
	 					WarningMsg.showMessage(msg);
	    			 return "redirect:/admin";
	    		 }else{
	    			 
	    		 
	    		 boolean isCreatedForumSuccessful = forumService.createForum(forumName);
	    		 try {			
	    		 if (isCreatedForumSuccessful) {
	    			 String msg="Forum created Successfull";
 					WarningMsg.showMessage(msg);
	    					logger.info("Forum created Successfull");
	    					return "redirect:/admin";//return
	    		
	    				} else {
	    					
	    					logger.error("Forum created UnSuccessfull");
	    					String msg="Forum created UnSuccessfull";
	    					WarningMsg.showMessage(msg);
	    					return "redirect:/admin";//return		

	    			}
	    		   }catch (Exception e) {
	    				logger.error(e);
	    				e.printStackTrace();
	    			}
	    }
	    }
	  
	    //If cancel button is pressed
	    else{

	    	return "redirect:/admin";

	    }
	  }
	  return "redirect:/admin";
	 }
	 
	 
	 
	 /**
	  *  RequestMapping handles deleteForum URL  
	  *  and calls deleteForum method.
	  * 
	  * @param request  on HttpServletRequest used to get HttpSession
	  * @param loginBean   used when someone  Entered URL illegally in web browser
	  * @param forumBean used to send updated list of forums
	  * @return String  redirects user to admin URL
	  */
		@RequestMapping(value="/deleteForum")
	public String deleteForum(@ModelAttribute LoginBean loginBean,@ModelAttribute ForumBean forumBean1,@RequestParam("forumName") String forumName,HttpServletRequest request ) {
			 /**
			 *  session object is created here and request.getSession(false) returns current session if it is there and 
			 *  doesn't create a new session 
			 */
		 
	  HttpSession session = request.getSession(false);
	  /**
		 * user type stores role attribute
		 * user gets null value or if he is not admin  indicates session is not created yet and shows it is an invalid access 
		 */
	  
	  String user=(String)session.getAttribute("role");
	  ModelAndView model=null;
	  if(user==null&&user!="admin"){
		  String msg="InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);
		  return "redirect:/logout";

	  }else{
		  
		  /**
		    * forumService calls deleteForum method to delete  forumName in database
		    */
		
		/**
		 * we need to update the forumList before passing the model with appropriate view
		 * 
		 *  forumBean uses forumService	to get updated forumList
		 */
		//Confirm dialog box is used to ask for confirmation of deletion.
				int option = JOptionPane.showConfirmDialog(null, "Press Ok to delete", "Confirm", JOptionPane.YES_NO_OPTION);
				//If answer is yes then deleting the user
				  if (option == 0) { //The ISSUE is here
				     System.out.print("delete");
				    
				     forumService.deleteForum(forumName);
				     String msg="Forum Deleted Successfull";
	 					WarningMsg.showMessage(msg);
				     //redirecting to see the new List after deletion.
				     return "redirect:/admin";
				  } else {
				     System.out.print("no");
				     String msg="You selected No option so Forum no created ";
	 					WarningMsg.showMessage(msg);
				     //redirecting to see the new List after deletion.
				     return "redirect:/admin";
				  }

		  
	  }
	  
	
	}

	
	 
}