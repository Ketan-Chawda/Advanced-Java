package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class for Servlet: ActivityReport
 *
 */
 public class ActivityReport extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ActivityReport() {
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
		Connection mycon=null;
		String tdate=new Date().toGMTString().toString(),forrole=my.getAttribute("urole").toString();
		
		
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
				kout.println("<tr><td colspan=100%><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=75%><blockquote><h2>"+Styles.ccase("Report For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=75%><blockquote><h2>"+Styles.ccase("Report Title : "+"Activity Organized")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				
				Statement st;
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from activity_master");
				
				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor='black'>");
				while(rs.next())
				{
					String str=rs.getString("actid");
					//kout.println("<tr><td colspan=11 width='90%'>****************************************************************************************************************************************************************************************************************");
					
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(150,"*"));
					
					if(c%2==1)
					kout.println("<tr><td colspan=95%><center><h1>Activity Id :"+str+"</center></h1>");
					else
					kout.println("<tr><td colspan=95%><center><h2>Activity Id :"+str+"</center></h2>");
					
					kout.println("<tr><td>Sr No:<td>Activity Name<td>Description<td>Organized Date<td>Duration<td>File<td>");
					kout.println("<tr><td>"+c);

					kout.println("<td>"+rs.getString("actname"));
					kout.println("<td>"+rs.getString("adesc"));
					kout.println("<td>"+rs.getString("actdate"));
					kout.println("<td>"+rs.getString("duration"));
					String p=rs.getString("apath");
					kout.println("<td><a href='"+p+"' target='_blank' >View</a>");
					c=c+1;
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					
				}
				rs.close();
				st.close();
		
				kout.println("</table></center>");
				if(c==1)
				kout.println("<br><br><br><h1>Sorry No Activity Is Declared Or Defined Till Now");
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