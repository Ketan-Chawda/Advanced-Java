

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
import mybest.Styles;

/**
 * Servlet implementation class for Servlet: Mind
 *
 */
 public class Mind extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Mind() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void display()
	{
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
				System.out.println("yes established");
				kout.println("yes established");
			}
		}
		catch(Exception k)
		{
			
		}
		
	}
	PrintWriter kout=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		kout=response.getWriter();
		
		kout.println("ok");
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
				display();
			try
			{
				String str="";
				kout.println("<html>");
				kout.println("<script type='text/javascript'>");
				kout.println("function my(my1)");
				kout.println("{");
				kout.println("my1.t3.value='nice1';str='chawda'");
				kout.println("}");
				kout.println("</script>");
				kout.println("<body>");
				kout.println("<form>");
				kout.println("<br><input type='button' name='t1'  value='ketan'>");
				kout.println("<br><input type='text' name='t2' value='ketan' onkeypress='my(this.form)'>");
				kout.println("<br><input type='text' name='t3' value='ketan'>");
				kout.println("</form>");
				kout.println("<body></html>");
				kout.println("oks n str="+str);
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			mycon.close();
		}
		}
		else
		kout.println("<br><br><h2><center>Sorry Connection Is Not Active Now");
	}
	catch(Exception k)
	{
		System.err.println(k);
	}
	finally
	{
		kout.println("<html><body background='myreport1.jpg'>");
		kout.println("</body></html>");
		kout.close();
	}
}
}