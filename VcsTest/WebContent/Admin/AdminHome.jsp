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
<title>Home | Welcome To Administrator</title>
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
<script language=JavaScript>
var c=new Array(),p=0,timer,i=1,n=5;
c[0]="maroon";
c[1]="#006caa";
c[2]="orange";
c[3]="#26b6e8";
c[4]="#8e8e8e";
function text_color()
{
	if(p>4)
	{
	p=0;
	}
	document.getElementById('scroll').style.color=c[p];
	p++;
id=setTimeout("text_color()",1330);
}
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
</head>
<body id="page1" OnLoad="text_color()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="AdminHome.jsp">Student's site</a></h1>
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
<form action="" id="search-form" onSubmit="srch()">
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


<li><span><a href="/VcsTest/Step1" onClick="">Dynamic Report</a></span></li>
<li><span><a href="/VcsTest/GUI.jsp" onClick="">GUI</a></span></li>

	


<li><span><a href="http://localhost:8080/chat1/login.jsp">Group Discussion</a></span></li>
	<li><span><a href="http://localhost:8080/mail/index.jsp">Sendmail</a></span></li>
		<li><span><a href="../changepasswd.jsp">Change Your Password</a></span></li>
	
	<li><span><a href="AdminPendingRequest.jsp" onClick="">Pending
	Request</a></span></li>
	<li><span><a href="AdminStudentConfirmRequest.jsp">Confirm
	Request</a></span></li>
	<li><span><a href="AdminStudentUpdate.jsp">Update
	Student Info</a></span></li>
	<li><span><a href="AdminStudentToAllumini.jsp">Student
	Alumni</a></span></li>
	<li><span><a href="AdminNewsLetter.jsp">Send News
	Letter</a></span></li>
	<li><span><a href="/VcsTest/StudentAttendanceReport">Student Attendance</a></span></li>
	<li><span><a href="/VcsTest/QuizAttendedReport">Student Quiz Status</a></span></li>
	<li><span><a href="/VcsTest/StudentAssignmentSubmitReport">Student Assignments</a></span></li>
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
<a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p>
<!-- content --> <section id="content"> 
  <p>
    <marquee
	behavior="alternate">
      <font face="Kristen ITC"><b
	id="scroll">Virtual Classroom System</b></font>
      </marquee> 
    </p>
  <p><img src="../images/welcomebar.gif" width="681" height="21"></p>
  <p><img src="../images/education_banner (1).jpg" width="685" height="192"><br>
  </p>
  <p></p>
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
