package oop.lambda;

interface MyPredicate<S>
{
    public boolean test(S s);
    
    public default MyPredicate<S> negate()
    {
        return s -> !this.test(s);
    }
    
    public static <T> MyPredicate<T> alwaysTrue()
    {
        return t -> true;
    }
}

class OldFashionedClass implements MyPredicate<String>
{
    @Override
    public boolean test(String s)
    {
        return s == null || s.length() == 0;
    }    
}

class NotFoundException extends Exception
{
}

class SomeStrings
{
    private String[] array;

    public SomeStrings(String[] array)
    {
        this.array = array.clone();
    }

    public String findFirst(MyPredicate<String> pred) throws NotFoundException
    {
        for(String s: array)
        {
            if(pred.test(s))
            {
                return s;
            }
        }
        throw new NotFoundException();
    }
}

public class MyPredicateMain
{
    public static void main(String[] args)
    {
        String[] example = new String[] {"null", "   ", "hallo"};
        SomeStrings strings = new SomeStrings(example);
        try
        {
            String result;
            
            System.out.println("1. Fall:");
            MyPredicate<String> empty = s -> s == null || s.trim().length() == 0; 
            result = strings.findFirst(empty);
            output(result);
            
            System.out.println("2. Fall:");
            result = strings.findFirst(empty.negate());
            output(result);
            
            System.out.println("3. Fall:");
            MyPredicate<String> always = MyPredicate.alwaysTrue();
            result = strings.findFirst(always);
            output(result);
            
            System.out.println("4. Fall:");
            result = strings.findFirst(always.negate());
            output(result);
        }
        catch(NotFoundException e)
        {
            System.out.println("nicht gefunden");
        }
    }
    
    private static void output(String result)
    {
        if(result == null)
        {
            System.out.println("null gefunden");
        }
        else
        {
            System.out.println("String der Länge " + result.length() + " gefunden: \"" + result + "\"");
        }
    }
}
