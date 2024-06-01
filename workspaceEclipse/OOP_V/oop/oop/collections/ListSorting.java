package oop.collections;

import java.util.*;

class Person
{
    private String givenName, familyName;

    public Person(String givenName, String familyName)
    {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public String getGivenName()
    {
        return givenName;
    }

    public String getFamilyName()
    {
        return familyName;
    }
    
    public int compareByFamilyName(Person p)
    {
        int familyComparison = familyName.compareToIgnoreCase(p.getFamilyName());
        if(familyComparison != 0)
        {
            return familyComparison;
        }
        return givenName.compareToIgnoreCase(p.getGivenName());
    }
    
    public int compareByGivenName(Person p)
    {
        int givenComparison = givenName.compareToIgnoreCase(p.getGivenName());
        if(givenComparison != 0)
        {
            return givenComparison;
        }
        return familyName.compareToIgnoreCase(p.getFamilyName());
    }
    
    public String toString()
    {
        return givenName + " " + familyName;
    }
}

public class ListSorting
{
    public static void main(String[] args)
    {
        Person p1 = new Person("Heinz", "Schmitz");
        Person p2 = new Person("Christoph", "Schmitz");
        Person p3 = new Person("Georg", "Schneider");
        Person p4 = new Person("Jörn", "Schneider");
        Person p5 = new Person("Georg", "Rock");
        Person p6 = new Person("Christoph", "Lürig");
        
        List<Person> profs = new ArrayList<>();
        Collections.addAll(profs, p1, p2, p3, p4, p5, p6);
        System.out.println(profs);
        
        profs.sort((person1, person2) -> person1.compareByFamilyName(person2));
        System.out.println(profs);
        
        Collections.shuffle(profs);
        System.out.println(profs);
        
        profs.sort((person1, person2) -> person1.compareByGivenName(person2));
        System.out.println(profs);
        
        Collections.reverse(profs);
        System.out.println(profs);
    }
}
