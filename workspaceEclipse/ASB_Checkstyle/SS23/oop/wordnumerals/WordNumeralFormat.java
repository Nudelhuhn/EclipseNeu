package oop.wordnumerals;

public class WordNumeralFormat
{
    public static String toString(int z)
    {
        String neu = Integer.toString(z);
        int len = neu.length();
        int zhlr = 0;
        String zsm = "";
        while (len > zhlr)
        {
            switch (neu.charAt(zhlr))
            {
                case '-':
                    zsm += "- ";
                    zhlr++;
                    break;
                case '0':
                    zsm += "null ";
                    zhlr++;
                    break;
                case '1':
                    zsm += "eins ";
                    zhlr++;
                    break;
                case '2':
                    zsm += "zwei ";
                    zhlr++;
                    break;
                case '3':
                    zsm += "drei ";
                    zhlr++;
                    break;
                case '4':
                    zsm += "vier ";
                    zhlr++;
                    break;
                case '5':
                    zsm += "fuenf ";
                    zhlr++;
                    break;
                case '6':
                    zsm += "sechs ";
                    zhlr++;
                    break;
                case '7':
                    zsm += "sieben ";
                    zhlr++;
                    break;
                case '8':
                    zsm += "acht ";
                    zhlr++;
                    break;
                case '9':
                    zsm += "neun ";
                    zhlr++;
                    break;
                default:
                    break;
            }
        }
        return zsm.substring(0, zsm.length() - 1);
    }

    public static int parse(String s)
    {
        String[] words = s.split(" ");
        int len = words.length;
        int zhlr = 0;
        String zsm = "";
        while (len > zhlr)
        {
            switch (words[zhlr])
            {
                case "-":
                    zsm += "-";
                    zhlr++;
                    break;
                case "null":
                    zsm += "0";
                    zhlr++;
                    break;
                case "eins":
                    zsm += "1";
                    zhlr++;
                    break;
                case "zwei":
                    zsm += "2";
                    zhlr++;
                    break;
                case "drei":
                    zsm += "3";
                    zhlr++;
                    break;
                case "vier":
                    zsm += "4";
                    zhlr++;
                    break;
                case "fuenf":
                    zsm += "5";
                    zhlr++;
                    break;
                case "sechs":
                    zsm += "6";
                    zhlr++;
                    break;
                case "sieben":
                    zsm += "7";
                    zhlr++;
                    break;
                case "acht":
                    zsm += "8";
                    zhlr++;
                    break;
                case "neun":
                    zsm += "9";
                    zhlr++;
                    break;

                default:
                    throw new NumberFormatException("Unexpected value");
            }
        }
        return Integer.parseInt(zsm);
    }

    public static void main(String[] args)
    {
        System.out.println(toString(-1234));
        System.out.println(WordNumeralFormat.parse("- neun eins null drei eins"));
    }
}
