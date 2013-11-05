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
 * Servlet implementation class for Servlet: ServletBlogsCreate
 *
 */
 public class ServletBlogsCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletBlogsCreate() {
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
		kout.println("<html><head><title>Blogs Creation</title></head>");
		HttpSession my=request.getSession(false);
		
		String mybid="",tuserid="",tprior="",tcoursename="";
		String tbdate="",tbmsg="",trole="",query1="";
		int d=new Date().getDate(),m=new Date().getMonth(),y=new Date().getYear()-100;
		
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
				//kout.println("<br><br><center>**************************************************************************************************************");
				//kout.println("<br><h1>Blogs Creation In VCS</h1>");
				//kout.println("<br><br><br>**************************************************************************************************************<br>");

				
				trole=my.getAttribute("urole").toString();
				if(trole.compareToIgnoreCase("student")==0)
				tpath="/VcsTest/Stud/StudBlogsCreate.jsp";
				else if(trole.compareToIgnoreCase("faculty")==0)
				tpath="/VcsTest/Faculty/FacultyBlogsCreate.jsp";
				else if(trole.compareToIgnoreCase("management")==0)
				tpath="/VcsTest/Mgt/MgtBlogsCreate.jsp";
				else if(trole.compareToIgnoreCase("admin")==0)
				tpath="/VcsTest/Admin/AdminBlogsCreate.jsp";
				
				
				tbmsg="my name is ketan";
				tbmsg=request.getParameter("bmsg");
				tbdate=m+"/"+d+"/"+(y+2000);//access
				tbdate=(y+2000)+"-"+m+"-"+d;//db2
				
				
				st=mycon.createStatement();
				ResultSet rs1=st.executeQuery("select * from countall where role='blogs' and entry='"+y+"'");
				int tot=0;
				while(rs1.next())
				{
				tot=Integer.parseInt(rs1.getString("total"));
				}
				tot=tot+1;
				if(tot==1)
				{
				query1="insert into countall(role,course,entry,total) values('blogs','optional','"+y+"','"+tot+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				else
				{
				query1="update countall set total='"+tot+"' where role='blogs' and course='optional' and entry='"+y+"' ";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				mybid=""+"b"+tot+"";
				tuserid="08bca12";
				tcoursename="bca";
				
				tprior=my.getAttribute("uprior").toString();
				tuserid=my.getAttribute("uid").toString();
				tcoursename=my.getAttribute("ucourse").toString();
				
				query1="insert into blog_master(bid,userid,coursename,bdate,bmsg,bprior) values('"+mybid+"','"+tuserid+"','"+tcoursename+"','"+tbdate+"','"+tbmsg+"','"+tprior+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				
				//kout.println("<br><br><br><h1> Ok Blog Created Successfully");
				mystr="Blog Created Successfully";
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