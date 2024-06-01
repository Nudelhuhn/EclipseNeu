package oop.execution;

class X
{
    private int x;

    public X(int x)
    {
        this.x = x;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }
}

public class CallByReference
{
    public static void method1(X x)
    {
        x = new X(47);
    }
    
    public static void method2(X x)
    {
        x.setX(47);
    }

    public static void main(String[] args)
    {
        X x = new X(1);
        method1(x);
        System.out.println(x.getX());
        method2(x);
        System.out.println(x.getX());
    }
}
