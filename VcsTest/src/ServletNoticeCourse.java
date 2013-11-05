

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Role
 *
 */
 public class ServletNoticeCourse extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletNoticeCourse() {
		super();
	}
	static String mystr="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();				
		int cnt=0;
		String course="",data="";		
		course=request.getParameter("course");		
		String query="select coursename from course_master";	
		
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
				ResultSet rs=st.executeQuery(query);
		    	kout.println("<html>");
                kout.println("<body OnLoad=document.getElementById('upd').submit();>");
                kout.println("<form name=upd id=upd method=post action=/VcsTest/Admin/AdminNoticeCreate.jsp>");												
				while(rs.next())
				{					
					cnt++;
					data=rs.getString(1);					
					kout.println("<input type=hidden name=c"+cnt+" value="+data+">");				
				}
				kout.println("<input type=hidden name=course value="+course+">");
				kout.println("<input type=hidden name=items value="+cnt+">");				
		        kout.println("</form>");
                kout.println("</body>");
                kout.println("</html>");				
			}
			catch(Exception e)
		    {
				System.err.println(e);
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
		}
		finally
		{
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminNoticeCreate.jsp>");												
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");
			
			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");
			kout.close();
} 
}
}