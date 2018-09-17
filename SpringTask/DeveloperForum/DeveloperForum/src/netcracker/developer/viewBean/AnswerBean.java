package netcracker.developer.viewBean;

import java.sql.Date;

public class AnswerBean {

	
	
	 private int solutionId;
	 private String solutionDesc;
	 private String userId;  
	 private int queryId;
	 private Date date_of_posting;
	 
	public int getSolutionId() {
		return solutionId;
	}
	
	public void setSolutionId(int i) {
		this.solutionId = i;
	}
	
	
	
	public String getSolutionDesc() {
		return solutionDesc;
	}

	public void setSolutionDesc(String solutionDesc) {
		this.solutionDesc = solutionDesc;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getQueryId() {
		return queryId;
	}
	
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	
	public Date getDate_of_posting() {
		return date_of_posting;
	}
	
	public void setDate_of_posting(Date date_of_posting) {
		this.date_of_posting = date_of_posting;
	}
	 
	 
	 
}
