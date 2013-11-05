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
<title>Course Creation</title>
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
String st;
st=request.getParameter("common");
%>
<%Connection mycon=null; %>
<%
        out.println("<script language=JavaScript>");
        out.println("var z=0;");
        out.println("function course(item){");
        out.println("opt=new Option();");
        out.println("document.getElementById('cc_alc').options[z]=opt;");
        out.println("document.getElementById('cc_alc').options[z].text=item;");
        out.println("document.getElementById('cc_alc').options[z].value=item;");
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
var cnt=0,a_cnt=0,m_cnt=0;
var a=new Array();
var set1,set2;

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
                if(bday<now_day)
                    {
                        document.getElementById('bdate_error').value="   * Start Date Can't From Past.";
                    }
                    else
                    {
                        document.getElementById('bdate_error').value="";
                    }
            }
            else if(bmonth<now_month)
            {
                document.getElementById('bdate_error').value="   * Start Date Can't From Past."
            }
            else
            {
                  document.getElementById('bdate_error').value="";
            }
        }
        else if(byear>now_year)
        {
            document.getElementById('bdate_error').value="";
        }
        else if(byear<now_year)
        {
            document.getElementById('bdate_error').value="   * Start Date Can't From Past.";
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
                if(bday<now_day)
                    {
                        document.getElementById('bdate_error').value="   * Start Date Can't From Past.";
                    }
                    else
                    {
                        document.getElementById('bdate_error').value="";
                    }
            }
            else if(bmonth<now_month)
            {
                document.getElementById('bdate_error').value="   * Start Date Can't From Past."
            }
            else
            {
                  document.getElementById('bdate_error').value="";
            }
        }
        else if(byear>now_year)
        {
            document.getElementById('bdate_error').value="";
        }
        else if(byear<now_year)
        {
            document.getElementById('bdate_error').value="   * Start Date Can't From Past.";
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
                if(bday<now_day)
                    {
                        document.getElementById('bdate_error').value="   * Start Date Can't From Past.";
                    }
                    else
                    {
                        document.getElementById('bdate_error').value="";
                    }
            }
            else if(bmonth<now_month)
            {
                document.getElementById('bdate_error').value="   * Start Date Can't From Past."
            }
            else
            {
                  document.getElementById('bdate_error').value="";
            }
        }
        else if(byear>now_year)
        {
            document.getElementById('bdate_error').value="";
        }
        else if(byear<now_year)
        {
            document.getElementById('bdate_error').value="   * Start Date Can't From Past.";
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

function item_add()
{
var cname=document.getElementById('cc_course').value;
var des=document.getElementById('c_desc').value;
var subitm=document.getElementById('cc_subject').value;
var facitem=document.getElementById('cc_alc').value;
var opt,facopt,smax;
cnt=document.getElementById('cc_subjects').length;
document.getElementById('crs_error').value="";
document.getElementById('desc_error').value="";
document.getElementById('sub_error').value="";
smax=document.getElementById('cc_nosub').value;
if(cnt<smax)
        {
            if(cname !="" && subitm !="" && des!="")
            {                
		document.getElementById('cc_course').disabled=true;
                opt=new Option();
		document.getElementById('cc_subjects').options[cnt]=opt;
		document.getElementById('cc_subjects').options[cnt].text=subitm;
		document.getElementById('cc_subjects').options[cnt].value=subitm;

                facopt=new Option();
                document.getElementById('cc_fac').options[cnt]=facopt;
		document.getElementById('cc_fac').options[cnt].text=facitem;
		document.getElementById('cc_fac').options[cnt].value=facitem;
		cnt++;
            }
            else if(cname=="" && subitm=="" && des=="")
            {
                   document.getElementById('crs_error').value="   * Course Name Required.";
                   document.getElementById('sub_error').value="   * Subject Name Required.";
                   document.getElementById('desc_error').value="   * Description Required.";
            }
            else if(cname=="" && subitm=="")
            {
                   document.getElementById('crs_error').value="   * Course Name Required.";
                   document.getElementById('sub_error').value="   * Subject Name Required.";
            }
            else if(subitm=="" && des=="")
            {
                   document.getElementById('sub_error').value="   * Subject Name Required.";
                   document.getElementById('desc_error').value="   * Description Required.";
            }
            else if(cname=="" && des=="")
            {
             document.getElementById('crs_error').value="   * Course Name Required.";            
             document.getElementById('desc_error').value="   * Description Required.";
            }
            else if(cname=="")
            {
            	   document.getElementById('crs_error').value="   * Course Name Required.";	                  
            }
            else if(subitm=="")
            {
                   document.getElementById('sub_error').value="   * Subject Name Required.";
                
            }
            else if(des=="")
            {
                   document.getElementById('desc_error').value="   * Description Required.";
            }            
	}	
		document.getElementById('cc_subject').value="";
                document.getElementById('cc_subject').focus();
}
function item_rem()
{	
	var indx=document.getElementById('cc_subjects').selectedIndex;        

		if(indx>=0)
		{
		document.getElementById('cc_subjects').options[indx]=null;
                document.getElementById('cc_fac').options[indx]=null;
		}
		else
		{
		
		}
        var len=document.getElementById('cc_subjects').options.length;
                if(len>=1)
                {                 
                 document.getElementById('cc_subjects').options[0].selected=true;
                }
                cnt=parseInt(document.getElementById('cc_subjects').length);
	if(cnt==0)
	{
	document.getElementById('cc_course').disabled=false;
	}
	else
	{

	}
}
function setter1()
{    
    set1=document.getElementById('cc_subjects').selectedIndex;    
    document.getElementById('cc_fac').options[set1].selected=true;
}
function setter2()
{

   set2=document.getElementById('cc_fac').selectedIndex;
   document.getElementById('cc_subjects').options[set2].selected=true;
}
function resetall(lbl)
{
    var rst,i;
    rst=document.getElementById('cc_subjects').options.length;    
    for(i=0;i<rst;i++)
    {        
        document.getElementById('cc_subjects').options[0]=null;
        document.getElementById('cc_fac').options[0]=null;
    }
    if(lbl=="rst")
    {
        document.getElementById('cc').reset();
    }
}
function finalcourse()
{
var course,nos,cday,cmonth,tsub,dy,dm,cyear,cerror,serror,derror,j,k;
course=document.getElementById('cc_course').value;
desc=document.getElementById('c_desc').value;
nos=parseInt(document.getElementById('cc_nosub').value);
tsub=parseInt(document.getElementById('cc_subjects').options.length);
cday=document.getElementById('reg_bday').value;
cyear=document.getElementById('reg_byear').value;
dy=document.getElementById('cc_year').value;
dm=document.getElementById('cc_month').value;
cerror=document.getElementById('crs_error').value;
serror=document.getElementById('sub_error').value;
derror=document.getElementById('bdate_error').value;
deserror=document.getElementById('desc_error').value;
var sub,fac;
if(course!="" && desc!="" && nos==tsub && (cday!="" || cday!="Day")&&(cyear!="" || cyear!="Year")&& (dy!="00" || dm!="00") && cerror=="" && deserror=="" && serror=="" && derror=="")
    {
        document.getElementById('totsub').value=tsub;        
        for(j=0;j<tsub;j++)
        {
        sub="s"+(j+1);
        fac="f"+(j+1);
        document.getElementById(sub).value=document.getElementById('cc_subjects').options[j].value;
        document.getElementById(fac).value=document.getElementById('cc_fac').options[j].value;
        }
	document.getElementById('cc_course').disabled=false;
        document.getElementById('cc').submit();
    }
    else
    {        
        document.getElementById('cc_error').value=" * Please Fill Every Fields Correctly.";
        dyn_blnkerror();
    }
}
function search()
            {   var search;
                search=document.getElementById('srchkey').value;
                if(search!="")
                {
                window.open("http://www.google.co.in/search?q="+search+"&cad=b&cad=h");
                }
                else
                {
                }
            }
            function searchviafunction()
            {
                search();
            }
function rem_spc(lbl)
{
    var crs,sub;
    if(lbl=="course")
    {
        crs=document.getElementById('cc_course').value;
        crs=crs.replace(/^\ +/,"").replace(/\ +$/,"");
        document.getElementById('cc_course').value=crs;
    }
    else if(lbl=="subject")
    {
        sub=document.getElementById('cc_subject').value;
        sub=sub.replace(/^\ +/,"").replace(/\ +$/,"");
        document.getElementById('cc_subject').value=sub;
    }
}
var timer,flag_color=false;
clearTimeout(timer);
    function dyn_blnkerror()
    {
	if(flag_color==true)
	{
	document.getElementById('cc_error').style.color="green";
	flag_color=false;
	}
	else
	{
	document.getElementById('cc_error').style.color="red";
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
</script>
</head>
<body id="page1" OnLoad="starter()">
<div class="wrap"><!-- header --> <header>
<div class="container">
<div class="container">
<h1><a href="AdminCourseCreate.jsp">Student's site</a></h1>
<nav>
<ul>
	<li><a href="AdminHome.jsp" class="m1">STUDENT</a></li>
	<li><a href="AdminFaculty.jsp" class="m2">FACULTY</a></li>
	<li><a href="AdminMgt.jsp" class="m3">MANAGEMENT</a></li>
	<li class="current"><a href="AdminCourses.jsp" class="m4">COURSE</a></li>
	<li><a href="AdminBlogs.jsp" class="m5">BLOGS</a></li>
	<li><a href="AdminReports.jsp" class="m6">REPORTS</a></li>
</ul>
</nav>
<form action="" id="search-form" onSubmit="search()">
<fieldset>
<div class="rowElem"><input type="text" id="srchkey"> <a
	href="#" class="srch" onClick="searchviafunction()">Search</a></div>
</fieldset>
</form>
</div>
</div>
</header>
<div class="container"><!-- aside --> <aside>
<h3>&nbsp;</h3>
<h3>Categories</h3>
<ul class="categories">
	<li><span><a href="AdminCourseCreate.jsp">Create Course</a></span></li>
	<li><span><a href="AdminCourseUpdate.jsp">Edit Course</a></span></li>
	<li><span><a href="AdminCourseDelete.jsp">Delete Course</a></span></li>
	<li class="last"><span><a href="/VcsTest/CourseReport">Reports</a></span></li>
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
<b><a href="AdminCommentCreate.jsp">Comment</a></b><b></div>
<p align="right"><b>
  Hi &nbsp;<%out.println(session.getAttribute("uid").toString());%>
  <a href="/VcsTest/ServletSignout" title="Log Out">Log Out</a> </b></p>

<!-- content --> <section id="content">
<fieldset><legend><font>
<h4>Course Creation</h4></legend>
<dd><br>
<form name="cc" id="cc" method="post"
	action="/VcsTest/ServletAdminCourseCreate">
<table name="frm_frm" id="frm_frm" align="center" width="82%">
	<tr>
		<td width="59"></td>
		<td colspan="3"><input type="text" name="cc_error" size="40"
			tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			id="upass_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet;"
			readonly></td>
		<td height="30" colspan="2" style="color: #a52a2a;">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="100%" style="color: #a52a2a;" height="30">Course Name:</td>
		<td colspan="3"><input type="text" name="coursename"
			id="cc_course" tabindex="1"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			OnFocus="textbackcolor('cc_course')"
			OnBlur="textback('cc_course');rem_spc('course');"></td>
		<td colspan="2"><input type="text" name="crs_error" size="30"
			tabindex="400" id="crs_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	<tr>
		<td width="100%" style="color: #a52a2a;" height="89">Description:</td>
		<td colspan="3"
			style="background: url(../images/roundedbig.gif) no-repeat top left;"><textarea
			name="cdesc" id="c_desc" tabindex="2"
			style="color: black; padding: 3px 5px 2px 7px; border: 0px solid; overflow: auto; background: url(images/roundedbig1.gif) no-repeat top left; height: 79px; width: 215px;"></textarea>
		</td>
		<td colspan="2"><input type="text" name="desc_error" size="30"
			tabindex="400" id="desc_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" style="color: #a52a2a" height="30">No.Of
		Subject:</td>
		<td colspan="3"><select name="total_subject" id="cc_nosub"
			tabindex="3"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="resetall('nos')">
			<option name="" value="01">01&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option name="" value="02">02</option>
			<option name="" value="03">03</option>
			<option name="" value="04">04</option>
			<option name="" value="05">05</option>
			<option name="" value="06">06</option>
			<option name="" value="07">07</option>
			<option name="" value="08">08</option>
			<option name="" value="09">09</option>
			<option name="" value="10">10</option>
		</select></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" style="color: #a52a2a;" height="30">Subject
		Name:</td>
		<td colspan="3"><input type="text" name="subject" id="cc_subject"
			maxlength="23" tabindex="4"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			OnFocus="textbackcolor('cc_subject')"
			OnBlur="textback('cc_subject');rem_spc('subject');"></td>
		<td colspan="2"><input type="text" name="sub_error" size="30"
			tabindex="400" id="sub_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td width="100%" style="color: #a52a2a;" height="30">Allocate To:</td>
		<td colspan="5"><select name="tofid" id="cc_alc" tabindex="5"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td width="52"><input type="button" name="cc_add" id="cc_add"
			tabindex="6" accesskey="a" value="   Add   " onClick="item_add()">
		&nbsp;</td>
		<td width="82"></td>
		<td>&nbsp;&nbsp; <input type="button" name="cc_rem" id="cc_rem"
			tabindex="7" accesskey="r" value="Remove" onClick="item_rem()">
		</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td><span style="color: #a52a2a;">Subjects:</span></td>
		<td colspan="5"><select name="cc_subjects" id="cc_subjects"
			size="5"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="setter1()">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td><span style="color: #a52a2a;">Faculty:</span></td>
		<td colspan="5"><select name="cc_fac" id="cc_fac" size="5"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			OnChange="setter2()">
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td style="color: #a52a2a;" height="30">Start Date:</td>
		<td colspan="3"><input type="text" name="sday" id="reg_bday"
			maxlength="2" tabindex="8"
			style="padding: 3px 5px 2px 7px; border: 0px solid; background: url(../images/roundedbirth.gif) no-repeat top left; height: 17px; width: 47px;"
			value="Day" OnFocus="focus_day()" OnBlur="b_day()"> <select
			name="smonth" id="reg_bmonth" tabindex="9"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;"
			onChange="b_month()" OnBlur="b_month()" OnFocus="b_month()">
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
		</select> &nbsp; <input type="text" name="syear" id="reg_byear" tabindex="10"
			style="padding: 3px 5px 2px 7px; border: 0px solid; background: url(../images/roundedbirth.gif) no-repeat top left; height: 17px; width: 47px;"
			maxlength="4" value="Year" OnFocus="focus_year()" OnBlur="b_year()"></td>
		<td colspan="2"><input type="text" name="bdate_error" size="30"
			tabindex="400" id="bdate_error"
			style="background: url(../images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><span style="color: #a52a2a;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Year</span></td>
		<td colspan="2"><span style="color: #a52a2a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Month</span></td>
		<td width="31">&nbsp;</td>
		<td width="180">&nbsp;</td>
	</tr>
	<tr>
		<td style="color: #a52a2a;" height="30">Duration:</td>
		<td colspan="3"><select name="duryear" id="cc_year" tabindex="11"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
			<option value="00">00&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>

		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select name="durmonth" id="cc_month" tabindex="12"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
			<option value="00">00&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
			<option value="10">10</option>
			<option value="11">11</option>

		</select></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"></td>
		<td align="left">&nbsp;</td>
		<td colspan="2" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"></td>
		<td align="left">&nbsp;</td>
		<td colspan="2" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"></td>
		<td align="left">&nbsp;</td>
		<td colspan="2" align="left">&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"></td>
		<td align="left">&nbsp;</td>
		<td colspan="2" align="left">&nbsp;</td>
	</tr>
	<%
  int u,s;
  out.println("<input type=\"hidden\" name=\"totsub\" value=\"\">");
  out.println("\n");
  for(u=1;u<=10;u++)
  {
      out.println("<input type=\"hidden\" name=\"s"+u+"\""+" id=\"s"+u+"\">");
  }
  out.println("\n");
  for(s=1;s<=10;s++)
  {
      out.println("<input type=\"hidden\" name=\"f"+s+"\""+" id=\"f"+s+"\">");
  }
  %>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 3px 10px 0 10px; height: 20px; border: 1px solid #a52a2a;"
			tabindex="13" class="fright" OnClick="resetall('rst')">Reset</a> </span></td>
		<td align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 3px 10px 0 10px; height: 20px; border: 1px solid #a52a2a;"
			tabindex="14" class="fright" OnClick="finalcourse()">Create</a> </span></td>
		<td colspan="2" align="left"></td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"></td>
		<td width="133" align="left"></td>
		<td width="31" align="left"></td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" valign="bottom"></td>


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
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="5"></td>
	</tr>
</table>

</form>
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
