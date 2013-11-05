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

/**
 * Servlet implementation class for Servlet: ServletAdminNewsLetter
 *
 */
 public class ServletAdminNewsLetter extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminNewsLetter() {
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
		kout.println("<html><head><title>NewsLetter Creation Only By Admin In Vcs</title></head>");

		String tnid="",tnmsg="",tndate="",query1="",query2="";
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
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>NewsLetter Creation Only By Admin In Vcs</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

		
				tndate=m+"/"+d+"/"+(myear+2000);//access
				//tndate=(myear+2000)+"-"+m+"/"+d;//db2
				tnmsg="news on 22-4-2011";
				tnmsg=request.getParameter("nmsg");
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from countall where role='letter' and course='optional' and entry='"+myear+"'");
				int tot=0;
				while(rs.next())
				{
				tot=Integer.parseInt(rs.getString("total"));
				}
				tot=tot+1;
				if(tot==1)
				{
				query1="insert into countall(role,course,entry,total) values('letter','optional','"+myear+"','"+tot+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				else
				{
				query1="update countall set total='"+tot+"' where role='letter' and course='optional' and entry='"+myear+"' ";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				}
				tnid=""+myear+"letter"+tot+"";
				if(tot%10==tot)
				tnid=""+myear+"letter0"+tot+"";
				query1="insert into newsletter(nid,nmsg,ndate) values('"+tnid+"','"+tnmsg+"','"+tndate+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				//kout.println("<br><br><br><h1>Newsletter Created Succesfully");
				mystr="Newsletter Created";
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminNewsLetter.jsp>");
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