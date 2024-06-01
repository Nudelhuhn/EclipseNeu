package oop.hanoi;

public class Move
{
    private int from, to;

    public Move(int from, int to)
    {
        if (from < 0 || from > 2 || to < 0 || to > 2 || from == to)
        {
            throw new IllegalArgumentException();
        }
        this.from = from;
        this.to = to;
    }

    public int getFrom()
    {
        return from;
    }

    public int getTo()
    {
        return to;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Move))
        {
            return false;
        }
        Move other = (Move) o;
        return this.getFrom() == other.getFrom() && this.getTo() == other.getTo();
    }

    @Override
    public String toString()
    {
        return from + " => " + to;
    }
}
