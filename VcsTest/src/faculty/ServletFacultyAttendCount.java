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

/**
 * Servlet implementation class for Servlet: ServletFacultyAttendCount
 *
 */
 public class ServletFacultyAttendCount extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyAttendCount() {
		super();
	}   	
	static String myerror=null;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Faculty's Final Attendance Report In VCS</title></head>");
		int myear=new Date().getYear()+1900,d=new Date().getDate(),m=new Date().getMonth()+1;
		String str="",tudate="";
		
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
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from login_master where role='faculty'");
				
				while(rs.next())
				{
					str=rs.getString("userid");
					st=mycon.createStatement();
					ResultSet rs1=st.executeQuery("select distinct fid,ondate from faculty_attend where fid='"+str+"' and status='IN' ");
					int total=0;
					while(rs1.next())
					{
					total=rs1.getRow();
					}
					//tudate=""+m+"/"+d+"/"+myear+"";//access
					tudate=myear+"-"+m+"-"+d;//db2

					st=mycon.createStatement();
					rs1=st.executeQuery("select * from faculty_cattend where fid='"+str+"'");	
					int exists=0;
					while(rs1.next())
					{
					exists=rs1.getRow();
					}
					
					st=mycon.createStatement();
					if(exists!=0)
					st.executeUpdate("delete from faculty_cattend where fid='"+str+"'");	
				
					st=mycon.createStatement();
					st.executeUpdate("insert into faculty_cattend(fid,udate,total) values('"+str+"','"+tudate+"','"+total+"')");
				
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
				response.sendRedirect("/VcsTest/Faculty/FacultyHome.jsp");
				
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