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
 * Servlet implementation class for Servlet: StudentResultCount
 *
 */
 public class StudentResultCount extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public StudentResultCount() {
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
		kout.println("<html><head><title>Calculating The Quiz Marks Of Student Who Attended The Self-Quiz In VCS</title></head>");
		String tsid="",tqid="",tselected="",tcorrectone="";
		int total=0;
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;
		
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
				st=mycon.createStatement();
				st.executeUpdate("delete from quiz_result");
				ResultSet rs=st.executeQuery("select distinct sid,qid from student_quiz");
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><br><h1>Calculating The Quiz Marks Of Student Who Attended The Self-Quiz In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");

				while(rs.next())
				{
				tsid=rs.getString("sid");
				tqid=rs.getString("qid");
				total=0;
				st1=mycon.createStatement();
				
				ResultSet rs1=st1.executeQuery("select * from student_quiz where sid='"+tsid+"' and qid='"+tqid+"'");
				while(rs1.next())
				{
				tselected=rs1.getString("selected");
				tcorrectone=rs1.getString("correctone");
				if(tselected.compareToIgnoreCase(tcorrectone)==0)
				total=total+1;
				}
				}
				String tqdate="";
				tqdate=m+"/"+d+"/"+(myear+2000);//access
				tqdate=(myear+2000)+"-"+m+"/"+d;//db2
				st=mycon.createStatement();
				st.executeUpdate("insert into quiz_result(sid,qid,score,qdate) values('"+tsid+"','"+tqid+"','"+total+"','"+tqdate+"')");
				st=mycon.createStatement();
				st.executeUpdate("delete from student_quiz");
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
			kout.println("Sorry Connection Is Not Active Now");
		
		}
		catch(Exception k)
		{
			System.err.println(k);
			myerror=myerror +"<br>"+ k.getMessage();

		}
		finally
		{
			kout.println("<br><br><h1><center> Quiz Finished Ok Check Your Marks In Reports");
			kout.println("<br><br><a href='/VcsTest/Stud/StudHome.jsp'>Go To Student Home</a></center>");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);
			
			kout.close();
}
}
}