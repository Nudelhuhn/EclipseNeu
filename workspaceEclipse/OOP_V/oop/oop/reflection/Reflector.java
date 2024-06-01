package oop.reflection;

import java.lang.reflect.*;

public class Reflector
{
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("arguments: list of class names");
            return;
        }
        for(String className: args)
        {
            analyze(className);
            System.out.println("=".repeat(40));
        }
    }

    private static void analyze(String className)
    {
        try
        {
            String headline = className + ":";
            System.out.println(headline);
            System.out.println("-".repeat(headline.length()));

            Class<?> c = Class.forName(className);
            
            System.out.print("  module: ");
            System.out.println(c.getModule().getName());
            System.out.print("  package: ");
            System.out.println(c.getPackage().getName());
            System.out.print("  class or interface: ");
            System.out.println(c.isInterface() ? "interface" : "class");
            System.out.print("  simple name: ");
            System.out.println(c.getSimpleName());
            System.out.print("  full name: ");
            System.out.println(c.getName());
            System.out.print("  modifiers: ");
            int modifiers = c.getModifiers();
            System.out.println(Modifier.toString(modifiers));
            
            System.out.print("  base class: ");
            Class<?> base = c.getSuperclass();
            if(base != null)
            {
                System.out.println(base.getName());
            }
            else
            {
                System.out.println("no base class");
            }
            
            System.out.println("  interfaces:");
            Class<?>[] interfaces = c.getInterfaces();
            for(Class<?> interf: interfaces)
            {
                System.out.println("    " + interf.getName());
            }
            
            System.out.println("  declared attributes:");
            Field[] declaredAttributes = c.getDeclaredFields();
            handleAttributes(declaredAttributes);
            
            System.out.println("  attributes:");
            Field[] attributes = c.getFields();
            handleAttributes(attributes);
            
            System.out.println("  declared constructors:");
            Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
            handleConstructors(declaredConstructors);
            
            System.out.println("  constructors:");
            Constructor<?>[] constructors = c.getConstructors();
            handleConstructors(constructors);
            
            System.out.println("  declared methods:");
            Method[] declaredMethods = c.getDeclaredMethods();
            handleMethods(declaredMethods);
            
            System.out.println("  methods:");
            Method[] methods = c.getMethods();
            handleMethods(methods);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(className + ": not found");
        }
    }
    
    private static void handleAttributes(Field[] attributes)
    {
        for(Field attribute: attributes)
        {
            System.out.println("    " + attribute.getName());
            int mod = attribute.getModifiers();
            System.out.println("      modifiers: " + Modifier.toString(mod));
            Class<?> type = attribute.getType();
            System.out.println("      type: " + type.getName());
        }
    }
    
    private static void handleConstructors(Constructor<?>[] constructors)
    {
        int i = 0;
        for(Constructor<?> constructor: constructors)
        {
            i++;
            System.out.println("    " + i + ". constructor:");
            int mod = constructor.getModifiers();
            System.out.println("      modifiers: " + Modifier.toString(mod));
            System.out.print("      parameters:");
            Class<?>[] params = constructor.getParameterTypes();
            for(Class<?> param : params)
            {
                System.out.print(" " + param.getName());
            }
            System.out.println();
            System.out.print("      exceptions:");
            Class<?>[] exceptions = constructor.getExceptionTypes();
            for(Class<?> exception : exceptions)
            {
                System.out.print(" " + exception.getName());
            }
            System.out.println();
        }
    }
    
    private static void handleMethods(Method[] methods)
    {
        for(Method method: methods)
        {
            System.out.println("    " + method.getName() + ":");
            int mod = method.getModifiers();
            System.out.println("      modifiers: " + Modifier.toString(mod));
            System.out.print("      parameters:");
            Class<?>[] params = method.getParameterTypes();
            for(Class<?> param : params)
            {
                System.out.print(" " + param.getName());
            }
            System.out.println();
            System.out.println("      return type: " +
                               method.getReturnType().getName());
            System.out.print("      exceptions:");
            Class<?>[] exceptions = method.getExceptionTypes();
            for(Class<?> exception : exceptions)
            {
                System.out.print(" " + exception.getName());
            }
            System.out.println();
        }
    }
}
//-----------------------------------------------------------------------
//Beispielklassen zum Ausprobieren:

interface I
{
    public int getPublicInt();
}

class X<T> implements I
{
    private int privateInt;
    protected int protectedInt;
    int defaultVisInt;
    public int publicInt;
    
    private X(int i)
    {
        privateInt = i;
    }
    
    protected X(int i1, int i2)
    {
        this(i1);
        protectedInt = i2;
    }
    
    public X(int i1, int i2, int i3)
    {
        this(i1, i2);
        //publicInt = i3;
    }
    
    @SuppressWarnings("unused")
    private int getPrivateInt()
    {
        return privateInt;
    }
    
    @SuppressWarnings("unused")
    private void setPrivateInt(int privateInt)
    {
        this.privateInt = privateInt;
    }
    
    protected int getProtectedInt()
    {
        return protectedInt;
    }
    
    protected void setProtectedInt(int protectedInt)
    {
        this.protectedInt = protectedInt;
    }

    int getDefaultVisInt()
    {
        //return defaultVisInt;
        return 0;
    }

    void setDefaultVisInt(int defaultVisInt)
    {
        //this.defaultVisInt = defaultVisInt;
    }

    public int getPublicInt()
    {
        //return publicInt;
        return 0;
    }

    public void setPublicInt(int publicInt)
    {
        //this.publicInt = publicInt;
    }
}

final class Y<T> extends X<T> implements I
{
    private String privateString;
    protected String protectedString;
    //String defaultVisString;
    //public String publicString;

    protected Y()
    {
        super(1, 2, 3);
    }

    public Y(int i)
    {
        super(i, 2, 3);
    }
    
    public String getPrivateString()
    {
        return privateString;
    }
    
    public void setPrivateString(String privateString)
    {
        this.privateString = privateString;
    }
}

class Z
{
    public void m(X<?> x)
    {
        System.out.println("m called");
    }
}

class Simple
{
    public void m(Integer i)
    {
    }
}

class Derived extends Simple
{
    public void m(Integer i)
    {
    }
}

enum Weekday { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};