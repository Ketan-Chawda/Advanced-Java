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
 * Servlet implementation class for Servlet: ServletCommentsCreate
 *
 */
 public class ServletCommentsCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletCommentsCreate() {
		super();
	}   	
	static String mystr="",tpath="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Comment Creation</title></head>");
		HttpSession my=request.getSession(false);
		
		String mycid="",tbid="",tuserid="",tcprior="",tcoursename="";
		String tcdate="",tcmsg="",query1="",trole="";
		
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
				kout.println("<br><h1>Comment Creation In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				trole=my.getAttribute("urole").toString();
		
				if(trole.compareToIgnoreCase("student")==0)
				tpath="/VcsTest/Stud/StudCommentCreate.jsp";
				else if(trole.compareToIgnoreCase("faculty")==0)
				tpath="/VcsTest/Faculty/FacultyCommentCreate.jsp";
				else if(trole.compareToIgnoreCase("management")==0)
				tpath="/VcsTest/Mgt/MgtCommentCreate.jsp";
				else if(trole.compareToIgnoreCase("admin")==0)
				tpath="/VcsTest/Admin/AdminCommentCreate.jsp";
				
				
				int d=new Date().getDate(),m=new Date().getMonth(),y=new Date().getYear()-100;
				
				st=mycon.createStatement();
				
				tuserid=my.getAttribute("uid").toString();
				tcoursename=my.getAttribute("ucourse").toString();
				
				tcmsg=request.getParameter("cmsg");
				
				tbid="b1";
				tbid=request.getParameter("bid");
				tcmsg="my name is chawda";
				tcmsg=request.getParameter("cmsg");
				tuserid="08bca12";
				tcoursename="bca";
				tcdate=m+"/"+d+"/"+y;//access
				tcdate=(y+2000)+"-"+m+"-"+d;//db2
				
				ResultSet rs=st.executeQuery("select * from comment_master where bid='"+tbid+"'");
				int tot=0;
				while(rs.next())
				{
				tot=tot+1;
				}
				tot=tot+1;
				mycid=""+tbid+"c"+tot+"";
				tuserid=my.getAttribute("uid").toString();
				tcprior=my.getAttribute("uprior").toString();
				tcoursename=my.getAttribute("ucourse").toString();
				
				query1="insert into comment_master(bid,cid,userid,coursename,cdate,cmsg,cprior) values('"+tbid+"','"+mycid+"','"+tuserid+"','"+tcoursename+"','"+tcdate+"','"+tcmsg+"','"+tcprior+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				//kout.println("<br><br><br><h1>Ok Comment Created");
				mystr="Comment Created";
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