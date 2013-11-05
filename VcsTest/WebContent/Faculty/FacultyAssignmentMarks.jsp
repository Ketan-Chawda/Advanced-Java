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
<title>Assignment Marks</title>
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
function final_mark()
{
    var mark,day,year,sid;
    var m_error,date_error;
    
    sid=document.getElementById('a_id').value;
    mark=document.getElementById('a_mark').value;
    day=document.getElementById('reg_bday').value;
    year=document.getElementById('reg_byear').value;
    
    m_error=document.getElementById('ass_error').value;
    date_error=document.getElementById('bdate_error').value;

    if(mark!="" && (day!="" && day!="Day")&&(year!="" && year!="Year"))
    {
    	if(sid=="---------------Select ID--------------")
    	{
    	document.getElementById('total_error').value="        * Please Select ID.";
        dyn_blnkerror();
    	}
    	else
    	{
    	document.getElementById('ass_marks').action="/VcsTest/ServletFacultyAssignmentStudentMarks";
        document.getElementById('ass_marks').submit();
        }
    }
    else
    {
        document.getElementById('total_error').value=" * Please Fill Every Fields Correctly.";
        dyn_blnkerror();
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
 var act;
 if(lbl=="a_mark")
 {
     act=document.getElementById(lbl).value;
     act=act.replace(/^\ +/,"").replace(/\ +$/,"");
     document.getElementById(lbl).value=act;
     if(act=="")
     {
         document.getElementById('ass_error').value="   * Marks Required."
     }
     else if(act!="")
     {
         if((act%2!=1)&&(act%2!=0))
         {
             document.getElementById('ass_error').value="   * Marks Not Valid."
         }
         else
         {
             document.getElementById('ass_error').value="";
         }
     }
     else
     {
             document.getElementById('ass_error').value="";
     }
 } 
}
var str_num="0123456789";
var bday="",bmonth="",byear="",f_date="",yearcnt=0,flagyear=false,leap=false,yflg=false;
var now=new Date();
var now_day=now.getDate();
var now_month=now.getMonth()+1;
var now_year=now.getFullYear();
function b_day()
{
var b_ans;
	bday=document.getElementById('reg_bday').value;
	byear=document.getElementById('reg_byear').value;
	bday=bday.replace(/^\ +/,"").replace(/\ +$/,"");
	bmonth=document.getElementById('reg_bmonth').value;
	if((bday.length==1 && (bday-1)>=0) || bday=="0"){bday="0"+bday;}
        if(bday=="00"){bday="01";}
	document.getElementById('reg_bday').value=bday;
	if(bday=='')
	{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
	}
	else if(bday<32 && bday>0)
	{
		if(byear=="")
		{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
		}
		else if(flagyear==true)
		{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
		}
		else
		{
		document.getElementById('bdate_error').value="";
		}
	}
	else
	{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
	}
	if(yearcnt==0)
	{
		if(bday=="" || (bday>0 && bday<32))
		{
			if(byear=="")
			{
			document.getElementById('bdate_error').value="   * Please Check Your Date.";
			}
		}
	}
if(byear>0)
{
		if((byear % 4 == 0) && bday==29 && bmonth==2)
		{
		leap=true;
		}
		else if((byear % 4 == 0) && bday>=29 && bmonth==2)
		{
		document.getElementById('reg_bday').value="29";
		leap=true;
		}
		else if((byear % 4 != 0) && bday>=29 && bmonth==2)
		{
		document.getElementById('reg_bday').value="28";
		leap=false;
		}
}
b_month();
}
function checkdy(lbl,b_str)
{
var dy="1234567890",i;
var dy_c,dy_i;

	for(i=0;i<b_str.length;i++)
	{
		dy_c=b_str.substr(i,1);
		dy_i=dy.indexOf(dy_c,0);
		if(dy_i == -1)
		{
			return lbl+" invalid";
		}
	}

}
function b_month()
{
bday=document.getElementById('reg_bday').value;
bmonth=document.getElementById('reg_bmonth').value;
byear=document.getElementById('reg_byear').value;
     if(bday<=31 && bday>0)
     {
	if(bmonth==4 && bday==31)
	{
	document.getElementById('reg_bday').value=30;
	}
	else if(bmonth==6 && bday==31)
	{
	document.getElementById('reg_bday').value="30";
	}
	else if(bmonth==9 && bday==31)
	{
	document.getElementById('reg_bday').value="30";
	}
	else if(bmonth==11 && bday==31)
	{
	document.getElementById('reg_bday').value="30";
	}

	else if(bmonth>=1 && bday=="Day")
	{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
	}
     }
     else
         {
               document.getElementById('bdate_error').value="   * Please Check Your Date.";
         }
	if((byear % 4 == 0) && bday==29 && bmonth==2)
	{
		leap=true;
	}
	else if((byear % 4 == 0) && bday>=29 && bmonth==2)
	{
		document.getElementById('reg_bday').value="29";
		leap=true;
	}
	else if((byear % 4 != 0) && bday>=29 && bmonth==2)
	{
		document.getElementById('reg_bday').value="28";
		leap=false;
	}
        else
            {}

    if(yflg==true)
    {
        if((bday !="" && byear!="")&&((bday%2==1 || bday%2==0)&&(byear%2==1 || byear%2==0))&&((bday<=31 && bday>0)))
        {
        if(byear==now_year)
        {
            if(bmonth==now_month)
            {
                if(bday>now_day)
                    {
                        document.getElementById('bdate_error').value="   * Date Can't To Future.";
                    }
                    else
                    {
                        document.getElementById('bdate_error').value="";
                    }
            }
            else if(bmonth>now_month)
            {
                document.getElementById('bdate_error').value="   * Date Can't To Future."
            }
            else
            {
                  document.getElementById('bdate_error').value="";
            }
        }
        else if(byear<now_year)
        {
            document.getElementById('bdate_error').value="";
        }
        else if(byear>now_year)
        {
            document.getElementById('bdate_error').value="   * Date Can't To Future.";
        }
        }
        else
            {
                document.getElementById('bdate_error').value="   * Please Check Your Date.";
            }
    }
}
function b_year()
{
bday=document.getElementById('reg_bday').value;
bmonth=document.getElementById('reg_bmonth').value;
byear=document.getElementById('reg_byear').value;
	if(bday=='' || (bday>0 && bday<32))
	{
		if(byear=='')
		{
			document.getElementById('bdate_error').value="   * Please Check Your Date."
		}
		else if(byear.length==4 && byear>1)
		{
			if((byear % 4 == 0) && bday=="29" && bmonth==2)
			{
				leap=true;
			}
			else if((byear % 4 == 0) && bday>=29 && bmonth==2)
			{
				document.getElementById('reg_bday').value="29";
				leap=true;
			}
			else if((byear % 4 != 0) && bday>=29 && bmonth==2)
			{
				document.getElementById('reg_bday').value="28";
				leap=false;
			}
			document.getElementById('bdate_error').value=""
			flagyear=false;
		}
		else
		{
			document.getElementById('bdate_error').value="   * Please Check Your Date."
			flagyear=true;
		}
	}

    if(yflg==true)
    {
        if((bday !="" && byear!="")&&((bday%2==1 || bday%2==0)&&(byear%2==1 || byear%2==0))&&((bday<=31 && bday>0)))
        {
        if(byear==now_year)
        {
            if(bmonth==now_month)
            {
                if(bday>now_day)
                    {
                        document.getElementById('bdate_error').value="   * Date Can't To Future.";
                    }
                    else
                    {
                        document.getElementById('bdate_error').value="";
                    }
            }
            else if(bmonth>now_month)
            {
                document.getElementById('bdate_error').value="   * Date Can't To Future."
            }
            else
            {
                  document.getElementById('bdate_error').value="";
            }
        }
        else if(byear<now_year)
        {
            document.getElementById('bdate_error').value="";
        }
        else if(byear>now_year)
        {
            document.getElementById('bdate_error').value="   * Date Can't To Future.";
        }
        }
        else
            {
                document.getElementById('bdate_error').value="   * Please Check Your Date.";
            }
    }
}
function focus_day()
{
var bday,byear;
bday=document.getElementById('reg_bday').value;
byear=document.getElementById('reg_byear').value;
	if(bday == 'Day')
	{
	document.getElementById('reg_bday').value='';
	}
	else if(bday=='' && byear=='')
	{
	document.getElementById('bdate_error').value="   * Please Check Your Date.";
	}

    if(yflg==true)
    {
        if((bday !="" && byear!="")&&((bday%2==1 || bday%2==0)&&(byear%2==1 || byear%2==0))&&((bday<=31 && bday>0)))
        {
        if(byear==now_year)
        {
            if(bmonth==now_month)
            {
                if(bday>now_day)
                    {
                        document.getElementById('bdate_error').value="   * Date Can't To Future.";
                    }
                    else
                    {
                        document.getElementById('bdate_error').value="";
                    }
            }
            else if(bmonth>now_month)
            {
                document.getElementById('bdate_error').value="   * Date Can't To Future."
            }
            else
            {
                  document.getElementById('bdate_error').value="";
            }
        }
        else if(byear<now_year)
        {
            document.getElementById('bdate_error').value="";
        }
        else if(byear>now_year)
        {
            document.getElementById('bdate_error').value="   * Date Can't To Future.";
        }
        }
        else
            {
                document.getElementById('bdate_error').value="   * Please Check Your Date.";
            }
    }
}
function focus_year()
{
yearcnt=1;
yflg=true;
	bday=document.getElementById('reg_bday').value;
	byear=document.getElementById('reg_byear').value;
	if(byear=="Year"){document.getElementById('reg_byear').value="";}
	if(bday=="Day")
	{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
	}
	else if(b_day=='')
	{
		if(b_year=='')
		{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
		}
		else
		{
		document.getElementById('bdate_error').value="   * Please Check Your Date.";
		}
	}
}
function selfsubmit()
{
document.getElementById('ass_marks').submit();
}
</script>
</head>
<%
String st="",item="",d_sid="",marks="";
sid="";
int it=0;
st=request.getParameter("common");
sid=request.getParameter("sid");
item=request.getParameter("items");
marks=request.getParameter("marks");
if(sid!=null)
{
	d_sid=sid;
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
        out.println("document.getElementById('a_id').options[z]=opt;");
        out.println("document.getElementById('a_id').options[z].text=item;");
        out.println("document.getElementById('a_id').options[z].value=item;");
        out.println("if(document.getElementById('a_id').options[z].text==\""+d_sid+"\"){");
        out.println("document.getElementById('a_id').options[z].selected=true;}");
        out.println("z++;}");
        out.println("function starter(){");
        %>

<%
        out.println("course('---------------Select ID--------------');");
        try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
			mycon=DriverManager.getConnection("jdbc:odbc:myvcs");			
			Statement stt;
			String data="";
			stt=mycon.createStatement();
			ResultSet rs=stt.executeQuery("select aid from assign_allocation");
			
			while(rs.next())
			{
				data=rs.getString("aid");
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
		if(it>0)
		{
        out.println("sub();");
		}
        out.println("}");
        %>

<%        
        out.println("var p=0;");
        out.println("function subs(item1){");        
        out.println("opt1=new Option();");
        out.println("document.getElementById('s_id').options[p]=opt1;");
        out.println("document.getElementById('s_id').options[p].text=item1;");
        out.println("document.getElementById('s_id').options[p].value=item1;");        
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
    <h1><a href="FacultyAssignmentMarks.jsp">Student's site</a></h1>
    <nav>
      <ul>
        <li><a href="FacultyHome.jsp" class="m1">LECTURE</a></li>
        <li class="current"><a href="FacultyAssignment.jsp" class="m2">ASSIGNMENT</a></li>
        <li><a href="FacultyQuiz.jsp" class="m3">QUIZ</a></li>
        <li><a href="FacultyNotice.jsp" class="m5">NOTICE</a></li>
        <li><a href="FacultyBlogs.jsp" class="m4">BLOGS</a></li>
        <li><a href="FacultyLibrary.jsp" class="m6">LIBRARY</a></li>
      </ul>
    </nav>
    <form action="" id="search-form" onSubmit="srch()">
      <fieldset>
        <div class="rowElem">
          <input type="text" id="srchkey">
          <a
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
	<li><span><a href="FacultyAssignmentCreate.jsp">Create
	Assignment</a></span></li>
	<li><span><a href="/VcsTest/AssignSubmittedReport"
		target="_blank">Submitted Assignment</a></span></li>
	<li><span><a href="FacultyAssignmentMarks.jsp">Assignment
	Marks</a></span></li>
	<li><span><a href="FacultyAssignmentDelete.jsp">Delete
	Assignment</a></span></li>
	<li class="last"><span><a
		href="/VcsTest/AssignmentMarksReport">View
	Assignment Marks</a></span></li>
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
<h4>Assignment Marks</h4>
</font></legend>
<dd><br>
<form name="ass_marks" id="ass_marks" method="post"
	action="/VcsTest/AssignMark">
<table name="frm_frm" id="frm_frm" align="center" width="86%">

	<tr>
		<td width="146"></td>
		<td colspan="7"><input type="text" name="total_error"
			id="total_error" size="40" tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td width="146" style="color: #a52a2a">Assignment ID:</td>
		<td colspan="7" height="30"><select name="a_id" id="a_id"
			tabindex="1"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="selfsubmit()">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td width="146" style="color: #a52a2a">Student ID:</td>
		<td colspan="7" height="30"><select name="sid" id="s_id"
			tabindex="2"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td width="146" style="color: #a52a2a">Total Marks:</td>
		<td colspan="7" height="30"><input type="text" name="total_mark"
			id="total_mark" tabindex="4"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 218px;"
			value="<%if(marks!=null){out.println(marks);} %>" readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td width="146" style="color: #a52a2a">Assign Marks:</td>
		<td height="30" colspan="5"><input type="text"
			name="securedmarks" id="a_mark" tabindex="5"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 218px;"
			OnFocus="textbackcolor('a_mark')"
			OnBlur="check('a_mark');textback('a_mark')"></td>
		<td height="30" colspan="2"><input type="text" name="ass_error"
			size="30" tabindex="400" id="ass_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td width="146" style="color: #a52a2a">Submitted On:</td>
		<td height="30" colspan="5"><input type="text" name="sday"
			id="reg_bday" maxlength="2" tabindex="6"
			style="padding: 3px 5px 2px 7px; border: 0px solid; background: url(../images/roundedbirth.gif) no-repeat top left; height: 17px; width: 47px;"
			value="Day" OnFocus="focus_day()" OnBlur="b_day()"> <select
			name="smonth" id="reg_bmonth" tabindex="7"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			onChange="b_month()" OnBlur="b_month()">
			<option name="reg_bjan" value="01">January&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option name="reg_bfeb" value="02">February</option>
			<option name="reg_bmar" value="03">March</option>
			<option name="reg_bapr" value="04">April</option>
			<option name="reg_bmay" value="05">May</option>
			<option name="reg_bjun" value="06">June</option>
			<option name="reg_bjul" value="07">July</option>
			<option name="reg_baug" value="08">August</option>
			<option name="reg_bsep" value="09">September</option>
			<option name="reg_boct" value="10">October</option>
			<option name="reg_bnov" value="11">November</option>
			<option name="reg_bdec" value="12">December</option>
		</select> &nbsp; <input type="text" name="syear" id="reg_byear" tabindex="8"
			style="padding: 3px 5px 2px 7px; border: 0px solid; background: url(../images/roundedbirth.gif) no-repeat top left; height: 17px; width: 47px;"
			maxlength="4" value="Year" OnFocus="focus_year()" OnBlur="b_year()">
		</td>
		<td height="30" colspan="2"><input type="text" name="bdate_error"
			size="30" tabindex="400" id="bdate_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td></td>
		<td width="8"></td>
		<td width="57"></td>
		<td width="63"></td>
		<td width="102"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="9" class="fright" OnClick="final_mark()">Assign</a> </span></td>
		<td width="9"></td>
		<td width="33" align="left"></td>
		<td width="139" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="7"></td>
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

