package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class for Servlet: QuizAttendedReport
 *
 */
 public class QuizAttendedReport extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public QuizAttendedReport() {
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
		kout.println("<html><head><title>Student Who Have Attended The Respective Quiz</title></head>");
		
		HttpSession my=request.getSession(false);
		String tdate=new Date().toGMTString().toString(),forrole=my.getAttribute("urole").toString();
		
		Connection mycon=null;
		Statement st,st1;
		
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
				
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<table border=0>");
				kout.println("<tr><td colspan=200><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report Title : "+"Student Quiz Attended")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				//kout.println("<br><br><h1>Student Who Have Attended The Respective Quiz In VCS</h1>");

				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select distinct qid from student_quiz");
				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor=black>");
				while(rs.next())
				{
					String tqid=rs.getString("qid");
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(153,"*"));
					
					if(c%2==0)
					kout.println("<tr><td colspan=95%><center><h1>Quiz Id : " +tqid+"</center></h1>");
					else
					kout.println("<tr><td colspan=95%><center><h2>Quiz Id : " +tqid+"</center></h2>");
					
					st1=mycon.createStatement();
					ResultSet rs1=st1.executeQuery("select distinct sid from student_quiz where qid='"+tqid+"'");
						
					kout.println("<tr><td>Sr No:<td>Student Id<td>Quiz Date<td>");
					int t=1;
					while(rs1.next())
					{
					kout.println("<tr><td>"+t);
					kout.println("<td>"+rs1.getString("sid"));
					kout.println("<td>"+"need to be added ok?");//rs1.getString("qdate"));
					t=t+1;
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					}
					if(t==1)
					kout.println("<br><br><br><h1>Sorry No Student Have Attended This Quiz Till Now");
					c=c+1;
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					rs1.close();
					
				}
				rs.close();
				st.close();
				kout.println("</table></center>");
				if(c==1)
				kout.println("<br><br><br><h1>Sorry No Quiz Has Been Attended Till Now");
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