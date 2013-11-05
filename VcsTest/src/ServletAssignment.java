

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
 public class ServletAssignment extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAssignment() {
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
		String course="",data="",quiz="",path="";		
		course=request.getParameter("coursename");
		quiz=request.getParameter("quiz");
		
		if(quiz.equals("qz"))
		{
			path="/VcsTest/Faculty/FacultyQuizCreate.jsp";
		}
		else if(quiz.equals("as"))
		{
			path="/VcsTest/Faculty/FacultyAssignmentCreate.jsp";
		}
		
		if(course.equals("-------------Select Course------------"))
		{
			kout.println("<html>");
	    	kout.println("<body OnLoad=document.getElementById('upd').submit();>");
            kout.println("<form name=upd id=upd method=post action="+path+">");
            kout.println("</form>");
            kout.println("</body>");
            kout.println("</html>");			
		}
		else
		{
			Connection mycon=null;
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
				mycon=DriverManager.getConnection("jdbc:odbc:myvcs");						
			}
			catch(Exception k)
			{
				System.err.println(k);
			}
			finally
			{
				if(mycon!=null)
				{
			String query="select subject from subject_master where coursename='"+course+"'";	
		
			try
			{
				Statement st;
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery(query);
		    	kout.println("<html>");
		    	kout.println("<body OnLoad=document.getElementById('upd').submit();>");
                kout.println("<form name=upd id=upd method=post action="+path+">");												
				while(rs.next())
				{					
					cnt++;
					data=rs.getString("subject");					
					kout.println("<input type=hidden name=s"+cnt+" value="+data+">");				
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
			}
				else
				mystr="Sorry Connection Is In-Active";
				kout.println("<html>");
				kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
				kout.println("<form id=vcs method=post action='"+path+"'>");
				kout.println("<input type=hidden name=common value='"+mystr+"' >");
				kout.println("</form>");
				kout.println("</body></html");
				kout.println("<html><body background='myreport1.jpg'>");
				kout.println("</body></html>");		
}   	  	    
}
}
}