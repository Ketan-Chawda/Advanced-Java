

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybest.Fortable;
import mybest.Styles;

import com.ibm.db2.jcc.DB2RowId;
/**
 * Servlet implementation class for Servlet: Test1
 *
 */
 public class Test1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Test1() {
		super();
	}   	
	static String myerror=null;	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	   //protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			// TODO Auto-generated method stub
			response.setContentType("text/html");
			PrintWriter kout=response.getWriter();
			kout.println("<html><head><title>Activity Organized In VCS</title></head>");
			String tdate=new Date().toGMTString().toString();
			Connection mycon=null;
			Statement st;

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
				mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
				//mycon=Drcom.ibm.db2.jcc.DB2RowID");
				//Class.forName("com.ibm.db2.jcc.DB2RowID").newInstance();
				//kout.println("<br><br>**************************************************************************************************************");
				//kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
				//kout.println("<br><br>**************************************************************************************************************");
				if(mycon!=null)
				{
				try
				{
					kout.println("<br><br><center>**************************************************************************************************************");
					kout.println("<table border=0>");
					kout.println("<tr><td colspan=100%><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
					kout.println("<tr><td><br>");
					kout.println("<tr><td colspan=75%><blockquote><h2>"+"<td><h2>"+Styles.ccase(tdate));
					kout.println("<tr><td colspan=75%><blockquote><h1>"+Styles.ccase("report type : dynamic report")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
					
					kout.println("<tr><td colspan=75%><blockquote><h2>"+"<td><h2><a href='/VcsTest/Step1'>Generate More</a>");
					
					kout.println("</table>");
					kout.println("<br><br><br>**************************************************************************************************************</center><br>");
					
					
					
					String v1="",v2="";
					String str="",query1="";
					
					st=mycon.createStatement();
					query1="select userid from login_master";
					ResultSet rs=st.executeQuery(query1);
					//rs.getObject("rowid","com.ibm.db2.jcc.DB2RowId");
					//str=rs.getObject(1,"com.ibm.db2.jcc.DB2RowId");
					ResultSetMetaData kac=rs.getMetaData();
										
					//com.ibm.db2.jcc.DB2RowID rme=new com.ibm.db2.jcc.DB2RowID;
					
					//int tot=Integer.parseInt(request.getParameter("tcol"));
					int c=1;
					
					rs.close();
					st.close();
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
		catch(Exception k)
		{
			System.err.println(k);
		}
		finally
		{
			if(myerror!=null)
			kout.println("myerror="+myerror);
			//else
			//response.sendRedirect("/VcsTest/FinalOne");
			kout.close();
		}
	}    	  	    
}