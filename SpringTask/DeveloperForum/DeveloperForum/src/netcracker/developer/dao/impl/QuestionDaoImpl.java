/**
 * QuestionDaoImpl.java    defined under netcracker.developer.dao.impl package
 *        This java interface class deals with  DAO implementations of posting ,viewing ,filtering questions
 *        by likes and latest time by different roles like admin,member,guest
 *       
 *         Only Members and Administrator can post a question under forum.  
 *         Administrator, Member and Guest can view a question and answer
 *         
 */
//package
package netcracker.developer.dao.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import netcracker.developer.dao.QuestionDao;
import netcracker.developer.viewBean.LoginBean;
import netcracker.developer.viewBean.QuestionBean;

    /**
     * @author kaas0616
     */
	public class QuestionDaoImpl implements QuestionDao{
/**
 * DataSource contains connection parameters to open a connection with database
 */
		DataSource dataSource; 
		public DataSource getDataSource() {
			return dataSource;
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		/**
		 * getCurrentDate() method 
		 * 
		 * @return Date  used to store in database
		 */
		private static java.sql.Date getCurrentDate() {
		    java.util.Date today = new java.util.Date();
		    return new java.sql.Date(today.getTime());
		}
		
		/**
		 * insertQuestion method
		 * 
		 * @param questionBean needed to take query Values to insert into database
		 * @param username  used to show which user posted and to store in database
		 * @return nothing
		 */
		@Override
		public void insertQuestion(QuestionBean questionBean, String username) {
			
			try{
           /**
            * when dataSource connection parameters supplied to JdbcTemplate of new instance
            * it opens a DataBase connection 
            */
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "INSERT INTO queries  VALUES (? , ? , ? , ? , ? , ? , ? , ?)";
			//gets current Date
			questionBean.setDate_of_posting(getCurrentDate());
			//inserting questions in database
			jdbcTemplate.update(sql,
					new Object[] {questionBean.getQueryId(),questionBean.getQueryHeading(),questionBean.getQueryDesc(),username,
							questionBean.getForumName(),0,0,questionBean.getDate_of_posting()});
			
		}
			catch(Exception e)
			{
				System.out.println("exception");
			}
	}
		/**
		 * viewQuestion method
		 * 
		 * @param questionBean needed fetched queries from database to store in list of QuestionBean objects
		 * @param forumName used to fetch queries related to given forumName 
		 * @return List Of QuestionBean objects
		 * @throws SQLException 
		 */
		
		
		    @Override
			public List<QuestionBean> viewQuestion(QuestionBean questionBean, String forumName) throws SQLException {
		       

				
		
				
				String sql = "select * from queries where forumName=?";
				
		        /* when dataSource connection parameters supplied to JdbcTemplate of new instance
		            * it opens a DataBase connection 
		            */
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				/**
				 * RowMapper interface allows to map a row of the relations with the instance of QuestionBean class.
				 *  It iterates the ResultSet internally and adds it into the collection.
                 *   RowMapper adds the data of ResultSet into the QuestionBean collection.
                 *   It defines only one method mapRow that accepts ResultSet instance and int as the parameter list
				 */
				
				return jdbcTemplate.query(sql,new Object[] { forumName },new RowMapper<QuestionBean>(){  
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

		    /**
			 * viewQuestion method
			 * 
			 * @param questionBean needed fetched queries from database to store in list of QuestionBean objects
			 * @param loginBean used to fetch all queries
			 * @return List Of QuestionBean objects
			 */
		    
			@Override
			public List<QuestionBean> viewQuestion(QuestionBean questionBean, LoginBean loginBean) {
					
				String sql = "select * from queries";
				 /**
		            * when dataSource connection parameters supplied to JdbcTemplate of new instance
		            * it opens a DataBase connection 
		            */
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				/**
				 * RowMapper interface allows to map a row of the relations with the instance of QuestionBean class.
				 *  It iterates the ResultSet internally and adds it into the collection.
                 *   RowMapper adds the data of ResultSet into the QuestionBean collection.
                 *   It defines only one method mapRow that accepts ResultSet instance and int as the parameter list
				 */
				return jdbcTemplate.query(sql,new RowMapper<QuestionBean>(){  
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

			/**
		      * filterByLikeQuestion
		      * @param questionBean needed fetched queries from database to store in list of QuestionBean objects  in sorted format of likes
		      * @param forumName used to fetch queries related to given forumName 
		      * @return List Of QuestionBean objects
		      */
			@Override
			public List<QuestionBean> filterByLikeQuestion(QuestionBean questionBean, String forumName) {
				
				String sql = "select * from queries where forumName=? order by no_of_likes DESC";
				 /**
		            * when dataSource connection parameters supplied to JdbcTemplate of new instance
		            * it opens a DataBase connection 
		            */
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				/**
				 * RowMapper interface allows to map a row of the relations with the instance of QuestionBean class.
				 *  It iterates the ResultSet internally and adds it into the collection.
                 *   RowMapper adds the data of ResultSet into the QuestionBean collection.
                 *   It defines only one method mapRow that accepts ResultSet instance and int as the parameter list
				 */
				return jdbcTemplate.query(sql,new Object[] { forumName },new RowMapper<QuestionBean>(){  
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
            
			 /**
		       * filterByLatestQuestion 
		       * @param questionBean needed fetched queries from database to store in list of QuestionBean objects  in sorted format of latest time
		       * @param forumName used to fetch queries related to given forumName
		       * @return  List Of QuestionBean objects
		       */
			
			@Override
			public List<QuestionBean> filterByLatestQuestion(QuestionBean questionBean, String forumName) {
				
				String sql = "select * from queries where forumName=? order by date_of_posting DESC";
				 /**
		            * when dataSource connection parameters supplied to JdbcTemplate of new instance
		            * it opens a DataBase connection 
		            */
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				/**
				 * RowMapper interface allows to map a row of the relations with the instance of QuestionBean class.
				 *  It iterates the ResultSet internally and adds it into the collection.
                 *   RowMapper adds the data of ResultSet into the QuestionBean collection.
                 *   It defines only one method mapRow that accepts ResultSet instance and int as the parameter list
				 */
				return jdbcTemplate.query(sql,new Object[] { forumName },new RowMapper<QuestionBean>(){  
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

			
		}//QuestionDaoImpl ends
	

