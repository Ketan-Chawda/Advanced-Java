<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function msg(form1)
{
form1.temp.value="yes"; 
<!--- document.write("ya"); ---->
}
</script>
<body background="myreport1.jpg">
<% String mystr=request.getParameter("k1"),a1,a2,a3,a4,c,q,p;%>
<%if(mystr!=null)
{
	a1=request.getParameter("o1");
	a2=request.getParameter("o2");
	a3=request.getParameter("o3");
	a4=request.getParameter("o4");
	c=request.getParameter("correct");
	q=request.getParameter("qid");
	p=request.getParameter("lasts");

}
else
{
	mystr="your question will be here ok";
	a1="option-1 will be here";
	a2="option-2 will be here";
	a3="option-3 will be here";
	a4="option-4 will be here";
	c="dnt know";
	q=request.getParameter("qid");
	p=request.getParameter("lasts");

}
%>
<form action="/VcsTest/ServletQuiz" method="post"><br>
<center>
<h1><br>******************************************************************************************************<br><br>
question=<%=mystr%><br>
<br>******************************************************************************************************<br><br>


<h2>
<input type="radio" name=selected value=1><%=a1%> <br>
<input type="radio" name=selected value=2><%=a2%> <br>
<input type="radio" name=selected value=3><%=a3%> <br>
<input type="radio" name=selected value=4><%=a4%> <br>
<input type="hidden" name=correct value=<%=c%>> <br>
<input type="hidden" name=qid value=<%=q%>> 
<input type="hidden" name=temp value="no">
<br><br>
<input type="submit" value="previous" onclick="msg(this.form)">
<form name="f2" action="/VcsTest/ServletQuiz" method="post">
<blockquote><blockquote><input type="submit" value="next"" >
</form>

</form>

</body>
</html>