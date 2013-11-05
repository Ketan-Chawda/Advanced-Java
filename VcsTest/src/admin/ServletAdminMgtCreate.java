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
 * Servlet implementation class for Servlet: ServletAdminMgtCreate
 *
 */
 public class ServletAdminMgtCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminMgtCreate() {
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
		kout.println("<html><head><title>Management Creation Only By Admin</title></head>");
		
		String mymid="",myone="";
		String tm_fname="",tm_iname="",tm_surname="";
		String tmday="",tmmonth="",tmyear="",tmbdate="";
		String tgender="",tmobileno="",tcountry="";
		String tstate="",tcity="",taddress="";
		String tzipcode="",temail="",tqualification="";
		String query1="",query2="";
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
				kout.println("<br><h1>Management Creation Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/

				
				tm_fname="ketan";
				tm_iname="a";
				tm_surname="chawda";
				tmday="19";
				tmmonth="10";
				tmyear="1989";
				tmbdate=" " + tmmonth + "/" + tmday + "/" + tmyear + " ";//access
				//tmbdate=" " + tmyear + "-" + tmmonth + "-" + tmday + " ";//db2
				
				tgender="male";
				tmobileno="99792";
				tcountry="india";
				tstate="gujarat";
				tcity="surat";
				taddress="bamroli";
				tzipcode="3984";
				temail="gentleman@yahoo";
				tqualification="phd";
				
				/*tm_fname=request.getParameter("m_fname");
				tm_iname=request.getParameter("m_mname");
				tm_surname=request.getParameter("m_sname");
				tmday=request.getParameter("m_bday");
				tmmonth=request.getParameter("m_bmonth");
				tmyear=request.getParameter("m_byear");
				tmbdate=" " + tmmonth + "/" + tmday + "/" + tmyear + " ";//access
				//tmbdate=" " + tmyear + "-" + tmmonth + "-" + tmday + " ";//db2
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
				ResultSet rs1=st1.executeQuery("select * from countall where role='management' and course='optional' and entry='"+myear+"'");;
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				tot=tot+1;
				mymid=""+myear+"mgt"+tot+"";
				myone=""+tstate+myear*myear*tot+"" ;
				
				if(tot%10==tot && myear%10==myear)
				mymid=""+"0"+myear+"mgt0"+tot+"";
				else if(tot%10==tot)
				mymid=""+myear+"mgt0"+tot+"";
				else if(myear%10==myear)
				mymid=""+"0"+myear+"mgt"+tot+"";
				
				query2="insert into mgt_master(mid,m_fname,m_iname,m_surname,mbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification) values('"+mymid+"','"+tm_fname+"','"+tm_iname+"','"+tm_surname+"','"+tmbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+"','"+tzipcode+"','"+temail+"','"+tqualification+"')";
					st=mycon.createStatement();
					st.executeUpdate(query2);
					query1="insert into login_master(userid,password,role,prior,coursename,locked) values ('"+mymid+"','"+myone+"','management','2','optional','no')";
					st=mycon.createStatement();
					st.executeUpdate(query1);
					query2="insert into countall(role,course,entry) values('management','optional','"+myear+"')";
					st=mycon.createStatement();
					st.executeUpdate(query2);
					mystr="Management Added Successfully";
					//kout.println("<br><br><br><h1>Management Added Successfully");
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