package faculty;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ServletFacultyNoticeCreate
 *
 */
 public class ServletFacultyNoticeCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyNoticeCreate() {
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
		kout.println("<html><head><title>Notice Creation By Admin</title></head>");
		HttpSession my=request.getSession();
		
		String mynid="",tprior="5",tnoticefrom="",tnoticeto="";
		String tforcourse="",tnmsg="",tndate="",query1="",query2="";
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth()+1;
							
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
				tnoticeto="bca";
				tnoticeto="all";
				tforcourse="optional";
				tnmsg="2mrw early morning lecture";
				
				tnoticeto=request.getParameter("noticeto");
				tnmsg=request.getParameter("nmsg");
				
				
				if(tnoticeto.compareToIgnoreCase("management")==0)
				tprior="2";
				else if(tnoticeto.compareToIgnoreCase("faculty")==0)
				tprior="3";
				else if(tnoticeto.compareToIgnoreCase("student")==0)
				tprior="4";
				else if(tnoticeto.compareToIgnoreCase("all")==0)
				tprior="5";
						
						
				if(tnoticeto.compareToIgnoreCase("course")==0)
				{
				tprior="5";
				tforcourse="bca";
				tforcourse=request.getParameter("course");
				}
					
				query1="select * from countall where role='notice' and course='optional' and entry='"+myear+"'";  
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery(query1);
				int tot=0;
				while(rs.next())
				{
				tot=Integer.parseInt(rs.getString("total"));
				}
				tot=tot+1;
				if(tot==1)
				{
				query1="insert into countall(role,course,entry,total) values('notice','optional','"+myear+"','"+tot+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				else
				{
				query1="update countall set total='"+tot+"' where role='notice' and course='optional' and entry='"+myear+"' ";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				tndate=""+m+"/"+d+"/"+(myear+2000)+"";//access
				//tndate=""+(myear+2000)+"-"+m+"-"+d+"";//db2
				tnoticefrom=my.getAttribute("uid").toString();
				
				mynid=""+myear+"notice"+tot+"";
				st=mycon.createStatement();
				query2="insert into notice(nid,forcourse,noticefrom,noticeto,nmsg,ndate,nprior) values('"+mynid+"','"+tforcourse+"','"+tnoticefrom+"','"+tnoticeto+"','"+tnmsg+"','"+tndate+"','"+tprior+"')";
				st.executeUpdate(query2);
				//kout.println("<br><br><br><h1> Notice Created Successfully </h2>");
				mystr=" Notice Created";
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
			kout.println("<form id=vcs method=post action=/VcsTest/Faculty/FacultyNoticeCreate.jsp>");
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