package faculty;

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
 * Servlet implementation class for Servlet: Role
 *
 */
 public class FacultyComment extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FacultyComment() {
		super();
	}   	 
	static String myerror=null;

	static String mystr="";

	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		HttpSession my=request.getSession(false);
		Statement st;
		Connection mycon=null;
		int ty=new Date().getYear()-100,td=new Date().getDate(),tm=new Date().getMonth()+1;
		int th=new Date().getHours(),tmin=new Date().getMinutes(),ts=new Date().getSeconds();

		String tdate=td+"/"+tm+"/"+ty+"   "+th+":"+tmin+":"+ts,forrole=my.getAttribute("urole").toString();
		String bid="",data="";		

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
						
			if(mycon!=null)
			{		
			bid=request.getParameter("bid");	
			String query="select * from blog_master where bid='"+bid+"'";						
			try
			{
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery(query);												
				while(rs.next())
				{										
					data=rs.getString("bmsg");														
				}
				kout.println("<html>");
                kout.println("<body OnLoad=document.getElementById('upd').submit();>");
                kout.println("<form name=upd id=upd method=post action='/VcsTest/Faculty/FacultyCommentCreate.jsp'>");
				kout.println("<input type=hidden name=bid value='"+bid+"'>");
				kout.println("<input type=hidden name=msg value='"+data+"'>");
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
			mystr="Sorry Connection Is Not Active Now";
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
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyCommentCreate.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");

			//kout.println("<html><body background='myreport1.jpg'>");
			//kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);
			
			kout.close();
}
}
}