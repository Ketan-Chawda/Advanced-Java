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
 * Servlet implementation class for Servlet: ServletAdminCourseUpdate
 *
 */
 public class ServletAdminCourseUpdate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminCourseUpdate() {
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
		kout.println("<html><head><title>Course Updation Only By Admin</title></head>");
		String tcoursename="",ttotal_subject="",tsubject="",ttofid="";
		String tsday="",tsmonth="",tsyear="";
		String tstart_date="",tduration="",tduryear="",tdurmonth="";
		String query1="",query2="";
				
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
				kout.println("<br><h1>Course Updation Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

				tcoursename="bca";
				tcoursename=request.getParameter("coursename");
				st=mycon.createStatement();
				query1="select * from course_master where coursename='"+tcoursename+"' ";
				ResultSet rs1=st.executeQuery(query1);
				int tot=0;
				kout.println("<br>select");
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				if(tot!=0)
				{
					kout.println("<br>select-2");
					
					ttotal_subject="12";
					tsubject="unix";
					ttofid="manika";
					tsday="26";
					tsmonth="11";
					tsyear="1986";
					tstart_date= tsmonth + "/"+ tsday+ "/" + tsyear;//access
					//tstart_date= tsyear + "-"+ tsmonth + "-" + tsday;//db2
					
					tduryear="6 years";
					tdurmonth="2 month";
					tduration=tduryear + "&"+ tdurmonth;
					
					
					/*ttotal_subject=request.getParameter("total_subject");
					tsubject=request.getParameter("subject");
					ttofid=request.getParameter("tofid");
					
					tday=request.getParameter("sday");
					tmonth=request.getParameter("smonth");
					tyear=request.getParameter("syear");
					tstart_date= tsmonth + "/"+ tsday+ "/" + tsyear;//access
					tstart_date= tsyear + "-"+ tsmonth + "-" + tsday;//db2
									
					tduryear=request.getParameter("duryear");
					tdurmonth=request.getParameter("durmonth");
					tduration=tduryear + "&"+ tdurmonth;*/
					
									
query1="update course_master set total_subject='"+ttotal_subject+"',start_date='"+tstart_date+"',duration='"+tduration+"' where coursename='"+tcoursename+"'";  
									
				st=mycon.createStatement();
				
				st.executeUpdate(query1);
				
				query2="update subject_master set subject='"+tsubject+"',tofid='"+ttofid+"' where coursename='"+tcoursename+"' ";
				st=mycon.createStatement();
				
				st.executeUpdate(query2);
				//kout.println("<br><br><br><h1>Course Updated Successfully");
				mystr="Course Updated";
				}
				else
				mystr="Course Does Not Exists";	
				//kout.println("<br><br><br><h1>Course Does Not Exists Or Some Restriction Is Kept So No Updation<h2>");
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminCourseUpdate.jsp>");
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