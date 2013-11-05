

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import mybest.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ServletPasswordChange
 *
 */
 public class ServletPasswordChange extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletPasswordChange() {
		super();
	}   	
	static String mystr="";
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Password Change For ALL User</title></head>");
		HttpSession my=request.getSession();
		
		String tuserid="",tnewone="",toldone="",tconfirm="",query1="";
		
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
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><br><h1>Password Change For ALL User In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");
				
				tuserid="rahul";//from session
				tuserid=request.getAttribute("uid").toString();
				toldone="bond";
				tnewone="james";
				tconfirm="james";
				
				
				toldone=request.getParameter("oldone");
				toldone=Secure.putp(toldone);
				tnewone=request.getParameter("newone");
				tconfirm=request.getParameter("confirm");
				

				if(tnewone.compareTo(tconfirm)==0)
				{	
				st1=mycon.createStatement();
				ResultSet rs1=st1.executeQuery("select * from login_master where userid='"+tuserid+"' and password='"+toldone+"'");
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				if(tot!=0)
				{
				tnewone=Secure.putp(tnewone);
				query1="update login_master set password='"+tnewone+"' where userid='"+tuserid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				//kout.println("<br><br><br><h1><center>Your Password Has Been Changed Successfully</center>");
				mystr="Your Password Is Changed Successfully";
				}
				else
				mystr="Sorry Your Password Is Not Changed";
				//kout.println("<br><br><br><h1><center>Sorry Your Password Is Not Changed Either OldPassword Is Valid Or NewOne & Confirm Are Not Same</center>");
			}
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
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
		}
		finally
		{
			kout.println("<html>");
			kout.println("<body OnLoad=document.getElementById('vcs').submit();>");
			kout.println("<form id=vcs method=post action=/VcsTest/changepasswd.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html>");

			kout.close();
			/*kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");*/		
}
}	    
}