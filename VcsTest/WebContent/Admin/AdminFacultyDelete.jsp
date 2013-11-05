<%int status=0;String sid="";try{HttpSession my=request.getSession(false);if(my!=null)
{sid=my.getId().toString();String str=my.getAttribute("urole").toString();
if(str.compareToIgnoreCase("admin")==0)status=12;
else	status=36;}}
catch(Exception e)
{System.out.println(e+"session is expired ok");}
finally{%>
<%if(status==12){%>

<!DOCTYPE html>
<%@page
	import="java.io.IOException,java.io.PrintWriter,java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,javax.servlet.ServletException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse;"%>

<html lang="en">
<head>
<title>Faculty Deletion</title>
<meta charset="utf-8">
<meta name="description" content="Place your description here">
<meta name="keywords" content="put, your, keyword, here">
<meta name="author" content="Templates.com - website templates provider">
<link rel="stylesheet" href="../css/reset.css" type="text/css"
	media="all">
<link rel="stylesheet" href="../css/style.css" type="text/css"
	media="all">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_300.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<!--[if lt IE 7]>
     <link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="screen">
     <script type="text/javascript" src="js/ie_png.js"></script>
     <script type="text/javascript">
        ie_png.fix('.png, footer, header nav ul li a, .nav-bg, .list li img');
     </script>
<![endif]-->
<!--[if lt IE 9]>
  	<script type="text/javascript" src="js/html5.js"></script>
  <![endif]-->
<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 295px;
	height: 27px;
	z-index: 1;
	left: 650px;
	top: 74px;
}
-->
</style>
<% int p=0;%>
<%Connection mycon=null; %>
<%
        out.println("<script language=JavaScript>");
        out.println("var z=0;");
        out.println("function course(item){");
        out.println("opt=new Option();");
        out.println("document.getElementById('facid').options[z]=opt;");
        out.println("document.getElementById('facid').options[z].text=item;");
        out.println("document.getElementById('facid').options[z].value=item;");
        out.println("z++;}");
        out.println("function starter(){");
        %>

<%
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String data="";
			stt=mycon.createStatement();
			ResultSet rs=stt.executeQuery("select fid from faculty_master");
			
			while(rs.next())
			{
				data=rs.getString("fid");
				out.println("course('"+data+"');");
			}			
		}
		catch(Exception k)
		{
			System.err.println(k);
		}
		finally
		{
		}
        out.println("}");
        %>
<%
        out.println("</script>");
        %>
<script language="JavaScript">
function srch()
{   var s;
    s=document.getElementById('srchkey').value;
    if(s!="")
    {
    window.open("http://www.google.co.in/search?q="+s+"&cad=b&cad=h");
    }
}
function srchbyfun()
{
    srch();
}
</script>
<%
String st;
st=request.getParameter("common");
%>
</head>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="AdminFacultyDelete.jsp">Student's site</a></h1>
<nav>
<ul>
	<li><a href="AdminHome.jsp" class="m1">STUDENT</a></li>
	<li class="current"><a href="AdminFaculty.jsp" class="m2">FACULTY</a></li>
	<li><a href="AdminMgt.jsp" class="m3">MANAGEMENT</a></li>
	<li><a href="AdminCourses.jsp" class="m4">COURSE</a></li>
	<li><a href="AdminBlogs.jsp" class="m5">BLOGS</a></li>
	<li><a href="AdminReports.jsp" class="m6">REPORTS</a></li>
</ul>
</nav>
<form action="" id="search-form" OnSubmit="srch()">
<fieldset>
<div class="rowElem"><input type="text" id="srchkey"> <a
	href="#" onClick="srchbyfun()">Search</a></div>
</fieldset>
</form>
</div>
</header>
<div class="container"><!-- aside --> <aside>
<h3>&nbsp;</h3>
<h3>Categories</h3>
<ul class="categories">
	<li><span><a href="AdminFacultyUpdate.jsp" onClick="">Edit
	Faculty Info</a></span></li>
	<li><span><a href="AdminFacultyDelete.jsp" onClick="">Delete
	Faculty</a></span></li>
	<li><span><a href="AdminFacultyToAllumini.jsp">Faculty
	To Alumni</a></span></li>
	<li><span><a href="/VcsTest/FacultyAlluminiReport">Faculty Lecture Info</a></span></li>
	<li><span><a href="/VcsTest/QuizReport">Faculty
	Quiz Info</a></span></li>
	<li class="last"><span><a href="/VcsTest/FacultyReport">Reports</a></span></li>
</ul>
<h2>Fresh <span>News</span></h2>

<%
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String date="",msg="";
			stt=mycon.createStatement();
			ResultSet rs=stt.executeQuery("select * from news");
			out.println("<ul class=\"news\">");
			
			while(rs.next())
			{
				out.println("<li><h5>"+rs.getString("ndate")+"</h5>");
				out.println("<h4><a href=\"#\">"+rs.getString("newsmsg")+"</a></h4></li>");
			}
			out.println("</ul>");
		}
		catch(Exception k)
		{
			System.err.println(k);
		}
		finally
		{
		}
		%> </aside>
<div id="global"><b><a href="AdminNewsCreate.jsp">News</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><a href="AdminNoticeCreate.jsp">Notice</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><a href="AdminLoginLock.jsp">Login Lock</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><a href="AdminLoginUnlock.jsp">Login Unlock</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><a href="AdminLoginDelete.jsp">Login Delete</a></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b><a href="AdminCommentCreate.jsp">Comment</a></b></div>
<p align="right"><b>
Hi &nbsp;<%out.println(session.getAttribute("uid").toString());%>
<a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p>
<p><img src="../images/warning.gif" alt="" width="679" height="14"></p>
<!-- content --> <section id="content">
<fieldset><legend><font>
<h4>Faculty Deletion</h4>
</font></legend>
<dd><br>
<form name="recover" id="recover" method="post"
	action="/VcsTest/ServletAdminFacultyDelete">
<table name="frm_frm" id="frm_frm" align="center" width="60%">

	<tr>
		<td width="103"></td>
		<td colspan="3"><input type="text" name="rec_error" size="40"
			tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			id="rec_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="103" style="color: #a52a2a">Faculty ID:</td>
		<td colspan="3"><select name="fid" id="facid"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td></td>
		<td width="147"></td>
		<td width="75" align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="3" class="fright"
			OnClick="document.getElementById('recover').submit()">Delete</a> </span></td>
		<td width="68" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>

</table>
</form>
<dd>
<dd><br>
<br>
</fieldset>
</section></div>
</div>
<!-- footer -->
<footer>
<div class="container">
<div class="inside">
<div class="wrapper">
<div class="fleft">24/7 Customer Service <span> 94286-91034</span></div>
<div class="aligncenter">This Website Under Construction. <br>
Due To Lack Of Time We Are Unable To Create Online Conference And
Others.</div>
</div>
</div>
</div>
</footer>
<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
<%}else if(status==36){%>
<h1><center>Sorry Page Exists But You Are Unauthorized User For
This Page Thanks Best Luck Next Time
<br><br><br><a href="/VcsTest/login.jsp">Login Again</a><br><br><br><a href="/VcsTest/home.jsp">Go To Home</a></center><%}
else{%><h1>
<center>Your Session Has Been Expired
<br><br><br><a href="/VcsTest/login.jsp">Login Again</a><br><br><br><a href="/VcsTest/home.jsp">Go To Home</a></center>
<%}}%>
