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
 * Servlet implementation class for Servlet: ServletAdminFacultyCreate
 *
 */
 public class ServletAdminFacultyCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminFacultyCreate() {
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
		kout.println("<html><head><title>Faculty Creation Only By Admin</title></head><body>");

		String myfid="",myone="",tf_fname="",tf_iname="",tf_surname="";
		String tfday="",tfmonth="",tfyear="",tfbdate="";
		String tgender="",tmobileno="",tcountry="",tstate="";
		String tcity="",taddress="",tzipcode="",temail="";
		String tqualification="",query1="",query2="";
		int myear=new Date().getYear()-100;
		
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
				kout.println("<br><h1>Faculty Creation Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

									
				tf_fname="ketan";
				tf_iname="a";
				tf_surname="chawda";
				tfday="19";
				tfmonth="10";
				tfyear="1989";
				//tfbdate=" " + tfmonth + "/" + tfday + "/" + tfyear + " ";//access
				tfbdate=" " + tfyear + "-" + tfmonth + "-" + tfday + " ";//db2
				tgender="male";
				tmobileno="99792";
				tcountry="india";
				tstate="gujarat";
				tcity="surat";
				taddress="bamroli";
				tzipcode="3984";
				temail="gentleman@yahoo";
				tqualification="phd";
				
				/*tf_fname=request.getParameter("f_fname");
				tf_iname=request.getParameter("f_iname");
				tf_surname=request.getParameter("f_surname");
				tfday=request.getParameter("f_bday");
				tfmonth=request.getParameter("f_bmonth");
				tfyear=request.getParameter("f_byear");
				tfbdate=" " + tfmonth + "/" + tfday + "/" + tfyear + " ";//access
				//tfbdate=" " + tfyear + "-" + tfmonth + "-" + tfday + " ";//db2
				
				tgender=request.getParameter("gender");
				tmobileno=request.getParameter("mobileno");
				tcountry=request.getParameter("country");
				tstate=request.getParameter("state");
				tcity=request.getParameter("city");
				taddress=request.getParameter("address");
				tzipcode=request.getParameter("zipcode");
				temail=request.getParameter("email");
				tqualification=request.getParameter("qualification");*/
				st1=mycon.createStatement();
				ResultSet rs1=st1.executeQuery("select * from countall where role='faculty' and course='optional' and entry='"+myear+"' ");
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				tot=tot+1;
				
				myfid=""+myear+"fact"+tot+"";
				myone=""+tstate+myear*tot*tot*myear+"" ;
				
				if(tot%10==tot && myear%10==myear)
				myfid="" + "0" + myear + "fact0" + tot + "";
				else if(tot%10==tot)
				myfid="" + myear + "fact0" + tot + "";
				else if(myear%10==myear)
				myfid="" + "0"+ myear + "fact" + tot + "";
					
				query2="insert into faculty_master(fid,f_fname,f_iname,f_surname,fbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification) values('"+myfid+"','"+tf_fname+"','"+tf_iname+"','"+tf_surname+"','"+tfbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+"','"+tzipcode+"','"+temail+"','"+tqualification+"')";
				st=mycon.createStatement();
				st.executeUpdate(query2);
				
				query1="insert into login_master(userid,password,role,prior,coursename,locked) values ('"+myfid+"','"+myone+"','faculty','3','optional','no')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
					
				query2="insert into countall(role,course,entry) values ('faculty','optional','" + myear+ "')";
				st=mycon.createStatement();
				st.executeUpdate(query2);
				
				mystr="Faculty Is Added Successfully";
				kout.println("<br><br><br><h1>Faculty Is Added Successfully");
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
			kout.println("<br><br><h2><center>Sorry Connection Is Not Active Now");
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
			kout.println("</body></html");
		
			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);
			
			kout.close();
}
}	    
}