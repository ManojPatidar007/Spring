            package netcracker.developer.service.impl;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.dao.AnswerDao;
import netcracker.developer.service.AnswerService;
import netcracker.developer.viewBean.AnswerBean;
import netcracker.developer.viewBean.QuestionBean;

public class AnswerServiceImpl implements AnswerService {
	
	AnswerDao answerDao;
	public AnswerDao getAnswerDao() {
		return answerDao;
	}
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	@Override
	public void insertAnswer(AnswerBean answerBean, String username, int queryId) {
	
	answerDao.insertAnswer(answerBean,username,queryId);
		}
	@Override
	public void increaseAnswer(QuestionBean questionBean) {
	increaseAnswer(questionBean);
		
	}
	@Override
	public List<AnswerBean> getAnswers(AnswerBean answerBean, int queryId) {
		
		return answerDao.getAnswers(answerBean, queryId);
	}
	@Override
	public List<QuestionBean> getQuestion(QuestionBean questionBean, int queryId) {
		return answerDao.getQuestion(questionBean,queryId);
		
	}
	@Override
	public boolean isValidQueryId(int queryId) throws SQLException {
		
		return  answerDao.isValidQueryId(queryId);
	}
	

}
