package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: FacultyVideoReport
 *
 */
 public class StudentTakeAssignment extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public StudentTakeAssignment() {
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
		kout.println("<html><head><title>E-Books</title></head>");
		String fid="",lpath="",ltitle="",cdate="";
		String alcdate="",coursename="",subject="",marks="";
		
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
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from assign_allocation");
				int c=1;												
					kout.println("<body background=\"myreport1.jpg\">");
					kout.println("<center>");
					kout.println("<table border=0>");
					
					kout.println("<b>Submit Your Assignment Till Following Date</b>");
																				
					int k=0;
					while(rs.next())
					{
						c++;
						
						fid=rs.getString("aid");
						
						alcdate=rs.getString("alloc_date");
						coursename=rs.getString("coursename");
						subject=rs.getString("subject");
						
						lpath=rs.getString("apath");
						ltitle=rs.getString("atitle");
						cdate=rs.getString("submission_date");
						marks=rs.getString("marks");
						
						
						kout.println("<tr>");
						kout.println("<td>");
						kout.println(fid);
						kout.println("</td>");	
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");
												
						kout.println("<td>");
						kout.println(alcdate);
						kout.println("</td>");	
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");
												
						kout.println("<td>");
						kout.println(coursename);
						kout.println("</td>");	
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");
												
						kout.println("<td>");
						kout.println(subject);
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
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");
												
						kout.println("<td>");
						kout.println(marks);
						kout.println("</td>");	
						
						kout.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						kout.println("</td>");
																								
						kout.println("</tr>");
					}					
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