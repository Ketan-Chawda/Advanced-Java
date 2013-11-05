package common;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ServletSignin
 *
 */
 public class ServletSignin extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletSignin() {
		super();
	}   	
	static String myerror=null;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Sign-In Process</title></head>");
		String myuser="",mypass="",myrole="",mycourse="";
		String t1="",t2="",myprior="";
	
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
		
				myuser=request.getParameter("uname");
				mypass=request.getParameter("upass");
				mypass=Secure.putp(mypass);
							
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from login_master where userid='"+myuser+"' and password='"+mypass+"' and locked='no'");
				int c=1;
				while(rs.next())
				{
					c=c+1;
					myrole=rs.getString("role");
					mycourse=rs.getString("coursename");
					myprior=rs.getString("prior");
					t1=rs.getString("userid");
					t2=rs.getString("password");
				}
				//t2=Secure.getp(t2);
				if(c!=1 && t1.compareTo(myuser)==0 && t2.compareTo(mypass)==0)
				{
					
					HttpSession mysession=request.getSession(true);
					Date ts;
					String tsdate="";
					
					//String tsdate=p+"/"+q+"-"+(r+2000);//access
										
					int d=new Date().getDate(),m=new Date().getMonth()+1,y=new Date().getYear()-100;
				
					/*if(mysession.isNew()==false)
					{
						response.sendRedirect("/VcsTest/ServletSignout");
					}
					else
					{
						mysession=request.getSession(true);
					}
					/*	ts=new Date(mysession.getLastAccessedTime());
						int p=ts.getDate()-10,q=ts.getMonth()+1,r=ts.getYear()-100;
						tsdate=(r+2000)+"-"+q+"-"+r;//db2
						
						if(myrole.compareToIgnoreCase("management")==0)
						{
						st=mycon.createStatement();
						st.executeUpdate("insert into mgt_attend values('"+myuser+"','"+tsdate+"','"+ts+"','OUT')");
						//response.sendRedirect("/VcsTest/ServletMgtAttendCount");
						}
						//else if(myrole.compareToIgnoreCase("admin")==0)
						//response.sendRedirect("/VcsTest/Admin/AdminHome.jsp");
						else if(myrole.compareToIgnoreCase("faculty")==0)
						{
						st=mycon.createStatement();
						st.executeUpdate("insert into faculty_attend values('"+myuser+"','"+tsdate+"','"+ts+"','OUT')");
						//response.sendRedirect("/VcsTest/ServletFacultyAttendCount");
						}
						else if(myrole.compareToIgnoreCase("student")==0)
						{
						st=mycon.createStatement();
						mycourse=mysession.getAttribute("ucourse").toString();
						st.executeUpdate("insert into student_attend values('"+myuser+"','"+mycourse+"','"+tsdate+"','"+ts+"','OUT')");
						//response.sendRedirect("/VcsTest/ServletStudentAttendCount");
						}
						mysession.invalidate();
						mysession=null;
					}

					mysession=request.getSession(true);*/

					kout.println("<br>session is="+mysession.getId()+"<br>");
					mysession.setAttribute("urole",myrole);
					mysession.setAttribute("uid",myuser);
					mysession.setAttribute("ucourse",mycourse);
					mysession.setAttribute("uprior",myprior);
										
					st=mycon.createStatement();
					//String tsdate=d+"/"+m+"/"+y;//access
					
					tsdate=(y+2000)+"-"+m+"-"+d;//db2
					ts=new Date(mysession.getCreationTime());
					
			if(myrole.compareToIgnoreCase("management")==0)
			{
			st=mycon.createStatement();
			st.executeUpdate("insert into mgt_attend values('"+myuser+"','"+tsdate+"','"+ts+"','IN')");
			response.sendRedirect("/VcsTest/ServletMgtAttendCount");
			}
			else if(myrole.compareToIgnoreCase("admin")==0)
			response.sendRedirect("/VcsTest/Admin/AdminHome.jsp");
			else if(myrole.compareToIgnoreCase("faculty")==0)
			{
			st=mycon.createStatement();
			st.executeUpdate("insert into faculty_attend values('"+myuser+"','"+tsdate+"','"+ts+"','IN')");
			response.sendRedirect("/VcsTest/ServletFacultyAttendCount");
			}
			else if(myrole.compareToIgnoreCase("student")==0)
			{
			st=mycon.createStatement();
			st.executeUpdate("insert into student_attend values('"+myuser+"','"+mycourse+"','"+tsdate+"','"+ts+"','IN')");
			response.sendRedirect("/VcsTest/ServletStudentAttendCount");
			}
			mysession.setMaxInactiveInterval(60*3);
			}
					
			
			else
			{
				kout.println("<script>alert('Your UserName Or Password Is Invalid Else Locked By Admin');</script>");
				kout.println("<br><br><br><br><center><h2><a href='/VcsTest/login.jsp'>Login Again <a>");
				//response.sendRedirect("/VcsTest/login.jsp");
			}
				//<br><br><br><h1>Sorry User-name Or Password Is Invalid Or Your Account Has Been Locked By Admin");
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
				kout.println("<br><br><h2><center>Sorry Connection Is Not Active Now");
			
		}
		catch(Exception k)
		{
			System.err.println(k);
			myerror=myerror +"<br>"+ k.getMessage();

		}
		finally
		{
			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);

			kout.close();
		}
}	    
}