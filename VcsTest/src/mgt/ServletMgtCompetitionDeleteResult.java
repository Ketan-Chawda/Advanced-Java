package mgt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mybest.*;


/**
 * Servlet implementation class for Servlet: ServletMgtCompetitionDeleteResult
 *
 */
 public class ServletMgtCompetitionDeleteResult extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletMgtCompetitionDeleteResult() {
		super();
	}   	
	static String myerror=null;

	static String mystr="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Competition Result Deletion Process Only By Management</title></head>");
		String tcompid="",query1="";
		
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
				 tcompid="11comp02";
				// tcompid=request.getParameter("compid");
				
				st=mycon.createStatement();
				query1="select * from comp_winner where  compid='"+tcompid+"'";
				ResultSet rs1=st.executeQuery(query1);
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				if(tot!=0)
				{
				st=mycon.createStatement();
				query1="delete from comp_winner where compid='"+tcompid+"'";
				st.executeUpdate(query1);
				st=mycon.createStatement();
								
				//kout.println("<br><br><br><h1>Competition Result Deleted Successfully");
				mystr="Competition Result Deleted";
				}
				else
				mystr="Result Does Not Exists";
				//kout.println("<br><br><br><h1>Competition Result Does Not Exists Or Some Restriction Is Kept<h2>");
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
		mystr="Sorry Connection Is In-Active";
		//kout.println("Sorry Connection Is Not Active Now");

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
			kout.println("<form id=vcs method=post action=/Mytest/Jleft.jsp>");
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
