package uebung2;

public class MethodCalls
{
	public static void m1()
	{
		m2();
		m3();
		m5();
	}

	public static void m2()
	{
		m3();
		m4();
	}

	public static void m3()
	{
		m4();
	}

	public static void m4()
	{
		Exception e = new Exception();
		e.printStackTrace(System.out);
	}

	public static void m5()
	{
		m3();
	}

	public static void main(String[] args)
	{
		m1();
	}
}
