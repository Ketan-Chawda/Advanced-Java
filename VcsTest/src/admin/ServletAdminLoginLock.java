package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ServletAdminLoginLock
 *
 */
 public class ServletAdminLoginLock extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminLoginLock() {
		super();
	}   	
	static String myerror=null;

	static String mystr="",tpath="/VcsTest/Admin/AdminLoginLock.jsp";
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Login Lock In Progress Only By Admin</title></head>");
		
		String tuserid="",tlocked="";
			
		Connection mycon=null;
		Statement st;
	
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
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Login Lock In Progress Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

				
				tuserid="11fact01";
				tlocked="no";
				tuserid=request.getParameter("userid");
				tlocked=(request.getParameter("locked")).toLowerCase();
				if(tlocked.compareToIgnoreCase("no")==0)
				tpath="/VcsTest/Admin/AdminLoginUnlock.jsp";
				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from login_master where userid='"+tuserid+"'");
				int tot=0;
				while(rs.next())
				{
				tot=rs.getRow();
				}
				if(tot!=0)
				{
				
				st=mycon.createStatement();
				st.executeUpdate("update login_master set locked='"+tlocked+"' where userid='"+tuserid+"'");
				
				//kout.println("<br><br><br><h1> User's Login Is Updated Successfully");
				mystr="User-Login Updated";
				}
				else
				mystr="This User Does Not Exists";
				//kout.println("<br><br><br><h1> Sorry This User Does Not Exists Or Already Deleted Before");
			}
			catch(Exception e)
			{
				System.err.println(e);
				myerror=e.getMessage();
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
		myerror=myerror +"<br>"+ k.getMessage();

		}
		finally
		{
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action='"+tpath+"'>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);

			kout.close();
}
}	    
}