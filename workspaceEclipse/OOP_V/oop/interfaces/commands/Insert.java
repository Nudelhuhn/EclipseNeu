package oop.interfaces.commands;

class Insert implements Command<String>
{
    private String insertionString;
    private int position;

    public Insert(String insertionString, int position)
    {
        this.insertionString = insertionString;
        this.position = position;
    }

    public Insert(String insertionString)
    {
        this(insertionString, 0);
    }

    @Override
    public String execute(String s)
    {
        try
        {
            String before = s.substring(0, position);
            String after = s.substring(position);
            return before + insertionString + after;
        }
        catch(Exception e)
        {
            return s;
        }
    }
    
    public String toString()
    {
        return "Einfügen von '" + insertionString + "' an Position " + position;
    }
}
