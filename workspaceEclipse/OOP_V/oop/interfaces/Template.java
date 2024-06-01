package oop.interfaces;

public class Template
{
    public static void execute(Application app)
    {
        app.step1();
        while(app.mustContinueStep2())
        {
            app.step2();
        }
        app.step3();
    }
}

interface Application
{
    public void step1();
    public boolean mustContinueStep2();
    public void step2();
    public void step3();
}
