package netcracker.developer.viewBean;

import java.sql.Date;

public class QuestionBean {
	private int queryId;
	private String queryHeading;
	private String queryDesc;
	private String userId;
	private String forumName;
	private int no_of_likes= 0;
	private int no_of_answers= 0;
	private Date date_of_posting;
	
	
	
	
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public String getQueryHeading() {
		return queryHeading;
	}
	public void setQueryHeading(String queryHeading) {
		this.queryHeading = queryHeading;
	}
	public String getQueryDesc() {
		return queryDesc;
	}
	public void setQueryDesc(String queryDesc) {
		this.queryDesc = queryDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getNo_of_likes() {
		return no_of_likes;
	}
	public void setNo_of_likes(int no_of_likes) {
		this.no_of_likes = no_of_likes;
	}
	public int getNo_of_answers() {
		return no_of_answers;
	}
	public void setNo_of_answers(int no_of_answers) {
		this.no_of_answers = no_of_answers;
	}
	public Date getDate_of_posting() {
		return date_of_posting;
	}
	public void setDate_of_posting(Date date_of_posting) {
		this.date_of_posting = date_of_posting;
	}
	
	
	
}
