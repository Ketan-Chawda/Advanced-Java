<%int status=0;String sid="";try{HttpSession my=request.getSession(false);if(my!=null)
{sid=my.getId().toString();String str=my.getAttribute("urole").toString();
if(str.compareToIgnoreCase("management")==0)status=12;
else	status=36;}}
catch(Exception e)
{System.out.println(e+"session is expired ok");}
finally{%>
<%if(status==12){%>


<%@ page import="java.util.*,java.io.*"%>

<%
String path=request.getParameter("afile");
//String path="C:\\Documents and Settings\\YASH\\Desktop\\AMITY.txt";

String newPath="c:\\";
int count=0;

if(path!=null)
{
ArrayList arr=new ArrayList();
StringTokenizer st=new StringTokenizer(path,"\\");
while(st.hasMoreTokens())
{
arr.add(count,st.nextToken());
count++;
}
// create ur own path

newPath="../"+arr.get(count-1);
int c;
FileInputStream fis=new FileInputStream(path);
FileOutputStream fos=new FileOutputStream(newPath);
while((c=fis.read())!=-1)
{
fos.write((char)c);
}
}

out.println("Thanks for using");
out.println("<br>");
out.println("<br>");
out.println("1.File1 Uploaded from :: "+path);
out.println("<br>");
out.println("<br>");
out.println("2.Uploaded File1 is Saved in :: "+newPath);
%>
<%}else if(status==36){%>
<h1><center>Sorry Page Exists But You Are Unauthorized User For
This Page Thanks Best Luck Next Time
<br><br><br><a href="/VcsTest/login.jsp">Login Again</a><br><br><br><a href="/VcsTest/home.jsp">Go To Home</a></center><%}
else{%><h1>
<center>Your Session Has Been Expired
<br><br><br><a href="/VcsTest/login.jsp">Login Again</a><br><br><br><a href="/VcsTest/home.jsp">Go To Home</a></center>
<%}}%>
