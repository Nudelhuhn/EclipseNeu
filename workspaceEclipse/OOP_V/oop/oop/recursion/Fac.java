package oop.recursion;

public class Fac
{
    public static long facIterative(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException();
        }
        
        long result = 1;
        for(int i = 2; i <= n; i++)
        {
            result *= i;
        }
        return result;
    }

    public static long facRecursive(int n)
    {
        if(n < 0)
        {
            throw new IllegalArgumentException();
        }
        
        //n >= 0
        if(n <= 1) //n == 0 || n == 1
        {
            return 1;
        }
        return n * facRecursive(n-1);
    }
    
    public static void main(String[] args)
    {
        int[] intArgs = {0, 1, 2, 6, 10, 20};
        for(int arg: intArgs)
        {
            long r1 = facIterative(arg);
            long r2 = facRecursive(arg);
            if(r1 == r2)
            {
                System.out.println(arg + "! = " + r1);
            }
            else
            {
                System.out.println("Fehler: unterschiedliche Resultate für " + arg + ": " + r1 + " und " + r2);
            }
        }
    }
}
