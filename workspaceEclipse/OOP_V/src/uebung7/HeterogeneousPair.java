package uebung7;

public class HeterogeneousPair<S, T>
{
	private S left;
	private T right;

	public HeterogeneousPair(S left, T right)
	{
		this.left = left;
		this.right = right;
	}

	public S getLeft()
	{
		return left;
	}

	public void setLeft(S left)
	{
		this.left = left;
	}

	public T getRight()
	{
		return right;
	}

	public void setRight(T right)
	{
		this.right = right;
	}

	@Override
	public String toString()
	{
		return "HeterogenousPair [left=" + left + ", right=" + right + "]";
	}

	// ? extends B = beliebiger Parametertyp ... von Klasse B
	public static void copy(HeterogeneousPair<? extends B, ? extends E> p1,
			HeterogeneousPair<? extends B, ? extends E> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
		/*
		 * in "?" können beliebige Typparameter eingelesen werden, da aber bei der
		 * Zuweisung auch beliebige Typparameter eingelesen werden können, kann es zu
		 * Typinkompatibilitäten führen (siehe copy1)
		 */
	}

	/*
	 * hier sieht man die Typinkompatibilität von der Methode copy an einem
	 * konkreten Fall
	 */
	public static void copy1(HeterogeneousPair<B, E> p1, HeterogeneousPair<C, F> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}

	/*
	 * S und T sind variable Typen, weshalb auch A,B,C,D,E und F eingesetzt werden
	 * können
	 */
	public static <S, T> void copy2(HeterogeneousPair<S, T> p1, HeterogeneousPair<S, T> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}

	public static void copy3(HeterogeneousPair<B, E> p1, HeterogeneousPair<B, E> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}

	// c)
	// c1)
	public static void copy4(HeterogeneousPair<? extends B, ? extends E> p1, HeterogeneousPair<B, E> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}

	// c2 nicht richtig
	public static <S extends B, T extends E> void copy5(HeterogeneousPair<? extends B, ? extends E> p1,
			HeterogeneousPair<S, T> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}
	// c3)

	// d)
	// Vertauschen
	public static <S, T> void exchange1(HeterogeneousPair<S, T> p1, HeterogeneousPair<S, T> p2)
	{
		S tmpLeft = p2.getLeft();
		T tmpRight = p2.getRight();
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
		p1.setLeft(tmpLeft);
		p1.setRight(tmpRight);
	}

	// e)
	// über kreuz vertauschen, ist aber nicht richtig, man musste beim zweiten
	// Parameter die Generics vertauschen also zu <T, S>
	public static <S, T> void exchange2(HeterogeneousPair<S, T> p1, HeterogeneousPair<S, T> p2)
	{
		S tmpRight = (S) p1.getRight();
		T tmpLeft = (T) p1.getLeft();
		S tmpRight1 = (S) p2.getRight();
		T tmpLeft1 = (T) p2.getLeft();
		p2.setLeft(tmpRight);
		p2.setRight(tmpLeft);
		p1.setLeft(tmpRight1);
		p1.setRight(tmpLeft1);
	}

	public static void main(String[] args)
	{
		/*
		 * funktioniert nicht, da die Typparamter B und E spezieller sind als als die
		 * erzeugten Objekte von A und D HeterogeneousPair<B, E> h1 = new
		 * HeterogeneousPair<>(new A(), new D());
		 */
		HeterogeneousPair<B, E> h2 = new HeterogeneousPair<>(new B(), new E());
		HeterogeneousPair<B, E> h3 = new HeterogeneousPair<>(new C(), new F());
//		copy(h2, h3);
//		copy1(h2, h3);
//		copy2(h2, h3);
//		copy3(h2, h3)

		exchange2(h2, h3);

		HomogeneousPair<A> h4 = new HomogeneousPair<>(new A(), new A());
		HomogeneousPair<A> h5 = new HomogeneousPair<>(new A(), new A());
		copy4(h4, h5);
		exchange1(h4, h5);
		exchange2(h4, h5);
	}
}

class A
{
}

class B extends A
{
}

class C extends B
{
}

class D
{
}

class E extends D
{
}

class F extends E
{
}
