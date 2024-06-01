package oop.eclipse;

public class HelloPlanets
{
    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("Zwei Argumente: Gruß Anzahl");
            return;
        }
        
        try
        {
            String greeting = args[0];
            int number = Integer.parseInt(args[1]);
            Planets planets = new Planets();
            while(number > 0)
            {
                if(number % 2 == 0)
                {
                    System.out.println(greeting + " " + planets.getRandomPlanet());
                }
                else
                {
                    System.err.println("***" + greeting + " " + planets.getRandomPlanet());
                }
                number--;
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("Sie müssen als zweites Argument eine Zahl eingeben");
        }
    }
}
