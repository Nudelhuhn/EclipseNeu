package oop.interfaces.observer;

public class Observers implements ChangeObserver
{
    private ChangeObserver chOb;

    private int from;

    private int to;

    public Observers(ChangeObserver chOb, int from, int to)
    {
        this.chOb = chOb;
        this.from = from;
        this.to = to;
    }

    public int getFrom()
    {
        return from;
    }

    public void setFrom(int from)
    {
        this.from = from;
    }

    public int getTo()
    {
        return to;
    }

    public void setTo(int to)
    {
        this.to = to;
    }

    public ChangeObserver getChOb()
    {
        return chOb;
    }

    public void setChOb(ChangeObserver chOb)
    {
        this.chOb = chOb;
    }

    public boolean compare(ChangeObserver a, int b, int c)
    {
        return a == chOb && b == from && c == to;
    }

    @Override
    public void valueHasChanged(int oldValue, int newValue)
    {
        if (newValue >= from && newValue <= to)
        {
            chOb.valueHasChanged(oldValue, newValue);
        }
    }
}
