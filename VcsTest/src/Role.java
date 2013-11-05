

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
 public class Role extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Role() {
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
		Statement st;
		
		int cnt=0;
		String role="",data="";
		String query="";
		role=request.getParameter("role");				
		Connection mycon=null;
		if(role.equals("STUDENT"))
		{
			query="select rid from student_register";
		}
		else if(role.equals("FACULTY"))
		{
			query="select rid from faculty_register";
		}
		else if(role.equals("MGT"))
		{
			query="select rid from mgt_register";
		}
		else
		{
			kout.println("<html>");
            kout.println("<body OnLoad=document.getElementById('upd').submit();>");
            kout.println("<form name=upd id=upd method=post action=/VcsTest/Admin/AdminStudentConfirmRequest.jsp>");
            kout.println("</form>");
            kout.println("</body>");
            kout.println("</html>");
		}	
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
                kout.println("<form name=upd id=upd method=post action=/VcsTest/Admin/AdminStudentConfirmRequest.jsp>");												
				while(rs.next())
				{					
					cnt++;
					data=rs.getString(1);					
					kout.println("<input type=hidden name=r"+cnt+" value="+data+">");				
				}				
				kout.println("<input type=hidden name=items value="+cnt+">");
				kout.println("<input type=hidden name=role value="+role+">");
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
			mystr="Sorry Connection Is Not Active Now";
		}
		catch(Exception k)
		{
			System.err.println(k);
		}
		finally
		{
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminStudentConfirmRequest.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");

			/*kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");*/
			
			kout.close();
}
}
}