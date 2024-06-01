package afg3_2;

import execution.SampleClass;

public class SampleClassUser
{
	private static SampleClass lastDeletedObject;
	private SampleClass[] objects;

	public SampleClassUser(int numberOfObjects)
	{
		if (numberOfObjects <= 0)
		{
			throw new IllegalArgumentException("Arg. zu klein");
		}
		if (numberOfObjects > 10)
		{
			throw new IllegalArgumentException("Arg. zu groß");
		}
		objects = new SampleClass[numberOfObjects];
		for (int i = 0; i < numberOfObjects; i++)
		{
			objects[i] = new SampleClass(i * 10 + 1, i * 10 + 2, null);
			if (i > 0)
			{
				objects[i].setNext(objects[i - 1]);
			}
		}
	}

	public void deleteObjectFromArray(int objectNumber)
	{
		lastDeletedObject = objects[objectNumber];
		objects[objectNumber] = null;
	}

	public static void showLastDeletedObject()
	{
		if (lastDeletedObject != null)
		{
			System.out.println(lastDeletedObject);
		} else
		{
			System.out.println("kein gelöschtes Objekt");
		}
	}

	public static void main(String[] args)
	{
		SampleClassUser scu = new SampleClassUser(4);
		scu.deleteObjectFromArray(1);
		scu.deleteObjectFromArray(3);
		scu.deleteObjectFromArray(3);
		showLastDeletedObject();
	}
}
