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
<title>Faculty Creation</title>
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
<script langauge="JavaScript" src="js/crt-upd.js">
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
<body id="page1">
<div class="wrap"><!-- header --> <header>
  <div class="container">
    <h1><a href="AdminFacultyCreate.jsp">Student's site</a></h1>
    <nav>
      <ul>
        <li><a href="AdminHome.jsp" class="m1">STUDENT</a></li>
        <li class="current"><a href="AdminFaculty.jsp" class="m2">FACULTY</a></li>
        <li><a href="AdminMgt.jsp" class="m3">MANAGEMENT</a></li>
        <li><a href="AdminCourses.jsp" class="m4">COURSE</a></li>
        <li><a href="AdminBlogs.jsp" class="m5">BLOGS</a></li>
        <li><a href="AdminReports.jsp" class="m6">REPORTS</a></li>
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
	<li><span><a href="AdminFacultyCreate.jsp" onClick="">Create
	Faculty</a></span></li>
	<li><span><a href="AdminFacultyUpdate.jsp" onClick="">Edit
	Faculty Info</a></span></li>
	<li><span><a href="AdminFacultyDelete.jsp" onClick="">Delete
	Faculty</a></span></li>
	<li><span><a href="AdminFacultyToAllumini.jsp">Faculty
	To Alumni</a></span></li>
	<li><span><a href="/VcsTest/FacultyAlluminiReport">Faculty Lecture Info</a></span></li>
	<li><span><a href="/VcsTest/QuizReport">Faculty
	Quiz Info</a></span></li>
	<li class="last"><span><a href="/VcsTest/FacultyReport">Reports</a></span></li>
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
<fieldset><legend>
<caption><font>
<h4>Faculty Creation</h4>
</font></caption>
</legend>
<dd><br>
<form name="register_frm" method="post"
	action="/VcsTest/ServletFacultyRegistration" id="register_frm">
<table name="rgst" align="center">

	<tr>
		<td style="color: #a52a2a">&nbsp;</td>
		<td colspan="2"><input type="text" id="total_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; height: 14px; font-family: Trebuchet MS;"
			readonly></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">First Name:</td>
		<td colspan="2"><input type="text" name="f_fname" id="reg_fname"
			tabindex="1"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="First Name"
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
		<td colspan="2"><input type="text" name="f_iname" id="reg_mname"
			tabindex="2"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="Middle Name"
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
		<td colspan="2"><input type="text" name="f_surname"
			id="reg_sname" tabindex="3"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="Surname" onFocus="if(this.value=='Surname'){this.value=''}"
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
		<td colspan="2"><input type="text" name="f_bday" id="reg_bday"
			maxlength="2" tabindex="4"
			style="padding: 3px 5px 2px 7px; border: 0px solid; background: url(../images/roundedbirth.gif) no-repeat top left; height: 17px; width: 47px;"
			value="Day" OnFocus="focus_day()" OnBlur="b_day()"> <select
			name="f_bmonth" id="reg_bmonth" tabindex="5"
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
		</select> &nbsp; <input type="text" name="f_byear" id="reg_byear" tabindex="6"
			style="padding: 3px 5px 2px 7px; border: 0px solid; background: url(../images/roundedbirth.gif) no-repeat top left; height: 17px; width: 47px;"
			maxlength="4" value="Year" OnFocus="focus_year()" OnBlur="b_year()"></td>
		<td width="100"><input type="text" id="bdate_error" size="35"
			style="background: url(images/errorbg.jpg) no-repeat top left; color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Gender:</td>
		<td colspan="2" style="color: #a52a2a">Male:&nbsp;&nbsp;<input
			name="gender" id="reg_gender" tabindex="7" type="radio" value="Male"
			OnClick="gender_mf('M')" checked>&nbsp;&nbsp;&nbsp;&nbsp;
		Female:&nbsp;&nbsp; <input name="gender" id="reg_gender" type="radio"
			tabindex="8" value="Female" OnClick="gender_mf('F')"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">Mobile No:</td>
		<td colspan="2"><input type="text" name="mobileno"
			id="reg_mobile" maxlength="10" tabindex="9"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="Mobile No."
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
		<td colspan="2"><select name="country" id="reg_country"
			tabindex="10"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
			<option value="Afghanistan">Afghanistan</option>
			<option value="Aland Islands">Aland Islands</option>

			<option value="Albania">Albania</option>
			<option value="Algeria">Algeria</option>
			<option value="American Samoa">American Samoa</option>
			<option value="Andorra">Andorra</option>
			<option value="Angola">Angola</option>
			<option value="Anguilla">Anguilla</option>

			<option value="Antarctica">Antarctica</option>
			<option value="Antigua and Barbuda">Antigua and Barbuda</option>
			<option value="Argentina">Argentina</option>
			<option value="Armenia">Armenia</option>
			<option value="Aruba">Aruba</option>
			<option value="Australia">Australia</option>

			<option value="Austria">Austria</option>
			<option value="Azerbaijan">Azerbaijan</option>
			<option value="Bahamas">Bahamas</option>
			<option value="Bahrain">Bahrain</option>
			<option value="Bangladesh">Bangladesh</option>
			<option value="Barbados">Barbados</option>

			<option value="Belarus">Belarus</option>
			<option value="Belgium">Belgium</option>
			<option value="Belize">Belize</option>
			<option value="Benin">Benin</option>
			<option value="Bermuda">Bermuda</option>
			<option value="Bhutan">Bhutan</option>

			<option value="Bolivia">Bolivia</option>
			<option value="Bosnia and Herzegovina">Bosnia and
			Herzegovina</option>
			<option value="Botswana">Botswana</option>
			<option value="Bouvet Island">Bouvet Island</option>
			<option value="Brazil">Brazil</option>
			<option value="British Indian Ocean Territory">British
			Indian Ocean Territory</option>

			<option value="British Virgin Islands">British Virgin
			Islands</option>
			<option value="Brunei">Brunei</option>
			<option value="Bulgaria">Bulgaria</option>
			<option value="Burkina Faso">Burkina Faso</option>
			<option value="Burundi">Burundi</option>
			<option value="Cambodia">Cambodia</option>

			<option value="Cameroon">Cameroon</option>
			<option value="Canada">Canada</option>
			<option value="Cape Verde">Cape Verde</option>
			<option value="Cayman Islands">Cayman Islands</option>
			<option value="Central African Republic">Central African
			Republic</option>
			<option value="Chad">Chad</option>

			<option value="Chile">Chile</option>
			<option value="China">China</option>
			<option value="Christmas Island">Christmas Island</option>
			<option value="Cocos (Keeling) Islands">Cocos (Keeling)
			Islands</option>
			<option value="Colombia">Colombia</option>
			<option value="Union of the Comoros">Union of the Comoros</option>

			<option value="Congo">Congo</option>
			<option value="Cook Islands">Cook Islands</option>
			<option value="Costa Rica">Costa Rica</option>
			<option value="Croatia">Croatia</option>
			<option value="Cuba">Cuba</option>
			<option value="Cyprus">Cyprus</option>

			<option value="Czech Republic">Czech Republic</option>
			<option value="Democratic Republic of Congo">Democratic
			Republic of Congo&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option value="Denmark">Denmark</option>
			<option value="Disputed Territory">Disputed Territory</option>
			<option value="Djibouti">Djibouti</option>
			<option value="Dominica">Dominica</option>

			<option value="Dominican Republic">Dominican Republic</option>
			<option value="East Timor">East Timor</option>
			<option value="Ecuador">Ecuador</option>
			<option value="Egypt">Egypt</option>
			<option value="El Salvador">El Salvador</option>
			<option value="Equatorial Guinea">Equatorial Guinea</option>

			<option value="Eritrea">Eritrea</option>
			<option value="Estonia">Estonia</option>
			<option value="Ethiopia">Ethiopia</option>
			<option value="Falkland Islands">Falkland Islands</option>
			<option value="Faroe Islands">Faroe Islands</option>

			<option value="Fiji">Fiji</option>
			<option value="Finland">Finland</option>
			<option value="France">France</option>
			<option value="French Guyana">French Guyana</option>
			<option value="French Polynesia">French Polynesia</option>
			<option value="French Southern Territories">French Southern
			Territories</option>

			<option value="Gabon">Gabon</option>
			<option value="Gambia">Gambia</option>
			<option value="Georgia">Georgia</option>
			<option value="Germany">Germany</option>
			<option value="Ghana">Ghana</option>
			<option value="Gibraltar">Gibraltar</option>

			<option value="Greece">Greece</option>
			<option value="Greenland">Greenland</option>
			<option value="Grenada">Grenada</option>
			<option value="Guadeloupe">Guadeloupe</option>
			<option value="Guam">Guam</option>
			<option value="Guatemala">Guatemala</option>

			<option value="Guinea">Guinea</option>
			<option value="Guinea-Bissau">Guinea-Bissau</option>
			<option value="Guyana">Guyana</option>
			<option value="Haiti">Haiti</option>
			<option value="McDonald Islands">McDonald Islands</option>
			<option value="Honduras">Honduras</option>

			<option value="Hong Kong">Hong Kong</option>
			<option value="Hungary">Hungary</option>
			<option value="Iceland">Iceland</option>
			<option value="India" selected>India</option>
			<option value="Indonesia">Indonesia</option>
			<option value="Iran">Iran</option>

			<option value="Iraq">Iraq</option>
			<option value="Iraq-Saudi Arabia">Iraq-Saudi Arabia</option>
			<option value="Ireland">Ireland</option>
			<option value="Israel">Israel</option>
			<option value="Italy">Italy</option>
			<option value="Ivory Coast">Ivory Coast</option>

			<option value="Jamaica">Jamaica</option>
			<option value="Japan">Japan</option>
			<option value="Jordan">Jordan</option>
			<option value="Kazakhstan">Kazakhstan</option>
			<option value="Kenya">Kenya</option>
			<option value="Kiribati">Kiribati</option>

			<option value="Kuwait">Kuwait</option>
			<option value="Kyrgyz Republic">Kyrgyz Republic</option>
			<option value="Laos">Laos</option>
			<option value="Latvia">Latvia</option>
			<option value="Lebanon">Lebanon</option>
			<option value="Lesotho">Lesotho</option>

			<option value="Liberia">Liberia</option>
			<option value="Libya">Libya</option>
			<option value="Liechtenstein">Liechtenstein</option>
			<option value="Lithuania">Lithuania</option>
			<option value="Luxembourg">Luxembourg</option>
			<option value="Macau">Macau</option>

			<option value="Macedonia">Macedonia</option>
			<option value="Madagascar">Madagascar</option>
			<option value="Malawi">Malawi</option>
			<option value="Malaysia">Malaysia</option>
			<option value="Maldives">Maldives</option>
			<option value="Mali">Mali</option>

			<option value="Malta">Malta</option>
			<option value="Marshall Islands">Marshall Islands</option>
			<option value="Martinique">Martinique</option>
			<option value="Mauritania">Mauritania</option>
			<option value="Mauritius">Mauritius</option>
			<option value="Mayotte">Mayotte</option>

			<option value="Mexico">Mexico</option>
			<option value="Moldova">Moldova</option>
			<option value="Monaco">Monaco</option>
			<option value="Mongolia">Mongolia</option>
			<option value="Montserrat">Montserrat</option>
			<option value="Morocco">Morocco</option>

			<option value="Mozambique">Mozambique</option>
			<option value="Myanmar">Myanmar</option>
			<option value="Namibia">Namibia</option>
			<option value="Nauru">Nauru</option>
			<option value="Nepal">Nepal</option>
			<option value="Netherlands">Netherlands</option>

			<option value="Netherlands Antilles">Netherlands Antilles</option>
			<option value="New Caledonia">New Caledonia</option>
			<option value="New Zealand">New Zealand</option>
			<option value="Nicaragua">Nicaragua</option>
			<option value="Niger">Niger</option>
			<option value="Nigeria">Nigeria</option>

			<option value="Niue">Niue</option>
			<option value="Norfolk Island">Norfolk Island</option>
			<option value="North Korea">North Korea</option>
			<option value="Northern Mariana Islands">Northern Mariana
			Islands</option>
			<option value="Norway">Norway</option>
			<option value="Oman">Oman</option>

			<option value="Pakistan">Pakistan</option>
			<option value="Palau">Palau</option>
			<option value="Palestinian Territories">Palestinian
			Territories</option>
			<option value="Panama">Panama</option>
			<option value="Papua New Guinea">Papua New Guinea</option>
			<option value="Paraguay">Paraguay</option>

			<option value="Peru">Peru</option>
			<option value="Philippines">Philippines</option>
			<option value="Pitcairn Islands">Pitcairn Islands</option>
			<option value="Poland">Poland</option>
			<option value="Portugal">Portugal</option>
			<option value="Puerto Rico">Puerto Rico</option>

			<option value="Qatar">Qatar</option>
			<option value="Reunion">Reunion</option>
			<option value="Romania">Romania</option>
			<option value="Russia">Russia</option>
			<option value="Rwanda">Rwanda</option>
			<option value="Saint Helena">Saint Helena</option>

			<option value="Saint Kitts & Nevis">Saint Kitts & Nevis</option>
			<option value="Saint Lucia">Saint Lucia</option>
			<option value="Saint Pierre">Saint Pierre</option>
			<option value="Saint Vincent">Saint Vincent</option>
			<option value="Samoa">Samoa</option>
			<option value="San Marino">San Marino</option>

			<option value="Sao Tome and Principe">Sao Tome and Principe</option>
			<option value="Saudi Arabia">Saudi Arabia</option>
			<option value="Senegal">Senegal</option>
			<option value="Seychelles">Seychelles</option>
			<option value="Sierra Leone">Sierra Leone</option>
			<option value="Singapore">Singapore</option>

			<option value="Slovakia">Slovakia</option>
			<option value="Slovenia">Slovenia</option>
			<option value="Solomon Islands">Solomon Islands</option>
			<option value="Somalia">Somalia</option>
			<option value="South Africa">South Africa</option>

			<option value="South Korea">South Korea</option>
			<option value="Spain">Spain</option>
			<option value="Spratly Islands">Spratly Islands</option>
			<option value="Sri Lanka">Sri Lanka</option>
			<option value="Sudan">Sudan</option>
			<option value="Suriname">Suriname</option>

			<option value="Swaziland">Swaziland</option>
			<option value="Sweden">Sweden</option>
			<option value="Switzerland">Switzerland</option>
			<option value="Syria">Syria</option>
			<option value="Taiwan">Taiwan</option>

			<option value="Tajikistan">Tajikistan</option>
			<option value="Tanzania">Tanzania</option>
			<option value="Thailand">Thailand</option>
			<option value="Togo">Togo</option>
			<option value="Tokelau">Tokelau</option>
			<option value="Tonga">Tonga</option>

			<option value="Trinidad and Tobago">Trinidad and Tobago</option>
			<option value="Tunisia">Tunisia</option>
			<option value="Turkey">Turkey</option>
			<option value="Turkmenistan">Turkmenistan</option>
			<option value="Tuvalu">Tuvalu</option>

			<option value="Uganda">Uganda</option>
			<option value="Ukraine">Ukraine</option>
			<option value="United Arab Emirates">United Arab Emirates</option>
			<option value="United Kingdom">United Kingdom</option>
			<option value="United States">United States</option>

			<option value="Uruguay">Uruguay</option>
			<option value="US Virgin Islands">US Virgin Islands</option>
			<option value="Uzbekistan">Uzbekistan</option>
			<option value="Vanuatu">Vanuatu</option>
			<option value="Vatican City">Vatican City</option>
			<option value="Venezuela">Venezuela</option>

			<option value="Vietnam">Vietnam</option>
			<option value="Western Sahara">Western Sahara</option>
			<option value="Yemen">Yemen</option>
			<option value="Zambia">Zambia</option>
			<option value="Zimbabwe">Zimbabwe</option>

			<option value="Serbia">Serbia</option>
			<option value="Montenegro">Montenegro</option>
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="100" style="color: #a52a2a">State:</td>
		<td colspan="2"><input type="text" name="state" id="reg_state"
			tabindex="11"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="State" OnFocus="if(this.value=='State'){this.value=''}"
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
			tabindex="12"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="City" OnFocus="if(this.value=='City'){this.value=''}"
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
		<textarea name="address" id="reg_address" tabindex="13"
			style="padding: 3px 5px 2px 7px; border: 0px solid; overflow: auto; background: url(images/roundedbig1.gif) no-repeat top left; height: 79px; width: 215px;"
			OnFocus="if(this.value=='Address'){this.value=''}"
			OnBlur="checkaddress()">Address</textarea><br>
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
			tabindex="14"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="Zipcode" OnFocus="if(this.value=='Zipcode'){this.value=''}"
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
			tabindex="15"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			value="E-Mail" OnFocus="if(this.value=='E-Mail'){this.value=''}"
			OnBlur="email()"></td>
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
		<td colspan="2"><select name="qualification" id="reg_edu"
			tabindex="16"
			style="border: 1px solid #a52a2a; font-family: arial; color: #FFFFFF; background-color: #26b6e8;">
			<option value="Less Than 10">Less Than
			10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option value="10th">10th</option>
			<option value="12th Science">12th Science</option>
			<option value="12th Commerce">12th Commerce</option>
			<option value="12th Arts">12th Arts</option>
			<option value="Diploma">Diploma</option>
			<option value="Graduate">Graduate</option>
			<option value="Post Graduate">Post Graduate</option>
			<option value="Phd">Phd</option>
		</select></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 13px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="19" class="fright" OnClick="registervalid()">Register</a></td>
		<td><a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 13px 0 15px; height: 22px; border: 1px solid #a52a2a;"
			tabindex="20" class="fright"
			OnClick="document.getElementById('register_frm').reset()">Reset</a></td>
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
