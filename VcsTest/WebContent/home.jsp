<!DOCTYPE html>
<%@page import="java.io.IOException,java.io.PrintWriter,java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,javax.servlet.ServletException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse;" %>
<html lang="en">
<head>
<title>Home - Home Page | Student's Site</title>
<meta charset="utf-8">
<meta name="description" content="Place your description here">
<meta name="keywords" content="put, your, keyword, here">
<meta name="author" content="Templates.com - website templates provider">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<script type="text/javascript" src="js/jquery-1.4.2.min.js" ></script>
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
	position:absolute;
	width:295px;
	height:27px;
	z-index:1;
	left: 650px;
	top: 74px;
}
-->
</style>
<script language=JavaScript>
var i=1,n=5,j,timer;
var c=new Array(),p=0;
c[0]="maroon";
c[1]="#006caa";
c[2]="orange";
c[3]="#26b6e8";
c[4]="#8e8e8e";
var a=new Array();
a[1]="images/banner1.jpg";
a[2]="images/banner2.jpg";
a[3]="images/banner3.jpg";
a[4]="images/banner4.jpg";
a[5]="images/banner5.jpg";
function image()
{
if (document.all) {
document.images.bnr.style.filter="blendTrans(duration=0)";
document.images.bnr.style.filter="blendTrans(duration=1)";
document.images.bnr.filters.blendTrans.Apply();
}
if(i<=n)
{
	if (document.all) 
	{
	document.images.bnr.filters.blendTrans.Play();
	}	
	document.bnr.src=a[i];	
	
	if(i==n)
	{
		i=1;
	}
	else
	{
		i++;
	}
	timer=setTimeout('image()',2500);
}
}
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
function redirect()
{
    var role=document.getElementById('role').value;
    if(role=="student")
    {
        window.location.href="Register.jsp";
    }
    else if(role=="faculty")
    {
        window.location.href="FacultyRegister.jsp";
    }
    else if(role=="management")
    {
        window.location.href="MgtRegister.jsp";
    }
}
function subs()
{
document.getElementById('newsletter-form').action="/VcsTest/ServletSubscribe";
data=document.getElementById('newsletter-form').submit();
}

function unsub()
{
document.getElementById('newsletter-form').action="/VcsTest/ServletUnSubscribe";
data=document.getElementById('newsletter-form').submit();
}
</script>
</head>
<body id="page1" OnLoad="image();text_color();">
<div class="wrap">
   <!-- header -->
   <header>
     <div class="container">
       <h1><a href="home.jsp"></a></h1>
       <nav>
         <ul>
           <li class="current"><a href="home.jsp" class="m1">Home</a></li>
           <li><a href="courses.jsp" class="m2">Courses</a></li>
           <li><a href="activities.jsp" class="m3">Activities</a></li>
           <li><a href="gallary.jsp" class="m4">Gallery</a></li>
           <li><a href="staff.jsp" class="m5">Staff</a></li>
           <li><a href="contactus.jsp" class="m6">Contact us</a></li>
         </ul>
       </nav>
       <form action="" id="search-form" OnSubmit="srch()">
         <fieldset>
           <div class="rowElem">
             <input type="text" id="srchkey">
             <a href="#" OnClick="srchbyfun()">Search</a></div>
         </fieldset>
       </form>
     </div>
</header>
     <div class="container">
       <!-- aside -->
       <aside>
         <h3>&nbsp;</h3>
         <h3>Categories</h3>
         <ul class="categories">
           <li><span><a href="/VcsTest/VStudentReport">Student Info</a></span></li>
           <li><span><a href="/VcsTest/VFacultyReport">Vacancies</a></span></li>
           <li><span><a href="/VcsTest/VMgtReport">Management</a></span></li>
         </ul>
<form action="" id="newsletter-form" method="post">
<%
String sb=request.getParameter("subscribe");
%>
<input type="text" id="total" value="<%if(sb!=null){out.println(sb);}%>" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; height: 14px; font-family: Trebuchet MS;"
			readonly>
<fieldset>
<div class="rowElem">
<h2>Newsletter</h2>
<input type="email" value="Enter Your Email Here"
	onFocus="if(this.value=='Enter Your Email Here'){this.value=''}"
	onBlur="if(this.value==''){this.value='Enter Your Email Here'}">
<div class="clear"><a href="#" class="fleft" OnClick="unsub()">Unsubscribe</a><a
	href="#" class="fright"
	onClick="subs()">Submit</a></div>
</div>
</fieldset>
</form>
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
				out.println("<li><h5>"+rs.getString("ndate")+"</h5");
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
       <p><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="maroon">Register As:&nbsp;&nbsp;</font>
         <select id="role" name="role" style="border:1px solid #a52a2a;font-family: arial;color: #FFFFFF;background-color: #26b6e8;">
           <option value="student">Student</option>
           <option value="faculty">Faculty</option>
           <option value="management">Management</option>
         </select>
         &nbsp;&nbsp;
         <input type="button" name="go" value=" Go " onClick="redirect()">
         &nbsp; &nbsp;&nbsp;|&nbsp; &nbsp;&nbsp; <a href="login.jsp" title="Already Registerd, Sign In">Log In</a> </b></p>
       <!-- content -->
       <section id="content">
         <marquee behavior="alternate">
           <font face="Kristen ITC"><b id="scroll">Virtual Classroom System</b></font>
         </marquee>
         <br>
         <p></p>
         <img src="images/banner1.jpg" width="691" height="300" name="bnr">
         <div class="inside">
           <h2>Recent <span>Articles</span></h2>
           <ul class="list">
             <table width="638" border="1">
               <tr>
                 <td width="398"><h4>&nbsp;</h4>
                   <h4>This System provides online learning. User can register themself and can attent online/Offline Lectures. </h4>
                   <p>&nbsp;</p>
                   <p>Registered user can read e-books in our library to enhance their knowledge.</p>
                   <p>Student can attend online activities/Competitions to carry out their all-round development</p>
                   <p>Student can attend online Quiz and can test their knowledge.</p>
                   <p>Student can enter into the chat room and can Carry out the discussion on various topic.</p>
                   <p>Student can also connect to experts and can Solve their Doubts and Queries.</p></td>
                 <td width="224">&nbsp; <span class="last"><img src="images/vcsban.gif" width="200" height="449"></span></td>
               </tr>
             </table>
             <li class="last">
               <table width="600" border="1">
                 <tr>
                   <td width="235"><h4>Management’s Time</h4>
                     <blockquote>
                       <p> Management are requested to submit progress reports of all students and faculty to Admin as soon as possible ...</p>
                     </blockquote></td>
                   <td width="235"><h4>Student’s Time</h4>
                     <blockquote>
                       <p> There is various New books added to Online Library including Magazines.
                         Soon we are launcing online e-Newspaper...</p>
                     </blockquote></td>
                 </tr>
               </table>
               <h4>&nbsp;</h4>
             </li>
           </ul>
           <h2>Move Forward <span>With Your Education</span></h2>
           <p><span class="txt1">Student’s Site</span> is a free e-learning Website using which registered user can attend online/offline lectures and can join their favourite Course. </p>
           <div class="img-box"><img src="images/1page-img.jpg">This Website offers you a online/Offline Education using Video conference Technology. Registered user can attend online Lectures and Quizes. Users can also read E-books in Library and Many more.</div>
           <p></p>
         </div>
       </section>
     </div>
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
