package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ServletAdminStudentUpdate
 *
 */
 public class ServletAdminStudentUpdate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminStudentUpdate() {
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
		kout.println("<html><head><title>Students Information Updation</title></head>");
		
		String tsid="",ts_fname="",ts_iname="",ts_surname="";
		String tsbdate="",tgender="",tmobileno="";
		String tcountry="",tstate="",tcity="";
		String taddress="",tzipcode="",temail="",tqualification="";
		String tcoursename="",query1="",query2="";
		
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
				kout.println("<br><h1>Students Information Updation In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				tsid="11bca36";
				tsid=request.getParameter("sid");
				
				st=mycon.createStatement();
				//kout.println("before select ok");
				
				query1="select * from student_master where sid='"+tsid+"'";
				ResultSet rs=st.executeQuery(query1);
				int c=0;
				while(rs.next())
				{
				c=rs.getRow();
				}
				if(c!=0)
				{
					
					ts_fname="ketan";
					ts_iname="arjunsinh";
					ts_surname="chawda";
					tsbdate="19/10/1989";//access
					//tsbdate="1989-19-10";//db2
					
					tgender="male";
					tmobileno="9979282384";
					tcountry="india";
					tstate="gujarat";
					tcity="surat";
					taddress="bardoli";
					tzipcode="394601";
					temail="ketan_gentleman012";
					tqualification="tybca";
					tcoursename="bca";
										
				ts_fname=request.getParameter("s_fname");
				ts_iname=request.getParameter("s_iname");
				ts_surname=request.getParameter("s_surname");
				tsbdate=request.getParameter("birth");//access
				//tsbdate=request.getParameter("sbdate");
				tgender=request.getParameter("gender");
				tmobileno=request.getParameter("mobileno");
				tcountry=request.getParameter("country");
				tstate=request.getParameter("state");
				tcity=request.getParameter("city");
				taddress=request.getParameter("address");
				tzipcode=request.getParameter("zipcode");
				temail=request.getParameter("email");
				tqualification=request.getParameter("qualification");
				tcoursename=request.getParameter("coursename");
//				kout.println("before update ok");
				query1="update student_master set s_fname='"+ts_fname+"',s_iname='"+ts_iname+"',s_surname='"+ts_surname+"',sbdate='"+tsbdate+"',gender='"+tgender+"',mobileno='"+tmobileno+"',country='"+tcountry+"',state='"+tstate+"',city='"+tcity+"',address='"+taddress+"',zipcode='"+tzipcode+"',email='"+temail+"',qualification='"+tqualification+"',coursename='"+tcoursename+"' where sid='"+tsid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				//kout.println("<br><br><br><h1>Remember Student's Previous Details Are Been Overridden By The New Values");
				mystr="Student Info Updated";
				}
				else
				mystr="Student Does Not Exists";	
				//kout.println("<br><br><br><h1>Sorry This Student Does Not Exists Or Updation Is Not Possible Due To Some Restriction Given");
				}
			catch(Exception k)
			{
				System.err.println(k.getMessage());
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminStudentUpdate.jsp>");
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