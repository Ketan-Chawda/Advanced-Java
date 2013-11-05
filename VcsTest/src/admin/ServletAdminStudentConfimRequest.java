package admin;

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

/**
 * Servlet implementation class for Servlet: ServletAdminStudentConfimRequest
 *
 */
 public class ServletAdminStudentConfimRequest extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminStudentConfimRequest() {
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
		kout.println("<html><head><title>Student Confirmation Process</title></head>");

		String trid="",mysid="";
		String ts_fname="",ts_iname="",ts_surname="";
		String tsbdate="",tgender="",tmobileno="";
		String tcountry="",tstate="",tcity="",taddress="";
		String tzipcode="",temail="",tqualification="",treqcourse="";
		String trdate="",myone="",query1="",query2="",query3="";

		Connection mycon=null;
		Statement st,st1;
		
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
		mycon=DriverManager.getConnection("jdbc:odbc:ketandb2");
		//kout.println("<br><br>**************************************************************************************************************");
		//kout.println("<br><h1>Your Connection With Mentioned DataSource Was Established Successfully </h1>");
		//kout.println("<br><br>**************************************************************************************************************");
		if(mycon!=null)
		{
			try
			{
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Student Confirmation Process In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				trid="r12";
				trid=request.getParameter("rid");
				
				st=mycon.createStatement();
				query1="select * from student_register where rid='" + trid + "' ";
				ResultSet rs=st.executeQuery(query1);
				int c=0;
				while(rs.next())
				{
				c=rs.getRow();	
				}
				if(c!=0)
				{
				int tot=0,myear=new Date().getYear()-100;;
				st=mycon.createStatement();
				query1="select * from student_register where rid='"+trid+"'";
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
				treqcourse=rs1.getString("reqcourse");
				trdate=rs1.getString("rdate");
											
				st1=mycon.createStatement();
				ResultSet rs2=st1.executeQuery("select * from countall where role='student' and course='"+treqcourse+"' and entry='"+myear+"'");;

				tot=0;
				while(rs2.next())
				{
				tot=rs1.getRow();
				}
				tot=tot+1;
				mysid=""+myear+treqcourse+tot+"";
				myone=""+treqcourse+tot*myear*tot+"" ;
				
				if(tot%10==tot && myear%10==myear)
				mysid=""+"0"+myear+treqcourse+"0"+tot+"";
				else if(tot%10==tot)
				mysid=""+myear+treqcourse+"0"+tot+"";
				else if(myear%10==myear)
				mysid=""+"0"+myear+treqcourse+tot+"";						
				query1="insert into login_master(userid,password,role,prior,coursename,locked) values ('"+mysid+"','"+myone+"','student','4','"+treqcourse+"','no')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				
				query2="insert into countall(role,course,entry) values('student','"+treqcourse+"','"+myear+"')";
				st=mycon.createStatement();
				st.executeUpdate(query2);
				
				query1="insert into student_master (sid,s_fname,s_iname,s_surname,sbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification,coursename) values('" + mysid + "','" + ts_fname + "','" + ts_iname + "','" +ts_surname + "','" + tsbdate + "','" + tgender + "','" + tmobileno + "','" + tcountry + "','" + tstate + "','" + tcity + "','" + taddress + " ','" + tzipcode + "','" + temail + "','" + tqualification + "','" + treqcourse + "')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				query1="delete from registration where rid='"+trid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				mystr="Student Is Confirmed";
				//kout.println("<br><br><br><h1>Yes Student Is Confirmed ");
				}
				else
				mystr="Sorry Register Id Is Not Valid-One";
				//kout.println("<br><br><br><h1>Sorry Register Id Is Not Valid One");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminStudentConfirmRequest.jsp>");
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