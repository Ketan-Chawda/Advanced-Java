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
 * Servlet implementation class for Servlet: SubmissionReport
 *
 */
 public class SubmissionReport extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SubmissionReport() {
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
		kout.println("<html><head><title>Students Who Have Submitted Respective Assignment</title></head>");
		HttpSession my=request.getSession(false);
		
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
				String tdate=new Date().toGMTString().toString(),forrole=my.getAttribute("urole").toString();
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<table border=0>");
				kout.println("<tr><td colspan=200><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report Title : "+"Activity Organized")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				//kout.println("<br><br><h1>Students Who Have Submitted Respective Assignment In VCS</h1>");
				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from assign_submitted");
				
				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor=black>");
				while(rs.next())
				{
					String str=rs.getString("sid");
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(153,"*"));
					
					if(c%2==0)
					kout.println("<tr><td colspan=95%><center><h1>Student Id : " +str+"</center></h1>");
					else
					kout.println("<tr><td colspan=95%><center><h2>Student Id :"+str+"</center></h2>");
					
					kout.println("<tr><td>Sr No:<td>Assignment Id<td>Subject<td>Course<td>Submitted On<td>Marks Secured<td>");
					kout.println("<tr><td>"+c);
					String taid=rs.getString("aid"),tsubject="",tcoursename="";
					kout.println("<td>"+taid);
					st1=mycon.createStatement();
					ResultSet rs1=st1.executeQuery("select * from assign_allocation where aid='"+taid+"'");
					while(rs1.next())
					{
						tsubject=rs1.getString("subject");
						tcoursename=rs1.getString("coursename");
					}
					kout.println("<td>"+tsubject);
					kout.println("<td>"+tcoursename);
					
					kout.println("<td>"+rs.getString("submittedon"));
					kout.println("<td>"+rs.getString("securedmarks"));

					c=c+1;
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					
				}
				kout.println("</table></center>");
				if(c==1)
				kout.println("<br><br><br><h1>Sorry No-one Has Submitted Assignment Uptill Now");
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