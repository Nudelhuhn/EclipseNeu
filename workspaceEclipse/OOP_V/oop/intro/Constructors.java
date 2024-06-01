package oop.intro;

class Base
{
    public Base(int i)
    {
    }
}

class Derived extends Base
{
    public Derived()
    {
        this(3);
    }
    
    public Derived(int i)
    {
        super(i);
    }
}

public class Constructors
{
    public static void main(String[] args)
    {
        new Derived();
    }
}
