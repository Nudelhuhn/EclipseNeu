package uebung7_SS24;

// Für Aufgabe 4 auskommentiert
//class A {} 
//class B extends A {} 
//class C extends B {} 
//class D {} 
//class E extends D {} 
//class F extends E {};

public class HeterogeneousPair<S, T>
{

	private S left;
	private T right;

	// a)
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

	
	// b)
	public static void copy(HeterogeneousPair<? extends B, ? extends E> 
	p1, HeterogeneousPair<? extends B, ? extends E> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}
	/*
	 * Heterogeneouspair benutzt Typvariablen. Zur Laufzeit stehen also die Datentypen
	 * fest. Da aber wildcards eingesetzt werden, die einen variablen Typen haben und
	 * zudem B und E jeweils zwei unterschiedliche Datentypen haben können, ist es
	 * sinnvoll dies als Syntaxfehler zu markieren, da nicht gewährleistet werden kann,
	 * dass jeweils die ersten und die zweiten Typen von p1 und p2 gleicht sind, bzw.
	 * kompaktibel sind.
	 */
	
	// c1)
	public static void copy2(HeterogeneousPair<? extends B, ? extends E> 
	p1, HeterogeneousPair<B,E> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}
	/*
	 * funktioniert jetzt, da die Typvariablen von p2 konkretisiert wurden
	 */
	
	// c2)
	public static void copy3(HeterogeneousPair<? extends B, ? extends E> 
	p1, HeterogeneousPair<? super B,? super E> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}
	/*
	 * funktioniert jetzt, da p2 mindestens den gleichen oder einen allgemeineren
	 * Datentyp hat
	 */
	
	// d)
	public static <S extends A, T extends D> void copy4(HeterogeneousPair<S, T> 
	p1, HeterogeneousPair<A, D> p2)
	{
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
	}
	/*
	 * - in generischer Methode kein super möglich
	 *   (zb <S super A, T super D>)
	 * - in generischer Methode in Typparameter kein super oder extends möglich
	 *   (nur mit wildcards möglich wie zb HeterogeneousPair<? extends B, ? extends E>)
	 * - es darf nur in etwas kopiert werden, was mindestens den gleichen Typen hat
	 *   oder allgemeiner ist
	 * Wenn also nun S und T alle Typen der obigen Klassen annehmen dürfen (um möglichst
	 * vieles zu erlauben), muss p2 maximal allgemein sein, um alle spezifischeren Typen
	 * zuzulassen.
	 */
	
	// e)
	public static <S /*extends A*/,T /*extends D*/> void exchange1(HeterogeneousPair<S, T> 
	p1, HeterogeneousPair<S, T> p2)
	{
		S left = p2.getLeft();
		T right = p2.getRight();
		p2.setLeft(p1.getLeft());
		p2.setRight(p1.getRight());
		p1.setLeft(left);
		p1.setRight(right);
	}
	
	// f)
	public static <S /*extends A*/,T /*extends D*/> void exchange2(HeterogeneousPair<S, T> 
	p1, HeterogeneousPair<T, S> p2)
	{	
		S left = (S)p2.getLeft();
		T right = (T)p2.getRight();
		p2.setLeft(p1.getRight());
		p2.setRight(p1.getLeft());
		p1.setLeft((S) right);
		p1.setRight((T) left);
	}
	
	public static void main(String[] args) {
		// c3)
		HeterogeneousPair<B, E> p1 = new HeterogeneousPair<B, E>(new B(), new E());
		HeterogeneousPair<B, D> p2 = new HeterogeneousPair<B, D>(new B(), new D());
		copy3(p1, p2);
		copy2(p1, p2);
		// weiteres Beispiel
		HeterogeneousPair<B, F> p3 = new HeterogeneousPair<B, F>(new B(), new F());
		copy2(p3, p1);
		
		// d) Beispiel
		HeterogeneousPair<C, F> spezifisch_in = new HeterogeneousPair<C, F>(new C(), new F());
		HeterogeneousPair<A, D> allgemein = new HeterogeneousPair<A, D>(new A(), new D());
		copy4(spezifisch_in, allgemein);
		
		// e)
		HeterogeneousPair<A, F> gleiche_Signatur_wie = new HeterogeneousPair<A, F>(new A(), new F());
		HeterogeneousPair<A, F> das_hier = new HeterogeneousPair<A, F>(new A(), new F());
		exchange1(gleiche_Signatur_wie, das_hier);
		
		// f)
		HeterogeneousPair<A, F> umgetauschte = new HeterogeneousPair<A, F>(new A(), new F());
		HeterogeneousPair<F, A> datentypen = new HeterogeneousPair<F, A>(new F(), new A());
		exchange2(umgetauschte, datentypen);
		
		// h)
		HeterogeneousPair<A, A> hetep;
		HomogeneousPair<A> homop = new HomogeneousPair<A>(new A());
		hetep = homop;
		
		// i)
		HeterogeneousPair<A, A> hop1 = new HomogeneousPair<A>(new A());
		HeterogeneousPair<A, A> hop2 = new HomogeneousPair<A>(new A());
		copy(hop1, hop2);
		copy2(hop1, hop2);
		copy3(hop1, hop2);
		copy4(hop1, hop2);
		exchange1(hop1, hop2);
		exchange2(hop1, hop2);
		/*
		 * Um HomogeneousPair-Objekte in die Methoden einsetzen zu können, müssen
		 * die Methoden gewährleisten, dass die Typparameter auf beiden Seiten gleich
		 * sind, was nur bei den exchange-Methoden gewährleistet wird.
		 */
	}
}

// g)
class HomogeneousPair<S> extends HeterogeneousPair<S, S>{

	private S left;
	
	public HomogeneousPair(S left)
	{
		super(left, left);
		this.left = left;
	}
	
}
