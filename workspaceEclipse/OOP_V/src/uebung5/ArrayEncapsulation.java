package uebung5;

public class ArrayEncapsulation
{
	public static void main(String[] args)
	{
		TwoDimensionalArray tda = new TwoDimensionalArray(10);
		int[][] data;

		// Teil 1:
		data = tda.getData();
		tda.print();
		System.out.println("------------");
		TwoDimensionalArray.print(data);
		System.out.println("=================");

		// Teil 2:
		data = tda.getData();
		data = new int[4][5];
		tda.print();
		System.out.println("------------");
		TwoDimensionalArray.print(data);
		System.out.println("=================");
		
		// Teil 3:
		data = tda.getData();
		data[4] = new int[20];
		tda.print();
		System.out.println("------------");
		TwoDimensionalArray.print(data);
		System.out.println("=================");
		
		// Teil 4:
		data = tda.getData();
		data[4][4] = -12345;
		tda.print();
		System.out.println("------------");
		TwoDimensionalArray.print(data);
	}
}
