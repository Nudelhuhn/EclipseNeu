package oop.intro;

enum Weekday { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};

public class EnumExample
{
    public static void main(String[] args)
    {
        Weekday day = Weekday.THURSDAY;
        System.out.println(day);
        
        Weekday day2 = Weekday.valueOf("THURSDAY");
        System.out.println(day == day2);

        try
        {
            Weekday day3 = Weekday.valueOf("DONNERSTAG");
            System.out.println(day == day3);
        }
        catch(Exception e)
        {
            System.out.println("So geht das aber nicht!");
        }
        
        System.out.println("Alle Tage: ");
        for(Weekday d: Weekday.values())
        {
            System.out.println("   " + d);
        }
    }
}
