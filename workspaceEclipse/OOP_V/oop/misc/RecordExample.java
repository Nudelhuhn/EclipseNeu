package oop.misc;

@SuppressWarnings("preview")
record MyTwoTuple(int number, String string)
{
    public MyTwoTuple(int number)
    {
        this(number, "unbekannt");
    }

    public MyTwoTuple(String s)
    {
        this(0, s);
    }
    
    public String toString()
    {
        return "(" + number + ", " + string + ")";
    }
}

public class RecordExample
{
    public static void main(String[] args)
    {
        MyTwoTuple tt1 = new MyTwoTuple(47, "elf");
        MyTwoTuple tt2 = new MyTwoTuple(17, "vier");
        System.out.println(tt1 + " (" + tt1.number() + ", " + tt1.string() + ")");
        System.out.println(tt2 + " (" + tt2.number() + ", " + tt2.string() + ")");
        
        MyTwoTuple tt3 = new MyTwoTuple(47, "elf");
        System.out.println("tt1.equals(tt2) = " + tt1.equals(tt2));
        System.out.println("tt1.equals(tt3) = " + tt1.equals(tt3));
    }
}
