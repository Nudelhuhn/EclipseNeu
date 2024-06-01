package uebung4;

public class OverloadOverwrite
{
	public static void main(String[] args) {
		//c)
//		X2Class x2 = new X2Class(); 
//		x2.m(new BClass());
		
		//d)
//		X1Class x1;
//		X2Class x2 = new X2Class();
//		x1 = x2;
//		x1.m(new BClass());
		
		//e)
		Y1Class y1; 
		Y2Class y2 = new Y2Class(); 
		y1 = y2; 
		y1.m(new AClass()); 
		y1.m(new BClass()); 
	}
}

class AClass
{
}

class BClass extends AClass
{
}

class X1Class
{
	public void m(AClass a)
	{
		System.out.println("X1Class.m(AClass)");
	}
}

class X2Class extends X1Class
{
	public void m(BClass b)
	{
		System.out.println("X2Class.m(BClass)");
	}
}

class Y1Class
{
	public void m(AClass a)
	{
		System.out.println("Y1Class.m(AClass)");
	}
}

class Y2Class extends Y1Class
{
	@Override
	public void m(AClass a)
	{
		System.out.println("Y2Class.m(AClass)");
	}
}
