

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

import mybest.Fortable;
import mybest.Styles;

/**
 * Servlet implementation class for Servlet: ServletNoticeView
 *
 */
 public class ServletNoticeView extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletNoticeView() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter kout=response.getWriter();
		kout.println("<html><head><title>Notice Viewing</title></head>");
		HttpSession my=request.getSession(false);
		
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
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<br><br><h1>Notice Viewing In VCS</h1>");
				kout.println("<br><br><br>**************************************************************************************************************<br>");
				String tdate=new Date().toGMTString().toString(),forrole=my.getAttribute("urole").toString();
				kout.println("<br><br><center>**************************************************************************************************************");
				kout.println("<table border=0>");
				kout.println("<tr><td colspan=200><center><h2>"+Styles.ccase("Organisation : virtual class room system")+"</center>");
				kout.println("<tr><td><br>");
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report For : "+forrole)+"<td><h2>"+Styles.ccase(tdate));
				kout.println("<tr><td colspan=150><blockquote><h2>"+Styles.ccase("Report Title : "+"Notice View")+"<td><h2><a href='javascript:history.go(-1)'>Back</a>");
				kout.println("</table>");
				kout.println("<br><br><br>**************************************************************************************************************</center><br>");
				

				st=mycon.createStatement();
				String trole="",tcourse="optional",query1;
				
				//trole="coursewise";
				//tcourse="bca";
				
				trole=my.getAttribute("urole").toString();
				tcourse=my.getAttribute("ucourse").toString();
				
				if(tcourse.compareToIgnoreCase("optional")!=0)
				query1="select * from notice where forcourse='"+tcourse+"'or noticeto='all' or noticeto='students'";
				else
				query1="select * from notice where noticeto='"+trole+"' or noticeto='all'";
				
				ResultSet rs=st.executeQuery(query1);
				int c=1;
				kout.println("<center><table align='right' width=95% border=2 bordercolor='black'>");
				while(rs.next())
				{
					String str=rs.getString("nid");
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(150,"*"));
					
					if(c%2==1)
					kout.println("<tr><td colspan=95%><center><h1>Notice Id : " +str+"</center></h1>");
					else
					kout.println("<tr><td colspan=95%><center><h2>Notice Id : "+str+"</center></h2>");
					
					kout.println("<tr><td>Sr No:<td>Notice From<td>Notice<td>Notice Date<td>");
					kout.println("<tr><td>"+c);
					kout.println("<td>"+rs.getString("noticefrom"));
					kout.println("<td>"+rs.getString("nmsg"));
					kout.println("<td>"+rs.getString("ndate"));
					c=c+1;
					kout.println("<tr><td colspan=100%><center>"+Fortable.display(33,"<<->>"));
					
				}
				kout.println("</table>");
				if(c==1)
					kout.println("<br><br><br><h1>Sorry No Notice Is For You People");
				}
			catch(Exception k)
			{
				System.err.println(k);
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