package netcracker.developer.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import netcracker.developer.dao.AnswerDao;
import netcracker.developer.viewBean.AnswerBean;
import netcracker.developer.viewBean.QuestionBean;

public class AnswerDaoImpl implements AnswerDao {
	DataSource dataSource; 

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	@Override
	public void insertAnswer(AnswerBean answerBean, String username,int queryId) {
		
			
			//System.out.println(username);
			//System.out.println(queryId);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "INSERT INTO solutions  VALUES (? , ? , ? , ? , ? )";
			String query= "update queries set no_of_answers=no_of_answers+1 where queryId="+queryId;
			answerBean.setDate_of_posting(getCurrentDate());
			jdbcTemplate.update(sql,
					new Object[] {answerBean.getSolutionId(),answerBean.getSolutionDesc(),username,
							queryId,answerBean.getDate_of_posting()});
			
			jdbcTemplate.execute(query);

		
	}
	/**
	 * @return 
	 * 
	 */
	@Override
	public List<AnswerBean> getAnswers(AnswerBean answerBean, int queryId) {
		

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select * from solutions where queryId=?";
			
			return jdbcTemplate.query(sql,new Object[] { queryId },new RowMapper<AnswerBean>(){  
		        public AnswerBean mapRow(ResultSet rs, int row) throws SQLException {  
		        	AnswerBean answerBean=new AnswerBean();
		        	  answerBean.setSolutionId(rs.getInt(1));
		        	  answerBean.setSolutionDesc(rs.getString(2));
		        	  answerBean.setUserId(rs.getString(3));
		        	  answerBean.setQueryId(rs.getInt(4));
		        	  answerBean.setDate_of_posting(rs.getDate(5));
		            return answerBean;  
		        }  
		    }); 

	}

	


	@Override
	public List<QuestionBean> getQuestion(QuestionBean questionBean, int queryId) {
		
		String sql = "select * from queries where queryId=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(sql,new Object[] { queryId },new RowMapper<QuestionBean>(){  
	        public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {  
	        	QuestionBean questionBean=new QuestionBean();
	        	questionBean.setQueryId(rs.getInt(1));    
	        	questionBean.setQueryHeading(rs.getString(2));    
	        	questionBean.setQueryDesc(rs.getString(3));    
	        	questionBean.setUserId(rs.getString(4));    
	        	questionBean.setForumName(rs.getString(5));    
	        	questionBean.setNo_of_likes(rs.getInt(6));    
	        	questionBean.setNo_of_answers(rs.getInt(7));    
	        	questionBean.setDate_of_posting(rs.getDate(8));    
	            return questionBean;  
	        }  
	    });  
	}

	@Override
	public boolean isValidQueryId(int queryId) throws SQLException{
		String query = "Select count(1) from queries where queryId = ? ";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, queryId);
	
		
		
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next())
			return (resultSet.getInt(1) > 0);
		else
			return false;
	}
}

	


