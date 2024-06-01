package oop.intro;

public class AutoboxingAutounboxing
{
    public static void main(String[] args)
    {
        int iPrim = 200;
        Integer iRef = iPrim; 
        iPrim = iRef;
        
        Integer iRef1 = Integer.valueOf(11);
        Integer iRef2 = Integer.valueOf(11);
        System.out.println("equals: " + iRef1.equals(iRef2));
        System.out.println("==: " + (iRef1 == iRef2));
        Integer iRef3 = Integer.valueOf(190);
        Integer iRef4 = Integer.valueOf(190);
        System.out.println("equals: " + iRef3.equals(iRef4));
        System.out.println("==: " + (iRef3 == iRef4));
    }
}
