package mgt;
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

/**
 * Servlet implementation class for Servlet: ServletMgtRegistration
 *
 */
 public class ServletMgtRegistration extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletMgtRegistration() {
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
		kout.println("<html><head><title>Management Registration In Progress</title></head><body>");
		String myrid="",tm_fname="",tm_iname="",tm_surname="";
		String tday="",tmonth="",tyear="",tmbdate="";
		String tgender="",tmobileno="",tcountry="",tstate="";
		String tcity="",taddress="",tzipcode="",temail="";
		String tqualification="",trdate="",query1="",query2="";
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;
							
		
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
				tm_fname="ketan mgt-2";
				tm_iname="a";
				tm_surname="chawda";
				tday="19";
				tmonth="10";
				tyear="1989";
				//tmbdate=tmonth+"/"+tday+"/"+tyear;//access
				tmbdate=tyear+"-"+tmonth+"-"+tday;//db2
				tgender="male";
				tmobileno="99792";
				tcountry="india";
				tstate="gujarat";
				tcity="surat";
				taddress="bamroli";
				tzipcode="3984";
				temail="gentleman@yahoo";
				tqualification="phd";
				
				//trdate=m+"/"+d+"/"+(myear+2000);//access
				trdate=(myear+2000)+"-"+m+"-"+d;//db2
				
				
				tm_fname=request.getParameter("m_fname");
				tm_iname=request.getParameter("m_iname");
				tm_surname=request.getParameter("m_surname");
				tday=request.getParameter("m_bday");
				tmonth=request.getParameter("m_bmonth");
				tyear=request.getParameter("m_byear");
				tmbdate=tday+"/"+tmonth+"/"+tyear;//access
				tmbdate=tyear+"-"+tmonth+"-"+tday;//db2
				
				tgender=request.getParameter("gender");
				tmobileno=request.getParameter("mobileno");
				tcountry=request.getParameter("country");
				tstate=request.getParameter("state");
				tcity=request.getParameter("city");
				taddress=request.getParameter("address");
				tzipcode=request.getParameter("zipcode");
				temail=request.getParameter("email");
				tqualification=request.getParameter("qualification");
				
				st1=mycon.createStatement();
				ResultSet rs1=st1.executeQuery("select * from countall where role='register' and course='optional' and entry='"+myear+"' ");
				int tot=0;
				while(rs1.next())
				{
				tot=Integer.parseInt(rs1.getString("total"));
				}
				tot=tot+1;
				if(tot==1)
				{
				query1="insert into countall(role,course,entry,total) values('register','optional','"+myear+"','"+tot+"')";
				st1=mycon.createStatement();
				st1.executeUpdate(query1);
				}
				else
				{
				query1="update countall set total='"+tot+"' where role='register' and course='optional' and entry='"+myear+"' ";
				st1=mycon.createStatement();
				st1.executeUpdate(query1);
				}
				
				myrid=""+myear+"register"+tot+"";
				//trdate=d+"/"+m+"/"+(myear+2000);//access
				trdate=(myear+2000)+"-"+m+"-"+d;//db2
		
					if(tot%10==tot && myear%10==myear)
					myrid=""+"0"+myear+"register0"+tot+"";
					else if(tot%10==tot)
					myrid=""+myear+"register0"+tot+"";
					else if(myear%10==myear)
					myrid=""+"0"+myear+"register"+tot+"";
		
							
				query1="insert into mgt_register(rid,m_fname,m_iname,m_surname,mbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification,rdate) values('"+myrid+"','"+tm_fname+"','"+tm_iname+"','"+tm_surname+"','"+tmbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+"','"+tzipcode+"','"+temail+"','"+tqualification+"','"+trdate+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
			//	kout.println("<br><br><br><h1>You Are Registered Successfully For Management Vacancy");
				//mystr="Registered As Mgt";	// With Id="+myrid;
				mystr="Registered Id="+myrid;
				
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
			kout.println("<form id=vcs method=post action=/VcsTest/MgtRegister.jsp>");
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