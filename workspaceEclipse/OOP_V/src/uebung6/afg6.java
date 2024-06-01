package uebung6;

public class afg6
{

	public String getX()
	{
		try
		{
			System.out.println("try");
			return "hallo";
		} finally
		{
			System.out.println("finally");
			return "huhu";
		}
	}

	public int getY()
	{
		while (true)
		{
			try
			{
				System.out.println("try");
				return 0;
			} finally
			{
				System.out.println("finally");
				break;
			}
		}
		return 1;
	}
	
	public int getZ() {
		try {
			throw new Exception("Exception");
		}finally {
			System.out.println("finally");
			return 0;
		}
	}

	public static void main(String[] args)
	{
		afg6 a = new afg6();
		a.getZ();
	}
}