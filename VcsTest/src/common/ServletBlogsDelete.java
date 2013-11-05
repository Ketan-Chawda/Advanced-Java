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
 * Servlet implementation class for Servlet: ServletBlogsDelete
 *
 */
 public class ServletBlogsDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletBlogsDelete() {
		super();
	}   	
	static String mystr="",tpath;
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Blogs Deletion In Vcs</title></head>");
		HttpSession my=request.getSession(false);
		String tbid="",tuserid="",tprior="",trole="";

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
				kout.println("<br><h1>Blogs Deletion In Vcs</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				trole=request.getAttribute("urole").toString();
				if(trole.compareToIgnoreCase("student")==0)
				tpath="/VcsTest/Stud/StudBlogsDelete.jsp";
				else if(trole.compareToIgnoreCase("faculty")==0)
				tpath="/VcsTest/Faculty/FacultyBlogsDelete.jsp";
				else if(trole.compareToIgnoreCase("management")==0)
				tpath="/VcsTest/Mgt/MgtBlogsDelete.jsp";
				else if(trole.compareToIgnoreCase("admin")==0)
				tpath="/VcsTest/Admin/AdminBlogsDelete.jsp";
				
				
				tbid="b2";
				tbid=request.getParameter("bid");
				tuserid=request.getAttribute("uid").toString();
				tprior=request.getAttribute("uprior").toString();
				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from blog_master where bid='"+tbid+"'");
				int tot=1;
				while(rs.next())
				{
				tot=rs.getRow();
				}
				if(tot!=0)
				{
				st=mycon.createStatement();
				st.executeUpdate("delete * from comment_master where bid='"+tbid+"'");		
					
				st=mycon.createStatement();
				st.executeUpdate("delete * from blog_master where bid='"+tbid+"'");		
				mystr="Respected Blog Is Deleted Successfully";
			//	kout.println("<br><br><br><h1>Respected Blog Is Deleted Successfully");
				}
				else
					mystr="Sorry Blog Is Not Deleted";
				//kout.println("<br><br><br><h1>Sorry Blog Is Not Deleted Since It May Not Exists");
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
			//kout.println("<br><br><br><h1>Sorry Connection Is Not Active Now");
	
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