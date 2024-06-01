package oop.checkdigit;

public class Check
{

    public static long transformToCheckedNumber(long a) throws IllegalArgumentException
    // throws Klausel nicht notwendig
    {
        // toString
        // Stellen auslesen
        // Zahlen an Stellen parse long
        // einzelne long zahlen summieren
        // Modulo berechnen
        // Quersumme 11 % 10 = 1, man braucht aber = 9 => -10 * (-1)
        // Dies f¸hrt dazu, dass wenn mod = 0 sein soll mod = 10 ist =>
        // zusaetzlich if-Bedingung
        // "umgedrehten" Modulowert an str anh‰ngen
        // Wenn zu long Zahl des geparsten Strings zu groﬂ wird um ausgegeben zu
        // werden, iae Overflow
        if (a < 0)
        {
            throw new IllegalArgumentException("Keine negativen Werte!");
        }
        String str = Long.toString(a);
        long crossSum = 0;
        for (int i = 0; i < str.length(); i++)
        {
            long temp = Character.getNumericValue(str.charAt(i));
            crossSum += temp;
        }
        long mod = (crossSum % 10 - 10) * (-1);
        if (mod == 10)
        {
            mod = 0;
        }
        str += String.valueOf(mod);

        try
        {
            return Long.parseLong(str);
        }
        catch (IllegalArgumentException iae)
        {
            throw new IllegalArgumentException("Eingegebene Zahl ist zu groﬂ um benoetigte Werte anzuhaengen, Overflow!");
        }

    }

    public static long transformToUncheckedNumber(long a)
    {
        // toString
        // Stellen auslesen
        // Quersumme bilden
        // Quersumme Modulo 10 == 0 => a mit abgeschnittener letzter zur¸ckgeben
        // Quersumme Modulo 10 != 0 => iae
        // Wenn Laenge von str zu kurz ist um beschnitten zu werden, wird er zu
        // Long ohne Beschneidung geparst
        if (a < 0)
        {
            throw new IllegalArgumentException("Keine negativen Werte oder 0!");
        }
        String str = Long.toString(a);
        long crossSum = 0;
        for (int i = 0; i < str.length(); i++)
        {
            long temp = Character.getNumericValue(str.charAt(i));
            crossSum += temp;
        }
        if (crossSum % 10 != 0)
        {
            throw new IllegalArgumentException("Eingabe ungueltig (Quersumme % 10 != 0)!");
        }
        if (str.length() < 2)
        {
            return Long.parseLong(str);
        }
        return Long.parseLong(str.substring(0, str.length() - 1));
    }

    public static void main(String[] args)
    {
        // Long.MAX_VALUE = 9223372036854775807L
        System.out.println(transformToCheckedNumber(0L));
        System.out.println(transformToUncheckedNumber(0L));
    }

}
