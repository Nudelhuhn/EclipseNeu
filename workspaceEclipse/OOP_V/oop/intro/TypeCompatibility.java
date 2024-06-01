package oop.intro;

class A
{
}

class B extends A
{
}

class C extends B
{
}

public class TypeCompatibility
{
    private static B makeObject()
    {
        return new C();
    }
    
    public static void main(String[] args)
    {
        C obj = (C)makeObject();
        System.out.format("%s / %x", obj.toString(), obj.hashCode());
    }
}
