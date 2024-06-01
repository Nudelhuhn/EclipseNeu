package oop.execution;

public class CallByValue
{
    public static void method(int x)
    {
        x = x + 1;
    }

    public static void main(String[] args)
    {
        int x = 4;
        method(x);
        int y = 8;
        method(y);
        System.out.println(x);
        System.out.println(y);
    }
}
