/**
 * ForumDaoImpl.java    defined under netcracker.developer.dao.Impl package
 *        This java  class deals DAO implementations with creating forum,deleting forum ,getting list of forums
 *       
 *         Only  Administrator can create a forum and delete it.         
 */

package netcracker.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;

import netcracker.developer.dao.ForumDao;
import netcracker.developer.imp.ExceptionHandling;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.viewBean.ForumBean;

public class ForumDaoImpl implements ForumDao {
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
		 * getForumList
		 * 
		 * @return List of forums 
		 */
	@Override
	 public List<ForumBean> getForumList() {
	  /**
         * when dataSource connection parameters supplied to JdbcTemplate of new instance
         * it opens a DataBase connection 
         */
	  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	  String sql = "select * from forums";
	  
	  /**
		 * RowMapper interface allows to map a row of the relations with the instance of ForumBean class.
		 *  It iterates the ResultSet internally and adds it into the collection.
         *   RowMapper adds the data of ResultSet into the ForumBean collection.
         *   It defines only one method mapRow that accepts ResultSet instance and int as the parameter list
		 */

	  return jdbcTemplate.query(sql,new RowMapper<ForumBean>(){  
	       
			public ForumBean mapRow(java.sql.ResultSet rs, int row) throws SQLException {
				ForumBean  forumList1=new ForumBean();
				 forumList1.setForumName(rs.getString(1));
				 return forumList1;
			}  
	    });
	 }
	
	
	/**
	 * createForum 
	 * 	  
	 * @param forumName used to insert forum in database
	 * @return nothing
	 */


	@ExceptionHandler({ExceptionHandling.class})
	 public boolean createForum(String forumName) throws SQLException {
	  boolean b=false;
	  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	  System.out.println(forumName);
	   try{
		   String sql = "INSERT INTO forums  VALUES (?)";
	   
	    /**
         * when dataSource connection parameters supplied to JdbcTemplate of new instance
         * it opens a DataBase connection 
         */
	  
	    int i=jdbcTemplate.update(sql,
	      new Object[] {forumName});
	    if(i==1){
	    b=true;
	    }
	    else{
	    	b=false;
	    }
	 }catch(DuplicateKeyException e){
		 System.out.println("ForumName Already  Exist");
			String msg="ForumName Already  Exist";
		
			WarningMsg.showDialog(msg); 
	 }catch (DataAccessException e) {
			e.printStackTrace();
		}
	   return b;
	}
	/**
	 * deleteForum 
	 * 
	 * @param forumName used to delete forum from database
	 * @return nothing
	 */
	 @Override
		public void deleteForum(String forumName) {
			
			String sql = "delete from forums where forumName='" +forumName+"'";
			/**
	         * when dataSource connection parameters supplied to JdbcTemplate of new instance
	         * it opens a DataBase connection 
	         */
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql);
			
		}

	@Override
	public List<String> getForumNames() {
		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		  String sql = "select * from forums";
		  return jdbcTemplate.query(sql,new RowMapper<String>(){  
		       
				public String mapRow(java.sql.ResultSet rs, int row) throws SQLException {
					
					 return rs.getString(1);
				}  
		    });

	
}//ForumDaoImpl class ends

	@Override
	public ForumBean getForumBean(String forumName) {
		ForumBean f=new ForumBean();
		f.setForumName(forumName);
		return f;
	}
}