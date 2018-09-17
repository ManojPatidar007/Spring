package netcracker.developer.dao;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.viewBean.AnswerBean;
import netcracker.developer.viewBean.QuestionBean;

public interface AnswerDao {
	
	
	List<AnswerBean> getAnswers(AnswerBean answerBean, int queryId);
	public List<QuestionBean> getQuestion(QuestionBean questionBean, int queryId);
	public void insertAnswer(AnswerBean answerBean, String username, int queryd);
	boolean isValidQueryId(int queryId) throws SQLException;
	
		
	
}
