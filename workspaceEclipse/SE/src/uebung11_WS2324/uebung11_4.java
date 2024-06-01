package uebung11_WS2324;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class uebung11_4
{
	// int... => beliebig viele Integerzahlen als Parameter also z.B.
	// createSet(1,2,3,4), createSet(23,11)
	public Set<Integer> createSet(boolean sorted, int... numbers){
		Set<Integer> set;
		if(sorted == true) {
			set = new TreeSet<Integer>();
		}else {
			set = new HashSet<Integer>();
		}
		for(int number : numbers) {
			set.add(number);
		}
		return set;
	}
	
	public List<Integer> toList(int... numbers){
		List<Integer> list;
		if(numbers == null) {
			return Collections.EMPTY_LIST;
		}else if(numbers.length == 1){
			return Collections.singletonList(numbers[0]);
		}else {
			list = new ArrayList<Integer>();
			for(int number : numbers) {
				list.add(number);
			}
			return list;
		}
	}
	
	public Number createNumber(double value) {
		int rounded = (int) Math.round(value);
		double x = value - rounded;
		if(x >= -0.2 && x <= 0.2) {
			return new Integer(rounded);
		}else {
			return new Double(value);
		}
	}
	
	
	public static void main(String[] args) {
		uebung11_4 obj = new uebung11_4();
		System.out.println(obj.createSet(true, 1,2,3));
		System.out.println(obj.createSet(false, 1,2,3));
		System.out.println(obj.toList());
		System.out.println(obj.toList(2));
		System.out.println(obj.toList(22,77,55,34));
		System.out.println(obj.createNumber(0.79));
		System.out.println(obj.createNumber(0.9));
		System.out.println(obj.createNumber(11.1));
	}
}