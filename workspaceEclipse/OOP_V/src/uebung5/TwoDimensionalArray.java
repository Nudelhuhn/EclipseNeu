package uebung5;

public class TwoDimensionalArray
{
	private int[][] array;

	public TwoDimensionalArray(int size)
	{
		if (size <= 0)
		{
			throw new IllegalArgumentException("size <= 0");
		}

		array = new int[size][];
		int number = 0;
		for (int i = 0; i < size; i++)
		{
			array[i] = new int[i + 1];
			for (int j = 0; j < array[i].length; j++)
			{
				number++;
				array[i][j] = number;
			}
		}
	}

	public void print()
	{
		print(array);
	}

	public static void print(int[][] data)
	{
		for (int i = 0; i < data.length; i++)
		{
			for (int j = 0; j < data[i].length; j++)
			{
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] getData()
	{
		int[][] copy = new int[array.length][];
		int number = 0;
		for (int i = 0; i < array.length; i++)
		{
			copy[i] = new int[i + 1];
			for (int j = 0; j < array[i].length; j++)
			{
				number++;
				copy[i][j] = number;
			}
		}
		return copy;
	}
}
