package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ServletAdminMgtDelete
 *
 */
 public class ServletAdminMgtDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminMgtDelete() {
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
		kout.println("<html><head><title>Management Deletion Only By Admin</title></head>");
		
		String tmid="",query1="";
		
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
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Management Deletion Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

				tmid="11mgt1";
				tmid=request.getParameter("mid");
				st=mycon.createStatement();
				ResultSet rs1=st.executeQuery("select * from mgt_master where mid='"+tmid+"'");
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}		
				if(tot!=0)
				{
				st=mycon.createStatement();
				query1="delete from mgt_master where mid='"+tmid+"'";
				st.executeUpdate(query1);
				st=mycon.createStatement();
				query1="delete from login_master where userid='"+tmid+"'";
				st.executeUpdate(query1);

				//kout.println("<br><br><br><h1>Management Deleted Successfully");
				mystr="Management Info Deleted";
				}
				else
				mystr="Management Is Not Deleted";
				//kout.println("<br><br><br><h1>Sorry Management Cannot Be Deleted Since Management May Not Exists Or Some Other Restriction Are Given");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminMgtDelete.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);
			
			kout.close();
}
}	    
}