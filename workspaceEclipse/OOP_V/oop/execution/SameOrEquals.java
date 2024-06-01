package oop.execution;

public class SameOrEquals
{
    public static void main(String[] args)
    {
        String s1 = new String("Hallo Welt");
        String s2 = s1;
        String s3 = new String("Hallo Welt");
        System.out.println(s1 == s2); //true
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s3); //false
        System.out.println(s1.equals(s3)); //true
    }
}
