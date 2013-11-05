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
<title>Student Confirm Request</title>
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
<%
String st,item,role,d_role="";
int it;
st=request.getParameter("common");
item=request.getParameter("items");
role=request.getParameter("role");
if(role!=null)
{
	d_role=role;
}	
if(item!=null)
{
	it=Integer.parseInt(item);
}
else
{
	it=0;
}	
%>
<% int p=0;%>
<%
        out.println("<script language=JavaScript>");
        out.println("var z=0;");
        out.println("function course(item){");
        out.println("opt=new Option();");
        out.println("document.getElementById('rid').options[z]=opt;");
        out.println("document.getElementById('rid').options[z].text=item;");
        out.println("document.getElementById('rid').options[z].value=item;");        
        out.println("z++;}");
        out.println("function starter(){");
        %>

<%
        for(p=1;p<=it;p++)
        {
        out.println("course('"+request.getParameter("r"+p)+"');");
        }
        out.println("}");
        %>
<%
        out.println("</script>");
        %>
<script language="JavaScript">
var timer,flag_color=false;
clearTimeout(timer);
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
function dyn_blnkerror()
{
	if(flag_color==true)
	{
	document.getElementById('tot_error').style.color="green";
	flag_color=false;
	}
	else
	{
	document.getElementById('tot_error').style.color="red";	
	flag_color=true;
	}
	timer=setTimeout('dyn_blnkerror()',500);
}
function confirm() 
{
var zrole;
zrole=document.getElementById('role').value;
if(zrole!="-------------Select Role--------------")
{
	document.getElementById('recover').action="/VcsTest/ServletAdminConfirmRequest";
	document.getElementById('recover').submit();
}
else
{
document.getElementById('tot_error').value="             * Please Select Role.";
dyn_blnkerror();
}
}
function selfSubmit()
{
	document.getElementById('recover').submit();	
}
</script>
</head>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="AdminStudentConfirmRequest.jsp">Student's site</a></h1>
<nav>
<ul>
	<li class="current"><a href="AdminHome.jsp" class="m1">STUDENT</a></li>
	<li><a href="AdminFaculty.jsp" class="m2">FACULTY</a></li>
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
	<li><span><a href="AdminPendingRequest.jsp" onClick="">Pending
	Request</a></span></li>
	<li><span><a href="AdminStudentConfirmRequest.jsp">Confirm
	Request</a></span></li>
	<li><span><a href="AdminStudentUpdate.jsp">Update
	Student Info</a></span></li>
	<li><span><a href="AdminStudentToAllumini.jsp">Student
	Alumni</a></span></li>
	<li><span><a href="/VcsTest/StudentAttendanceReport">Student Attendance</a></span></li>
	<li><span><a href="/VcsTest/QuizAttendedReport">Student Quiz Status</a></span></li>
	<li class="last"><span><a href="/VcsTest/StudentReport">Reports</a></span></li>


</ul>
<h2>Fresh <span>News</span></h2>
<%Connection mycon=null; %> <%
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
<a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p><!-- content --> <section id="content">
<fieldset><legend><font>
<h4>Request Confirmation</h4>
</font></legend>
<dd><br>
<form name="recover" id="recover" method="post" action="/VcsTest/Role">
<table name="frm_frm" id="frm_frm" align="center" width="60%">

	<tr>
		<td width="103"></td>
		<td colspan="3"><input type="text" name="tot_error" size="40"
			tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			id="tot_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td><span style="color: #a52a2a">Role:</span></td>
		<td colspan="3"><select id="role" name="role"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="selfSubmit()">
			<option value="-------------Select Role--------------">-------------Select
			Role--------------</option>
			<option value="STUDENT"
				<%if(d_role.equals("STUDENT")){out.println("selected");}%>>Student</option>
			<option value="FACULTY"
				<%if(d_role.equals("FACULTY")){out.println("selected");}%>>Faculty</option>
			<option value="MGT"
				<%if(d_role.equals("MGT")){out.println("selected");}%>>Management</option>
		</select></td>
	</tr>
	<tr>
		<td height="36">&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="103" " style="color: #a52a2a">Register ID:</td>
		<td colspan="3" height="30"><select id="rid" name="rid"
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
		<td width="138"></td>
		<td width="83" align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="3" class="fright" OnClick="confirm()">Confirm</a> </span></td>
		<td width="69" align="left">&nbsp;</td>
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
<div class="fleft">24/7 Customer Service <span>8.800.146.56.7</span></div>
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
