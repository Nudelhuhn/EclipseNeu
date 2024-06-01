package oop.collections;

import java.util.*;

public class SetExample
{
    public static void main(String[] args)
    {
//        Set<String> set1 = new HashSet<>();
//        set1.add(new String("hallo"));
//        set1.add(new String("hallo"));
//        System.out.println(set1);
//        
//        Set<Element> set2 = new HashSet<>();
//        Element element1 = new Element(1);
//        set2.add(element1);
//        set2.add(element1);
//        set2.add(element1);
//        set2.add(element1);
//        System.out.println(set2);
//        Element element2 = new Element(1);
//        set2.add(element2);
//        System.out.println(set2);
//        Element element3 = new Element(3);
//        set2.add(element3);
//        System.out.println(set2);
//        element3.setI(1);
//        System.out.println(set2);
//        set2.add(null);
//        set2.add(null);
//        System.out.println(set2);
        
        String string = "Ich heiße Rainer Oechsle und arbeite an der Hochschule Trier";
        String[] elements = string.split(" "); 
        Set<String> set3 = new HashSet<>();
        for(String elem: elements)
        {
            set3.add(elem);
        }
        System.out.println(set3);
        String otherString = "Und das ist gut so";
        String[] otherElements = otherString.split(" "); 
        for(String elem: otherElements)
        {
            set3.add(elem);
        }
        System.out.println(set3);
        
        Set<String> set4 = new LinkedHashSet<>();
        for(String elem: elements)
        {
            set4.add(elem);
        }
        System.out.println(set4);
        for(String elem: otherElements)
        {
            set4.add(elem);
        }
        System.out.println(set4);

        Set<String> set5 = new TreeSet<>();
        for(String elem: elements)
        {
            set5.add(elem);
        }
        System.out.println(set5);
        for(String elem: otherElements)
        {
            set5.add(elem);
        }
        System.out.println(set5);
        
        Person p1 = new Person("Heinz", "Schmitz");
        Person p2 = new Person("Christoph", "Schmitz");
        Person p3 = new Person("Georg", "Schneider");
        Person p4 = new Person("Jörn", "Schneider");
        Person p5 = new Person("Georg", "Rock");
        Person p6 = new Person("Christoph", "Lürig");        
        Set<Person> profs = new TreeSet<>((personA, personB) -> personA.compareByFamilyName(personB));
        Collections.addAll(profs, p1, p2, p3, p4, p5, p6);
        System.out.println(profs);
    }
}
