package student;

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
 * Servlet implementation class for Servlet: ServletStudentAttendCount
 *
 */
 public class ServletStudentAttendCount extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletStudentAttendCount() {
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
		kout.println("<html><head><title>Student's Attendance Report In VCS</title></head>");
		int myear=new Date().getYear()+1900,d=new Date().getDate(),m=new Date().getMonth()+1;
		String str="",tcoursename="",tudate="";
		
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
				ResultSet rs=st.executeQuery("select * from login_master where role='student'");
				while(rs.next())
				{
					str=rs.getString("userid");
					tcoursename=rs.getString("coursename");
					st=mycon.createStatement();
					ResultSet rs1=st.executeQuery("select distinct sid,ondate from student_attend where sid='"+str+"' and status='IN' ");
					int total=0;
					while(rs1.next())
					{
					tcoursename=rs1.getString("coursename");
					total=rs1.getRow();
					}
					//tudate=""+m+"/"+d+"/"+myear+"";//access
					tudate=myear+"-"+m+"-"+d;//db2
					st=mycon.createStatement();
					rs1=st.executeQuery("select * from student_cattend where sid='"+str+"'");	
					int exists=0;
					while(rs1.next())
					{
					exists=rs1.getRow();
					}
					
					st=mycon.createStatement();
					if(exists!=0)
					st.executeUpdate("delete from student_cattend where sid='"+str+"'");	
					
					st=mycon.createStatement();
					st.executeUpdate("insert into student_cattend(sid,coursename,udate,total) values('"+str+"','"+tcoursename+"','"+tudate+"','"+total+"')");
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
				response.sendRedirect("/VcsTest/Stud/StudHome.jsp");
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
