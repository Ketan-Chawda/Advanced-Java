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
 * Servlet implementation class for Servlet: ServletFacultyQuizDefine
 *
 */
 public class ServletFacultyQuizDefine extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyQuizDefine() {
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
		kout.println("<html><head><title>Quiz Question Creation In Progress Only By Faculty</title></head>");
		HttpSession my=request.getSession(false);
		
		String myqid="",tfid="",tcoursename="",tsubject="";
		String tqdesc="",tnoq="",tweightage="";
		String tqdate="",query1="",query2="";
		int myear=new Date().getYear()-100;
		
		Connection mycon=null;
		Statement st,st1;
		
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
		kout.println("<br><br>**************************************************************************************************************");
		kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
		kout.println("<br><br>**************************************************************************************************************");
		if(mycon!=null)
		{
			
			try
			{
				
				tfid="11fact01";//from session
				tfid=my.getAttribute("uid").toString();
							
				tcoursename="bca";
				tsubject="java";
				tqdesc="for bca n java subject";
				tnoq="15";
				tweightage="10";
				tqdate="12/2/2010";
				
				
			
				st1=mycon.createStatement();
				ResultSet rs1=st1.executeQuery("select * from quiz_creation where coursename='"+tcoursename+"' and subject='"+tsubject+"'");
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				tot=tot+1;
				myqid=""+tsubject+tot+tcoursename+myear+"";
				
				if(tot%10==tot && myear%10==myear)
				myqid=""+tsubject+"0"+tot+tcoursename+"0"+myear+"";
				else if(tot%10==tot)
				myqid=""+tsubject+"0"+tot+tcoursename+myear+"";
				else if(myear%10==myear)
				myqid=""+tsubject+tot+tcoursename+"0"+myear+"";
			
				query2="insert into quiz_creation(qid,fid,coursename,subject,qdesc,noq,weightage,qdate) values('"+myqid+"','"+tfid+"','"+tcoursename+"','"+tsubject+"','"+tqdesc+"','"+tnoq+"','"+tweightage+"','"+tqdate+"')";
				st=mycon.createStatement();
				st.executeUpdate(query2);
				//kout.println("<br><br><br><h1>Quiz Declare or Allocated Successfully");
				mystr="Quiz Defined";
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
				myerror=e.getMessage();

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
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyQuestionCreate.jsp>");
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