package oop.interfaces;

interface I
{
    public void m();
}

class C implements I
{
    public void m()
    {
    }
}

public class SimpleExample
{
    public static void main(String[] args)
    {
        I i = new C();
        C c = new C();
        n(c);
        n(i);
    }
    
    private static void n(I i)
    {
        i.m();
    }
}
