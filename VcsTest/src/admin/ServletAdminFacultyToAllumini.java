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
 * Servlet implementation class for Servlet: ServletAdminFacultyToAllumini
 *
 */
 public class ServletAdminFacultyToAllumini extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminFacultyToAllumini() {
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
		kout.println("<html><head><title>Faculty To Allumini Process Only By Admin</title></head>");
		
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
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Faculty To Allumini Process Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

				String tfid="",tf_fname="",tf_iname="",tf_surname="";
				String tfbdate="",tgender="",tmobileno="";
				String tcountry="",tstate="",tcity="",taddress="";
				String tzipcode="",temail="",tqualification="";
				String query1="",query2="";
			
				tfid="11fact01";
				tfid=request.getParameter("fid");
									
				query1="select * from faculty_master where fid='" + tfid + "' ";
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery(query1);
				int c=1;
				while(rs.next())
				{
					tfid=rs.getString("fid");
					tf_fname=rs.getString("f_fname");
					tf_iname=rs.getString("f_iname");
					tf_surname=rs.getString("f_surname");
					tfbdate=rs.getString("fbdate");
					tgender=rs.getString("gender");
					tmobileno=rs.getString("mobileno");
					tcountry=rs.getString("country");
					tstate=rs.getString("state");
					tcity=rs.getString("city");
					taddress=rs.getString("address");
					tzipcode=rs.getString("zipcode");
					temail=rs.getString("email");
					tqualification=rs.getString("qualification");
					st=mycon.createStatement();
					query2="insert into faculty_allumini(fid,f_fname,f_iname,f_surname,fbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification) values('"+tfid+"','"+tf_fname+"','"+tf_iname+"','"+tf_surname+"','"+tfbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+"','"+tzipcode+"','"+temail+"','"+tqualification+"')";
					st.executeUpdate(query2);
					st=mycon.createStatement();
					query1="delete from faculty_master where fid='"+tfid+"'";
					st.executeUpdate(query1);
					c=c+1;
					st=mycon.createStatement();
					query1="delete from login_master where userid='"+tfid+"'";
					st.executeUpdate(query1);
					
				}
				if(c!=1)
					mystr="Faculty Shifted To Allumini";
				//kout.println("<br><br><br><h1>Faculty Shifted To Allumini Successfully");
				else
					mystr="Faculty Is Not Shifted";
				//kout.println("<br><br><br><h1>Sorry This Faculty Is Not Shifted To Allumini <br><br>Since Faculty May Not Exists Or Already In Allumnini Or Etc");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminFacultyToAllumini.jsp>");
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