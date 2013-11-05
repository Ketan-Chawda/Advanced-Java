package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ServletAdminMgtUpdate
 *
 */
 public class ServletAdminMgtUpdate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminMgtUpdate() {
		super();
	}   	
	static String myerror=null;

	static String mystr="";
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Management Updation Process By Admin Only</title></head>");

		String tmid="",tm_fname="",tm_iname="",tm_surname="";
		String tmbdate="",tgender="",tmobileno="",tcountry="";
		String tstate="",tcity="",taddress="",tzipcode="";
		String temail="",tqualification="",query1="";

		Connection mycon=null;
		Statement st,st1;
		
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
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Management Updation Process By Admin Only In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				tmid="11mgt5";
				tmid=request.getParameter("mid");
				st1=mycon.createStatement();
				ResultSet rs1=st1.executeQuery("select * from mgt_master where mid='"+tmid+"'");
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				if(tot!=0)
				{
					tm_fname="ketan";
					tm_iname="arjunsinh";
					tm_surname="chawda";
					tmbdate="19/10/1989";	//access
					//tmbdate="1989-10-19";	//db2
					tgender="male";
					tmobileno="99792";
					tcountry="india";
					tstate="gujarat";
					tcity="surat";
					taddress="bamroli";
					tzipcode="3984";
					temail="gentleman@yahoo";
					tqualification="phd";
					
					tm_fname=request.getParameter("m_fname");
					tm_iname=request.getParameter("m_iname");
					tm_surname=request.getParameter("m_surname");
					tmbdate=request.getParameter("birth");
					tgender=request.getParameter("gender");
					tmobileno=request.getParameter("mobileno");
					tcountry=request.getParameter("country");
					tstate=request.getParameter("state");
					tcity=request.getParameter("city");
					taddress=request.getParameter("address");
					tzipcode=request.getParameter("zipcode");
					temail=request.getParameter("email");
					tqualification=request.getParameter("qualification");
					
					
				query1="update mgt_master set m_fname='"+tm_fname+"',m_iname='"+tm_iname+"',m_surname='"+tm_surname+"',mbdate='"+tmbdate+"',gender='"+tgender+"',mobileno='"+tmobileno+"',country='"+tcountry+"',state='"+tstate+"',city='"+tcity+"',address='"+taddress+"',zipcode='"+tzipcode+"',email='"+temail+"',qualification='"+tqualification+"' where mid='"+tmid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				mystr="Management Info Updated";
				//kout.println("<br><br><br><h1> Remember This Management's Previous Details Will Be Overriden By This New Values As Per Your Wish");
				}
				else
				mystr="Management Does Not Exists";
				//kout.println("<br><br><br><h1> Sorry This Management Does Not Exists Or Some Other Restriction Are Been Given ");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminMgtUpdate.jsp>");
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