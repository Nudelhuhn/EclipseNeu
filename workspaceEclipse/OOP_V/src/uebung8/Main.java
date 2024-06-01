package uebung8;

public class Main
{

	public static void main(String[] args)
	{
		HelloImpl hello = new HelloImpl();
		String[] names = {"Klaus", "Max", "Ben", "Alena", "Anja", "Carolina", "Luisa"};
		LoggingProxy log1 = new LoggingProxy(hello, ">>>");
		TimeSwitch ts = new TimeSwitch(log1);
		ts.setStartTime((System.currentTimeMillis() + 10000));
		ts.setEndTime((System.currentTimeMillis() + 60000));
		LoggingProxy log2 = new LoggingProxy(ts, "+++");
		useHello(log2, names, 10);
	}

	private static void useHello(Hello hello, String[]names, int times) {
		for(int i = 0; i < times; i++) {
			for(String name : names) {
				System.out.println(hello.sayHelloTo(name));
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					
				}
			}
		}
	}
	
}
class TimeSwitch extends GeneralProxy{
	private long startTime, endTime;
	
	public TimeSwitch(Hello hello) {
		super(hello);
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String sayHelloTo(String name) {
		long now = System.currentTimeMillis();
		if(startTime <= now && now <= endTime) {
			return hello.sayHelloTo(name);
		}
		return null;
	}
}

class LoggingProxy extends GeneralProxy{
	private String prefix;
	
	public LoggingProxy(Hello hello, String prefix) {
		super(hello);
		this.prefix = prefix;
	}
	public String sayHelloTo(String name) {
		System.out.println(prefix + "Aufruf von sayHello(" + name + ")");
		String result = hello.sayHelloTo(name);
		System.out.println(prefix + "Rückgabe: " + result);
		return result;
	}
}

abstract class GeneralProxy implements Hello{
	protected Hello hello;
	public GeneralProxy(Hello hello) {
		this.hello = hello;
	}
}

class HelloImpl implements Hello{
	private String[] greetings = {"Hallo", "Huhu", "Guten Tag", "Schönen Tag", "Grüßgott", "Moin moin", "Grüezi"};
	
	public String sayHelloTo(String name) {
		String greeting = greetings[(int)(Math.random()*greetings.length)];
		return greeting + " " + name;
	}
}

interface Hello{
	public String sayHelloTo(String name);
}
