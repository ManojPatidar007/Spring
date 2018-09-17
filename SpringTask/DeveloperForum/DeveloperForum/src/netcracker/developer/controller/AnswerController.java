package netcracker.developer.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.service.AnswerService;
import netcracker.developer.service.ForumService;
import netcracker.developer.service.QuestionService;
import netcracker.developer.viewBean.AnswerBean;
import netcracker.developer.viewBean.ForumBean;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;
import sun.util.logging.resources.logging;

/**
 * 
 * @author yogs0616
 * 
 *         This is the controller class for viewing answers in the database
 * @Controller Annotation used to denote the class as Controller Class
 *
 */
@Controller
public class AnswerController {

	// Defining logger for AnswerController page
	private Logger logger = Logger.getLogger(AnswerController.class);

	/**
	 * @Autowired annotation on setter methods to get rid of the <property>
	 *            element in XML configuration file.
	 */


	@Autowired
	private AnswerService answerService;

	@Autowired
	private ForumService forumService;

	@Autowired
	private QuestionService questionService;

	/**
	 * @RequestMapping Annotation for mapping web requests onto specific handler
	 *                 classes and/or handler methods.
	 * @param request
	 *            this is the {@link HttpServletRequest} object to handle
	 *            requests from browser
	 * @param response
	 *            this is the {@link HttpServletResponse} object to send
	 *            responses to the browser.
	 * @param answerBean
	 *            Object of Bean Class {@link AnswerBean}
	 * @param result
	 *            {@link BindingResult} object
	 * @param solutionDesc
	 *            requested parameter description of solution form jsp page
	 * @return will redirect to logout and view answer pages based on specific
	 *         requests.
	 */
	@RequestMapping(value = "/viewAns", method = RequestMethod.POST)
	public String postAnswer(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute() AnswerBean answerBean, BindingResult result,
			@RequestParam("solutionDesc") String solutionDesc) {
		HttpSession session = request.getSession(false);
		String user = (String) session.getAttribute("role");
		if (user == null) {

			request.setAttribute("message", "InValid Access Please Log In To Continue");
			return "redirect:/logout";
		} else {

			/**
			 * getting the current logged in user and the query id from the
			 * HttpSession
			 * 
			 */
			String username = (String) session.getAttribute("loggedInUser");
			int queryId = (int) session.getAttribute("qId");
			/**
			 * calling insertAnswer function to insert the answer into the
			 * database.
			 */
			answerService.insertAnswer(answerBean, username, queryId);
			String msg = "Answer posted successfully";
			WarningMsg.showMessage(msg);
			session.removeAttribute("qId");
			if (user.equals("admin")) {
				return "redirect:/viewAns?queryId=" + queryId;
			} else {
				return "redirect:/viewAns?queryId=" + queryId;
			}

		}

	}

	/**
	 * 
	 * @param queryId
	 *            requested parameter of query id from the jsp page.
	 * @param request
	 *            this is the {@link HttpServletRequest} object to handle
	 *            requests from browser
	 * @param response
	 *            this is the {@link HttpServletResponse} object to send
	 *            responses to the browser.
	 * @param answerBean
	 *            Object of Bean Class {@link AnswerBean}
	 * @param questionBean
	 *            Object of Bean Class {@link QuestionBean}
	 * @param loginBean
	 *            Object of Bean Class {@link LoginBean}
	 * @return Object of {@link ModelAndView} and model the jsp pages based on
	 *         GET requests.
	 * @throws SQLException
	 */
	@RequestMapping(value = "/viewAns", method = RequestMethod.GET)
	public ModelAndView viewAnswers(@RequestParam("queryId") int queryId, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute AnswerBean answerBean,
			@ModelAttribute("QuestionBean") QuestionBean questionBean, @ModelAttribute LoginBean loginBean)
			throws SQLException {
		ModelAndView model = null;
		HttpSession session = request.getSession(false);
		String user = (String) session.getAttribute("role");
		if (user == null) {
			model = new ModelAndView("login");
			request.setAttribute("message", "InValid Access Please Log In To Continue");

			String msg = "InValid Access Please Log In To Continue";
			WarningMsg.showDialog(msg);

			model.addObject("loginBean", loginBean);

		} else {
			boolean isValidqId = answerService.isValidQueryId(queryId);
			if (isValidqId) {
				List<AnswerBean> answerList = new ArrayList<AnswerBean>();
				List<QuestionBean> queList = new ArrayList<QuestionBean>();
				/**
				 * function call to get the list of answer which is to be
				 * displayed on the Administrator or member page specific to the
				 * query id.
				 */
				answerList = answerService.getAnswers(answerBean, queryId);
				/**
				 * function call to get the question requested by query id
				 */
				queList = answerService.getQuestion(questionBean, queryId);
				session.setAttribute("qId", queryId);
				if (answerList.isEmpty()) {
					session.setAttribute("Answer", "No Answers Found");
				} else {
					session.setAttribute("Answer", "");
				}
				if (user.equals("admin")) {
					model = new ModelAndView("adminWelcome2");
				} else {
					model = new ModelAndView("memberWelcome2");
				}
				/**
				 * adding the question and answer list to the model which is
				 * object of the ModelAndView.
				 */
				List<ForumBean> forumBean = forumService.getForumList();
				model.addObject("forumBean", forumBean);
				model.addObject("answerList", answerList);
				model.addObject("queList", queList);

			} else {
				String msg = "Query doesnt exist";
				WarningMsg.showDialog(msg);
				// List of Questions is created and stored in queList
				List<QuestionBean> queList = new ArrayList<QuestionBean>();
				// List of Forum is created and ForumList have been fetched from
				// the database and stored in forumBean .
				List<ForumBean> forumBean = forumService.getForumList();
				// questions are retrieved from the database using viewQuestion
				// method and storing in queList
				queList = questionService.viewQuestion(questionBean, loginBean);
				// New AdminWelcome model is created
				model = new ModelAndView("adminWelcome");
				// List of questions have been added to that model
				model.addObject("queList", queList);
				// List of forumNames have been added to that model
				model.addObject("forumBean", forumBean);
				logger.info("Admin model created and returned");

			}

		}
		return model;
	}

	/**
	 * @RequestMapping Annotation for mapping web requests onto specific handler
	 *                 classes and/or handler methods.
	 * @param request
	 *            this is the {@link HttpServletRequest} object to handle
	 *            requests from browser
	 * @param response
	 *            this is the {@link HttpServletResponse} object to send
	 *            responses to the browser.
	 * @param answerBean
	 *            Object of Bean Class {@link AnswerBean}
	 * @param result
	 *            {@link BindingResult} object
	 * @param questionBean
	 *            Object of Bean Class {@link QuestionBean}
	 * @param loginBean
	 *            Object of Bean Class {@link LoginBean}
	 * @return Object of {@link ModelAndView} and model the jsp pages based on
	 *         GET requests.
	 * @throws SQLException
	 */
	@RequestMapping(value = "/guestWelcome2", method = RequestMethod.GET)
	public ModelAndView postAnswersGuest(@RequestParam("queryId") int queryId, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("AnswerBean") AnswerBean answerBean,
			@ModelAttribute("QuestionBean") QuestionBean questionBean, BindingResult result,
			@ModelAttribute LoginBean loginBean) throws SQLException {
		HttpSession session = request.getSession(false);
		List<AnswerBean> answerList = new ArrayList<AnswerBean>();
		List<QuestionBean> queList = new ArrayList<QuestionBean>();
		ModelAndView model = null;
		boolean isValidqId = answerService.isValidQueryId(queryId);
		if (isValidqId) {

			/**
			 * function call to get the question requested by query id
			 */
			queList = answerService.getQuestion(questionBean, queryId);
			/**
			 * function call to get the list of answer which is to be displayed
			 * on the guest page specific to the query id.
			 */
			answerList = answerService.getAnswers(answerBean, queryId);
			if(answerList.isEmpty()){
				   session.setAttribute("Answer","No Answers Found");
				  }
				  else{
				   session.setAttribute("Answer","");
				  }
			model = new ModelAndView("guestWelcome2");
			/**
			 * adding the question and answer list to the model which is object
			 * of the ModelAndView.
			 */
			model.addObject("answerList", answerList);

		} else {
			String msg = "Query doesnt exist";
			WarningMsg.showDialog(msg);
			queList = questionService.viewQuestion(questionBean, loginBean);
			model = new ModelAndView("guestWelcome");

		}
		model.addObject("queList", queList);
		List<ForumBean> forumBean = forumService.getForumList();
		model.addObject("forumBean", forumBean);
		return model;
	}

}
