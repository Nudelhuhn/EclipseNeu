package oop.reflection;

import java.lang.reflect.*;
import java.util.ArrayList;

public class ReflectorForGenerics
{
    private static ArrayList<Type> seenTypes;
    
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
        seenTypes = new ArrayList<Type>();
        try
        {
            String headline = className + ":";
            System.out.println(headline);
            System.out.println("-".repeat(headline.length()));

            Class<?> c = Class.forName(className);
            
            System.out.println("  type parameters: ");
            Type typeParams[] = c.getTypeParameters();
            for(Type typeParam : typeParams)
            {
                printType(typeParam, 4);
            }
            
            System.out.println("  generic base class: ");
            Type genericBase = c.getGenericSuperclass();
            if(genericBase != null)
            {
                printType(genericBase, 4);
            }
            
            System.out.println("  generic implemented interfaces:");
            Type[] genericInterfaces = c.getGenericInterfaces();
            if(genericInterfaces.length == 1)
            {
                printType(genericInterfaces[0], 4);
            }
            else
            {
                int i = 0;
                for(Type genInterf: genericInterfaces)
                {
                    i++;
                    System.out.println(" ".repeat(4) + i + ". interface:");
                    printType(genInterf, 6);
                }
            }
            
            System.out.println("  declared attributes:");
            Field[] declaredAttributes = c.getDeclaredFields();
            handleAttributes(declaredAttributes);
            
            System.out.println("  declared constructors:");
            Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
            handleConstructors(declaredConstructors);
            
            System.out.println("  declared methods:");
            Method[] declaredMethods = c.getDeclaredMethods();
            handleMethods(declaredMethods);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(className + ": not found");
        }
    }
    
    private static void printType(Type t, int indentation)
    {
        System.out.print(" ".repeat(indentation));
        if(t instanceof Class<?>)
        {
            Class<?> cls = (Class<?>) t;
            if(cls.isInterface())
            {
                System.out.print("interface: ");
            }
            else
            {
                System.out.print("class: ");
            }
            System.out.println(cls.getName());
        }
        else if(t instanceof TypeVariable<?>)
        {
            TypeVariable<?> typeVar = (TypeVariable<?>) t;
            System.out.println("type variable: " + typeVar.getName());
            if(seenTypes.contains(t))
            {
                return;
            }
            seenTypes.add(t);
            System.out.println(" ".repeat(indentation+2) + "bounds:");
            Type[] bounds = typeVar.getBounds();
            if(bounds.length == 1)
            {
                printType(bounds[0], indentation+4);
            }
            else
            {
                int i = 0;
                for(Type bound : bounds)
                {
                    i++;
                    System.out.println(" ".repeat(indentation+4) + i + ". bound:");
                    printType(bound, indentation+6);
                }
            }
        }
        else if(t instanceof ParameterizedType)
        {
            ParameterizedType paramType = (ParameterizedType) t;
            Type rawType = paramType.getRawType();
            System.out.println("parameterized type:");
            System.out.println(" ".repeat(indentation+2) + "raw type:");
            printType(rawType, indentation+4);
            System.out.println(" ".repeat(indentation+2) + "actual type arguments:");
            Type[] actualTypeArgs = paramType.getActualTypeArguments();
            if(actualTypeArgs.length == 1)
            {
                printType(actualTypeArgs[0], indentation+4);
            }
            else
            {
                int i = 0;
                for(Type actualTypeArg : actualTypeArgs)
                {
                    i++;
                    System.out.println(" ".repeat(indentation+4) + i + ". argument:");
                    printType(actualTypeArg, indentation+6);
                }
            }
        }
        else if(t instanceof WildcardType)
        {
            System.out.println("wildcard type");
            System.out.println(" ".repeat(indentation+2) + "lower bounds:");
            WildcardType wildcardType = (WildcardType) t;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            if(lowerBounds.length == 1)
            {
                printType(lowerBounds[0], indentation+4);
            }
            else
            {
                int i = 0;
                for(Type lowerBound : lowerBounds)
                {
                    i++;
                    System.out.println(" ".repeat(indentation+4) + i + ". lower bound:");
                    printType(lowerBound, indentation+6);
                }
            }
            System.out.println(" ".repeat(indentation+2) + "upper bounds:");
            Type[] upperBounds = wildcardType.getUpperBounds();
            if(upperBounds.length == 1)
            {
                printType(upperBounds[0], indentation+4);
            }
            else
            {
                int i = 0;
                for(Type upperBound : upperBounds)
                {
                    i++;
                    System.out.println(" ".repeat(indentation+4) + i + ". upper bound:");
                    printType(upperBound, indentation+6);
                }
            }
        }
        else if(t instanceof GenericArrayType)
        {
            GenericArrayType arrayType = (GenericArrayType) t;
            System.out.println("generic array type with component type:");
            Type compType = arrayType.getGenericComponentType();
            printType(compType, indentation+2);
        }
        else
        {
            System.out.println("<<<unknown type!!!!!>>>");
        }
    }
    
    private static void handleAttributes(Field[] attributes)
    {
        for(Field attribute: attributes)
        {
            System.out.println("    " + attribute.getName());
            int mod = attribute.getModifiers();
            System.out.println("      modifiers: " + Modifier.toString(mod));
            Type genericType = attribute.getGenericType();
            System.out.println("      generic type:");
            printType(genericType, 8);
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
            System.out.println("      generic parameters: ");
            Type[] params = constructor.getGenericParameterTypes();
            int j = 0;
            for(Type param : params)
            {
                j++;
                System.out.println(" ".repeat(8) + j + ". parameter:");
                printType(param, 10);
            }
            System.out.println("      generic exceptions: ");
            Type[] exceptions = constructor.getGenericExceptionTypes();
            j = 0;
            for(Type exception : exceptions)
            {
                j++;
                System.out.println(" ".repeat(8) + j + ". exception:");
                printType(exception, 10);
            }
        }
    }
    
    private static void handleMethods(Method[] methods)
    {
        for(Method method: methods)
        {
            System.out.println("    " + method.getName() + ":");
            int mod = method.getModifiers();
            System.out.println("      modifiers: " + Modifier.toString(mod));
            System.out.println("      generic parameters: ");
            Type[] params = method.getGenericParameterTypes();
            int i = 0;
            for(Type param : params)
            {
                i++;
                System.out.println(" ".repeat(8) + i + ". parameter:");
                printType(param, 10);
            }
            System.out.println("      generic return type: ");
            Type returnType = method.getGenericReturnType();
            printType(returnType, 8);
            System.out.println("      generic exceptions: ");
            Type[] exceptions = method.getGenericExceptionTypes();
            i = 0;
            for(Type exception : exceptions)
            {
                i++;
                System.out.println(" ".repeat(8) + i + ". exception:");
                printType(exception, 10);
            }
        }
    }
}
//-----------------------------------------------------------------------
//Beispielklassen zum Ausprobieren:

class A<T1, T2> {}
class B<T1, T2> extends A<T1, C>
{
    private A<T2,D> a;
    public A<T2,D> m(B<C,D> b)
    {
        return a;
    }
}
class C {}
class D {}
class E {}

interface J1<T1,T2> {}
interface J2<T3,T4> {}

class K<T1, T2, T3> implements J1<T1, A<C,C>>, J2<B<D,E>,T2>
{
    private A<E,D> someData;
    
    public K(T1 t1, T2 t2)
    {
        someData = new A<E,D>();
    }
    
    public void someMethod(T1 t1, A<? super C, ? extends D> a)
    {
        System.out.println(someData);
    }
}

class L1<T0, T1> {}
class L2<T0 extends D, T1 extends E> {}
class L3<T>
{
    @SuppressWarnings("unused")
    private T t;
}
class L4<T extends D>
{
    @SuppressWarnings("unused")
    private T t;
}

interface Schnitt1 {}
interface Schnitt2 {}
class Klasse implements Schnitt1, Schnitt2 {}
class Xyz
{
    public int m(String s)
    {
        System.out.println("m(" + s + ")");
        return 4;
    }
    /*
    public String m(GenericClass<? extends Schnitt2> gc)
    {
        System.out.println("m(GenericClass<String>)");
        return "hallo";
    }
    */
    /*
    public static void main(String[] args)
    {
        Xyz xyz = new Xyz();
        GenericClass<Klasse> gck = new GenericClass<Klasse>();
        String s = xyz.m(gck);
        int i = xyz.m(gck);        
    }
    */
}

//class X1234<T> {}
class Y1234<T extends Y1234<T>>
{
    public <S extends C> void m(S t1, T t2) {}
}
