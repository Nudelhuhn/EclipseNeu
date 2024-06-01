package uebung6;

public class afg7
{

	public void method() {
		try
		{
			try
			{
				System.out.println("Codeteil 1");
				throw new Exception2();
				// Codeteil 1 ...
			} catch (Exception1 e)
			{
				System.out.println("Codeteil 2");
				// Codeteil 2 ...
			}
			System.out.println("Codeteil 3");
			throw new Exception1();
			// Codeteil 3 ...
		}catch(Exception2 e)
		{
			System.out.println("Codeteil 4");
			// Codeteil 4 ...
		}
	}

	public static void main(String[] args)
	{
		afg7 a = new afg7();
		a.method();

	}

}

class Exception1 extends Exception2
{

}

class Exception2 extends Exception{
	
}