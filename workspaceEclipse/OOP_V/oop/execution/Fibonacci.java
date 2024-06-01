package oop.execution;

public class Fibonacci
{
    public static int fib(int n)
    {
        if(n <= 1)
        {
            return n;
        }
        int fib1 = fib(n-1);
        int fib2 = fib(n-2);
        return fib1 + fib2;
    }
    
    public static void main(String[] args)
    {
        for(int i = 0; i <= 15; i++)
        {
            System.out.println("fib(" + i + ") = " + fib(i));
        }
    }
}
