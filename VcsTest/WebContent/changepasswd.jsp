<html lang="en">
<head>
<title>Login</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<style type="text/css">
</style>
<%
String st=request.getParameter("common");
%>
</head>
<script language="javascript" src="js/textcol.js">
</script>
<body background="images/tail-middle.jpg">
<section id="content">
<center>
<dd><dd>
</center>
<font>
<h4 align="center">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Change Your Password</h4>
<dd><br>
<form name="changepasswd" id="changepasswd" method="post" action="/VcsTest/ServletPasswordChange">
<table name="frm_frm" id="frm_frm" align="right" width="67%">
	<tr>
		<td width="112"></td>
		<td colspan="3"><input type="text" name="rec_error" size="40"
			tabindex="400"
			value="<% if(st!=null){out.println(st);} %>"
			id="rec_error"
			style="color: red; border: 0px solid; font-family: Trebuchet MS;"
			readonly></td>
	</tr>
	<tr>
		<td width="112" style="color: #a52a2a">Old Password:</td>
		<td colspan="3" height="30"><input type="password" name="oldone"
			id="chg_old"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			tabindex="1" OnFocus="textbackcolor('chg_old')"
			OnBlur="textback('chg_old')"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="112" style="color: #a52a2a">New Password:</td>
		<td colspan="3" height="30"><input type="password" name="newone"
			id="chg_new"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			tabindex="2" OnFocus="textbackcolor('chg_new')"
			OnBlur="textback('chg_new')"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td width="112" style="color: #a52a2a">Confirm:</td>
		<td colspan="3" height="30"><input type="password"
			name="confirm" id="chg_conf"
			style="padding: 2px 5px 4px 5px; border: 1px double #0087c1; width: 221px;"
			tabindex="3" OnFocus="textbackcolor('chg_conf')"
			OnBlur="textback('chg_conf')"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3"></td>
	</tr>
	<tr>
		<td><input type="submit" value=""
			style="height: 0px; width: 0px;"></td>
		<td width="106"><a href="javascript:history.go(-3)">BACK</a></td>
		<td width="91" align="left"><span> <a href="#"
			style="text-transform: uppercase; font-size: .900em; text-decoration: none; float: right; padding: 6px 12px 0 15px; height: 30px; border: 1px solid #a52a2a;"
			tabindex="4" class="fright"
			OnClick="document.getElementById('changepasswd').submit();">Change</a>
		</span></td>
		<td width="215" align="left">&nbsp;</td>
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
<br>
</form>
</section>
</body>
</html>
