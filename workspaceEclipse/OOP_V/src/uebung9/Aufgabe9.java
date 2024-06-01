package uebung9;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.IntFunction;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Aufgabe9
{
	public static void main(String[] args)
	{
		// a)
		// (Integer i) -> System.out.println(i)
		Consumer<Integer> c = System.out::println;
		// b)
		// o -> Objects.isNull(o)
		Predicate<Object> o = Objects::isNull;
		// c)
		// (int i) -> new Square(i)
		IntFunction<Square> ifSq = Square::new;
		// d)
		// (int i) -> new String[i]
		IntFunction<String[]> ifStr = String[]::new;
		// e)
		// (String s1, String s2) -> s1.indexOf(s2)
		BiFunction<String, String, Integer> bf = String::indexOf;
		// f)
		// s -> sLocal.indexOf(s)
		String sLocal = "Etwas";
		Function<String, Integer> f = sLocal::indexOf;
	}
}

class Square
{
	public Square(int i) {}
}
