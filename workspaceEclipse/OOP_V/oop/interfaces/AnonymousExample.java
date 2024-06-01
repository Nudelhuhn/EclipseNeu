package oop.interfaces;

interface SomeInterface
{
    public void method(int i, String s);
    public default void methodVerbose(int i, String s)
    {
        System.out.println("calling method with " + i + " and " + s + ":");
        method(i, s);
    }
}

public class AnonymousExample
{
    public static void main(String[] args)
    {
        SomeInterface si = new SomeInterface()
        {
            public void method(int i, String s)
            {
                System.out.println("this is method(" + i + ", " + s + ")");
            }
        };
        si.method(47, "elf");
        si.methodVerbose(11, "siebenundvierzig");
    }
}
