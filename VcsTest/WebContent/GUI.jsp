<%int status=0;String sid="";try{HttpSession my=request.getSession(false);if(my!=null)
{sid=my.getId().toString();String str=my.getAttribute("urole").toString();
if(str.compareToIgnoreCase("admin")==0)status=12;
else	status=36;}}
catch(Exception e)
{System.out.println(e+"session is expired ok");}
finally{%>
<%if(status==12){%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="myreport1.jpg">
<center><h2><a href='/VcsTest/Admin/AdminHome.jsp'>HOME</a></h2></center>
<table align='left' cellspacing="60%">

<tr>

<th align="left">
<i>Quiz & Assignment</i>
<br>
<br>
<a href="/VcsTest/QuizAttendedEasyDelete" >QuizAttended</a>
<br>
<a href="/VcsTest/QuizCourseWiseEasyDelete" >QuizCourseWise</a>
<br>
<a href="/VcsTest/QuizEasyDelete" >Quiz Details</a>
<br>
<a href="/VcsTest/QuizResultEasyDelete">Quiz Result</a>
<br>
<a href="/VcsTest/AssignmentCourseWiseEasyDelete">Assign Course-Wise</a>
<br>
<a href="/VcsTest/AssignmentEasyDelete">Assignment Details</a>
<br>
<a href="/VcsTest/AssignmentMarksEasyDelete">Assignment Marks</a>
<br>
<a href="/VcsTest/AssignSubmittedEasyDelete">Assign Submitted</a>


<th align="left">
<i>Faculty's</i>
<br>
<br>
<a href="/VcsTest/FacultyAlluminiEasyDelete" >Allumni's Details</a>
<br>
<a href="/VcsTest/FacultyAttendanceEasyDelete">Attendance</a>
<br>
<a href="/VcsTest/FacultyBlogsEasyDelete" >Blogs</a>
<br>
<a href="/VcsTest/FacultyEasyDelete" >Current Faculty</a>
<br>
<a href="/VcsTest/FacultyFinalAttendEasyDelete" >Final Attendance</a>
<br>
<a href="/VcsTest/FacultyLectureEasyDelete" >Lecture Details</a>
<br>
<a href="/VcsTest/FacultyLibraryEasyDelete" >Library Details</a>
<br>
<a href="/VcsTest/FacultyPendingEasyDelete" >Pending Request</a>
<br>
<a href="/VcsTest/FacultyVideoEasyDelete" >Video Details</a>



<th align="left">
<i>Student's</i>
<br>
<br>
<a href="/VcsTest/StudentAlluminiEasyDelete">Allumni Details</a>
<br>
<a href="/VcsTest/StudentAttendanceCourseWiseEasyDelete" >Attend Course-Wise</a>
<br>
<a href="/VcsTest/StudentBlogsEasyDelete" >Blog</a>
<br>
<a href="/VcsTest/StudentCourseWiseEasyDelete" >Course-Wise</a>
<br>
<a href="/VcsTest/StudentEasyDelete" >Current Student</a>
<br>
<a href="/VcsTest/StudentFinalAttendEasyDelete" >Final Attendance</a>
<br>
<a href="/VcsTest/StudentPendingEasyDelete">Pending Request</a>
<br>
<a href="/VcsTest/SubmissionEasyDelete">Submission Details</a>
<br>
<a href="/VcsTest/StudentTakeAssignmentEasyDelete">Assignment's Details</a>



<th align="left">
<i>Management's</i>
<br>
<br>
<a href="/VcsTest/MgtAlluminiEasyDelete">Allumni Details</a>
<br>
<a href="/VcsTest/MgtAttendanceEasyDelete">Attendance</a>
<br>
<a href="/VcsTest/MgtBlogsEasyDelete" >Blogs</a>
<br>
<a href="/VcsTest/MgtEasyDelete" >Current Mgt</a>
<br>
<a href="/VcsTest/MgtFinalAttendEasyDelete">Final Attendance</a>
<br>
<a href="/VcsTest/MgtPendingEasyDelete">Pending Request</a>
<br>
<br>
<tr>

<th align="left">
<i>Other's</i>
<br>
<br>
<a href="/VcsTest/CourseEasyDelete">Course's</a>
<br>
<a href="/VcsTest/LoggingRoleWiseEasyDelete">Logging R-Wise</a>
<br>
<a href="/VcsTest/LoggingEasyDelete">Logging Details</a>
<br>
<a href="/VcsTest/NewsLetterEasyDelete">NewsLetter</a>
<br>
<a href="/VcsTest/NoticeEasyDelete">Notices</a>


<th align="left">
<i>Blogs & Comment's</i>
<br>
<br>
<a href="/VcsTest/BlogsCourseWiseEasyDelete">Blogs Course-Wise</a>
<br>
<a href="/VcsTest/BlogsEasyDelete" >Blogs</a>
<br>
<a href="/VcsTest/CommentsCourseWiseEasyDelete" >Comment Course-Wise</a>
<br>
<a href="/VcsTest/CommentsEasyDelete" >Comments</a>

<th align="left">
<i>Activity & Competition</i>
<br>
<br>
<a href="/VcsTest/ActivityEasyDelete" >Activity Details</a>
<br>
<a href="/VcsTest/CompetitionEasyDelete" >Competition Details</a>
<br>
<a href="/VcsTest/CompParticipantsEasyDelete" >Competition Participant's</a>
<br>
<a href="/VcsTest/CompWinnerEasyDelete" >Winner's Details</a>

<th align="left">
<i>Easy Shifting</i>
<br>
<br>
<a href="/VcsTest/AdminFacultyConfirmEasy" >Confirm Faculty</a>
<br>
<a href="/VcsTest/AdminFacultyToEasy" >Move Faculty To Allumni</a>
<br>
<a href="/VcsTest/AdminMgtConfirmEasy" >Confirm Mgt</a>
<br>
<a href="/VcsTest/AdminMgtToEasy" >Move Mgt To Allumni</a>
<br>
<a href="/VcsTest/AdminStudentConfirmEasy" >Confirm Student</a>
<br>
<a href="/VcsTest/AdminStudentToEasy" >Move Student To Allumni</a>

<br>
<a href="/VcsTest/AdminLockEasy" >Lock User</a>
<br>
<a href="/VcsTest/AdminUnLockEasy" >UnLock User</a>



</table>
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
