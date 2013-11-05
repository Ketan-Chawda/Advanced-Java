package faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mybest.*;


/**
 * Servlet implementation class for Servlet: ServletFacultyAssignmentUpdate
 *
 */
 public class ServletFacultyAssignmentUpdate extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletFacultyAssignmentUpdate() {
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
		kout.println("<html><head><title>Assignment Updation Process Only By Faculty</title></head>");
		
		String myaid="",talloc_date="",tadesc="",tsubmission_date="";
		String tmarks="",query1="",query2="";
		
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
				
				myaid="bca01java";
				talloc_date="1/2/2010";
				tadesc="for bca n java subject";
				tsubmission_date="12/2/2010";
				tmarks="10";
				
				/*myaid=request.getParameter("aid");
				talloc_date=request.getParameter("alloc_date");
				tadesc=request.getParameter("adesc");
				tsubmission_date=request.getParameter("submission_date");
				tmarks=request.getParameter("marks");*/
				
				st1=mycon.createStatement();
				ResultSet rs1=st1.executeQuery("select * from assign_allocation where aid'"+myaid+"'");
				int tot=0;
				while(rs1.next())
				{
				tot=rs1.getRow();
				}
				if(tot!=0)
				{
				query2="insert into assign_allocation(alloc_date,adesc,submission_date,marks) values('"+talloc_date+"','"+tadesc+"','"+tsubmission_date+"','"+tmarks+"')";
				st=mycon.createStatement();
				st.executeUpdate(query2);
				//kout.println("<br><br><br><h1>Assignment Updated Successfully");
				mystr="Assignment Details Updated";
				}
				else
				mystr="Assignment Does Not Exists";
				//kout.println("<br><br><br><h1>Assignment Does Not Exists");
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
				myerror=e.getMessage();

			}
			finally
			{
				mycon.close();
			}
		}
		else
		mystr="Sorry Connection Is In-Active";
		//kout.println("Sorry Connection Is Not Active Now");
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
			kout.println("</body></html>");

			kout.println("<html><body background='myreport1.jpg'>");
			kout.println("</body></html>");

			if(myerror!=null)
			kout.println("There Is A Error From Server-Side="+myerror);

			kout.close();
}
}	    
}