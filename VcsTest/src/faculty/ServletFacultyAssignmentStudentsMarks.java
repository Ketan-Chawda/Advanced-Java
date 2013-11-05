package faculty;

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

import mybest.*;

/**
 * Servlet implementation class for Servlet: ServletFacultyAssignmentStudentsMarks
 *
 */
 public class ServletFacultyAssignmentStudentsMarks extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyAssignmentStudentsMarks() {
		super();
	}
	static String myerror=null;

	static String mystr="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Competition Creation Process</title></head>");
		HttpSession my=request.getSession();
		
		String taid="",tsid="",tsubmittedon="",tsecuredmarks="";
		String tfid="",tsubmissionwas="",query1="";
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;
		
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
				taid="11comp02";
				tsid="4";
				tsubmittedon="19/10/1989";
				tsecuredmarks="12";
				tsubmissionwas="11/2/2011";
				
				taid=request.getParameter("aid");
				tsid=request.getParameter("sid");
				tsubmissionwas=request.getParameter("submissionwas");
				tsecuredmarks=request.getParameter("securedmarks");
				
				tsubmittedon=request.getParameter("submittedon");
				tsubmittedon=m+"/"+d+"/"+(myear+2000);//access
				tsubmittedon=(myear+2000)+"/"+m+"/"+d;//db2
				
				tfid=request.getAttribute("uid").toString();
				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from assign_allocation where aid='"+taid+"'");
				int tot=0;
				while(rs.next())
				{
				tot=rs.getRow();
				}
				if(tot!=0)
				{
			query1="insert into assign_submitted(aid,sid,submittedon,submissionwas,securedmarks,fid) values('"+taid+"','"+tsid+"','"+tsubmittedon+"','"+tsubmissionwas+"','"+tsecuredmarks+"','"+tfid+"')";
					st=mycon.createStatement();
					st.executeUpdate(query1);
					kout.println("<br><br><br><h1>Student's Assignment Marks Are Declared Or Added Successfully");
					mystr="Assignment Marks Are Declared";
				}
				else
					mystr="Assignment Does Not Exists";
				//kout.println("<br><br><br><h1>Sorry Assignment Does Not Exists");
			}
			catch(Exception e)
			{
				System.err.println(e);
				myerror=e.getMessage();

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
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyAssignmentMarks.jsp>");
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