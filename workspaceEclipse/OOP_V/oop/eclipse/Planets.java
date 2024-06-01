package oop.eclipse;

public class Planets
{
    private String[] planets = new String[] {"Welt", "Mond", "Sonne", "Neptun", "Saturn", "Jupiter"};
    
    public String getRandomPlanet()
    {
        int index = (int)(Math.random() * planets.length);
        return planets[index];
    }
}
