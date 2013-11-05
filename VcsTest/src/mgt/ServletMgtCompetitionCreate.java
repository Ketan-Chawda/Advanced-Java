package mgt;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ServletMgtCompetitionCreate
 *
 */
 public class ServletMgtCompetitionCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletMgtCompetitionCreate() {
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
		kout.println("<html><head><title>Competition Creation Or Declaration Only By Management</title></head>");
		
		Connection myconn=null;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		myconn=DriverManager.getConnection("jdbc:odbc:myvcs");
		/*kout.println("<br><br>**************************************************************************************************************");
		kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
		kout.println("<br><br>**************************************************************************************************************");*/
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
					Statement st,st1;
					String mycompid,tcompname,tcompdate,tday,tmonth,tyear,tcdesc,tduration,query1,query2,path="",lpath="",ltitle="";
					int myear=new Date().getYear()-100;
					
					tcompname="comps";
					tcompdate="19/10/1989";
					tcdesc="checking";
					tduration="6";
					
					tcompname=request.getParameter("compname");
					//tcompdate=request.getParameter("compdate");
					tcdesc=request.getParameter("cdesc");
					tduration=request.getParameter("duration");
					tday=request.getParameter("cday");
					tmonth=request.getParameter("cmonth");
					tyear=request.getParameter("cyear");
					
					tcompdate=tmonth+"/"+tday+"/"+tyear; //access
					tcompdate=(tyear+2000)+"-"+tmonth+"-"+tday; //db2
					
					path=request.getParameter("act_file");
					st1=myconn.createStatement();
					ResultSet rs1=st1.executeQuery("select * from countall where role='competition' and course='optional' and entry='"+myear+"'");;
					int tot=0;
					while(rs1.next())
					{
					tot=Integer.parseInt(rs1.getString("total"));
					}
					tot=tot+1;
					if(tot==1)
					{
					query1="insert into countall(role,course,entry,total) values('competition','optional','"+myear+"','"+tot+"')";
					st1=myconn.createStatement();
					st1.executeUpdate(query1);
					}
					else
					{
					query1="update countall set total='"+tot+"' where role='competition' and course='optional' and entry='"+myear+"' ";
					st1=myconn.createStatement();
					st1.executeUpdate(query1);
					}
					mycompid=""+myear+"comp"+tot+"";
					if(tot%10==tot && myear%10==myear)
					mycompid=""+"0"+myear+"comp0"+tot+"";
					else if(tot%10==tot)
					mycompid=""+myear+"comp0"+tot+"";
					else if(myear%10==myear)
					mycompid=""+"0"+myear+"comp"+tot+"";
					
					mycompid=mycompid.toLowerCase();
					
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
					newPath="..//repository//default//VcsTest//1.0//VcsTest-1.0.car//competition//"+arr.get(count-1);
					//newPath="..//video//"+arr.get(count-1);						
					//lpath=newPath;
					lpath="http://localhost:8080//VcsTest//competition//"+arr.get(count-1);
					ltitle=""+arr.get(count-1);
					
					int c;
					FileInputStream fis=new FileInputStream(path);
					FileOutputStream fos=new FileOutputStream(newPath);
					while((c=fis.read())!=-1)
					{
					fos.write((char)c);
					}
					}
					query2="insert into competition_master(compid,compname,compdate,cdesc,ctitle,cpath,duration) values('"+mycompid+"','"+tcompname+"','"+tcompdate+"','"+tcdesc+"','"+ltitle+"','"+lpath+"','"+tduration+"')";
					st=myconn.createStatement();
					kout.println("<html>");
	                kout.println("<body OnLoad=document.getElementById('login_error').submit();>");
	                kout.println("<form name=login_error id=login_error method=post action=/VcsTest/Mgt/MgtCompetitionCreate.jsp>");
	                kout.println("<input type=hidden name=ass_error value=\"      Competition Created.\">");
	                kout.println("</form>");
	                kout.println("</body>");
	                kout.println("</html>");

					
					st.executeUpdate(query2);
					//kout.println("<br><br><br><h1>Competition Added Successfully");
					mystr="Competition Added Successfully";
					
				}
				catch(Exception e)
				{
					System.err.println(e);
				}
			}
			else
			mystr="Sorry Connection Is In-Active";
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/Mgt/MgtCompetitionCreate.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html");
						

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");		
}
}	    
}