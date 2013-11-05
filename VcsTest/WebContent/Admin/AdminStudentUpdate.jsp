<%int status=0;String sid="";try{HttpSession my=request.getSession(false);if(my!=null)
{sid=my.getId().toString();String str=my.getAttribute("urole").toString();
if(str.compareToIgnoreCase("admin")==0)status=12;
else	status=36;}}
catch(Exception e)
{System.out.println(e+"session is expired ok");}
finally{%>
<%if(status==12){%>

<!DOCTYPE html>
<%@page import="java.io.IOException,java.io.PrintWriter,java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement,javax.servlet.ServletException,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse;"%>
<html lang="en">
<head>
<title>Student Updation</title>
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
td {
	color: #008cc4;
}
</style>
<script language="JavaScript">
var str_alpha="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
var str_num="0123456789";
var str,i,ic,cc,ans,global_str="",flag=false,mobile="";
var bday="",bmonth="",byear="",f_date="",yearcnt=0,flagyear=false,leap=false,gender="Male";
var now=new Date();
var now_year=now.getFullYear();
var timer,flag_color=false;
function registervalid()
{
var sid,firstname,middlename,surname,mobile,state,city,address,zipcode,email;
sid=document.getElementById('sid').value;
firstname=document.getElementById('reg_fname').value;
middlename=document.getElementById('reg_mname').value;
surname=document.getElementById('reg_sname').value;
mobile=document.getElementById('reg_mobile').value;
state=document.getElementById('reg_state').value;
city=document.getElementById('reg_city').value;
address=document.getElementById('reg_address').value;
zipcode=document.getElementById('reg_zip').value;
email=document.getElementById('reg_email').value;

f_error=document.getElementById('fname_error').value;
m_error=document.getElementById('mname_error').value;
s_error=document.getElementById('sname_error').value;
mob_error=document.getElementById('mobile_error').value;
state_error=document.getElementById('state_error').value;
city_error=document.getElementById('city_error').value;
add_error=document.getElementById('address_error').value;
zip_error=document.getElementById('zipcode_error').value;
mailid_error=document.getElementById('email_error').value;

hclearTimeout(timer);

if(firstname!="First Name" && middlename!="Middle Name" && surname!="Surname" && mobile !="Mobile No." && state !="State" && city !="City" && address !="Address" && zipcode !="Zipcode" && email !="E-Mail" && f_error=="" && m_error=="" && s_error=="" && mob_error=="" && state_error=="" && city_error=="" && add_error=="" && zip_error=="" && mailid_error=="")
{	
		if(sid=="--------------Select One-------------")
		{
		document.getElementById('total_error').value="       * Please Select Student ID."
		dyn_blnkerror();
		}
		else
		{				
		document.getElementById("register_frm").action="/VcsTest/ServletAdminStudentUpdate";
		document.getElementById('register_frm').submit();
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


function gender_mf(gndr)
{
var gndr;
	if(gndr=='M')
	{
		gender="Male";
	}
	else if(gndr=='F')
	{
		gender="Female";
	}
}
function changetext(myform,s_arg)
{
	if(s_arg=="fname")
	{
		str=document.getElementById('reg_fname').value;
		str=str.split(' ').join('');
		document.getElementById('reg_fname').value=str;
		if(myform.reg_fname.value=='')
		{		
		document.getElementById('fname_error').value="   * First Name Required.";
		}
		if(str!="")
		{	
			if(str.length>=2)						
			{
				ans=check_str("fname",str);			
				if(ans=="fname invalid")
				{			
				document.getElementById('fname_error').value="   * First Name Not Valid.";
				}
				else
				{
				document.getElementById('reg_fname').value=global_str;
				document.getElementById('fname_error').value="";
				}	
			}
			else
			{
			str=str.toUpperCase();
			document.getElementById('reg_fname').value=str;
			document.getElementById('fname_error').value="   * Must Have Atleast 2 Character.";
			}			
		}									
	}
	else if(s_arg=="mname")
	{
		str=document.getElementById('reg_mname').value;
		str=str.split(' ').join('');
		document.getElementById('reg_mname').value=str;
		if(myform.reg_mname.value=='')
		{
		document.getElementById('mname_error').value="   * Middle Name Required.";
		}		
		if(str!="")
		{
		if(str.length>=2)						
			{
				ans=check_str("mname",str);				
				if(ans=="mname invalid")
				{
				document.getElementById('mname_error').value="   * Middle Name Not Valid.";
				}
				else
				{
				document.getElementById('reg_mname').value=global_str;
				document.getElementById('mname_error').value="";
				}
			}
			else
			{
			str=str.toUpperCase();
			document.getElementById('reg_mname').value=str;
			document.getElementById('mname_error').value="   * Must Have Atleast 2 Character.";
			}
		}
	}
	else if(s_arg=="sname")
	{
		str=document.getElementById('reg_sname').value;
		str=str.split(' ').join('');
		document.getElementById('reg_sname').value=str;
		if(myform.reg_sname.value=='')
		{
		document.getElementById('sname_error').value="   * Surname Required.";
		}	
		if(str!="")
		{
			if(str.length>=2)
			{
				ans=check_str("sname",str);	
				if(ans=="sname invalid")
				{
				document.getElementById('sname_error').value="   * Surname Not Valid.";
				}
				else
				{
				document.getElementById('reg_sname').value=global_str;
				document.getElementById('sname_error').value="";
				}
			}
			else
			{
				str=str.toUpperCase();
				document.getElementById('reg_sname').value=str;
				document.getElementById('sname_error').value="   * Must Have Atleast 2 Character.";
			}
		}		
	}
	else if(s_arg=="state")
	{
		str=document.getElementById('reg_state').value;
		str=str.split(' ').join('');
		document.getElementById('reg_state').value=str;
		if(myform.reg_state.value=='')
		{
			document.getElementById('state_error').value="   * State Required.";
		}	
		if(str!="")
		{
			if(str.length>=2)
			{
				ans=check_str("state",str);
				if(ans=="state invalid")
				{
				document.getElementById('state_error').value="   * State Not Valid.";
				}
				else
				{
				document.getElementById('reg_state').value=global_str;
				document.getElementById('state_error').value="";
				}
			}
			else
			{
			str=str.toUpperCase();
			document.getElementById('reg_state').value=str;
			document.getElementById('state_error').value="   * Must Have Atleast 2 Character.";				
			}
		}
	}
	else if(s_arg=="city")
	{
		str=document.getElementById('reg_city').value;
		str=str.split(' ').join('');
		document.getElementById('reg_city').value=str;
		if(myform.reg_city.value=='')
		{
			document.getElementById('city_error').value="   * City Required.";
		}	
		if(str!="")
		{
			if(str.length>=2)
			{
				ans=check_str("city",str);
				if(ans=="city invalid")
				{
				document.getElementById('city_error').value="   * City Is Not Valid";
				}
				else
				{
				document.getElementById('reg_city').value=global_str;
				document.getElementById('city_error').value="";
				}
			}
			else
			{
			str=str.toUpperCase();
			document.getElementById('reg_city').value=str;
			document.getElementById('city_error').value="   * Must Have Atleast 2 Character.";
			}
		}		
	}
	
}
function check_str(lbl,f_str)
{
var lw,up,ac,totalch;

	for(i=0;i<f_str.length;i++)
	{				
		cc=f_str.substr(i,1);
		ic=str_alpha.indexOf(cc,0);
		if(ic == -1)
		{
			flag=false;
			return lbl+" invalid";			
		}
		else
		{			
			flag=true;
		}
	}	
if(flag==true)
{
	up=f_str.substr(0,1);
	up=up.toUpperCase();
	lw=f_str.substr(1,f_str.length)
	lw=lw.toLowerCase();
	totalch=up+lw;
	global_str=totalch;
}
}
function checknum(numfrm,num_str)
{
var ans,ans_zip;
	if(num_str=="zip")
	{
		ans_zip=document.getElementById('reg_zip').value;
		ans_zip=ans_zip.replace(/^\ +/,"").replace(/\ +$/,"");
		document.getElementById('reg_zip').value=ans_zip;
		if(ans_zip=='')
		{
		document.getElementById('zipcode_error').value="   * Zipcode Required.";
		}
		else if(ans_zip!="")
		{	
		document.getElementById('zipcode_error').value="";
		}
	}
	else if(num_str=="mob")
	{
		arg=document.getElementById('reg_mobile').value;
		arg=arg.replace(/^\ +/,"").replace(/\ +$/,"");
		document.getElementById('reg_mobile').value=arg;
		if(arg=='')
		{
		document.getElementById('mobile_error').value="   * Mobile Number Required.";
		}
		else if(arg!="")
			{
		
				if(arg.length==10)
				{
					ans=mob_no(arg);	
					if(ans=="invalid")
					{	
						document.getElementById('mobile_error').value="   * Mobile Number Is Not Valid.";
					}
					else
					{
						document.getElementById('mobile_error').value="";	
					}
				}
				else
				{
					ans=mob_no(arg);	
					if(ans=="invalid")
					{	
						document.getElementById('mobile_error').value="   * Mobile Number Is Not Valid.";
					}	
					else if(arg.length!=10)
					{
						document.getElementById('mobile_error').value="   * Mobile No. Must Have 10 Digit.";
					}											
				}
			}		
	}	
}
function mob_no(num_st)
{
var zc,iz,k;
	for(k=0;k<num_st.length;k++)
	{
		zc=num_st.substr(k,1);
		iz=str_num.indexOf(zc,0);

		if(iz == -1)
		{
			return "invalid";
		}		
	}
}
function checkaddress()
{
var adrs;
adrs=document.getElementById('reg_address').value;
adrs=adrs.replace(/^\ +/,"").replace(/\ +$/,"");
document.getElementById('reg_address').value=adrs;
	if(adrs=='')
	{
	document.getElementById('address_error').value="   * Address Required."
	}
	else
	{
	document.getElementById('address_error').value=""	
	}	
}
function checkemail()
{
var mailid;
mailid=document.getElementById('reg_email').value;
mailid=mailid.replace(/^\ +/,"").replace(/\ +$/,"");
document.getElementById('reg_email').value=mailid;
	if(mailid=='' || mailid=='null')
	{
		document.getElementById('email_error').value="   * E-Mail ID Required."
	}
	else
	{

		var at="@"
		var dot="."
		var lat=mailid.indexOf(at)
		var lstr=mailid.length
		var ldot=mailid.indexOf(dot)
		if (mailid.indexOf(at)==-1)
		{
		document.getElementById('email_error').value="   * Invalid E-Mail ID."
		}
		else if (mailid.indexOf(at)==-1 || mailid.indexOf(at)==0 || mailid.indexOf(at)==lstr)
		{
		document.getElementById('email_error').value="   * Invalid E-Mail ID."
		}
		else if (mailid.indexOf(dot)==-1 || mailid.indexOf(dot)==0 || mailid.indexOf(dot)==lstr)
		{
    		document.getElementById('email_error').value="   * Invalid E-Mail ID."		
		}
		else if (mailid.indexOf(at,(lat+1))!=-1)
		{
		document.getElementById('email_error').value="   * Invalid E-Mail ID."
		}
		else if (mailid.substring(lat-1,lat)==dot || mailid.substring(lat+1,lat+2)==dot)
		{
		document.getElementById('email_error').value="   * Invalid E-Mail ID."
		}
		else if (mailid.indexOf(dot,(lat+2))==-1)
		{
		document.getElementById('email_error').value="   * Invalid E-Mail ID."
		}
		else if (mailid.indexOf(" ")!=-1)
		{
		document.getElementById('email_error').value="   * Invalid E-Mail ID."
		}		
		else
		{
		document.getElementById('email_error').value=""
		}
	}
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
function selfSubmit()
{
	document.getElementById('register_frm').submit();	
}
</script>
<% int p=0;%>
<%Connection mycon=null; %>
<%
        String s_id=request.getParameter("sid");
        out.println("<script language=JavaScript>");
        out.println("var z=0;");
        out.println("function course(item){");
        out.println("opt=new Option();");
        out.println("document.getElementById('sid').options[z]=opt;");        
        out.println("document.getElementById('sid').options[z].text=item;");
        out.println("document.getElementById('sid').options[z].value=item;");
        out.println("if(document.getElementById('sid').options[z].text==\""+s_id+"\"){");
        out.println("document.getElementById('sid').options[z].selected=true;}");
        out.println("z++;}");
        out.println("function starter(){");
        out.println("course("+"'"+"--------------Select One-------------"+"'"+");");
        %>

<%
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String data="",k="";
			stt=mycon.createStatement();
			ResultSet rs=stt.executeQuery("select sid from student_master");
			
			while(rs.next())
			{
    		k=rs.getString("sid");
    	out.println("course("+"'"+k+"'"+");");    	
    	}
		}
        catch(Exception e)
        {
    	out.println(e.toString());
		}        
		finally
		{
			
		}
		%>

<%
        out.println("}");
        %>
<%
        out.println("</script>");
        %>
</head>
<%
String st=request.getParameter("common");
String fname=request.getParameter("fname");
String iname=request.getParameter("iname");
String sname=request.getParameter("sname");
String birth=request.getParameter("birth");
String gender=request.getParameter("gender");
String mobile=request.getParameter("mobile");
String country=request.getParameter("country");
String state=request.getParameter("state");
String city=request.getParameter("city");
String address=request.getParameter("address");
String zipcode=request.getParameter("zipcode");
String email=request.getParameter("email");
String qlf=request.getParameter("qlf");
String course=request.getParameter("course");

%>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
  <div class="container">
    <h1><a href="AdminStudentUpdate.jsp">Student's site</a></h1>
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
    <form action="" onSubmit="srch()" id="search-form">
      <fieldset>
        <div class="rowElem">
          <input type="text" id="srchkey">
          <a
	href="#" class="srch" onClick="srchbyfun()">Search</a></div>
      </fieldset>
    </form>
  </div>
</header>
<div class="container"><!-- aside --> <aside>
<h3>&nbsp;</h3>
<h3>Categories</h3>
<ul class="categories">
	<li><span><a href="/VcsTest/ServletStudentUpdate" onClick="">Pending
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
<fieldset><legend>
<caption><font>
<h4>Student Updation</h4>
</font></caption>
</legend>
<dd><br>
<form name="register_frm" method="post"
	action="/VcsTest/ServletStudentUpdate" id="register_frm">
<table name="rgst" align="center">
	<tr>
		<td style="color: #a52a2a">&nbsp;</td>
		<td colspan="2"><input type="hidden" name="q_role" value="stu">
		<input type="text" id="total_error" value="<% if(st!=null){out.println(st);} %>" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; height: 14px; font-family: Trebuchet MS;"
			readonly></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td><span style="color: #a52a2a">Student Id:</span></td>
		<td colspan="2"><select name="sid" tabindex="1" id="sid"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="selfSubmit();">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">First Name:</td>
		<td colspan="2"><input type="text" name="s_fname" id="reg_fname"
			tabindex="2"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(fname!=null){out.println(fname);}else{out.println("First Name");} %>"
			OnFocus="if(this.value=='First Name'){this.value=''}"
			OnBlur="changetext(this.form,'fname')"></td>
		<td width="100"><input type="text" id="fname_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Middle Name:</td>
		<td colspan="2"><input type="text" name="s_iname" id="reg_mname"
			tabindex="3"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(iname!=null){out.println(iname);}else{out.println("Middle Name");} %>"
			OnFocus="if(this.value=='Middle Name'){this.value=''}"
			OnBlur="changetext(this.form,'mname')"></td>
		<td width="100"><input type="text" id="mname_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Surname:</td>
		<td colspan="2"><input type="text" name="s_surname"
			id="reg_sname" tabindex="4"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(sname!=null){out.println(sname);}else{out.println("Surname");} %>"
			onFocus="if(this.value=='Surname'){this.value=''}"
			onBlur="changetext(this.form,'sname')"></td>
		<td width="100"><input type="text" id="sname_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Birthdate:</td>
		<td colspan="2"><input type="text" name="birth" id="birth"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(birth!=null){out.println(birth);}else{out.println("Birthdate");} %>"
			readonly></td>
		<td width="100"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Gender:</td>
		<td colspan="2"><input type="text" name="gender" id="gender"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(gender!=null){out.println(gender);}else{out.println("Gender");} %>"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Mobile No:</td>
		<td colspan="2"><input type="text" name="mobileno"
			id="reg_mobile" maxlength="10" tabindex="7"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(mobile!=null){out.println(mobile);}else{out.println("Mobile No.");} %>"
			OnFocus="if(this.value=='Mobile No.'){this.value=''}"
			OnBlur="checknum(this.form,'mob')"></td>
		<td width="100"><input type="text" id="mobile_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Country:</td>
		<td colspan="2"><input type="text" name="country" id="country"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(country!=null){out.println(country);}else{out.println("Country");} %>"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">State:</td>
		<td colspan="2"><input type="text" name="state" id="reg_state"
			tabindex="6"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(state!=null){out.println(state);}else{out.println("State");} %>"
			OnFocus="if(this.value=='State'){this.value=''}"
			OnBlur="changetext(this.form,'state')"></td>
		<td width="100"><input type="text" id="state_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">City:</td>
		<td colspan="2"><input type="text" name="city" id="reg_city"
			tabindex="7"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(city!=null){out.println(city);}else{out.println("City");} %>"
			OnFocus="if(this.value=='City'){this.value=''}"
			OnBlur="changetext(this.form,'city')"></td>
		<td width="100"><input type="text" id="city_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Address:</td>
		<td colspan="2"
			style="background: url(../images/roundedbig.gif) no-repeat top left;">
		<textarea name="address" id="reg_address" tabindex="8"
			style="padding: 3px 5px 2px 7px; border: 0px solid; overflow: auto; background: url(images/roundedbig1.gif) no-repeat top left; height: 79px; width: 215px;"
			OnFocus="if(this.value=='Address'){this.value=''}"
			OnBlur="checkaddress()"><%if(address!=null){out.println(address);}else{out.println("Address");}%></textarea><br>
		&nbsp;</td>
		<td width="100"><input type="text" id="address_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Zipcode:</td>
		<td colspan="2"><input type="text" name="zipcode" id="reg_zip"
			tabindex="9"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(zipcode!=null){out.println(zipcode);}else{out.println("Zipcode");} %>"
			OnFocus="if(this.value=='Zipcode'){this.value=''}"
			OnBlur="checknum(this.form,'zip')"></td>
		<td width="100"><input type="text" id="zipcode_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">E-Mail:</td>
		<td colspan="2"><input type="text" name="email" id="reg_email"
			tabindex="10"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(email!=null){out.println(email);}else{out.println("E-Mail");} %>"
			OnFocus="if(this.value=='E-Mail'){this.value=''}"
			OnBlur="checkemail()"></td>
		<td width="100"><input type="text" id="email_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Qualification:</td>
		<td colspan="2"><input type="text" name="qualification"
			id="qualification"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(qlf!=null){out.println(qlf);}else{out.println("Qualification");} %>"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Course:</td>
		<td colspan="2"><input type="text" name="reqcourse"
			id="reqcourse"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="<%if(course!=null){out.println(course);}else{out.println("Course");} %>"
			readonly>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 13px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="17" class="fright" OnClick="registervalid()">Update</a></td>
		<td></td>
		<td width="100"></td>
	</tr>

</table>
</form>
<br>
<br>
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
