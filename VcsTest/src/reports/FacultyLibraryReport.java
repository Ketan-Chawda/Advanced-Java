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
 * Servlet implementation class for Servlet: FacultyVideoReport
 *
 */
 public class FacultyLibraryReport extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FacultyLibraryReport() {
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
		kout.println("<html><head><title>View E-Books In Vcs</title></head>");
		
		HttpSession my=request.getSession(false);
		String tdate=new Date().toGMTString().toString(),forrole=my.getAttribute("urole").toString();
		
		Connection mycon=null;
		Statement st;
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
			if(mycon!=null)
			{
			try
			{
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<table border=0>");
				kout.println("<tr><td colspan=200><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report Title : "+"E-Books")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");

				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from library where status='book'");
				String fid="",lpath="",ltitle="",cdate="";
				int c=1;												
					kout.println("<body background='myreport1.jpg'>");
					kout.println("<center>");
					kout.println("<table border=0>");
																				
					int k=0;
					while(rs.next())
					{
						c++;
						
						fid=rs.getString("fid");
						lpath=rs.getString("lpath");
						ltitle=rs.getString("ltitle");
						cdate=rs.getString("cdate");
						
						kout.println("<tr>");
						kout.println("<td>");
						kout.println(fid);
						kout.println("</td>");	
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");
						
						kout.println("<td>");						
						kout.println("<a href=\""+lpath+"\" title=\""+ltitle+"\" target=\"_blank\">"+ltitle+"</a>");
						kout.println("</td>");
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");						
						
						kout.println("<td>");
						kout.println(cdate);
						kout.println("</td>");
																								
						kout.println("</tr>");
						kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
						
					}
					rs.close();
					st.close();
					kout.println("</table><br>");				
				if(c==1)
				kout.println("<br><br><h1>Sorry No Books Have Been Uploaded At Present");
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