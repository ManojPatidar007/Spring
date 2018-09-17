package netcracker.developer.service;

import java.sql.SQLException;
import java.util.List;

import netcracker.developer.viewBean.AnswerBean;
import netcracker.developer.viewBean.QuestionBean;

public interface AnswerService {

	void insertAnswer(AnswerBean answerBean, String username, int queryId);

	List<AnswerBean> getAnswers(AnswerBean answerBean, int queryId);


	void increaseAnswer(QuestionBean questionBean);

	List<QuestionBean> getQuestion(QuestionBean questionBean, int queryId);

	boolean isValidQueryId(int queryId) throws SQLException;

}
