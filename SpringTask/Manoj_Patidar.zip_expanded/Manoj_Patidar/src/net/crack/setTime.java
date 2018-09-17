/*
SetTime.java:
this class which take some data from another javapage(no of seat which user want to book) and search into the database that 
the no of seat which user want is available in which room so he show all the which saties fied user requirment.
*/
package net.crack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.sql.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class setTime
 */
public class setTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setTime() {
        super();
        // TODO Auto-generated constructor stub
    }
	  ApplicationContext ctx;
    GetConnectionJdbc gcjb;
    
   @Override
    public void init(ServletConfig config) throws ServletException {
	     ctx=new ClassPathXmlApplicationContext("Spring.xml");
		 gcjb=ctx.getBean("GetConnectionJdbc",GetConnectionJdbc.class);
		System.out.println("init method done");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw= response.getWriter();
		ArrayList<Integer> al=new ArrayList<Integer>();
		Connection con=null;
		
		
		DataSource dataSource=gcjb.getDataSource();
		try {
			connect=dataSource.getConnection();
		
		/**
		 * 2.Perform a database connection using driver manager
		 *------------------------------------------------
		 * 	Drivermanager.getConnection("URL","USERNAME","PASSWORD")
		 * 	Attempts to establish a connection to the given database URL.
		 *	The Driver manager attempts to select an appropriate driver from the
		 * 	set of registered JDBC drivers.
		 *
		 *	Here we are also storing the connection info in appropriate
		 * 	object form.
		 */
		@SuppressWarnings("unused")
	
		
		String cap= request.getParameter("Capacity");
		int capacity=Integer.parseInt(cap);
		int k=0;
		int ar[]={10,20,30,40};
		for(int i=0;i<ar.length;i++){
  			if(!(ar[i]<capacity)){
  				al.add(ar[i]);
  				k++;
  			}
  		}
		int count=k;
		System.out.println(count);
		String qvalues=Arrays.asList(al).toString().replaceAll("\\[","").replaceAll("\\]",""); 
		System.out.println(qvalues);
		String showtabnames="select RoomName from roomspecs where Cap in ("+qvalues+")" ;
  		Statement st=connect.createStatement();
  		ResultSet rs1  = st.executeQuery(showtabnames);
  		ArrayList<String> arr=new ArrayList<String>();
  		
  		
  		while(rs1.next()){
  			arr.add(rs1.getString(1));
  			}
  		System.out.println(arr);
  		pw.println("<html>"
					+ "<head>"
					+ "</head>"
					+ "<body>"
					+ "<h2> The Following Rooms Suit Your Requirements</h2>");
					for(int h=0;h<arr.size();h++){
					pw.println("<a href='./settimemain.url?"+arr.get(h)+"' style='text-decoration: none; background-color: #f44336;padding: 14px 25px; text-align: center;	text-decoration: none;display: inline-block;'>"+arr.get(h)+"</a>");	
					System.out.println(arr.get(h));
					}
					pw.println( "</body>"
					+ "</html>");
  	}
		catch(Exception e){
			e.printStackTrace();}
		
	
		}
		

}
