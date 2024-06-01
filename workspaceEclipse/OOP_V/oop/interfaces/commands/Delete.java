package oop.interfaces.commands;

class Delete implements Command<String>
{
    private int begin, end;

    public Delete(int begin, int end)
    {
        if(begin > end)
        {
            throw new IllegalArgumentException();
        }
        this.begin = begin;
        this.end = end;
    }

    public Delete(int position)
    {
        this(position, position);
    }

    @Override
    public String execute(String s)
    {
        try
        {
            String firstPart, secondPart;
            if(begin > 0)
            {
                firstPart = s.substring(0, begin);
            }
            else
            {
                firstPart = "";
            }
            secondPart = s.substring(end+1);
            return firstPart + secondPart;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return s;
        }
    }
    
    public String toString()
    {
        if(begin < end)
        {
            return "Löschen der Zeichen von Position " + begin + " bis " + end;
        }
        else
        {
            return "Löschen des Zeichens an Position " + begin;
        }
    }
}