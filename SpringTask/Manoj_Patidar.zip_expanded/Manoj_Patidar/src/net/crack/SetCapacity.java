/*
*SetCapacity.java;
*this class takes input from user that how many no of seat he want.
According to that if he want more seat but room capacity is not match to its requirment thin
in this case he get message that no of seat is not match to there requirment.
*/
package net.crack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCapacity
 */
public class SetCapacity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCapacity() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *Doget method which take a input which is send by user as a query String (name of room which he want to book)
	 *and show user one interface so that user enter no of seat he want inside room.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw= response.getWriter();
		String name = request.getQueryString();//getting  the name of room.
		pw.println("<html>"
				+ "<head>"
				+ "<title>"
				+ "Set Capacity"
				+ "</title>"
				+ "</head>"
				+ "<body>"
				+ "<h2> Booking for "+name+" meeting</h2>"
						+ "<form action=\"Time.url\" method= \"post\">"
						+ "<h3><em>Enter The Capacity Required</em></h3>"
						+"<select name=\"Capacity\">"
						+"<option value=\"10\" selected>>5</option>"
						+"<option value=\"20\">>10</option>"
						+"<option value=\"30\">>20</option>"
						+"<option value=\"40\">>30</option>"
						+"</select>"
						+ "<input type=\"submit\" value=\"SUBMIT\">"
						+ "</body></html>");
	}

}
