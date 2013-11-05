package faculty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mybest.*;


/**
 * Servlet implementation class for Servlet: ServletFacultyAssignmentCreate
 *
 */
 public class ServletFacultyAssignmentCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyAssignmentCreate() {
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
		kout.println("<html><head><title>Assignment Creation Process Only Faculty</title></head>");
		
		Connection myconn=null;
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		myconn=DriverManager.getConnection("jdbc:odbc:myvcs");
		//kout.println("<br><br>**************************************************************************************************************");
		//kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
		//kout.println("<br><br>**************************************************************************************************************");
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
					/*kout.println("<br><br><center>**************************************************************************************************************");
					kout.println("<br><h1>Assignment Creation Process Only For Faculty In VCS</h1>");
					kout.println("<br><br><br>**************************************************************************************************************<br>");*/

					Statement st,st1;
					String myaid,talloc_date,tcoursename,tsubject,tsday,tsmonth,tsyear,tadesc,tsubmission_date,tmarks,query1,query2,ltitle="",lpath="",path="";
					int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;
					
					talloc_date="1/2/2010";
					tcoursename="bca";
					tsubject="java";
					tadesc="for bca n java subject";
					tsubmission_date="12/2/2010";
					tmarks="10";
					
					
					tcoursename=request.getParameter("coursename");
					tsubject=request.getParameter("subject");
					tadesc=request.getParameter("adesc");
					tsday=request.getParameter("sday");
					tsmonth=request.getParameter("smonth");
					tsyear=request.getParameter("syear");
					
					tsubmission_date=tsmonth+"/"+tsday+"/"+tsyear;//access
					tsubmission_date=tsyear+"-"+tsmonth+"-"+tsday;//db2
					
					talloc_date=m+"/"+d+"/"+(myear+2000);//access
					talloc_date=(myear+2000)+"-"+m+"-"+d;//db2
					
					
					tmarks=request.getParameter("marks");
					path=request.getParameter("afile");	
					
					st1=myconn.createStatement();
					ResultSet rs1=st1.executeQuery("select * from assign_allocation where coursename='"+tcoursename+"' and subject='"+tsubject+"'");
					int tot=0;
					while(rs1.next())
					{
					tot=rs1.getRow();
					}
					tot=tot+1;
					myaid=""+tcoursename+tot+tsubject+myear+"";
					
					myaid=myaid.toLowerCase();
					
					if(tot%10==tot && myear%10==myear)
					myaid=""+tcoursename+"0"+tot+tsubject+"";
					else if(tot%10==tot)
					myaid=""+tcoursename+"0"+tot+tsubject+"";
					else if(myear%10==myear)
					myaid=""+tcoursename+tot+tsubject+"";
					
					myaid=myaid.toLowerCase();
											
					
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
					newPath="..//repository//default//VcsTest//1.0//VcsTest-1.0.car//assignment//"+arr.get(count-1);
					//newPath="..//video//"+arr.get(count-1);						
					//lpath=newPath;
					lpath="http://localhost:8080//VcsTest//assignment//"+arr.get(count-1);
					ltitle=""+arr.get(count-1);
					
					int c;
					FileInputStream fis=new FileInputStream(path);
					FileOutputStream fos=new FileOutputStream(newPath);
					while((c=fis.read())!=-1)
					{
					fos.write((char)c);
					}
					
				query2="insert into assign_allocation(aid,alloc_date,coursename,subject,atitle,apath,submission_date,marks) values('"+myaid+"','"+talloc_date+"','"+tcoursename+"','"+tsubject+"','"+ltitle+"','"+lpath+"','"+tsubmission_date+"','"+tmarks+"')";
					st=myconn.createStatement();
					mystr="Assignment Created Successfully";
					st.executeUpdate(query2);
					}
				}
				catch(Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
			else
			mystr="Sorry Connection Is In-Active";
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyAssignmentCreate.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");
			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");		
}
}	    
}