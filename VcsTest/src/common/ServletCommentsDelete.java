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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ServletCommentsDelete
 *
 */
 public class ServletCommentsDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletCommentsDelete() {
		super();
	}   	
	static String mystr="",tpath="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Comment Deletion</title></head>");
		HttpSession my=request.getSession(false);
		
		String tcid="",trole="";
		
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
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Comment Deletion In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");

				
				trole=request.getAttribute("urole").toString();
				if(trole.compareToIgnoreCase("student")==0)
				tpath="/VcsTest/Stud/StudCommentDelete.jsp";
				else if(trole.compareToIgnoreCase("faculty")==0)
				tpath="/VcsTest/Faculty/FacultyCommentDelete.jsp";
				else if(trole.compareToIgnoreCase("management")==0)
				tpath="/VcsTest/Mgt/MgtCommentDelete.jsp";
				else if(trole.compareToIgnoreCase("admin")==0)
				tpath="/VcsTest/Admin/AdminCommentDelete.jsp";
							
				tcid="b1c1";
				tcid=request.getParameter("cid");
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from comment_master where cid='"+tcid+"'");
				int tot=0;
				while(rs.next())
				{
				tot=rs.getRow();
				}
				if(tot!=0)
				{
				st=mycon.createStatement();
				st.executeUpdate("delete * from comment_master where cid='"+tcid+"'");		
				kout.println("<br><br><br><h1> Ok Comment Is Deleted");
				mystr="Comment Is Deleted";
				}
				else
				mystr="Sorry Comment Is Not Deleted";
				//kout.println("<br><br><br><h1>Sorry Comment Is Not Deleted");
			}
			catch(Exception k)
			{
				System.err.println(k);
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
			kout.println("<form id=vcs method=post action='"+tpath+"'>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");
			kout.close();
			
}
}	    
}