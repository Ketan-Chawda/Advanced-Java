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
 * Servlet implementation class for Servlet: ServletAdminConfirmRequest
 *
 */
 public class ServletAdminConfirmRequest extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletAdminConfirmRequest() {
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
		kout.println("<html><head><title>Student,Faculty,Management Confirmation In Progress Only By Admin</title></head>");
		String myrole="",myrid="",mysid="";
		String ts_fname="",ts_iname="",ts_surname="";
		String tsbdate="",tgender="",tmobileno="",tcountry="";
		String tstate="",tcity="",taddress="",tzipcode="";
		String temail="",tqualification="",treqcourse="",trdate="";
		String myone="",query1="",query2="",query3="";
		
		String myfid="",tf_fname="",tf_iname="",tf_surname="",tfbdate="";
		String mymid="",tm_fname="",tm_iname="",tm_surname="",tmbdate="";
				
		int myear=new Date().getYear()-100,d=new Date().getDate(),m=new Date().getMonth();
		
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
				//myrole="student";//from previous page may be student,mgt or faculty only
				//myrid="11register17";
				myrole=request.getParameter("role");
				myrid=request.getParameter("rid");
				/*kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><h1>Student,Faculty,Management Confirmation In Progress Only By Admin In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");*/
		
				if(myrole.compareToIgnoreCase("student")==0)
				{
				st=mycon.createStatement();
				query1="select * from student_register where rid='"+myrid+"'";
				ResultSet rs=st.executeQuery(query1);
				int c=0;
				while(rs.next())
				{
				c=rs.getRow();	
				}
				if(c!=0)
				{
				st=mycon.createStatement();
				query1="select * from student_register where rid='"+myrid+"'";
				ResultSet rs1=st.executeQuery(query1);
				while(rs1.next())
				{
				ts_fname=rs1.getString("s_fname");
				ts_iname=rs1.getString("s_iname");
				ts_surname=rs1.getString("s_surname");
				tsbdate=rs1.getString("sbdate");
				tgender=rs1.getString("gender");
				tmobileno=rs1.getString("mobileno");
				tcountry=rs1.getString("country");
				tstate=rs1.getString("state");
				tcity=rs1.getString("city");
				taddress=rs1.getString("address");
				tzipcode=rs1.getString("zipcode");
				temail=rs1.getString("email");
				tqualification=rs1.getString("qualification");
				treqcourse=rs1.getString("reqcourse");
				trdate=rs1.getString("rdate");
											
				st1=mycon.createStatement();
				ResultSet rs2=st1.executeQuery("select * from countall where role='student' and course='"+treqcourse+"' and entry='"+myear+"'");;

				int tot=0;
				while(rs2.next())
				{
				tot=Integer.parseInt(rs2.getString("total"));
				}
				rs2.close();
				tot=tot+1;
				if(tot==1)
				{
				query1="insert into countall(role,course,entry,total) values('student','"+treqcourse+"','"+myear+"','"+tot+"')";
				st1=mycon.createStatement();
				st1.executeUpdate(query1);
				}
				else
				{
				query1="update countall set total='"+tot+"' where role='student' and course='"+treqcourse+"' and entry='"+myear+"' ";
				st1=mycon.createStatement();
				st1.executeUpdate(query1);
				}
				mysid=""+myear+treqcourse+tot+"";
				myone=""+treqcourse+tot*myear*tot+"" ;
				myone=Secure.putp(myone);
				
				if(tot%10==tot && myear%10==myear)
				mysid=""+"0"+myear+treqcourse+"0"+tot+"";
				else if(tot%10==tot)
				mysid=""+myear+treqcourse+"0"+tot+"";
				else if(myear%10==myear)
				mysid=""+"0"+myear+treqcourse+tot+"";						
				query1="insert into login_master(userid,password,role,prior,coursename,locked) values ('"+mysid+"','"+myone+"','student','4','"+treqcourse+"','no')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				
				query1="insert into student_master(sid,s_fname,s_iname,s_surname,sbdate,gender,mobileno,country,state,city,address,zipcode,email,qualification,coursename) values('"+mysid+"','"+ts_fname+"','"+ts_iname+"','"+ts_surname+"','"+tsbdate+"','"+tgender+"','"+tmobileno+"','"+tcountry+"','"+tstate+"','"+tcity+"','"+taddress+"','"+tzipcode+"','"+temail+"','"+tqualification+"','"+treqcourse+"')";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				query1="delete from student_register where rid='"+myrid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				st.close();
				
				}
				mystr="Student Is Confirmed ";
				//kout.println("<br><br><br><h1> Sorry Register Id Is Not Valid One For Student");
				}
				else
					mystr="Register Id Is Not Valid-One";
					//kout.println("<br><br><br><h1> Sorry Register Id Is Not Valid One For Student");
				}
				else if(myrole.compareToIgnoreCase("faculty")==0)
				{
					st=mycon.createStatement();
					query1="select * from faculty_register where rid='"+myrid+"'";
					ResultSet rs=st.executeQuery(query1);
					int c=0;
					while(rs.next())
					{
					c=rs.getRow();	
					}
					if(c==1)
					{
					myear=new Date().getYear()-100;
					st=mycon.createStatement();
					query1="select * from faculty_register where rid='"+myrid+"'";
					ResultSet rs1=st.executeQuery(query1);
					while(rs1.next())
					{
					tf_fname=rs1.getString("f_fname");
					tf_iname=rs1.getString("f_iname");
					tf_surname=rs1.getString("f_surname");
					tfbdate=rs1.getString("fbdate");
					tgender=rs1.getString("gender");
					tmobileno=rs1.getString("mobileno");
					tcountry=rs1.getString("country");
					tstate=rs1.getString("state");
					tcity=rs1.getString("city");
					taddress=rs1.getString("address");
					tzipcode=rs1.getString("zipcode");
					temail=rs1.getString("email");
					tqualification=rs1.getString("qualification");
					trdate=rs1.getString("rdate");
												
					st1=mycon.createStatement();
					ResultSet rs2=st1.executeQuery("select * from countall where role='faculty' and course='optional' and entry='"+myear+"'");;

					int tot=0;
					while(rs2.next())
					{
					tot=Integer.parseInt(rs2.getString("total"));
					}
					tot=tot+1;
					if(tot==1)
					{
					query1="insert into countall(role,course,entry,total) values('faculty','optional','"+myear+"','"+tot+"')";
					st1=mycon.createStatement();
					st1.executeUpdate(query1);
					}
					else
					{
					query1="update countall set total='"+tot+"' where role='faculty' and course='optional' and entry='"+myear+"' ";
					st1=mycon.createStatement();
					st1.executeUpdate(query1);
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
					query1="delete from faculty_register where rid='"+myrid+"'";
					st=mycon.createStatement();
					st.executeUpdate(query1);
					}
					mystr="Faculty Is Confirmed";
					//kout.println("<br><br><h2>Yes Faculty Is Confirmed ");
					}
					else
					mystr="Register Id Is Not Valid-One";
					//kout.println("<br><br><h2> Sorry Register Id Is Not Valid One For Faculty");
			}
			else
			{
				st=mycon.createStatement();
				query1="select * from mgt_register where rid='" + myrid + "' ";
				ResultSet rs=st.executeQuery(query1);
				int c=0;
				while(rs.next())
				{
				c=rs.getRow();	
				}
				if(c==1)
				{
				myear=new Date().getYear()-100;;
				st=mycon.createStatement();
				query1="select * from mgt_register where rid='"+myrid+"'";
				ResultSet rs1=st.executeQuery(query1);
				while(rs1.next())
				{
				tm_fname=rs1.getString("m_fname");
				tm_iname=rs1.getString("m_iname");
				tm_surname=rs1.getString("m_surname");
				tmbdate=rs1.getString("mbdate");
				tgender=rs1.getString("gender");
				tmobileno=rs1.getString("mobileno");
				tcountry=rs1.getString("country");
				tstate=rs1.getString("state");
				tcity=rs1.getString("city");
				taddress=rs1.getString("address");
				tzipcode=rs1.getString("zipcode");
				temail=rs1.getString("email");
				tqualification=rs1.getString("qualification");
				trdate=rs1.getString("rdate");
											
				st1=mycon.createStatement();
				ResultSet rs2=st1.executeQuery("select * from countall where role='management' and course='optional' and entry='"+myear+"'");;
				int tot=0;
				while(rs2.next())
				{
				tot=Integer.parseInt(rs2.getString("total"));
				}
				tot=tot+1;
				if(tot==1)
				{
				query1="insert into countall(role,course,entry,total) values('management','optional','"+myear+"','"+tot+"')";
				st1=mycon.createStatement();
				st1.executeUpdate(query1);
				}
				else
				{
				query1="update countall set total='"+tot+"' where role='management' and course='optional' and entry='"+myear+"' ";
				st1=mycon.createStatement();
				st1.executeUpdate(query1);
				}
				mymid=""+myear+"mgt"+tot+"";
				myone=""+tstate+myear*myear*tot+"" ;
				myone=Secure.putp(myone);

				
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
				
				query1="delete from mgt_register where rid='"+myrid+"'";
				st=mycon.createStatement();
				st.executeUpdate(query1);
				
				//kout.println("<br><br><h2>Yes Management Is Confirmed ");
				mystr="Management Is Confirmed ";
				}
				}
				else
				mystr="Register Id Is Not Valid-One";
			}
			}
			catch(Exception k)
			{
				System.err.println(k);
				myerror=k.getMessage();

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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminStudentConfirmRequest.jsp>");
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