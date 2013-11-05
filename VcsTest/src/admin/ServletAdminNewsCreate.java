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
 * Servlet implementation class for Servlet: ServletAdminNewsCreate
 *
 */
 public class ServletAdminNewsCreate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminNewsCreate() {
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
		kout.println("<html><head><title>NewsLetter Creation Only By Admin In Vcs</title></head>");
		
		String tnid="",tnewsmsg="",tndate="",query1="",str="";
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
				kout.println("<br><h1>NewsLetter Creation Only By Admin In Vcs In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");*/

				
				tndate=m+"/"+d+"/"+(myear+2000);//access
				//tndate=(myear+2000)+"-"+m+"/"+d;//db2
				//tnewsmsg="ketan news on 22-4-2011";
				tnewsmsg=request.getParameter("nmsg");
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery("select * from news");
				int tot=0;
				while(rs.next())
				{
					tot=rs.getRow();
				}
				if(tot==5)
				{
					str=""+myear+"news0"+1;
					st1=mycon.createStatement();
					st1.executeUpdate("delete from news where nid='"+str+"'");
					st=mycon.createStatement();
					ResultSet rs1=st.executeQuery("select * from news");
					int t=2;
					while(rs1.next())
					{
						String str1=""+myear+"news0"+(t-1);
						str=""+myear+"news0"+t;
						st1=mycon.createStatement();
						st1.executeUpdate("update news set nid='"+str1+"' where nid='"+str+"'");
						t=t+1;
					}
					str=""+myear+"news0"+5;
				}
				else
				{
				tot=tot+1;
				str=""+myear+"news0"+tot;
				}
				st1=mycon.createStatement();
				st1.executeUpdate("insert into news(nid,newsmsg,ndate) values('"+str+"','"+tnewsmsg+"','"+tndate+"')");
				//kout.println("<br><br><br><h1>News Created Succesfully");
				mystr="News Created";
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminNewsCreate.jsp>");
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