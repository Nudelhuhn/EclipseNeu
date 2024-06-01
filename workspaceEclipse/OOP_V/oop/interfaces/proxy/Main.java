package oop.interfaces.proxy;

public class Main
{
    public static void main(String[] args)
    {
        HelloImpl hello = new HelloImpl();
        String[] names = {"Klaus", "Peter", "Max", "Ben", "Alena", "Anja", "Carolina", "Luisa"};
        LoggingProxy log1 = new LoggingProxy(hello, ">>>");
        TimeSwitch ts = new TimeSwitch(log1);
        ts.setStartTime(System.currentTimeMillis() + 10000);
        ts.setEndTime(System.currentTimeMillis() + 60000);
        LoggingProxy log2 = new LoggingProxy(ts, "+++");
        useHello(log2, names, 10);
    }
    
    private static void useHello(Hello hello, String[] names, int times)
    {
        for(int i = 0; i < times; i++)
        {
            for(String name: names)
            {
                System.out.println(hello.sayHelloTo(name));
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                }
            }
        }
    }
}
