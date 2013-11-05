package mybest;

public class Fortable {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println(Fortable.display(8,"<<<->>>"));

	}
	public static String display(int total,String sign)
	{
		String str="";
		for(int k=0;k<total;k=k+2)
		{
		str=str+sign+" ";	
		}
		return str;
	}

}
