

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
import javax.servlet.http.HttpSession;

import mybest.Fortable;
import mybest.Styles;

/**
 * Servlet implementation class for Servlet: Final
 *
 */
 public class Final extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Final() {
		super();
	}   	
	static String myerror=null;
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	   //protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			// TODO Auto-generated method stub
			response.setContentType("text/html");
			PrintWriter kout=response.getWriter();
			kout.println("<html><head><title>Dynamic Reports In VCS</title></head>");
			HttpSession my=request.getSession(false);
			
			String tdate=new Date().toGMTString().toString();
			String myrole="",mypath="";
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
					myrole=my.getAttribute("urole").toString();
					if(myrole.compareToIgnoreCase("admin")==0)
					mypath="/VcsTest/Step1";
					else if(myrole.compareToIgnoreCase("management")==0)
					mypath="/VcsTest/MStep";
					else if(myrole.compareToIgnoreCase("faculty")==0)
					mypath="/VcsTest/FStep";
					else if(myrole.compareToIgnoreCase("student")==0)
					mypath="/VcsTest/SStep";
				
				try
				{
					kout.println("<br><br><center>**************************************************************************************************************");
					kout.println("<table border=0>");
					kout.println("<tr><td colspan=100%><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
					kout.println("<tr><td><br>");
					kout.println("<tr><td colspan=75%><blockquote><h2>"+"<td><h2>"+Styles.ccase(tdate));
					kout.println("<tr><td colspan=75%><blockquote><h1>"+Styles.ccase("report type : dynamic report")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
					
					kout.println("<tr><td colspan=75%><blockquote><h2>"+"<td><h2><a href='"+mypath+"'>Generate More</a>");
					
					kout.println("</table>");
					kout.println("<br><br><br>**************************************************************************************************************</center><br>");
					
					String gtable=request.getParameter("gtable"),query1="";
					//kout.println("<br>table name is="+gtable);
					String which=request.getParameter("format");
				//	kout.println("<br>format name is="+f);
					int count=Integer.parseInt(request.getParameter("trow")),inc=1,keep=0;
					
					
					if(count==0)
					inc=0;
					else
					count=count-1;
						
					
					
					/*kout.println("<br>count is="+count);
					kout.println("<br>inc is="+inc);
					kout.println("<br>keep is="+keep);*/
					
					String v1="",v2="";
					String str="";
					
					st=mycon.createStatement();
					query1="select * from " + gtable;
					ResultSet rs=st.executeQuery(query1);
					ResultSetMetaData kac=rs.getMetaData();
					
					int tot=Integer.parseInt(request.getParameter("tcol"));
					int c=1;
					
				if(which.compareToIgnoreCase("one")==0)
				{
					kout.println("<br><br>");
					keep=0;
					while(rs.next())
					{
						if(keep<=count)
						{
						keep=keep+inc;
						kout.println("<center><table width=90% border=2 bordercolor='black'>");
						
						kout.println("<tr><td width=25%>Sr No:<td>"+c);	
						
						for(int k=1;k<=tot;k++)
						{
						v1="c"+k;
						v2=request.getParameter(v1);
						if(v2!=null)
						{
						str=kac.getColumnName(k);
						kout.println("<tr><td width=25%>"+str);
						str=rs.getString(v2);
						kout.println("<td>"+str);
						}
						}	
						kout.println("</table></center>");
						c=c+1;
						}
					}
				
				
					}
					else
					{
						keep=0;
						kout.println("<center><table align='right' width=95% border=2 bordercolor='black'>");
						while(rs.next())
						{
							if(keep<=count)
							{
							keep=keep+inc;
									
							kout.println("<tr><td colspan=100%><center>"+Fortable.display(150,"*"));
							kout.println("<tr><td>Sr No");
						
							for(int k=1;k<=tot;k++)
							{
							v1="c"+k;
							v2=request.getParameter(v1);
							if(v2!=null)
							{
							str=kac.getColumnName(k);
							kout.println("<td>"+str);
							}
							}
							
							kout.println("<tr><td>"+c);	
							for(int k=1;k<=tot;k++)
							{
							v1="c"+k;
							v2=request.getParameter(v1);
							if(v2!=null)
							{
								str=rs.getString(v2);
								kout.println("<td>"+str);
							}
							}	
							kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
							c=c+1;
							}
						}
						kout.println("</table></center>");
					}
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