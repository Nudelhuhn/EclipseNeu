package oop.reflection;

import java.lang.reflect.*;

class CallSample
{
    public void m1()
    {
        System.out.println("m1()");
    }
    
    public void m2()
    {
        System.out.println("m2()");
    }
    
    public void m3()
    {
        System.out.println("m3()");
    }
    
    public void m4(int i)
    {
        System.out.println("m4(" + i + ")");
    }
    
    public void m5(int i1, int i2)
    {
        System.out.println("m5("+ i1 + "," + i2 + ")");
    }
    
    public void m6(boolean b, int i)
    {
        System.out.println("m6("+ b + "," + i + ")");
    }
    
    public void m7(Integer i1, Integer i2)
    {
        System.out.println("m7("+ i1 + "," + i2 + ")");
    }
    
    public void m8(Integer i1, int i2)
    {
        System.out.println("m8("+ i1 + "," + i2 + ")");
    }
    
    void m9(int i1, int i2)
    {
        System.out.println("m9("+ i1 + "," + i2 + ")");
    }
    
    protected void m10(int i1, int i2)
    {
        System.out.println("m10("+ i1 + "," + i2 + ")");
    }
    
//    @SuppressWarnings("unused")
//    private void m11(int i1, int i2)
//    {
//        System.out.println("m11("+ i1 + "," + i2 + ")");
//    }
}

public class CallTwoInts
{
    public static void main(String[] args)
    {
        try
        {
            call("oop.reflection.CallSample", 1, 2);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    /*
     * For each method in class with two int parameters:
     * create object, and call method with given arguments
     */
    public static void call(String className, int i1, int i2) throws Exception
    {
        Class<?> classDesc = Class.forName(className);

        Method[] methods = classDesc.getDeclaredMethods();
        for(Method m : methods)
        {
            Class<?>[] params = m.getParameterTypes();
            if(params.length == 2 &&
                params[0] == int.class &&
                params[1] == int.class)
            {
                Object obj = classDesc.getDeclaredConstructor().newInstance();
                //m.setAccessible(true);
                m.invoke(obj, i1, i2);
            }
        }
    }
}
