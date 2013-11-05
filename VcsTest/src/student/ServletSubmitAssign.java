package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ServletFacultyNoticeCreate
 *
 */
 public class ServletSubmitAssign extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletSubmitAssign() {
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
		
		String aid="",sid="",ltitle="",lpath="";
		String cdate="",query="",path="";					
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;														
		
		Connection mycon=null;
		Statement st;

		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
		
		if(mycon!=null)
		{				
			try
			{
				st=mycon.createStatement();	
				
				
				aid=request.getParameter("aid");
				sid=request.getParameter("sid");
				cdate=""+m+"/"+d+"/"+(myear)+"";//access
				cdate=(myear+2000)+"-"+m+"-"+d;//db2
									
				path=request.getParameter("lfile");

				String newPath="c:\\";
				int count=0;					
				if(path!=null)
				{						
				ArrayList arr=new ArrayList();
				StringTokenizer stt=new StringTokenizer(path,"\\");
				while(stt.hasMoreTokens())
				{
				arr.add(count,stt.nextToken());
				count++;
				}					
				// create ur own path	
									
					newPath="..//repository//default//VcsTest//1.0//VcsTest-1.0.car//asubmit//"+arr.get(count-1);
					//newPath="..//video//"+arr.get(count-1);						
					//lpath=newPath;
					lpath="http://localhost:8080//VcsTest//asubmit//"+arr.get(count-1);
					ltitle=""+arr.get(count-1);	
					
					ResultSet rs=st.executeQuery("select lpath from library where lpath='"+lpath+"'");
					int cnt=1;
					
					while(rs.next())
					{
						cnt++;
						kout.println("ziyad");
					}
					
					if(cnt>1)
					{
						
						kout.println("<html>");
			            kout.println("<body OnLoad=document.getElementById('login_error').submit();>");
			            kout.println("<form name=login_error id=login_error method=post action=/VcsTest/Faculty/FacultyLibrary.jsp>");
			            kout.println("<input type=hidden name=common value=\"      Sorry Server Having Same File Already Exist.\">");
			            kout.println("</form>");
			            kout.println("</body>");
			            kout.println("</html>");
						
					}
					else
					{	
				int c;
				FileInputStream fis=new FileInputStream(path);
				FileOutputStream fos=new FileOutputStream(newPath);
				while((c=fis.read())!=-1)
				{
				fos.write((char)c);
				}
				}
				kout.println(newPath);
				query="insert into submit_assignment(aid,sid,stitle,spath,sdate)values('"+aid+"','"+sid+"','"+ltitle+"','"+lpath+"','"+cdate+"')"; 
				st=mycon.createStatement();					
				kout.println("<html>");
	            kout.println("<body OnLoad=document.getElementById('login_error').submit();>");
	            kout.println("<form name=login_error id=login_error method=post action=/VcsTest/Stud/StudAssignmentSubmit.jsp>");
	            kout.println("<input type=hidden name=common value=\"    "+ltitle+" Uploaded Successfully.\">");
	            kout.println("</form>");
	            kout.println("</body>");
	            kout.println("</html>");
				st.executeUpdate(query);	
				}
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
		{	
			kout.println("<html>");
            kout.println("<body OnLoad=document.getElementById('login_error').submit();>");
            kout.println("<form name=login_error id=login_error method=post action=/VcsTest/Stud/StudAssignmentSubmit.jsp>");
            kout.println("<input type=hidden name=common value=\"    * Connection Failed.\">");
            kout.println("</form>");
            kout.println("</body>");
            kout.println("</html>");				
		}
		}
		catch(Exception k)
		{
		System.err.println(k);
		myerror=myerror +"<br>"+ k.getMessage();
		}
		finally
		{
			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);

			kout.close();
		}
}	    
}