package net.crack;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("GetConnectionJdbc")
public class GetConnectionJdbc  {
 @Autowired
  private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
	this.dataSource=dataSource;
}

public DataSource getDataSource() {
	return dataSource;
}

}
