package oop.collections;

import java.util.*;

class Element
{
    private int i;

    public Element(int i)
    {
        this.i = i;
    }

    public int getI()
    {
        return i;
    }

    public void setI(int i)
    {
        this.i = i;
    }
    
    @Override
    public String toString()
    {
        return "" + i;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        if(!(o instanceof Element))
        {
            return false;
        }
        Element other = (Element) o;
        return this.i == other.i;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(i);
    }
}

public class ListExample
{
    public static void main(String[] args)
    {
        List<Element> list = new LinkedList<>();
        Element element1 = new Element(1);
        list.add(element1);
        list.add(element1);
        System.out.println(list);
        
        Element element2 = new Element(1);
        list.add(element2);
        System.out.println(list);

        Element element3 = new Element(1);
        list.add(element3);
        System.out.println(list);

        element1.setI(3);
        System.out.println(list);

        list.add(null);
        System.out.println(list);
        
        element1.setI(1);
        while(list.size()>1)
        {
            list.remove(element3);
            System.out.println(list);
        }
    }
}
