package netcracker.developer.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;

import netcracker.developer.dao.RegDao;
import netcracker.developer.imp.ExceptionHandling;
import netcracker.developer.imp.WarningMsg;
import netcracker.developer.viewBean.RegBean;

public class RegDaoImpl implements RegDao {
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@ExceptionHandler({ExceptionHandling.class})
	public boolean isRegSuccesful(RegBean regBean) throws SQLException {

		
		boolean b=false;
		try{
			JdbcTemplate template = new JdbcTemplate(dataSource);
		String query = "insert into user values(?,?,?,?,?,?,?)";
		int i=template.update(query,
				new Object[] {regBean.getEmailId(), regBean.getFirstName(), regBean.getLastName(),
						regBean.getMobileNumber(),regBean.getPassword(),2,"active"});

		
		if(i==1){
			b=true;
		}
		else {
			b=false;
		}
		
		}
	catch (DuplicateKeyException e) {
		System.out.println("EmailId Already  Exist");
		String msg="EmailId Already  Exist";
		//JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
		WarningMsg.showDialog(msg);
	
		} catch (DataAccessException e) {
		e.printStackTrace();
	}
		return b;
		
	}
	
}

