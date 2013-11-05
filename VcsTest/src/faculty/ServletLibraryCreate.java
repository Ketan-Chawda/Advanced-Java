package faculty;


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
 public class ServletLibraryCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletLibraryCreate() {
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
		Connection myconn=null;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		myconn=DriverManager.getConnection("jdbc:odbc:myvcs");		
		}
		catch(Exception k)
		{
		System.err.println(k);
		}
		finally
		{
			if(myconn!=null)
			{				
				try
				{
					Statement st;
					st=myconn.createStatement();					
					
										
					String fid="",ltitle="",lpath="",cdate="",query="",path="",status="",ctg="";					
					int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;														
					
					fid=request.getParameter("fid");
					//cdate=""+m+"/"+d+"/"+(myear)+"";//access
					cdate=(myear+2000)+"-"+m+"-"+d;//db2
					
					ctg=request.getParameter("ctg");
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
					if(ctg.equals("video"))
					{
						newPath="..//repository//default//VcsTest//1.0//VcsTest-1.0.car//videos//"+arr.get(count-1);
						//newPath="..//video//"+arr.get(count-1);						
						//lpath=newPath;
						lpath="http://localhost:8080//VcsTest//videos//"+arr.get(count-1);
						ltitle=""+arr.get(count-1);
						status="video";
					}
					else if(ctg.equals("book"))
					{
						newPath="..//repository//default//VcsTest//1.0//VcsTest-1.0.car//books//"+arr.get(count-1);
						//newPath="..//book//"+arr.get(count-1);					
						//lpath=newPath;
						lpath="http://localhost:8080//VcsTest//books//"+arr.get(count-1);
						ltitle=""+arr.get(count-1);
						status="book";
					}
					
					ResultSet rs=st.executeQuery("select lpath from library where lpath='"+lpath+"'");
					int cnt=1;
					
					while(rs.next())
					{
						cnt++;
					}
					if(cnt>1)
					{
					mystr="Sorry Server Having Same File Already Exist";
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
						
						query="insert into library(fid,lpath,ltitle,cdate,status)values('"+fid+"','"+lpath+"','"+ltitle+"','"+cdate+"','"+status+"')"; 
						st=myconn.createStatement();
						mystr=ltitle+"Uploaded Successfully";
						st.executeUpdate(query);
					}																				
					}
				}
				catch(Exception k)
				{
					System.err.println(k);
				}
			}
			else
			mystr="Sorry Connection Is Not Active Now";
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyLibrary.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");
			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");
			
}
}	    
}