package mgt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybest.*;


/**
 * Servlet implementation class for Servlet: ServletMgtCompetitionDeclareResult
 *
 */
 public class ServletMgtCompetitionDeclareResult extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletMgtCompetitionDeclareResult() {
		super();
	}   
	static String myerror=null;

	static String mystr="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Declaration Of Result Of Competition Held Only By Management</title></head>");
		HttpSession my=request.getSession(false);
		
		String tenterby="",mycompid="",tnop="";
		String tfirstp="",tsecondp="",tthirdp="";
		String trdate="",query1="";
		
		Connection mycon=null;
		Statement st;
		
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
		kout.println("<br><br>**************************************************************************************************************");
		kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
		kout.println("<br><br>**************************************************************************************************************");
		if(mycon!=null)
		{
			
			try
			{
				tenterby=my.getAttribute("uid").toString();
				
				mycompid="11comp02";
				tnop="4";
				tfirstp="08bca12";
				tsecondp="08bca46";
				tthirdp="08bca36";
				trdate="19/10/1989";
				
				/*mycompid=request.getParameter("compid");
				tnop=request.getParameter("nop");
				tfirstp=request.getParameter("firstp");
				tsecondp=request.getParameter("secondp");
				tthirdp=request.getParameter("thirdp");
				trdate=request.getParameter("rdate");*/
				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from comp_participants where compid='"+mycompid+"'");
				int tot=0;
				while(rs.next())
				{
				tot=rs.getRow();
				}
				if(tot!=0)
				{
			query1="insert into comp_winner(compid,nop,firstp,secondp,thirdp,rdate,enterby) values('"+mycompid+"','"+tnop+"','"+tfirstp+"','"+tsecondp+"','"+tthirdp+"','"+trdate+"','"+tenterby+"')";
					st=mycon.createStatement();
					st.executeUpdate(query1);
					//kout.println("<br><br><br><h1>Competition Result Declared Successfully");
					mystr="Competition Result Declared";
				}
				else
				mystr="Competition Does Not Exists";	
					//kout.println("<br><br><br><h1>Sorry Competition Does Not Exists");
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
		mystr="Sorry Connection Is In-Active";
		//kout.println("Sorry Connection Is Not Active Now");

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
			kout.println("<form id=vcs method=post action=/Mytest/Jleft.jsp>");
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