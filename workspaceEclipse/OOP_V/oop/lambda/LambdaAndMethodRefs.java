package oop.lambda;

interface SomeInterface
{
    public void method(int i, String s);
}

interface OtherInterface
{
    public void method(OtherClass obj, int i, String s);
}

class SomeInterfaceImpl implements SomeInterface
{
    public void method(int i, String s)
    {
        System.out.println("method(" + i + ", " + s + ")");
    }
}

class SomeClassWithStatic
{
    public static void output(int i, String s)
    {
        System.out.println("output(" + i + ", " + s + ")");
    }
}

class SomeClass
{
    public void output(int i, String s)
    {
        System.out.println("output(" + this + ", " + i + ", " + s + ")");
    }
}

class OtherClass
{
    private String s;
    
    public OtherClass(String s)
    {
        this.s = s;
    }

    public String toString()
    {
        return "OtherClass-Objekt: " + s;
    }

    public void otherOutput(int i, String s)
    {
        System.out.println("otherOutput(" + this + ", " + i + ", " + s + ")");
    }
}

public class LambdaAndMethodRefs
{
    public static void useInterface(SomeInterface someInterface)
    {
        someInterface.method(47, "elf");
    }

    public static void useOtherInterface(OtherInterface otherInterface)
    {
        OtherClass obj = new OtherClass("hallo");
        otherInterface.method(obj, 47, "elf");
    }
    
    public static void main(String[] args)
    {
        useInterface(new SomeInterfaceImpl());
        
        SomeInterface someInt = new SomeInterface()
        {
            public void method(int i, String s)
            {
                System.out.println("method(" + i + ", " + s + ")");
            }
        };
        useInterface(someInt);
        
        useInterface((int i, String s) -> {System.out.println("method(" + i + ", " + s + ")");});
        useInterface((int i, String s) -> System.out.println("method(" + i + ", " + s + ")"));

        useInterface((int i, String s) -> SomeClassWithStatic.output(i, s));

        useInterface(SomeClassWithStatic::output);
        
        SomeClass sc = new SomeClass();
        useInterface((i, s)->sc.output(i, s));
        useInterface(sc::output);

        useOtherInterface((obj, i, s)->obj.otherOutput(i, s));
        useOtherInterface(OtherClass::otherOutput);

        
    }
}
