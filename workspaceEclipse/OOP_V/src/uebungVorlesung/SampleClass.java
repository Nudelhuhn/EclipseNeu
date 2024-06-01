package uebungVorlesung;

public class SampleClass
{
	private static int nextNumber;

	private int i, j;
	private String s;
	private SampleClass next;
	private int myNumber;

	public SampleClass(int i, int j, String s)
	{
		this.i = i;
		this.j = j;
		this.s = s;
		this.next = null;
		nextNumber++;
		myNumber = nextNumber;
		// oder myNumber = ++nextNumber;
	}

	public String toString()
	{
		String result = s + " (id: " + myNumber + "): " + i + ", " + j;
		if (next == null)
		{
			return result;
		}
		return result + " // " + next.toString();
	}

	public static String toStringStatic(SampleClass thisObject)
	{
		String result = thisObject.s + " (id: " + thisObject.myNumber + "): " + thisObject.i + ", " + thisObject.j;
		if (thisObject.next == null)
		{
			return result;
		}
		return result + " // " + toStringStatic(thisObject.next);
	}

	public void setNext(SampleClass n)
	{
		next = n;
	}

	private static void execute(SampleClass sc)
	{
		System.out.println(sc.toString());
		System.out.println("--------------");
		System.out.println(toStringStatic(sc));
	}

	public static void main(String[] args)
	{
		SampleClass obj1 = new SampleClass(11, 12, "Obj1");
		SampleClass obj2 = new SampleClass(21, 22, "Obj2");
		SampleClass obj3 = new SampleClass(31, 32, "Obj3");
		obj1.setNext(obj2);
		obj2.setNext(obj3);
		// obj3.setNext(obj1);
		execute(obj1);
	}
}
