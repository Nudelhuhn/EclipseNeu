package oop.interfaces.proxy;

public class HelloImpl implements Hello
{
    private String[] greetings = {"Hallo", "Huhu", "Guten Tag,", "Schönen Tag,", "Grüßgott",
                                  "Moin moin", "Grüezi"};

    public String sayHelloTo(String name)
    {
        String greeting = greetings[(int)(Math.random()*greetings.length)];
        return greeting + " " + name;
    }

}
