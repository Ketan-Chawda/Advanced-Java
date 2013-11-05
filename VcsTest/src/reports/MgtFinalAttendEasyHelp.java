package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: MgtFinalAttendEasyHelp
 *
 */
 public class MgtFinalAttendEasyHelp extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MgtFinalAttendEasyHelp() {
		super();
	}   	
	static String myerror=null;
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Deleting Activity Organized In VCS</title></head>");
		Connection mycon=null;
		Statement st;
		String v1="",v2="";
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
				int tot=Integer.parseInt(request.getParameter("total"));
				for(int k=1;k<tot;k++)
				{
				v1="c"+k;
				v2=request.getParameter(v1);
				//kout.println("<br>checkbox-"+v1+"<blockquote> value="+v2);
				st=mycon.createStatement();
				if(v2!=null)
				st.executeUpdate("delete from mgt_cattend where mid='"+v2+"'");
				}
			}
			catch(Exception e)
			{
			System.out.println(e);
			myerror=e.getMessage();

			}
			finally
			{	
			mycon.close();
			response.sendRedirect("/VcsTest/MgtFinalAttendEasyDelete");
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