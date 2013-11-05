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
 * Servlet implementation class for Servlet: ServletAdminStudentToAllumini
 *
 */
 public class ServletAdminStudentToAllumini extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminStudentToAllumini() {
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
		kout.println("<html><head><title>Student To Allumini Process</title></head>");
		
		String tsid="",ts_fname="",ts_iname="",ts_surname="";
		String tsbdate="",tgender="",tmobileno="";
		String tcountry="",tstate="",tcity="",taddress="";
		String tzipcode="",temail="",tqualification="",treqcourse="";
		String trdate="",tsresult="",query1="";
		
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
				kout.println("<br><h1>Student To Allumini Process In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				tsid="11bom01";
				tsid=request.getParameter("sid");
				
				st=mycon.createStatement();
				query1="select * from student_master where sid='" + tsid + "' ";
				ResultSet rs=st.executeQuery(query1);
				int c=0;
				while(rs.next())
				{
				c=rs.getRow();	
				}
				if(c==1)
				{
				st=mycon.createStatement();
				query1="select * from student_master where sid='"+tsid+"'";
				ResultSet rs1=st.executeQuery(query1);
				
				while(rs1.next())
				{
				ts_fname=rs1.getString("s_fname");
				ts_iname=rs1.getString("s_iname");
				ts_surname=rs1.getString("s_surname");
				tsbdate=rs1.getString("sbdate");
				tgender=rs1.getString("gender");
				tmobileno=rs1.getString("mobileno");
				tcountry=rs1.getString("country");
				tstate=rs1.getString("state");
				tcity=rs1.getString("city");
				taddress=rs1.getString("address");
				tzipcode=rs1.getString("zipcode");
				temail=rs1.getString("email");
				tqualification=rs1.getString("qualification");
				treqcourse=rs1.getString("coursename");
																		
				tsresult="distinction";
				query1="insert into student_allumini(sid,s_fname,s_iname,s_surname,sbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification,coursename,sresult) values('"+tsid+"','"+ts_fname+"','"+ts_iname+"','"+ts_surname+"','"+tsbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+" ','"+tzipcode+"','"+temail+"','"+tqualification+"','"+treqcourse+"','"+tsresult+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				query1="delete from student_master where sid='"+tsid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				query1="delete from login_master where userid='"+tsid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				//kout.println("<br><br><br><h1>Yes Student Is Added To Students Allumini");
				mystr="Student Is Shifted Allumini";
				}
				else
				mystr="Student Is Not Shifted Allumini";
				//kout.println("<br><br><br><h1>Sorry This Student Cannot Be Added To Students Allumini Since The Student Does Not Exists Or Restriction Is Kept");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminStudentToAllumini.jsp>");
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