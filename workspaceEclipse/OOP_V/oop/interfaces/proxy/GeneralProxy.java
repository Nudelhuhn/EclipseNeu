package oop.interfaces.proxy;

public abstract class GeneralProxy implements Hello
{
    protected Hello hello;

    public GeneralProxy(Hello hello)
    {
        this.hello = hello;
    }
}
