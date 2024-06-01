package oop.execution;

public class ReturnAddresses
{
    public static void m1()
    {
        System.out.println("m1");
        if(Math.random() < 0.5)
        {
            m2();
            m4();
        }
        else
        {
            m3();
        }
    }

    public static void m2()
    {
        System.out.println("m2");
        if(Math.random() < 0.5)
        {
            m3();
        }
        else
        {
            m4();
        }
    }

    public static void m3()
    {
        System.out.println("m3");
        if(Math.random() < 0.5)
        {
            m1();
            m4();
        }
        else
        {
            m2();
        }
    }

    public static void m4()
    {
        System.out.println("m4");
        if(Math.random() < 0.1)
        {
            m3();
        }
    }
    
    public static void main(String[] args)
    {
        m1();
        System.out.println("-------------------------");
        m2();
    }
}
