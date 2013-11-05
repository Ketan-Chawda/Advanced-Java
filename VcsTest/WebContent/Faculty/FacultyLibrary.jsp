
<%int status=0;String sid="";try{HttpSession my=request.getSession(false);if(my!=null)
{sid=my.getId().toString();String str=my.getAttribute("urole").toString();
if(str.compareToIgnoreCase("faculty")==0)status=12;
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
<title>Library Creation</title>
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
	document.getElementById('total_error').style.color="green";
	flag_color=false;
	}
	else
	{
	document.getElementById('total_error').style.color="red";
	flag_color=true;
	}
	timer=setTimeout('dyn_blnkerror()',500);
    }

function finalsend()
{
var pt=document.getElementById('lfile').value;
document.getElementById('total_error').value="";
	if(pt!="")
	{
		document.getElementById('library').submit();
	}
	else
	{
        document.getElementById('total_error').value="               * Please Select File.";		
	}
}
</script>
<%
String st=request.getParameter("common");
%>
</head>
<body id="page1" OnLoad="dyn_blnkerror()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="FacultyHome.jsp">Student's site</a></h1>
<nav>
<ul>
	<li><a href="FacultyHome.jsp" class="m1">LECTURE</a></li>
	<li><a href="FacultyAssignment.jsp" class="m2">ASSIGNMENT</a></li>
	<li><a href="FacultyQuiz.jsp" class="m3">QUIZ</a></li>
	<li><a href="FacultyNotice.jsp" class="m5">NOTICE</a></li>
	<li><a href="FacultyBlogs.jsp" class="m4">BLOGS</a></li>
	<li><a href="FacultyLibrary.jsp" class="m6">LIBRARY</a></li>
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
	<li><span><a href="FacultyLibrary.jsp" onClick="">Upload
	Videos Or E-Books</a></span></li>
	<li><span><a href="/VcsTest/FacultyVideoReport">View Videos</a></span></li>
	<li class="last"><span><a
		href="/VcsTest/FacultyLibraryReport">View Books</a></span></li>
</ul>
<h2>Fresh <span>News</span></h2>
<%Connection mycon=null; %>
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
		%>
</aside>
<p align="right"><b>
Hi &nbsp;<%out.println(session.getAttribute("uid").toString());%>
<a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p>
<!-- content --> <section id="content">
<fieldset><legend><font>
<h4>Create Library</h4>
</font></legend>
<dd><br>
<form name="library" id="library" method="post"
	action="/VcsTest/ServletLibraryCreate">
<table name="frm_frm" id="frm_frm" align="center" width="60%">
	<tr>
		<td width="101"></td>
		<td colspan="3"><input type="text" name="total_error"
			value="<% if(st!=null){out.println(st);} %>" size="60" tabindex="400"
			id="total_error"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS; font-weight: 500"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<input type="hidden" name="fid"
		value="<% out.println(session.getAttribute("uid").toString()); %>">
	<tr>
		<td width="101" style="color: #a52a2a;" height="30">Category:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td colspan="3"><select name="ctg" tabindex="1" id="bid"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
			<option value="video">Video&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </option>
			<option value="book">E-Book</option>
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="101" style="color: #a52a2a;" height="30">File:</td>
		<td colspan="3"><input type="file" name="lfile" id="lfile"
			tabindex="2"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 321px;"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td></td>
		<td width="64" align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="3" class="fright" OnClick="finalsend()">Upload</a> </span>
		<td width="71" align="left">&nbsp;</td>
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
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="../images/banner_resources.jpg"  width="602" height="150">
<br><br><br><br>
</fieldset>
</section></div>
</div>
<!-- footer -->
<footer>
   <div class="container">
      <div class="inside">
         <div class="wrapper">
            <div class="fleft">24/7 Customer Service <span>  94286-91034</span></div>
            <div class="aligncenter">
           This Website Under Construction. <br> Due To Lack Of Time We Are Unable To Create Online Conference And Others.
               </div>
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
