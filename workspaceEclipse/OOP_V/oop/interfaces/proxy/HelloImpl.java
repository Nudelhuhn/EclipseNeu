package oop.interfaces.proxy;

public class HelloImpl implements Hello
{
    private String[] greetings = {"Hallo", "Huhu", "Guten Tag,", "Sch�nen Tag,", "Gr��gott",
                                  "Moin moin", "Gr�ezi"};

    public String sayHelloTo(String name)
    {
        String greeting = greetings[(int)(Math.random()*greetings.length)];
        return greeting + " " + name;
    }

}
