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

import mybest.Secure;

/**
 * Servlet implementation class for Servlet: AdminFacultyConfirmEasyHelp
 *
 */
 public class AdminFacultyConfirmEasyHelp extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AdminFacultyConfirmEasyHelp() {
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
		String tf_fname="",tf_iname="",tf_surname="",tfbdate="";
		String tgender="",tmobileno="",tcountry="",tstate="";
		String tcity="",taddress="",tzipcode="",temail="";
		String tqualification="",query1="";
		String myfid="",myone="",query2="";
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
		
					query1="select * from faculty_register where rid='"+v2+"'";
					ResultSet rs=st.executeQuery(query1);
					while(rs.next())
					{
						tf_fname=rs.getString("f_fname");
						tf_iname=rs.getString("f_iname");
						tf_surname=rs.getString("f_surname");
						tfbdate=rs.getString("fbdate");
						tgender=rs.getString("gender");
						tmobileno=rs.getString("mobileno");
						tcountry=rs.getString("country");
						tstate=rs.getString("state");
						tcity=rs.getString("city");
						taddress=rs.getString("address");
						tzipcode=rs.getString("zipcode");
						temail=rs.getString("email");
						tqualification=rs.getString("qualification");
								
						st=mycon.createStatement();
						ResultSet rs1=st.executeQuery("select * from countall where role='faculty' and course='optional' and entry='"+myear+"'");;

						tot=0;
						while(rs1.next())
						{
							tot=Integer.parseInt(rs1.getString("total"));
						}
						tot=tot+1;
						if(tot==1)
						{
						query1="insert into countall(role,course,entry,total) values('faculty','optional','"+myear+"','"+tot+"')";
						st=mycon.createStatement();
						st.executeUpdate(query1);
						}
						else
						{
						query1="update countall set total='"+tot+"' where role='faculty' and course='optional' and entry='"+myear+"' ";
						st=mycon.createStatement();
						st.executeUpdate(query1);
						}
						myfid=""+myear+"fact"+tot+"";
						myone=""+tstate+myear*tot*tot*myear+"" ;
						myone=Secure.putp(myone);
						
						
						if(tot%10==tot && myear%10==myear)
						myfid="" + "0" + myear+ "fact0" + tot + "";
						else if(tot%10==tot)
						myfid="" + myear + "fact0" + tot + "";
						else if(myear%10==myear)
						myfid="" + "0"+ myear + "fact" + tot + "";
		
query1="insert into login_master(userid,password,role,prior,coursename,locked) values ('"+myfid+"','"+myone+"','faculty','4','optional','no')";
		st=mycon.createStatement();
		st.executeUpdate(query1);
	
		query1="insert into faculty_master(fid,f_fname,f_iname,f_surname,fbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification) values('" + myfid + "','" + tf_fname + "','" + tf_iname + "','" +tf_surname + "','" + tfbdate + "','" + tgender + "','" + tmobileno + "','" + tcountry + "','" + tstate + "','" + tcity + "','" + taddress + " ','" + tzipcode + "','" + temail + "','" + tqualification + "')";
		st=mycon.createStatement();
		st.executeUpdate(query1);
		query1="delete from faculty_register where rid='"+v2+"'";
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
		response.sendRedirect("/VcsTest/AdminFacultyConfirmEasy");
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