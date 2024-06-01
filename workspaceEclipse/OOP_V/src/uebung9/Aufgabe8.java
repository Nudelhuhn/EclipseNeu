package uebung9;

import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

public class Aufgabe8 implements IntUnaryOperator, IntSupplier, IntBinaryOperator
{
	public static void main(String[] args) {
		//a)
		IntUnaryOperator iuo = new Aufgabe8();
		iuo = (int x) -> x + 1;
		
//		iuo = int x -> x + 1;
		//Klammern notwendig
		iuo = (int x) -> x + 1;
		
		iuo = (x) -> x + 1;
		
//		iuo = (int x) -> return x + 1;
		//return ist überflüssig oder man setzt geschweifte Klammern
		iuo = (int x) -> x + 1;
		iuo = (int x) -> {return x + 1;};
		
//		iuo = (x) -> {return x + 1};
		//Semikolon fehlt
		iuo = (x) -> {return x + 1;};
		
		iuo = x -> x + 1;
		iuo = (x) -> (x > 0) ? 1 : 0;
		
		//b)
		IntSupplier is = new Aufgabe8();
		is = () -> 42;
		is = () -> Integer.valueOf(42);
		
		//c)
		IntBinaryOperator ibo = new Aufgabe8();
		ibo = (int x, int y) -> x + y;
		
//		ibo = int x, int y -> x + y;
		//Klammern fehlen
		ibo = (int x, int y) -> x + y;
		
		ibo = (x, y) -> x + y;
		
		ibo = (x, y) -> {if(x > y) return x; else return y;};
		
//		ibo = x, y -> x + y;
		//Klammern fehlen
		ibo = (x, y) -> x + y;
		
//		ibo = (int x, y) -> x + y;
		//entweder beide Parameter mit Typ angeben oder beide ohne
		ibo = (x, y) -> x + y;
		ibo = (int x, int y) -> x + y;
	}

	@Override
	public int applyAsInt(int operand)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAsInt()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int applyAsInt(int left, int right)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
