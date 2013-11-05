package reports;

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

import mybest.Fortable;
import mybest.Styles;

/**
 * Servlet implementation class for Servlet: FacultyAlluminiEasyDelete
 *
 */
 public class FacultyAlluminiEasyDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FacultyAlluminiEasyDelete() {
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
		kout.println("<html><head><title>Easy Deletion Of Faculty Who Are Choosen As Allumini In VCS</title></head>");
		
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
				kout.println("<tr><td colspan=100%><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=75%><blockquote><h2>"+Styles.ccase("Operation For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=75%><blockquote><h2>"+Styles.ccase("Operation On : "+" Faculty Allumni")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				
				//kout.println("<br><br><h1>Faculty Who Are Choosen As Allumini In VCS</h1>");

				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from faculty_allumini");

				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor=black>");
				kout.println("<form action='/VcsTest/FacultyAlluminiEasyHelp' method='post'>");
				while(rs.next())
				{
					String str=rs.getString("fid");
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(153,"*"));
					
					if(c%2==1)
					kout.println("<tr><td colspan=95%><center><h1>Faculty Id Was : " +str+"</center></h1>");
					else
					kout.println("<tr><td colspan=95%><center><h2>Faculty Id Was : "+str+"</center></h2>");
					
					kout.println("<tr><td><input type='submit' value='Delete'><td>Sr No:<td>Faculty Name<td>Gender<td>Qualification<td>Country<td>city<td>");
					kout.println("<tr><td><input type='checkbox' name='"+"c"+c+"' value='"+str+"'>");
					
					kout.println("<td>"+c);
					kout.println("<td>"+rs.getString("f_fname"));
					kout.println("<td>"+rs.getString("gender"));
					kout.println("<td>"+rs.getString("qualification"));
					kout.println("<td>"+rs.getString("country"));
					kout.println("<td>"+rs.getString("city"));
				c=c+1;
				kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
				
				}
				kout.println("<input type='hidden' name='total' value="+c+">");
				kout.println("</form>");
				
				rs.close();
				st.close();
				kout.println("</table></center>");
				if(c==1)
				kout.println("<br><br><br><h1>Sorry No Faculty Is Selected For Faculty Allumini Till Now");
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