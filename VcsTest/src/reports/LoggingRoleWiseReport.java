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
 * Servlet implementation class for Servlet: LoggingRoleWiseReport
 *
 */
 public class LoggingRoleWiseReport extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoggingRoleWiseReport() {
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
		kout.println("<html><head><title>View All Current Logging Of VCS</title></head>");
		
		HttpSession my=request.getSession(false);
		String tdate=new Date().toGMTString().toString(),forrole=my.getAttribute("urole").toString();
		
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
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<table border=0>");
				kout.println("<tr><td colspan=200><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report Title : "+"Current Logging Role Wise")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				//kout.println("<br><br><h1>View All Current Logging Role Wise Of VCS</h1>");

				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select distinct role from login_master");

				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor=black>");
				while(rs.next())
				{
					String str=rs.getString("role");
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(153,"*"));
										
					if(c%2==1)
					kout.println("<tr><td>Sr No:"+c+"<td colspan=95%><center><h1>Role Is : " +str+"</center></h1>");
					else
					kout.println("<tr><td>Sr No:"+c+"<td colspan=95%><center><h2>Role Is : "+str+"</center></h2>");
				 
					st=mycon.createStatement();
					ResultSet rs1=st.executeQuery("select * from login_master where role='"+str+"'");
					
					int k=1;
					while(rs1.next())
					{
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(151,"-"));
					kout.println("<tr><td>Total:<td>User Id<td>Locked<td>Priority<td>Password<td>");
					
					kout.println("<tr><td>"+k);
					kout.println("<td>"+rs1.getString("userid"));
					kout.println("<td>"+rs1.getString("locked"));
					kout.println("<td>"+rs1.getString("prior"));
					kout.println("<td>"+Secure.getp(rs1.getString("password")));
					k=k+1;
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					
					}
					rs1.close();
					if(k==1)
					kout.println("<center><h3>Sorry No User Is Currently With This Role In VCS</h3></center>");
					c=c+1;
					//kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					
			}
				rs.close();
				st.close();
				kout.println("</table></center>");
			if(c==1)
			kout.println("<br><br><br><h1>Sorry No User Is Currently Working With VCS");
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