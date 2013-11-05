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
<title>Quiz Creation</title>
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
function final_ass()
{
    var desc,total;
    var d_error,t_error,date_error;
    desc=document.getElementById('q_desc').value;
    total=document.getElementById('no_q').value;    

    d_error=document.getElementById('desc_error').value;
    t_error=document.getElementById('mark_error').value;    

    if(desc!="" && total!="" && d_error=="" && t_error=="")
    {
    	document.getElementById('q_c').action="/VcsTest/ServletFacultyQuizDeclare";
        document.getElementById('q_c').submit();
    }
    else
    {
        document.getElementById('total_error').value=" * Please Fill Every Fields Correctly.";
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
function textbackcolor(lbl)
    {
        document.getElementById(lbl).style.backgroundColor="cornsilk";
        document.getElementById(lbl).style.borderStyle="dotted";
        document.getElementById(lbl).style.borderColor="green";
    }
function textback(lbl)
{
        document.getElementById(lbl).style.backgroundColor="";
        document.getElementById(lbl).style.borderStyle="double";
        document.getElementById(lbl).style.borderColor="#0087c1";
}
function check(lbl)
{
 var act,desc;
 if(lbl=="no_q")
 {
     act=document.getElementById(lbl).value;
     act=act.replace(/^\ +/,"").replace(/\ +$/,"");
     document.getElementById(lbl).value=act;
     if(act=="")
     {
         document.getElementById('mark_error').value="   * No. Of Question Required."
     }
     else if(act!="")
     {
         if((act%2!=1)&&(act%2!=0))
         {
             document.getElementById('mark_error').value="   * Number Not Valid."
         }
         else
         {
             document.getElementById('mark_error').value="";
         }
     }
     else
     {
             document.getElementById('mark_error').value="";
     }
 }
 else if(lbl=="q_desc")
    {
     desc=document.getElementById(lbl).value;
     desc=desc.replace(/^\ +/,"").replace(/\ +$/,"");
     document.getElementById(lbl).value=desc;
     if(desc=="")
     {
         document.getElementById('desc_error').value="   * Description Required."
     }
     else
     {
         document.getElementById('desc_error').value="";
     }
 }
}
</script>
<script language="JavaScript">
function selfsubmit()
{
document.getElementById('q_c').submit();
}
</script>
</head>
<%
String st="",course="",item="",d_course="";
int it;
st=request.getParameter("common");
course=request.getParameter("course");
item=request.getParameter("items");
if(course!=null)
{
	d_course=course;
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
<% int p=0;int k=0;%>
<%Connection mycon=null; %>
<%
        out.println("<script language=JavaScript>");
        out.println("var z=0;");
        out.println("function course(item){");
        out.println("opt=new Option();");
        out.println("document.getElementById('q_course').options[z]=opt;");
        out.println("document.getElementById('q_course').options[z].text=item;");
        out.println("document.getElementById('q_course').options[z].value=item;");
        out.println("if(document.getElementById('q_course').options[z].text==\""+d_course+"\"){");
        out.println("document.getElementById('q_course').options[z].selected=true;}");
        out.println("z++;}");
        out.println("function starter(){");
        %>

<%
        out.println("course('-------------Select Course------------');");        
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String data="";
			stt=mycon.createStatement();
			ResultSet rs=stt.executeQuery("select coursename from course_master");
			
			while(rs.next())
			{
				data=rs.getString("coursename");
				out.println("course('"+data+"');");
			}			
		}
		catch(Exception zp)
		{
			System.err.println(zp);
		}
		finally
		{
		}        
        out.println("sub();");
        out.println("}");
        %>

<%        
        out.println("var p=0;");
        out.println("function subs(item1){");        
        out.println("opt1=new Option();");
        out.println("document.getElementById('q_sub').options[p]=opt1;");
        out.println("document.getElementById('q_sub').options[p].text=item1;");
        out.println("document.getElementById('q_sub').options[p].value=item1;");        
        out.println("p++;}");
        out.println("function sub(){");
        %>
<%
        	for(k=1;k<=it;k++)
			{				
				out.println("subs('"+request.getParameter("s"+k)+"');");  
			}					                            
        out.println("}");
        %>
<%
        out.println("</script>");
        %>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<h1><a href="FacultyQuizCreate.jsp">Student's site</a></h1>
<nav>
<ul>
	<li><a href="FacultyHome.jsp" class="m1">LECTURE</a></li>
	<li><a href="FacultyAssignment.jsp" class="m2">ASSIGNMENT</a></li>
	<li class="current"><a href="FacultyQuiz.jsp" class="m3">QUIZ</a></li>
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
	<li><span><a href="FacultyReports.jsp">All Reports</a></span></li>
	<li><span><a href="FacultyQuizCreate.jsp">Create Quiz</a></span></li>
	<li><span><a href="FacultyQuestionCreate.jsp">Create
	Quiz Questions</a></span></li>
	<li class="last"><span><a href="FacultyQuizDelete.jsp">Delete
	Quiz</a></span></li>

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
		catch(Exception ex)
		{
			System.err.println(ex);
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
<h4>Quiz Creation</h4>
</font></legend>
<dd><br>
<form name="q_c" id="q_c" method="post"
	action="/VcsTest/ServletAssignment">
<table name="frm_frm" id="frm_frm" align="center" width="84%">
	<tr>
		<td width="128"></td>
		<td colspan="5"><input type="text" name="total_error" size="40"
			tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			id="total_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="128" style="color: #a52a2a">Course Name:</td>
		<td colspan="5" height="30"><select name="coursename"
			tabindex="1" id="q_course"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="selfsubmit()">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="128" style="color: #a52a2a">Subject Name:</td>
		<td colspan="5" height="30"><select name="subject" id="q_sub"
			tabindex="2"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
		</select> <input type="hidden" name="quiz" value="qz"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="128" style="color: #a52a2a">Description:</td>

		<td colspan="3"
			style="background: url(../images/roundedbig.gif) no-repeat top left;">
		<textarea name="qdesc" id="q_desc" tabindex="3"
			style="padding: 3px 5px 2px 7px; border: 0px solid; overflow: auto; background: url(images/roundedbig1.gif) no-repeat top left; height: 79px; width: 215px; color: black"
			OnBlur="check('q_desc')"></textarea> <br>
		&nbsp;</td>
		<td colspan="2"><input type="text" name="desc_error" size="30"
			tabindex="400" id="desc_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="128" style="color: #a52a2a">No. Of Ques:</td>
		<td height="30" colspan="3"><input type="text" name="noq"
			id="no_q" tabindex="4"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 218px;"
			OnFocus="textbackcolor('no_q')"
			OnBlur="check('no_q');textback('no_q')"></td>
		<td height="30" colspan="2"><input type="text" name="mark_error"
			size="30" tabindex="400" id="mark_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="128" style="color: #a52a2a">Weightage:</td>
		<td colspan="5"><select name="weightage" id="q_weight"
			tabindex="5"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
			<option name="01" value="01">01&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option name="02" value="02">02</option>
			<option name="03" value="03">03</option>
			<option name="04" value="04">04</option>
			<option name="05" value="05">05</option>
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td></td>
		<td width="0"></td>
		<td width="247"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="6" class="fright" OnClick="final_ass()">Create</a> </span></td>
		<td width="28"></td>
		<td width="1"></td>
		<td width="147" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
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
