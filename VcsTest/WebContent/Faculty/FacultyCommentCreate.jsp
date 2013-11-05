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
<title>Comment</title>
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
String st,msg,b_id;
msg=request.getParameter("msg");
st=request.getParameter("common");
b_id=request.getParameter("bid");
%>
<% int p=0;%>
<%Connection mycon=null; %>
<%        
        out.println("<script language=JavaScript>");
        out.println("var z=0;");
        out.println("function course(item){");
        out.println("opt=new Option();");
        out.println("document.getElementById('bid').options[z]=opt;");
        out.println("document.getElementById('bid').options[z].text=item;");
        out.println("document.getElementById('bid').options[z].value=item;");
        out.println("if(document.getElementById('bid').options[z].text==\""+b_id+"\"){");
        out.println("document.getElementById('bid').options[z].selected=true;}");
        out.println("z++;}");
        out.println("function starter(){");
        out.println("course('"+"--------------Select One-------------"+"');");
        %>

<%
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String data="";
			stt=mycon.createStatement();
			ResultSet rs=stt.executeQuery("select bid from blog_master");
			
			while(rs.next())
			{
				data=rs.getString("bid");
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
<script language="JavaScript" src="js/textcol.js">
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
<script language="JavaScript">
var timer,flag_color=false;
function selfSubmit()
{
	document.getElementById('recover').submit();	
}
function finalcomment()
{
var bid=document.getElementById('bid').value;
var cmt=document.getElementById('cmt').value;
if(cmt!="")
{
	if(bid=="--------------Select One-------------")
	{
		document.getElementById('total_error').value="    * Please Select Blog ID."
		dyn_blnkerror();
	}
	else
	{
	document.getElementById('recover').action="/VcsTest/ServletCommentsCreate";
	document.getElementById('recover').submit();
	}
}
else
{
	document.getElementById('total_error').value="   * Please Fill Each & Every Fields."
	dyn_blnkerror();
}
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
</script>
</head>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="AdminNewsCreate.jsp">Student's site</a></h1>
<nav>
<ul>
	<li><a href="FacultyHome.jsp" class="m1">LECTURE</a></li>
	<li><a href="FacultyAssignment.jsp" class="m2">ASSIGNMENT</a></li>
	<li><a href="FacultyQuiz.jsp" class="m3">QUIZ</a></li>
	<li><a href="FacultyNotice.jsp" class="m5">NOTICE</a></li>
	<li class="current"><a href="FacultyBlogs.jsp" class="m4">BLOGS</a></li>
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
	<li><span><a href="FacultyReports.jsp">All Reports</a></span></li>
	<li><span><a href="FacultyBlogsCreate.jsp">Create Blogs</a></span></li>
	<li><span><a href="FacultyBlogsDelete.jsp">Delete Blogs</a></span></li>
	<li><span><a href="FacultyCommentCreate.jsp">Comment</a></span></li>
	<li><span><a href="FacultyCommentDelete.jsp">Comment Delete</a></span></li>
	<li class="last"><span><a href="/VcsTest/CommentsReport">View Comments</a></span></li>
</ul>
<h2>Fresh <span>News</span></h2>
<%
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String date="",msg1="";
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
<p align="right"><b>
Hi &nbsp;<%out.println(session.getAttribute("uid").toString());%>
<a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p>
<!-- content --> <section id="content">
<fieldset><legend><font>
<h4>Comment</h4>
</font></legend>
<dd><br>
<form name="recover" id="recover" method="post"
	action="/VcsTest/FacultyComment">
<table name="frm_frm" id="frm_frm" align="center" width="60%">

	<tr>
		<td width="97"></td>
		<td colspan="3"><input type="text" name="total_error" size="40"
			tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			id="total_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
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
		<td width="97" style="color: #a52a2a">Blog ID:</td>
		<td colspan="3"><select id="bid" name="bid"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="selfSubmit()">
		</select> <br>
		&nbsp;</td>
	</tr>
	<tr>
		<td width="97" style="color: #a52a2a">Blog Message:</td>
		<td colspan="3" style="background: url(../images/roundedbig.gif) no-repeat top left;"><textarea
			name="bmsg" id="q_desc" tabindex="3"
			style="padding: 3px 5px 2px 7px; border: 0px solid; overflow: auto; background: url(images/roundedbig1.gif) no-repeat top left; color: black; height: 79px; width: 215px;"
			readonly><%if(msg!=null){out.println(msg);};%></textarea> <br>
		&nbsp;</td>
	</tr>
	<tr>
		<td width="97" style="color: #a52a2a">Comment:</td>
		<td colspan="3"><input type="text" name="cmsg" id="cmt"
			tabindex="3"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			OnFocus="textbackcolor('cmt')" OnBlur="textback('cmt')"></td>
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
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td></td>
		<td width="114"></td>
		<td width="114" align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="3" class="fright" OnClick="finalcomment()">Comment</a> </span></td>
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
