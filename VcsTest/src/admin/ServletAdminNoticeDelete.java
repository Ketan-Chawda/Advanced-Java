package admin;

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
 * Servlet implementation class for Servlet: ServletAdminNoticeDelete
 *
 */
 public class ServletAdminNoticeDelete extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminNoticeDelete() {
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
		kout.println("<html><head><title>Notice Deletion By Admin</title></head>");
		String tnid="",query1="",query2="";
		
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
				kout.println("<br><h1>Notice Deletion By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				tnid="bca";
				tnid=request.getParameter("nid");
				
				st=mycon.createStatement();
				query1="select * from notice where nid='"+tnid+"'";
				ResultSet rs=st.executeQuery(query1);
				int tot=0;
				while(rs.next())
				{
				tot=rs.getRow();
				}
				
				/*st=mycon.createStatement();
				query1="select * from notice where nid='"+tnid+"'and noticefrom='"+tuserid+"'";
				ResultSet rs1=st.executeQuery(query1);
				int status=0;
				while(rs.next() || trole.compareToIgnoreCase("admin")==0)
				{
				status=status+1;
				}*/
				
				if(tot!=0)// && status!=0)
				{
				st=mycon.createStatement();
				query1="delete from notice where nid='"+tnid+"'";
				st.executeUpdate(query1);
				//kout.println("<br><br><br><h1>Notice Deleted Successfully");
				mystr="Notice Deleted";
				}
				else
				mystr="Notice Does Not Exists";
				//kout.println("<br><br><br><h1>Sorry Admin This Notice Does Not Exists<h2>");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminNoticeDelete.jsp>");
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