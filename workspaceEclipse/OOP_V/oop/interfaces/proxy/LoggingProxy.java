package oop.interfaces.proxy;

public class LoggingProxy extends GeneralProxy
{
    private String prefix;
    
    public LoggingProxy(Hello hello, String prefix)
    {
        super(hello);
        this.prefix = prefix;
    }

    @Override
    public String sayHelloTo(String name)
    {
        System.out.println(prefix + "Aufruf von sayHello(" + name + ")");
        String result = hello.sayHelloTo(name);
        System.out.println(prefix + "Rückgabe: " + result);
        return result;
    }

}
