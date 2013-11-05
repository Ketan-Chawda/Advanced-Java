<%int status=0;String sid="";try{HttpSession my=request.getSession();if(my!=null)
{sid=my.getId().toString();String str=my.getAttribute("urole").toString();
if(str.compareToIgnoreCase("management")==0)status=12;
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
<title>Notice Creation</title>
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
String st,item,course,d_course="";
int it;
st=request.getParameter("common");
item=request.getParameter("items");
course=request.getParameter("course");
if(course!=null)
{
	d_course="cs";
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
        out.println("document.getElementById('ncourse').options[z]=opt;");
        out.println("document.getElementById('ncourse').options[z].text=item;");
        out.println("document.getElementById('ncourse').options[z].value=item;");
        out.println("z++;}");
        out.println("function starter(){");
        %>

<%                    
        for(p=1;p<=it;p++)
        {
        	out.println("course('"+request.getParameter("c"+p)+"');");
        }
        if(d_course.equals("cs"))
        {
        out.println("document.getElementById('course').options[2].selected=true;");
        out.println("document.getElementById('lbl_crs').style.visibility=\"\";");
        out.println("document.getElementById('ncourse').style.visibility=\"\";");
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
<script langauge="JavaScript">
function servcourse()
{
var course;
course=document.getElementById('course').value;
	if(course=="Course")
	{		
		document.getElementById('recover').submit();
	}
	else
	{
	document.getElementById('lbl_crs').style.visibility="hidden";
	document.getElementById('ncourse').style.visibility="hidden";
	}
}
function finalcourse()
{
document.getElementById('recover').action="/VcsTest/ServletMgtNoticeCreate";
document.getElementById('recover').submit();
}
</script>

</head>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="AdminNewsCreate.jsp">Student's site</a></h1>
<nav>
<ul>
	<li><a href="MgtHome.jsp" class="m1">ACTIVITY</a></li>
	<li><a href="MgtCompetition.jsp" class="m2">COMPETITION</a></li>
	<li><a href="MgtBlogs.jsp" class="m4">BLOGS</a></li>
	<li class="current"><a href="MgtNotice.jsp" class="m5">NOTICE</a></li>
	<li><a href="MgtReport.jsp" class="m3">REPORTS</a></li>
	<li><a href="MgtOthers.jsp" class="m6">OTHERS</a></li>
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
	<li><span><a href="MgtNoticeCreate.jsp">Create Notice</a></span></li>
	<li><span><a href="MgtNoticeDelete.jsp">Delete Notice</a></span></li>
	<li class="last"><span><a href="/VcsTest/NoticeReport">View Notice</a></span></li>
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

<p align="right"><b>
Hi &nbsp;<%out.println(session.getAttribute("uid").toString());%> 
<a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p>
<!-- content --> <section id="content">
<fieldset><legend><font>
<h4>Notice Creation</h4>
</font></legend>
<dd><br>
<form name="recover" id="recover" method="post"
	action="/VcsTest/MgtNotice">
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
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="103" style="color: #a52a2a">Notice To:</td>
		<td colspan="3"><select id="course" name="noticeto"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="servcourse()">
			<option value="All">All</option>
			<option value="Faculty">Faculties</option>
			<option value="Student">Students</option>
			<option value="Course">Coursewise</option>
		</select> <br>
		&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td height="38"><input type="text" name="lbl_crs" size="10"
			tabindex="400" value="Course" id="lbl_crs"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: #a52a2a; border: 0px solid; visibility: hidden;"
			readonly></td>
		<td colspan="3"><select id="ncourse" name="course"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8; visibility: hidden;">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="103" style="color: #a52a2a">Notice:</td>
		<td colspan="3"
			style="background: url(../images/roundedbig.gif) no-repeat top left;"><textarea
			name="nmsg" id="q_desc" tabindex="3"
			style="padding: 3px 5px 2px 7px; border: 0px solid; overflow: auto; background: url(images/roundedbig1.gif) no-repeat top left; height: 79px; width: 215px;"></textarea>
		<br>
		&nbsp;</td>
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
		<td width="147"></td>
		<td width="75" align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="3" class="fright" OnClick="finalcourse()">Create</a> </span></td>
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
