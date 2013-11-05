package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ServletSignout
 *
 */
 public class ServletSignout extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletSignout() {
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
		kout.println("<html><head><title>Signout Process</title></head>");
		HttpSession last=request.getSession(false);
		String sid=last.getId().toString(),myuser="",mycourse="",myrole="";
		int d=new Date().getDate(),m=new Date().getMonth()+1,y=new Date().getYear()-100;
		
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
			sid=last.getId().toString();
			kout.println("<br> session id="+sid);
			
			if(sid!=null)
			{
				st=mycon.createStatement();
				HttpSession t=request.getSession();
				
				Date ts=new Date(t.getLastAccessedTime());
				
				myuser=t.getAttribute("uid").toString();
				mycourse=t.getAttribute("ucourse").toString();
				myrole=t.getAttribute("urole").toString();
				
				//String tsdate=d+"/"+m+"/"+y;//access
				String tsdate=(y+2000)+"-"+m+"-"+d;//db2
	
				if(myrole.compareToIgnoreCase("management")==0)
				{
				st=mycon.createStatement();
				st.executeUpdate("insert into mgt_attend values('"+myuser+"','"+tsdate+"','"+ts+"','OUT')");
				}
				else if(myrole.compareToIgnoreCase("faculty")==0)
				{
				st=mycon.createStatement();
				st.executeUpdate("insert into faculty_attend values('"+myuser+"','"+tsdate+"','"+ts+"','OUT')");
				}
				else if(myrole.compareToIgnoreCase("student")==0)
				{
				st=mycon.createStatement();
				st.executeUpdate("insert into student_attend values('"+myuser+"','"+mycourse+"','"+tsdate+"','"+ts+"','OUT')");
				}
				last.invalidate();
				last=null;
				response.sendRedirect("/VcsTest/home.jsp");
				//kout.println("<br> ok destroyed");
			}
			else
			{
				kout.println("<br><br><br><h1>Session Does Not Exists");
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
		}
		}
		else
			kout.println("<br><br><h2><center>Sorry Connection Is Not Active Now");
			
		}
		catch(Exception e)
		{
			System.err.println(e);
			myerror=myerror +"<br>"+ e.getMessage();

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