package uebung9;

import java.util.function.Function;

public class Aufgabe7
{
	public static void main(String[] args)
	{
		Function<String, Integer> stringLength = String::length;
		Function<Integer, String> intToString = Object::toString;

		Function<String, String> stringLengthAndThenIntToString = stringLength.andThen(intToString);

		String input = "Hello";
		String result = stringLengthAndThenIntToString.apply(input);
		System.out.println(result); // Ausgabe: "5"

	}
}
