
package oop.version;

public class VersionNumber
{
    public static int compare(String v1, String v2)
    {
        String[] parts1 = (removeRedundantDots(v1)).split("\\.");
        String[] parts2 = (removeRedundantDots(v2)).split("\\.");

        /*
         * Erstellen eines neuen Arrays mit der Größe des größten Arrays und
         * anschließendes Auffüllen des neuen Arrays mit Nullen
         */
        if (parts1.length > parts2.length)
        {
            String[] tmp = new String[parts1.length];
            System.arraycopy(parts2, 0, tmp, 0, parts2.length);

            for (int i = 0; i < parts1.length; i++)
            {
                if (tmp[i] == null)
                {
                    tmp[i] = "0";
                }
            }
            parts2 = tmp;
        }
        else if (parts1.length < parts2.length)
        {
            String[] tmp = new String[parts2.length];
            System.arraycopy(parts1, 0, tmp, 0, parts1.length);

            for (int i = 0; i < parts2.length; i++)
            {
                if (tmp[i] == null)
                {
                    tmp[i] = "0";
                }
            }
            parts1 = tmp;
        }

        /*
         * Vergleichen der zu int umgewandelten Zahlen der String Arrays. Werfen
         * von Exceptions sollten Arrays ungültige Werte haben.
         */
        int result = 0;
        for (int i = 0; i < parts1.length; i++)
        {
            try
            {
                int number1 = Integer.parseInt(parts1[i]);
                int number2 = Integer.parseInt(parts2[i]);
                if (number1 > number2)
                {
                    if (result == 0)
                    {
                        result = 1;
                    }
                }
                if (number1 < number2)
                {
                    if (result == 0)
                    {
                        result = -1;
                    }
                }
                if (number1 < 0 || number2 < 0)
                {
                    throw new NumberFormatException("Keine negativen Werte in Versionsnummern erlaubt!");
                }
            }
            catch (NumberFormatException e)
            {
                throw new NumberFormatException("Nur ein Punkt zwischen den Zahlen erlaubt!\nNur positive Zahlen erlaubt!\nEs darf nicht nichts dastehen!");
            }
        }
        return result;
    }

    /*
     * Übergabe des ursprünglichen Strings. String in ein Stringarray umwandeln,
     * damit man auf die Stellen zugreifen kann. Überprüfen ob die rechteste
     * Stelle kein gültiger Zahlenwert ist, wenn ja dann Ursprungsstring um eine
     * Stelle kürzen, ansonsten aus der Schleife ausbrechen und den substring
     * zurückgeben.
     */
    public static String removeRedundantDots(String a)
    {
        String[] c = a.split("");
        for (int i = a.length() - 1; i >= 0; i--)
        {
            if (c[i].equals("."))
            {
                a = a.substring(0, i);
            }
            else
            {
                break;
            }
        }
        return a;
    }

    public static void main(String[] args)
    {
        // String a = ".";
        // String b = "12.-2";
        // String c = "1.23.456";
        // String d = "1.23.456......";
        // String e = "1.23";
        // String f = "2.23";
        String g = "1.2.3.19.20.21.22.23.24...";
        String h = "1.3";
        System.out.println(compare(g, h));
        // System.out.println(compare(c, d)); // 0 (gleich)
        // System.out.println(compare(e, f)); // -1 (e ist kleiner bzw älter als
        // f)
        // System.out.println(compare(c, e)); // 1 (c ist größer bzw neuer als
        // e)
        // System.out.println(compare(a, b)); // Exception
    }
}
