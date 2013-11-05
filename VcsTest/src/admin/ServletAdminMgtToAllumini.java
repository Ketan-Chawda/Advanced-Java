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
 * Servlet implementation class for Servlet: ServletAdminMgtToAllumini
 *
 */
 public class ServletAdminMgtToAllumini extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminMgtToAllumini() {
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
		kout.println("<html><head><title>Management To Allumini Process Only By Admin</title></head>");

		String tmid="",tm_fname="",tm_iname="",tm_surname="";
		String tmbdate="",tgender="",tmobileno="";
		String tcountry="",tstate="",tcity="";
		String taddress="",tzipcode="",temail="";
		String tqualification="",query1="",query2="";
	
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
				kout.println("<br><h1>Management To Allumini Process Only By Admin</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				tmid="11mgt01";
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
					st=mycon.createStatement();
					ResultSet rs=st.executeQuery("select * from mgt_master where mid='"+tmid+"'");
					while(rs.next())
					{
					tmid=rs.getString("mid");
					tm_fname=rs.getString("m_fname");
					tm_iname=rs.getString("m_iname");
					tm_surname=rs.getString("m_surname");
					tmbdate=rs.getString("mbdate");
					tgender=rs.getString("gender");
					tmobileno=rs.getString("mobileno");
					tcountry=rs.getString("country");
					tstate=rs.getString("state");
					tcity=rs.getString("city");
					taddress=rs.getString("address");
					tzipcode=rs.getString("zipcode");
					temail=rs.getString("email");
					tqualification=rs.getString("qualification");
					st1=mycon.createStatement();
					query2="insert into mgt_allumini(mid,m_fname,m_iname,m_surname,mbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification) values('"+tmid+"','"+tm_fname+"','"+tm_iname+"','"+tm_surname+"','"+tmbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+"','"+tzipcode+"','"+temail+"','"+tqualification+"')";
					st1.executeUpdate(query2);
					st1=mycon.createStatement();
					query1="delete from mgt_master where mid='"+tmid+"'";
					st1.executeUpdate(query1);
					st1=mycon.createStatement();
					query1="delete from login_master where userid='"+tmid+"'";
					st1.executeUpdate(query1);
				}
									
				mystr="Management Shifted To Allumini";
				//kout.println("<br><br><br><h1> Management Shifted To Allumini Successfully");
				
				}
				else
					mystr="Management Is Not Shifted";
				//kout.println("<br><br><br><h1> Sorry This Management Is Not Shifted To Allumini <br><br>Since Management May Not Exists Or Already In Allumnini Or Etc");
			}
			catch(Exception k)
			{
				System.err.println(k);
				myerror=k.getMessage();

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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminMgtToAllumini.jsp>");
			kout.println("<input type=hidden name=common value='"+mystr+"' >");
			kout.println("</form>");
			kout.println("</body></html");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);
			
			kout.close();
}
}	    
}