package faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybest.*;


/**
 * Servlet implementation class for Servlet: ServletFacultyQuizDelete
 *
 */
 public class ServletFacultyQuizDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyQuizDelete() {
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
		kout.println("<html><head><title>Quiz Deletion Process Only By Faculty</title></head>");
		HttpSession my=request.getSession(false);
		String tqid="",tfid="",query1="";
		
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
				
				tfid=my.getAttribute("uid").toString();
				
				tqid="bca01java";
				tqid=request.getParameter("qid");
				
				st=mycon.createStatement();
				query1="select * from quiz_creation where qid='"+tqid+"' where fid='"+tfid+"'";
				ResultSet rs1=st.executeQuery(query1);
				
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				
				if(tot!=0)
				{
				st=mycon.createStatement();
				query1="delete from ques_creation where qid='"+tqid+"'";
				st.executeUpdate(query1);
				st=mycon.createStatement();
				query1="delete from quiz_creation where qid='"+tqid+"'";
				st.executeUpdate(query1);
				//kout.println("<br><br><br><h1>Quiz Deleted Successfully");
				mystr="Quiz Deleted";
				}
				else
					mystr="Quiz Not Deleted";
			//	kout.println("<br><br><br><h1>Quiz Was Not Defined Or Was Not Declared Or Some Restriction May Be Kept<h2>");
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
		mystr="Sorry Connection Is Not Active Now";

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
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyQuizDelete.jsp>");
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