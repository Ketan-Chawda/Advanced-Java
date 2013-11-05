package mybest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;

public class Secure {

	/**
	 * @param args
	 */
	static String mystr="";
	public static void main(String[] args)
	throws IOException
	{
		// TODO Auto-generated method stub
		String str="pink",str1;
		str1=putp(str);
		System.out.println(str1);
		System.out.println(getp(str1));
	}
	public static String putp(String str)
	{
		mystr="";
		int l=str.length(),s;
		if(l<3)
		s=17;
		else if(l%2==0)
		s=3*l;
		else
		s=4*l;
		//System.out.println("s="+s);
		for(int k=0;k<l;k++)
		{
			int d=str.charAt(k)+s;
			//System.out.println("here d="+d);
			if(d>126)
			{
				d=d-86;
			//	System.out.println("ya d="+d + "and char of d="+(char)d);
			mystr=(char)d +mystr;
			}
			else
				mystr=(char)d +mystr;
				
		}
		for(int k=0;k<l;k=k+2)
		{
			int d=str.charAt(k)+s;
			if(d>126)
			{
				d=d-85;
			mystr=mystr + (char)d;
			}
			else
				mystr=mystr +(char)d;
		}
		//System.out.println("length is now="+mystr.length());
		return mystr;
	}
	public static String getp(String str)
	{
		mystr="";
		double l=str.length(),s;
		l=l-Math.ceil(l/3);
		//System.out.println("math round l="+l);
		if(l<3)
		s=17;
		else if(l%2==0)
		s=3*l;
		else
		s=4*l;
		//System.out.println("s="+s);
					
		for(int k=0;k<l;k++)
		{
			int d=str.charAt(k)-(int)s;
			if(d<40)
			{
				d=d+86;
			mystr=(char)d+mystr;
			}
			else
				mystr=(char)d+mystr;		
		}
		//System.out.println("length is now="+mystr.length());
		
		return mystr;
	}

}
