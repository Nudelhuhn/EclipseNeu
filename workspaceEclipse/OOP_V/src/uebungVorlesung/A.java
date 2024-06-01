package uebungVorlesung;

public class A
{

	public static void main(String[] args)
	{
		for (int i = 0; i < args.length; i++)
		{
			System.out.println("Argument Nr. " + i + " :   " + args[i]);
			// Test fuer Uebergabe von Argumenten
			// Fuer Windows Kommandozeile
			// zuerst in den bin Ordner des jeweilen workspace gehen
			// zb "java uebungVorlesung.A Das ist ein Test fuer die Anzahl der Argumente"
			// Fuer Eclipse
			// links oben auf "Play-Button" Dropdown-Menue => Run Configurations => Arguments
		}
	}

}
