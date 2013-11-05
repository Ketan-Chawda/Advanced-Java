package student;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.*; 

/**
 * Servlet implementation class for Servlet: ServletStudentUpdate
 *
 */
 public class ServletStudentUpdate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletStudentUpdate() {
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
		Connection mycon=null;		
		Statement st;
		
		String sid="",fid="",mid="";
		String first="",middle="",surname="",birth="",gender="";
		String mobile="",country="",state="",city="";
		String address="",zipcode="",email="",qualification="",course="";
		String query="",q_role,path="";		
		
		q_role=request.getParameter("q_role");
		if(q_role.equals("stu"))
		{
		sid=request.getParameter("sid");	
		query="select * from student_master where sid='"+sid+"'";
		path="/VcsTest/Admin/AdminStudentUpdate.jsp";
		}
		else if(q_role.equals("fac"))
		{			
		sid=request.getParameter("fid");	
		kout.println(sid);
		query="select * from faculty_master where fid='"+sid+"'";
		path="/VcsTest/Admin/AdminFacultyUpdate.jsp";
		}
		else if(q_role.equals("mgt"))
		{
		sid=request.getParameter("mid");	
		query="select * from mgt_master where mid='"+sid+"'";
		path="/VcsTest/Admin/AdminMgtUpdate.jsp";
		}
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");
			if(mycon!=null)
			{
			try
			{				
				st=mycon.createStatement();
				ResultSet rs=st.executeQuery(query);							
				while(rs.next())
				{
				//kout.println("fkfkkf");
				first=rs.getString(2);
				middle=rs.getString(3);
				surname=rs.getString(4);
				birth=rs.getString(5);
				gender=rs.getString(6);
				mobile=rs.getString(7);
				country=rs.getString(8);
				state=rs.getString(9);
				city=rs.getString(10);
				address=rs.getString(11);
				zipcode=rs.getString(12);
				email=rs.getString(13);
				qualification=rs.getString(14);
				if(q_role.equals("stu"))
				{	
				course=rs.getString(15);
				}				
				}				
				kout.println("<html>");
                kout.println("<body OnLoad=document.getElementById('upd').submit();>");
                kout.println("<form name=upd id=upd method=post action="+path+">");
                kout.println("<input type=hidden name=sid value='"+sid+"'>");
                kout.println("<input type=hidden name=fname value='"+first+"'>");
                kout.println("<input type=hidden name=iname value='"+middle+"'>");
                kout.println("<input type=hidden name=sname value='"+surname+"'>");
                kout.println("<input type=hidden name=birth value='"+birth+"'>");
                kout.println("<input type=hidden name=gender value='"+gender+"'>");
                kout.println("<input type=hidden name=mobile value='"+mobile+"'>");
                kout.println("<input type=hidden name=country value='"+country+"'>");
                kout.println("<input type=hidden name=state value='"+state+"'>");
                kout.println("<input type=hidden name=city value='"+city+"'>");
                kout.println("<input type=hidden name=address value='"+address+"'>");
                kout.println("<input type=hidden name=zipcode value='"+zipcode+"'>");
                kout.println("<input type=hidden name=email value='"+email+"'>");
                kout.println("<input type=hidden name=qlf value='"+qualification+"'>");
                kout.println("<input type=hidden name=course value='"+course+"'>");
                kout.println("</form>");
                kout.println("</body>");
                kout.println("</html>");			
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
			kout.println("<form id=vcs method=post action=/VcsTest/Admin/AdminStudentUpdate.jsp>");
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