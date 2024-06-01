package oop.recursion;

class Element
{
    private int number;
    private Element next;
    
    public Element(Element next)
    {
        this.next = next;
    }
    
    public void setPositiveRandomNumber()
    {
        number = (int)(Math.random() * 100) + 1;
        if(next != null)
        {
            next.setNegativeRandomNumber();
        }
    }
    
    public void setNegativeRandomNumber()
    {
        number = (int)(Math.random() * -100) - 1;
        if(next != null)
        {
            next.setPositiveRandomNumber();
        }
    }
    
    public void print()
    {
        System.out.print(number);
        if(next != null)
        {
            System.out.print(" / ");
            next.print();
        }
        else
        {
            System.out.println();
        }
    }
}

public class PositiveNegativeRandomNumber
{
    public static void main(String[] args)
    {
        Element previous = null;
        Element current = null;
        for(int i = 0; i < 10; i++)
        {
            current = new Element(previous);
            previous = current;
        }
        
        current.print();
        current.setPositiveRandomNumber();
        current.print();
        current.setNegativeRandomNumber();
        current.print();
    }
}
