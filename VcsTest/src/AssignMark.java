

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

import java.sql.*; 

/**
 * Servlet implementation class for Servlet: AssignMark
 *
 */
 public class AssignMark extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AssignMark() {
		super();
	}  
	static String myerror=null;

	static String mystr="";
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		Connection mycon=null;
		Statement st;
		ResultSet rs,rs1;
			
		int cnt=0;
		String aid="",data="",marks="";		
		aid=request.getParameter("a_id");
		if(aid.equals("---------------Select ID--------------"))
		{
			kout.println("<html>");
	    	kout.println("<body OnLoad=document.getElementById('upd').submit();>");
            kout.println("<form name=upd id=upd method=post action=/VcsTest/Faculty/FacultyAssignmentMarks.jsp>");
            kout.println("</form>");
            kout.println("</body>");
            kout.println("</html>");			
		}
		else
		{						
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
				mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
				
				if(mycon!=null)
				{														
					String query="select sid from assign_submitted where aid='"+aid+"'";
					String query1="select marks from assign_allocation where aid='"+aid+"'";
					
			try
			{
				st=mycon.createStatement();
				rs=st.executeQuery(query);				
		    	kout.println("<html>");
		    	kout.println("<body OnLoad=document.getElementById('upd').submit();>");
                kout.println("<form name=upd id=upd method=post action=/VcsTest/Faculty/FacultyAssignmentMarks.jsp>");												
				while(rs.next())
				{					
					cnt++;
					data=rs.getString("sid");					
					kout.println("<input type=hidden name=s"+cnt+" value="+data+">");				
				}				
				rs.close();
				rs1=st.executeQuery(query1);
				while(rs1.next())
				{
					marks=rs1.getString("marks");					
				}
				rs1.close();
				st.close();
				kout.println("<input type=hidden name=sid value="+aid+">");
				kout.println("<input type=hidden name=marks value="+marks+">");
				kout.println("<input type=hidden name=items value="+cnt+">");				
		        kout.println("</form>");
                kout.println("</body>");
                kout.println("</html>");				
			}
			catch(Exception e)
		    {
				System.err.println(e);
				myerror=e.getMessage();

		    }
			finally
			{
				mycon.close();
			}
			}
			else
				mystr="Connection Failed";
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
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyAssignmentMarks.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");
			
			/*kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");*/
			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);

			kout.close();
			}			
}   	  	    
}
}