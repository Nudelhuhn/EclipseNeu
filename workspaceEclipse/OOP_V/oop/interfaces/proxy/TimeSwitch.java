package oop.interfaces.proxy;

public class TimeSwitch extends GeneralProxy
{
    private long startTime, endTime;

    public TimeSwitch(Hello hello)
    {
        super(hello);
    }
    
    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }

    @Override
    public String sayHelloTo(String name)
    {
        long now = System.currentTimeMillis();
        if(startTime <= now && now <= endTime)
        {
            return hello.sayHelloTo(name);
        }
        return null;
    }

}
