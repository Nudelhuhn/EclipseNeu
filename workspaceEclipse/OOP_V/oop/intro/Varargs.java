package oop.intro;

public class Varargs
{
    public static int times(int ... factors)
    {
        int result = 1;
        for(int factor: factors)
        {
            result *= factor;
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        int fak = times(1, 2, 3, 4, 5, 6);
        System.out.println(fak);
    }
}
