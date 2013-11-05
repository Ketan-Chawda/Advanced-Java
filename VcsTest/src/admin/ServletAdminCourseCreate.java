package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.convert.IntegerConverter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.fmt.ParseNumberSupport;
import org.apache.taglibs.standard.tag.rt.fmt.ParseNumberTag;

import java.sql.*; 

/**
 * Servlet implementation class for Servlet: ServletAdminCourseCreate
 *
 */
 public class ServletAdminCourseCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminCourseCreate() {
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
		kout.println("<html><head><title>Course Creation Only By Admin</title></head>");
		
		String tcoursename="",tcdesc="",ttotal_subject="",tsubject="";
		String ttofid="",tsday="",tsmonth="",tsyear="",tstart_date="";
		String tduration="",tduryear="",tdurmonth="",tsubdesc="",query1="",query2="";

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
				
				//tcoursename="bca";
				tcoursename=request.getParameter("coursename");
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from course_master where coursename='"+tcoursename+"'");
				int ok=0;
				while(rs.next())
				{
					ok=ok+1;
				}
				rs.close();
				if(ok==0)
				{
				tcdesc="bachelor of computer application";
				ttotal_subject="5";
				tsubject="dbms";
				ttofid="faculty-1";
				tsday="19";
				tsmonth="10";
				tsyear="1989";
				tstart_date=tsmonth+"/"+tsday+"/"+tsyear;//access
				//tstart_date= tsyear + "-"+ tsmonth + "-" + tsday;//db2
				
				tduryear="default year and  ";
				tdurmonth="default months";
				tduration=tduryear+"&"+tdurmonth;
				
				//tcdesc=request.getParameter("cdesc");
				ttotal_subject=request.getParameter("total_subject");
				//tsubject=request.getParameter("subject");
				//ttofid=request.getParameter("tofid");
				
				tsday=request.getParameter("sday");
				tsmonth=request.getParameter("smonth");
				tsyear=request.getParameter("syear");
				tstart_date=tsmonth+"/"+tsday+"/"+tsyear;//access
				//tstart_date= tsyear + "/"+ tsmonth + "/" + tsday;//db2
				
				tduryear=request.getParameter("duryear");
				tdurmonth=request.getParameter("durmonth");
				tduration=tduryear+"&"+tdurmonth;				
				tsubdesc="no desc";
				query1="insert into course_master(coursename,total_subject,start_date,duration,cdesc) values('"+tcoursename+"','"+ttotal_subject+"','"+tstart_date+"','"+tduration+"','"+tcdesc+"')";  
				st=mycon.createStatement();
				st.executeUpdate(query1);
				
				int upto=Integer.parseInt(ttotal_subject);
		        for(int start=1;start<=upto;start++)
		        {
		        tsubject=request.getParameter("s"+start);
		        ttofid=request.getParameter("f"+start);
		        query2="insert into subject_master(coursename,subject,tofid,subdesc) values('"+tcoursename+"','"+tsubject+"','"+ttofid+"','"+tsubdesc+"')";
		        st=mycon.createStatement();
				st.executeUpdate(query2);
				}
		        //kout.println("<br><br><br><h1>Course Declared And Subject Are Inserted Successfully In VCS</h2>");
		        mystr="Course Created With Subjects";
				}
		        else
		        mystr="Course Already Exists";
		        	
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminCourseCreate.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);
			
			kout.close();
}
}	    
}