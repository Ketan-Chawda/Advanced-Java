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
 * Servlet implementation class for Servlet: ServletAdminCourseDelete
 *
 */
 public class ServletAdminCourseDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminCourseDelete() {
		super();
	}   	
	static String myerror=null;

	static String mystr="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Course Deletion Only By Admin</title></head>");
		String tcoursename="",query1="",query2="";
		
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
				kout.println("<br><h1>Course Deletion Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

				
				tcoursename="bca";
				tcoursename=request.getParameter("coursename");
				
				st=mycon.createStatement();
				query1="select * from course_master where coursename='"+tcoursename+"'";
				ResultSet rs1=st.executeQuery(query1);
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				if(tot!=0)
				{
				st=mycon.createStatement();
				query1="delete from subject_master where coursename='"+tcoursename+"'";
				st.executeUpdate(query1);
				st=mycon.createStatement();
				query2="delete from course_master where coursename='"+tcoursename+"'";
				st.executeUpdate(query2);
				mystr="Course Deleted";
				//kout.println("<br><br><br><h1>Course Deleted Successfully");
				}
				else
				mystr="Course Does Not Exists";
				//kout.println("<br><br><br><h1>Course Does Not Exists Or Some Restriction Is Kept<h2>");
			}
			catch(Exception k)
			{
				System.err.println(k);
				myerror=k.getMessage();

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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminCourseDelete.jsp>");
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