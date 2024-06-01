package oop.collections;

import java.util.*;
import java.util.function.BiConsumer;

public class HashMapExample
{
    public static void main(String[] args)
    {
        Map<String,Number> num = new HashMap<>();
        num.put( "zwei", 2 );
        num.put( "drei", 3.0 );
        BiConsumer<String,Number> action = 
            (key, value) -> System.out.println( key + " = " + value);
        num.forEach( action );
        
        String base = "Ich heiﬂe Rainer Oechsle und arbeite an der Hochschule Trier";
        String[] elements = base.split(" "); 
        Map<String, Integer> map1 = new HashMap<>();
        for(String elem: elements)
        {
            map1.put(elem, elem.length());
        }
        System.out.println(map1);
        
        Map<String, Integer> map2 = new LinkedHashMap<>();
        for(String elem: elements)
        {
            map2.put(elem, elem.length());
        }
        System.out.println(map2);

        Map<String, Integer> map3 = new TreeMap<>();
        for(String elem: elements)
        {
            map3.put(elem, elem.length());
        }
        System.out.println(map3);
    }
}
