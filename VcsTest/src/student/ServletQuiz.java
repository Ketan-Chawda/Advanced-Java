package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mybest.*;
import java.util.Date;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class for Servlet: ServletQuiz
 *
 */
 public class ServletQuiz extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletQuiz() {
		super();
	}   	
	static String myerror=null;

	static int t=0;
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Student Quiz In Progress</title></head>");
		
		HttpSession my=request.getSession(false);
		String which="",str="",a1="",a2="",a3="",a4="";
		String tsid="",tcorrectone="",tqid="",tselected="";
		
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
				tqid="q1";
				tsid="08bca12";
				tqid=request.getParameter("qid");
				//tsid=request.getAttribute("uid").toString();
				which=request.getParameter("temp");
				if(t==0)
				{
				t=1;
				//m=new Date().getTime();
				}
				else if(which.compareToIgnoreCase("yes")==0)
				{
				t=t-1;
				st=mycon.createStatement();
				st.executeUpdate("delete from student_quiz where qid='"+tqid+"'and qno='"+t+"'");
				}
				else
				{
				tselected=request.getParameter("selected");
				tcorrectone=request.getParameter("correct");
				st=mycon.createStatement();
				st.executeUpdate("insert into student_quiz(sid,qid,qno,selected,correctone) values('"+tsid+"','"+tqid+"','"+t+"','"+tselected+"','"+tcorrectone+"')");
				t=t+1;
				}
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from ques_creation where qid='"+tqid+"'");
				int c=0,high=0;
				while(rs.next())
				{
				c=rs.getRow();
				if(c==t)
				{
				str=rs.getString("question");
				a1=rs.getString("op1");
				a2=rs.getString("op2");
				a3=rs.getString("op3");
				a4=rs.getString("op4");
				tcorrectone=rs.getString("correctone");
				}
				high=rs.getRow();
				}
				if(c!=0 && t<=high)
				{
				kout.println("<html>");
				kout.println("<body OnLoad=document.getElementById('kac').submit()>");
				kout.println("<form id=kac method=post action=/VcsTest/Allq.jsp>");
				kout.println("<input type=hidden name=k1 value='"+str+"'>");
				kout.println("<input type=hidden name=o1 value='"+a1+"'>");
				kout.println("<input type=hidden name=o2 value='"+a2+"'>");
				kout.println("<input type=hidden name=o3 value='"+a3+"'>");
				kout.println("<input type=hidden name=o4 value='"+a4+"'>");
				kout.println("<input type=hidden name=correct value='"+tcorrectone+"'>");
				kout.println("<input type=hidden name=qid value='"+tqid+"'>");
				//kout.println("<input type=hidden name=lasts value='"+s+"'>");
				
				kout.println("</form>");
				}
				else if(c==0)
				kout.println("<br><br><h1><center> Quiz Does Not Exists</center>");
				else
				{
				t=0;
				response.sendRedirect("/VcsTest/StudentResultCount");
				//kout.println("<br><br><h1><center> Quiz Finished Ok Check Your Marks In Reports</center>");
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
