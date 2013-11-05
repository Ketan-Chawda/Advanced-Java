package common;

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

/**
 * Servlet implementation class for Servlet: ServletUnSubscribe
 *
 */
 public class ServletUnSubscribe extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletUnSubscribe() {
		super();
	}   	
	static String mystr="";
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>NewsLetter Un-Subsription In Progress For All</title></head>");
		String temail,tsdate="";
			
		Connection mycon=null;
		Statement st=null;
	
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
		//kout.println("<br><br>**************************************************************************************************************");
		//kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
		//kout.println("<br><br>**************************************************************************************************************");
		if(mycon!=null)
		{
			try
			{
				temail="ketan_gentleman012@yahoo.com";
				temail=request.getParameter("temail");
				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from subscribe");
				int tot=0;
				while(rs.next())
				{
				tot=rs.getRow();
				tsdate=rs.getString("sdate");
				}
				
				if(tot!=0)
				{
				st=mycon.createStatement();
				st.executeUpdate("delete from subscribe where email='"+temail+"'");
				//kout.println("<br><center><h2>Ok You Are Un-Subscribed For This NewsLetter Facility<br>Your Previous Subcription Date Was="+tsdate+"</center>");
				mystr="ok Un-Subscribed,Your Previous Subcription Date Was="+tsdate+"";
				}
				else
					mystr="You Are Already Un-Subscribed";
				//kout.println("<br><center><h2>You Are Already Un-Subscribed For This NewsLetter Facility</center>");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			finally
			{
				mycon.close();
			}
		}
		else
		mystr="Sorry Connection Is In-Active";
		
		}
		catch(Exception k)
		{
		System.err.println(k);
		}
		finally
		{
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/home.jsp>");
			kout.println("<input type=hidden name=subscribe value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");
			kout.close();
}
}	    
}