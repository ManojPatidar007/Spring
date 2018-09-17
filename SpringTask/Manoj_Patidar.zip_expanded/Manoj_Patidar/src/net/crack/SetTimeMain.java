/*
*SetTimeMain.java:
*this class which take some input like start time and end time from the user .
Start time :Time from which he want to use or book any room that is starting time.
End time: Time up to which he will left the room or leaving time .
After enter time he just send data with the help of request, response object to validate page that 
this time is free or not or we can say that another user is book at the same time . 
*/
package net.crack;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.sql.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class SetTimeMain
 */
public class SetTimeMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetTimeMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String Rname=request.getQueryString();
		String Rname1=Rname.replaceAll("%20", " ");
		System.out.println(Rname);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("HH.mm");
 		//get current date time with Date()
 		Date date = new Date();
 		Date date1 = new Date();
 		dateFormat.format(date);
 		dateFormat1.format(date1);
 		//get current date time with Calendar()
 	   Calendar cal = Calendar.getInstance();
 	   String CDate=dateFormat.format(cal.getTime());
 	   String CHours=dateFormat1.format(cal.getTime());
 	   float bookStartTimefs=09.00f;
 	   float bookEndTimefs=19.00f;
 	   float bookStartTimefe=09.00f;
	   float bookEndTimefe=19.00f;
 	   DecimalFormat def=new DecimalFormat("##.##");
		out.println("<html>");
				out.println ("<head>");
				out.println ("<title>Check Availability</title>");
				out.println ("</head>");
				out.println ("<body>");
				out.println("<h3>Check Availability For "+Rname+".");
		 		
		out.println("<h4>Current Date and Time is :"+CDate+" : "+ CHours+"</h4>");
		out.println("<form action=\"./timeValidation.url\" method=\"post\">");
				out.println ("<h3>Enter Start Time For Your Booking in 24-hour format:"
						+ "<input type=\"hidden\" name=\"Day\" value=\""+CDate+"\">"
								+ "<input type=\"hidden\" name=\"Time\" value=\""+CHours+"\">"
						+ "<input type=\"hidden\" name=\"roomname\" value=\""+Rname1+"\"/>"
						+"<select name=\"S_time\">"
						+"<option value=\""+def.format(bookStartTimefs)+"\" selected>"+def.format(bookStartTimefs)+"</option> ");
						while(bookStartTimefs<bookEndTimefs){
							for(int i=0;i<=10;i++){
								bookStartTimefs+=0.05;
								out.println("<option value=\""+def.format(bookStartTimefs)+"\">"+def.format(bookStartTimefs)+"</option> ");
							}
								bookStartTimefs+=0.45;
								out.println("<option value=\""+def.format(bookStartTimefs)+"\">"+def.format(bookStartTimefs)+"</option> ");
						}
					out.println("</select>"
								+ "<h3>Enter End Time For Your Booking in 24-hour format:"
								+"<select name=\"E_time\">"
								+"<option value=\""+def.format(bookStartTimefe)+"\" selected>"+def.format(bookStartTimefe)+"</option> ");
						while(bookStartTimefe<bookEndTimefe){
							for(int i=0;i<=10;i++){
								bookStartTimefe+=0.05;
								out.println("<option value=\""+def.format(bookStartTimefe)+"\">"+def.format(bookStartTimefe)+"</option> ");
							}
								bookStartTimefe+=0.45;
								out.println("<option value=\""+def.format(bookStartTimefe)+"\">"+def.format(bookStartTimefe)+"</option> ");
						}
					
						//out.println("<option value=\""+bookEndTime+"\">"+bookEndTime+"</option>"
						out.println("</select>"
						+ "<input type=\"submit\" value=\"SUBMIT\">");		
		out.println( "		</form></body></html>");
		
	}

}
