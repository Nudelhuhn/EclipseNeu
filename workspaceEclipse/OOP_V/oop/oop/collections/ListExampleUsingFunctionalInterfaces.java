package oop.collections;

import java.util.*;

public class ListExampleUsingFunctionalInterfaces
{
    public static void main(String[] args)
    {
        List<String> list = new LinkedList<>();
        Collections.addAll(list, "1", "", " ", "zwei", null, "Polizei");
        list.removeIf(s -> s == null || s.trim().isEmpty());
        System.out.println(list); // [1, zwei, Polizei]
        
        list.forEach(System.out::println);
        
        list.replaceAll(s -> s.toUpperCase());
        System.out.println(list); // [1, ZWEI, POLIZEI]
    }
}
