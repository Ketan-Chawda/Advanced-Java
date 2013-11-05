var str_alpha="abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
var str_num="0123456789";
var str,i,ic,cc,ans,global_str="",flag=false,mobile="";
var bday="",bmonth="",byear="",f_date="",yearcnt=0,flagyear=false,leap=false,gender="Male";
var now=new Date();
var now_year=now.getFullYear();
var timer,flag_color=false;
function registervalid()
{

var firstname,middlename,surname,birthdate,mobileno,country,state,city,address,zipcode,email,qualification;
firstname=document.getElementById('reg_fname').value;
middlename=document.getElementById('reg_mname').value;
surname=document.getElementById('reg_sname').value;
birthdate=(document.getElementById('reg_bday').value)+"-"+(document.getElementById('reg_bmonth').value)+"-"+(document.getElementById('reg_byear').value);
mobile=document.getElementById('reg_mobile').value;
country=document.getElementById('reg_country').value;
state=document.getElementById('reg_state').value;
city=document.getElementById('reg_city').value;
address=document.getElementById('reg_address').value;
zipcode=document.getElementById('reg_zip').value;
email=document.getElementById('reg_email').value;
qualification=document.getElementById('reg_edu').value;

f_error=document.getElementById('fname_error').value;
m_error=document.getElementById('mname_error').value;
s_error=document.getElementById('sname_error').value;
b_error=document.getElementById('bdate_error').value
mob_error=document.getElementById('mobile_error').value;
state_error=document.getElementById('state_error').value;
city_error=document.getElementById('city_error').value;
add_error=document.getElementById('address_error').value;
zip_error=document.getElementById('zipcode_error').value;
mailid_error=document.getElementById('email_error').value;

clearTimeout(timer);

if(firstname!="First Name" && middlename!="Middle Name" && surname!="Surname" && document.getElementById('reg_bday').value !="Day" && document.getElementById('reg_byear').value !="Year" && mobile !="Mobile No." && state !="State" && city !="City" && address !="Address" && zipcode !="Zipcode" && email !="E-Mail")
{
	if(f_error=="" && m_error=="" && s_error=="" && b_error=="" && mob_error=="" && state_error=="" && city_error=="" && add_error=="" && zip_error=="" && mailid_error=="")
	{
		document.getElementById('register_frm').submit();
	}	
	else
	{
	document.getElementById('total_error').value="   * Please Fill Each & Every Fields."
	dyn_blnkerror();
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
function b_day()
{
var b_ans;	
	bday=document.getElementById('reg_bday').value;
	byear=document.getElementById('reg_byear').value;
	bday=bday.replace(/^\ +/,"").replace(/\ +$/,"");
	bmonth=document.getElementById('reg_bmonth').value;
	if((bday.length==1 && (bday-1)>=0) || bday=="0"){bday="0"+bday;}
	document.getElementById('reg_bday').value=bday;	
	if(bday=='')
	{					
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
	}	
	else if(bday<32 && bday>0)
	{
		if(byear=="")
		{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
		}
		else if(flagyear==true)
		{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
		}
		else
		{
		document.getElementById('bdate_error').value="";
		}
	}
	else
	{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
	}
	if(yearcnt==0)
	{
		if(bday=="" || (bday>0 && bday<32))
		{
			if(byear=="")
			{
			document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
			}			
		}
	}
if(byear>0)
{
	if(byear>now_year)
	{
		document.getElementById('bdate_error').value="   * Are You Really From Future."
	}
	else if(byear<(now_year-100))
	{
		document.getElementById('bdate_error').value="   * Are You Really Too Old."
	}
	else if(byear<=(now_year-15))
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
	else
	{
		document.getElementById('bdate_error').value="   * You Are Not Eligible."
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
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
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
}
function b_year()
{
bday=document.getElementById('reg_bday').value;
bmonth=document.getElementById('reg_bmonth').value;
byear=document.getElementById('reg_byear').value;
	if(byear>now_year)
	{
		document.getElementById('bdate_error').value="   * Are You Really From Future."
	}
	else if(byear<(now_year-100) && byear != "")
	{
		document.getElementById('bdate_error').value="   * Are You Really Too Old."	
	}
	else if(byear<=(now_year-15))
	{
	if(bday=='' || (bday>0 && bday<32))
	{		
		if(byear=='')	
		{
			document.getElementById('bdate_error').value="   * Please Check Your Birthdate."
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
			document.getElementById('bdate_error').value="   * Please Check Your Birthdate."
			flagyear=true;
		}
	}	
	}
	else if((byear%2!=0)&&(byear%2!=1))
	{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate."
	}
	else
	{
		document.getElementById('bdate_error').value="   * You Are Not Eligible."
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
	document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
	}
}
function focus_year()
{
yearcnt=1;
	bday=document.getElementById('reg_bday').value;
	byear=document.getElementById('reg_byear').value;
	if(byear=="Year"){document.getElementById('reg_byear').value="";}
	if(bday=="Day")
	{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
	}
	else if(b_day=='')
	{
		if(b_year=='')
		{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
		}
		else
		{
		document.getElementById('bdate_error').value="   * Please Check Your Birthdate.";
		}		
	}
}

