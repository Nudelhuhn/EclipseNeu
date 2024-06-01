package oop.interfaces.commands;

class Append implements Command<String>
{
    private String appendString;

    public Append(String appendString)
    {
        this.appendString = appendString;
    }

    @Override
    public String execute(String s)
    {
        return s + appendString;
    }
    
    public String toString()
    {
        return "Anh�ngen von '" + appendString + "' am Ende";
    }
}
