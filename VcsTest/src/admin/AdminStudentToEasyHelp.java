package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybest.Fortable;
import mybest.Secure;

/**
 * Servlet implementation class for Servlet: AdminStudentToEasyHelp
 *
 */
 public class AdminStudentToEasyHelp extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AdminStudentToEasyHelp() {
		super();
	}   	
	static String myerror=null;

	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Deleting Activity Organized In VCS</title></head>");
		String v1="",v2="";
		String ts_fname="",ts_iname="",ts_surname="",tsbdate="";
		String tgender="",tmobileno="",tcountry="",tstate="";
		String tcity="",taddress="",tzipcode="",temail="";
		String tqualification="",treqcourse="",trdate="",query1="";
		String mysid="",myone="",tsresult="";
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth();
		
		
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
				int tot=Integer.parseInt(request.getParameter("total"));
				for(int k=1;k<tot;k++)
				{
				v1="c"+k;
				v2=request.getParameter(v1);
				//kout.println("<br>checkbox-"+v1+"<blockquote> value="+v2);
				st=mycon.createStatement();
				if(v2!=null)
				{
				st=mycon.createStatement();
				query1="select * from student_master where sid='"+v2+"'";
				ResultSet rs=st.executeQuery(query1);
				
				while(rs.next())
				{
				ts_fname=rs.getString("s_fname");
				ts_iname=rs.getString("s_iname");
				ts_surname=rs.getString("s_surname");
				tsbdate=rs.getString("sbdate");
				tgender=rs.getString("gender");
				tmobileno=rs.getString("mobileno");
				tcountry=rs.getString("country");
				tstate=rs.getString("state");
				tcity=rs.getString("city");
				taddress=rs.getString("address");
				tzipcode=rs.getString("zipcode");
				temail=rs.getString("email");
				tqualification=rs.getString("qualification");
				treqcourse=rs.getString("coursename");
																		
				tsresult="distinction";
				query1="insert into student_allumini(sid,s_fname,s_iname,s_surname,sbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification,coursename,sresult) values('"+v2+"','"+ts_fname+"','"+ts_iname+"','"+ts_surname+"','"+tsbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+" ','"+tzipcode+"','"+temail+"','"+tqualification+"','"+treqcourse+"','"+tsresult+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				query1="delete  from student_master where sid='"+v2+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				query1="delete  from login_master where userid='"+v2+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				}
				}
			}
			catch(Exception e)
			{
			System.out.println(e);
			myerror=e.getMessage();

			}
			finally
			{	
			mycon.close();
			response.sendRedirect("/VcsTest/AdminStudentToEasy");
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
		if(myerror!=null)
		kout.println("There Is A Error From Server-Side="+myerror);

		kout.close();
	}
}	
}
 
 
