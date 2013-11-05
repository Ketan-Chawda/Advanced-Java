<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function my(my1)
{

my1.t3.value="nice1";
}
</script>
<body>
<form>
<br>
<input type='button' name='t1'  value='ketan'>

<br>
<input type='text' name='t2' value='ketan' onkeypress='my(this.form)'>

<br>
<input type='text' name='t3' value='ketan'>

</form>
</body>
</html>