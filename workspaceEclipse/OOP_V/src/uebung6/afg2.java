package uebung6;

public class afg2
{

}

class A implements B
{
	@Override
	public void method()
	{
		System.out.println("Methode von A");
	}
}

interface B
{
	public void method();

	default void method2()
	{
	};
}

