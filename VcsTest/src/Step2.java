

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

/**
 * Servlet implementation class for Servlet: Step2
 *
 */
 public class Step2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
   static String myerror=null;
	
   public Step2() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
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
			//kout.println("<br><br>**************************************************************************************************************");
			//kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
			//kout.println("<br><br>**************************************************************************************************************");
			if(mycon!=null)
			{
			try
			{
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<table border=0>");
				kout.println("<tr><td colspan=200><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=150><blockquote><h1>"+Styles.ccase("step-2 for generating dynamic report")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+"<h2>"+Styles.ccase("here you need to select desired fields you want in report"));
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				
				String gtable=request.getParameter("mytable"),query1="";
				
				query1="select * from "+gtable;
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery(query1);
				
				ResultSetMetaData kac=rs.getMetaData();
				
				int tcol=kac.getColumnCount();
				
				kout.println("<center><table align='right' width=95% border=2 bordercolor='black'>");
				kout.println("<form action='/VcsTest/Final' method='post'>");
				kout.println("<input type='hidden' name='gtable' value='"+gtable+"'>");
				kout.println("<input type='hidden' name='tcol' value='"+tcol+"'>");
					
					for(int i=1;i<=tcol;i++)
					{
					String cname=kac.getColumnName(i);
					kout.println("<tr><td><input type='checkbox' name='"+"c"+i+"' value='"+cname+"'>");
					kout.println("<td>"+cname);
					}
					
				
				kout.println("<tr><td><input type='radio' name='format' value='one'>Format-1");
				kout.println("<td>If Column Are More");
				
				
				kout.println("<tr><td><input type='radio' name='format' value='two' checked='checked'>Format-2");
				kout.println("<td>If Column Are Few");
				
				kout.println("<tr><td>Rows");
				
				kout.println("<select name=trow>");
				kout.println("<option value=1>1</option>");
				kout.println("<option value=2>2</option>");
				kout.println("<option value=3>3</option>");
				kout.println("<option value=4>4</option>");
				kout.println("<option value=5>5</option>");
				kout.println("<option value=6>6</option>");
				kout.println("<option value=7>7</option>");
				kout.println("<option value=8>8</option>");
				kout.println("<option value=9>9</option>");
				kout.println("<option value=10>10</option>");
				kout.println("<option value=15><=15</option>");
				kout.println("<option value=20><=20</option>");
				kout.println("<option value=25><=25</option>");
				kout.println("<option value=30><=30</option>");
				kout.println("<option value=35><=35</option>");
				kout.println("<option value=40><=40</option>");
				kout.println("<option value=45><=45</option>");
				kout.println("<option value=50><=50</option>");
				kout.println("<option value=0 selected='selected'>All</option>");
				kout.println("</select>");

				kout.println("<td><input type='submit' value='Generate Dynamic Report'>");
				
				kout.println("</form>");
				
				rs.close();
				st.close();
		
				kout.println("</table></center>");
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
		kout.println("<html><body background='myreport1.jpg'>");
		kout.println("</body></html>");
		
		if(myerror!=null)
		kout.println("myerror="+myerror);
		//else
		//response.sendRedirect("/VcsTest/FinalOne");
		kout.close();
	}
}
}