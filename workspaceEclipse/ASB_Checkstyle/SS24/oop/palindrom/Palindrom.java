package oop.palindrom;

public class Palindrom
{
    public static boolean check(String word)
    {
        int start = 0;
        int end = word.length() - 1;
        while (start < end)
        {
            if (word.charAt(start) != word.charAt(end))
            {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args)
    {
        System.out.println(check("1öllö1")); // true
        System.out.println(check("1")); // true
        System.out.println(check("Hallo")); // false
    }
}
