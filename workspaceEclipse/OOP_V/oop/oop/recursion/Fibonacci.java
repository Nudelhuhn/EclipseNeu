package oop.recursion;

public class Fibonacci
{
    public static long fibRecursive(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException();
        }

        if(n <= 1)
        {
            return n;
        }
        long fib1 = fibRecursive(n-1);
        long fib2 = fibRecursive(n-2);
        return fib1 + fib2;
    }
    
    public static void main(String[] args)
    {
        int[] intArgs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 20, 30 , 40, 50};
        for(int arg: intArgs)
        {
            long result = fibRecursive(arg);
            System.out.println("fib(" + arg + ") = " + result);
        }
    }
}
