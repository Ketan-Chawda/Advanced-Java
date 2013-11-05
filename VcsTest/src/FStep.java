

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybest.Styles;

/**
 * Servlet implementation class for Servlet: FStep
 *
 */
 public class FStep extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FStep() {
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
	kout.println("<html><head><title>Activity Organized In VCS</title></head>");
	HttpSession my=request.getSession(false);
	
	String myrole="",mypath="",tdate=new Date().toGMTString().toString();
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
			mypath="/VcsTest/Faculty/FacultyHome.jsp";
			
			kout.println("<br><br><center>**************************************************************************************************************");
			kout.println("<table border=0>");
			kout.println("<tr><td colspan=100%><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
			kout.println("<tr><td><br>");
			kout.println("<tr><td colspan=75%><blockquote><h2>"+"<td><h2>"+Styles.ccase(tdate));
			kout.println("<tr><td colspan=75%><blockquote><h1>"+Styles.ccase("step -1 for generating dynamic report")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
			kout.println("<tr><td colspan=75%><blockquote><h2>"+"<h2>"+Styles.ccase("here you need to give the table which exists")+"<td><h2><a href='"+mypath+"'>Home</a>");
			kout.println("</table>");
			kout.println("<br><br><br>**************************************************************************************************************</center><br>");
			
			
			kout.println("<form action='/VcsTest/Step2' method='post'>");
			kout.println("<center>Please Enter Table Name Here: <select name='mytable'>");
			kout.println("<option>ACTIVITY_MASTER</option>");
			kout.println("<option>ACTIVITY_PARTICIPANTS</option>");
			kout.println("<option>ASSIGN_ALLOCATION</option>");
			kout.println("<option>ASSIGN_SUBMITTED</option>");
			kout.println("<option>BLOG_MASTER</option>");
			
			kout.println("<option>COMMENT_MASTER</option>");
			kout.println("<option>COMP_PARTICIPANTS</option>");
			kout.println("<option>COMP_WINNER</option>");
			kout.println("<option>COMPETITION_MASTER</option>");
			//kout.println("<option>COUNTALL</option>");
			
			kout.println("<option>COURSE_MASTER</option>");
			kout.println("<option>FACULTY_ALLUMINI</option>");
			kout.println("<option>FACULTY_ATTEND</option>");
			kout.println("<option>FACULTY_CATTEND</option>");
			kout.println("<option>FACULTY_MASTER</option>");
			
			kout.println("<option>FACULTY_REGISTER</option>");
			kout.println("<option>LIBRARY</option>");
			//kout.println("<option>LOGIN_MASTER</option>");
			kout.println("<option>MGT_ALLUMINI</option>");
			//kout.println("<option>MGT_ATTEND</option>");
			
			//kout.println("<option>MGT_CATTEND</option>");
			kout.println("<option>MGT_MASTER</option>");
			kout.println("<option>MGT_REGISTER</option>");
			kout.println("<option>NEWS</option>");
			kout.println("<option>NEWSLETTER</option>");
			
			//kout.println("<option>NOTICE</option>");
			kout.println("<option>QUES_CREATION</option>");
			kout.println("<option>QUIZ_CREATION</option>");
			kout.println("<option>QUIZ_RESULT</option>");
			kout.println("<option>STUDENT_ALLUMINI</option>");
			
			kout.println("<option>STUDENT_ATTEND</option>");
			kout.println("<option>STUDENT_CATTEND</option>");
			kout.println("<option>STUDENT_MASTER</option>");
			kout.println("<option>STUDENT_QUIZ</option>");
			kout.println("<option>STUDENT_REGISTER</option>");
			
			kout.println("<option>SUBJECT_MASTER</option>");
			kout.println("<option>SUBMIT_ASSIGNMENT</option>");
			kout.println("<option>SUBSCRIBE</option>");
			kout.println("</select>");
			kout.println("<table border=0><tr><td><input type='button' name='goback' value='Back'>");
			kout.println("<td><input type='reset' value='ResetAll'>");
			kout.println("<td><input type='submit' value='Next'>");
			kout.println("</table></center>");
			kout.println("</form>");
		
	}
	catch(Exception e)
	{
		
		System.out.println(e);
		myerror=e.getMessage();
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
}
finally
{
	kout.println("<html><body background='myreport1.jpg'>");
	kout.println("</body></html>");
	
	if(myerror!=null)
	kout.println("myerror="+myerror);
	//else
	//response.sendRedirect("/VcsTest/Step2");
	kout.close();
}
}   	  	    
}