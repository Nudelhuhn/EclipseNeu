package uebungVorlesung;

/**
 * hallo
 * @author Gregor
 *
 */

public class PlanetSelectionTest
{
	/**
	 * Hauptprogramm
	 * 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args)
	{
		PlanetSelection ps = new PlanetSelection();
		if (args.length != 2)
		{
			System.out.println("Es muessen genau zwei Argumente uebergeben werden");
			return;
		}
		String greetings = args[0];
		try
		{
			int number = Integer.parseInt(args[1]);
			while (number > 0)
			{
				if (number % 2 == 0)
				{
					System.out.println(number + " - " + greetings + " " + ps.getPlanet());
				} else
				{
					System.err.println(number + " - " + greetings + " " + ps.getPlanet());
				}
				number--;
			}
		} catch (NumberFormatException e)
		{
			System.out.println("Zahl als zweites Argument eingeben!");
		}
	}
}
