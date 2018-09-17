/*
Validate_Time.java:
this class which take information  from SetTimemain and connect with data and check that 
time enterd by new user slot is free or not if room is not free between the slot then he 
provide message to user that this time slot is allredy is book by some one. If time slot is not book 
then he save all info like user name, id, start Time, End Time, room name etc into database for future use. 
*/
package net.crack;

import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sql.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class Validate_Time
 */
public class Validate_Time extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate_Time() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String cdate=request.getParameter("Day");
		String chours=request.getParameter("Time");
		Float ctime= Float.parseFloat(chours);
		String fte=request.getParameter("S_time");
		Float ft=Float.parseFloat(fte);
		String eti=request.getParameter("E_time");
		Float et=Float.parseFloat(eti);
		String roomname1=request.getParameter("roomname");
		int retval=Float.compare(ft, et);
		System.out.println(ft);
		System.out.println(et);

	Connection connnect=null;
		
		
		DataSource dataSource=gcjb.getDataSource();
		
			

		if(retval<0){
			System.out.println("Will redirect good to go");
				try{
					
                                        connect=dataSource.getConnection();
					String querycollect= "select * from bookroom where RoomName = '"+roomname1+"'";
					Statement st1=connect.createStatement();
			  		ResultSet rs2  = st1.executeQuery(querycollect);
			  		boolean flag=true;
			  		System.out.println("brfore try");
			  		float dbstart_time=0.0f;
			  		float dbend_time=0.0f;
			  		String Room_Name="";
			  		
			  		while(rs2.next())
			  		{
			  		
			  			
			  			System.out.println("Inside While");
			  			dbstart_time=rs2.getFloat(3);
			  			System.out.println(dbstart_time);
			  			dbend_time=rs2.getFloat(4);
			  			System.out.println(dbend_time);
			  			Room_Name=rs2.getString(2);
			  			System.out.println(Room_Name);
			  		}
			  			int retvalc=Float.compare(ft, ctime);
			  				
			  			while(retvalc > 0){
			  			
			  			if(ft>=dbstart_time && ft<=dbend_time )
	  					{
	  					System.out.println("Inside while>if");
	  					out.println("<script>alert('SLOT IS BOOKED TRY ANOTHER.!! :('); window.location = './settimemain.url?"+roomname1+"';</script>");
	  					break;
	  					}
			  			else if(et<=dbstart_time || ft>=dbend_time){
				  			System.out.println("Inside if(flag)");
				  			String newVals="'"+roomname1+"' , "+ft +" , "+ et;
				  			System.out.println(newVals);
				  			String UpdateEntry= "insert into bookroom (RoomName , StartTime , EndTime) values ("+newVals+")";
				  			System.out.println(UpdateEntry);
				  			Statement st2=connect.createStatement();
				  			int mark=st2.executeUpdate(UpdateEntry);
				  			System.out.println("Inserted");
				  			if(mark==1){
				  				out.println("<script>alert('Your Requested Slot is Yours...!! :('); window.location = './settimemain.url?"+roomname1+" , "+ft+" , "+et+"  "+"';</script>");
				  			}
				  			else{
				  				out.println("<script>alert('Unsuccessful TRY Again.!! :('); window.location = './settimemain.url?"+roomname1+"';</script>");
				  				
				  				}
				  			break;
			  				}
			  			else{
			  				out.println("<script>alert('Unsuccessful TRY Again.!! :('); window.location = './settimemain.url?"+roomname1+"';</script>");
			  			}
			  			
			  			}
			  			out.println("<script>alert('You cannot book a room before current time.!! :('); window.location = './settimemain.url?"+roomname1+"';</script>");
			  		
			  		System.out.println("while finished");
			  	}
			
				catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
				}
		else if(retval>0){
			out.println("<script>alert('How Can You End Something Before Even Starting It...!! :('); window.location = './settimemain.url?"+roomname1+"';</script>");
		}
		else{
			out.println("<script>alert('You Cannot Book For 0 Time Interval...!! :('); window.location = './settimemain.url?"+roomname1+"';</script>");
		}
	}

}