

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybest.Fortable;
import mybest.Styles;
import java.sql.*;


/**
 * Servlet implementation class for Servlet: Check1
 *
 */
 public class Check1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Check1() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Easy Deletion Of Activity Organized In VCS</title></head>");
		try
		{
				
				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor='black'>");
				
				String str="ya";
				while(c<7)
				{
					
					
					kout.println("<tr><td>"+c+"<td><input type='checkbox' name='"+"c"+c+"' value='"+str+"'>");
					c=c+1;
					
				}
				kout.println("<input type='hidden' name='total' value="+c+">");
				kout.println("<input type='submit' value='submit it'>");
				kout.println("</form>");
				
				kout.println("</table></center>");
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		finally
	{
		/*kout.println("<html><body background='myreport1.jpg'>");
		kout.println("</body></html>");*/
		kout.close();
	}
}}