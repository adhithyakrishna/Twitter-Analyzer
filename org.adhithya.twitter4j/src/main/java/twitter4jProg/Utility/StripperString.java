package twitter4jProg.Utility;

public class StripperString {
	public static void main(String args[])
	{
		String data = "welcome to the @world";
		if(!data.contains("@world"))
		{
			System.out.println("illa");
		}
		else {
			System.out.println("iruku");
		}
	}
}
