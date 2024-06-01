package oop.roman;

public class RomanNumeralFormat
{
    /**
     * Returns a String converted from int param a. It first checks if a is in
     * the defined value range, if not, it throws a new
     * IllegalArgumentException. The while loop is called until the counter
     * variable is equal to the length of a if ((len - 1) >= 0... else if ((len
     * - 2) >= 0... ... makes sure, that the maximum index of a wont get longer
     * than len-1 to prevent the IndexOutOfBoundsException(). ...&& counter ==
     * len - 1) ...&& counter == len - 2) ... ensures that the correct index
     * position is addressed. After that, the next index position, will be
     * addressed with counter++. The Switch-Case Operation asks for a int number
     * to convert it into a roman number
     * 
     * @param a
     * @return
     */
    public static String toString(int a)
    {
        if (a > 3999 || a < 1)
        {
            throw new IllegalArgumentException("Zahl außerhalb des erlaubten Wertebereichs!");
        }
        String str = Integer.toString(a);
        int len = str.length();
        int counter = 0;
        String result = "";

        while (counter < len)
        {
            if ((len - 1) >= 0 && counter == len - 1)
            {
                counter++;
                switch (str.charAt(len - 1))
                {
                    case '1':
                        result += "I";
                        break;
                    case '2':
                        result += "II";
                        break;
                    case '3':
                        result += "III";
                        break;
                    case '4':
                        result += "IV";
                        break;
                    case '5':
                        result += "V";
                        break;
                    case '6':
                        result += "VI";
                        break;
                    case '7':
                        result += "VII";
                        break;
                    case '8':
                        result += "VIII";
                        break;
                    case '9':
                        result += "IX";
                        break;
                    default:
                        break;
                }
            }
            else if ((len - 2) >= 0 && counter == len - 2)
            {
                counter++;
                switch (str.charAt(len - 2))
                {
                    case '1':
                        result += "X";
                        break;
                    case '2':
                        result += "XX";
                        break;
                    case '3':
                        result += "XXX";
                        break;
                    case '4':
                        result += "XL";
                        break;
                    case '5':
                        result += "L";
                        break;
                    case '6':
                        result += "LX";
                        break;
                    case '7':
                        result += "LXX";
                        break;
                    case '8':
                        result += "LXXX";
                        break;
                    case '9':
                        result += "XC";
                        break;
                    default:
                        break;
                }
            }
            else if ((len - 3) >= 0 && counter == len - 3)
            {
                counter++;
                switch (str.charAt(len - 3))
                {
                    case '1':
                        result += "C";
                        break;
                    case '2':
                        result += "CC";
                        break;
                    case '3':
                        result += "CCC";
                        break;
                    case '4':
                        result += "CD";
                        break;
                    case '5':
                        result += "D";
                        break;
                    case '6':
                        result += "DC";
                        break;
                    case '7':
                        result += "DCC";
                        break;
                    case '8':
                        result += "DCCC";
                        break;
                    case '9':
                        result += "CM";
                        break;
                    default:
                        break;
                }
            }
            else if ((len - 4) >= 0 && counter == len - 4)
            {
                counter++;
                switch (str.charAt(len - 4))
                {
                    case '1':
                        result += "M";
                        break;
                    case '2':
                        result += "MM";
                        break;
                    case '3':
                        result += "MMM";
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }

    /**
     * Returns an int number converted from String b. The for loop only allows
     * numbers within the defined number range. The if statement in the for loop
     * simply uses the toString() method from above and looks if any result of
     * toString(i) matches b. If the result is not legal respectively b does not
     * equal any return value of toString(i), it throws a new
     * NumberFormatException().
     * 
     * @param b
     * @return
     */
    public static int parse(String b)
    {
        int result = -1;
        for (int i = 1; i <= 3999; i++)
        {
            if (b.equals(toString(i)))
            {
                result = i;
                break;
            }
        }
        if (result == -1)
        {
            throw new NumberFormatException("Keine gültige römische Zahl!");
        }
        return result;
    }

    public static void main(String[] args)
    {
        // System.out.println(toString(3128));
        // System.out.println(toString(987));
        // System.out.println(toString(15));
        // System.out.println(parse("MMMCXXVIII"));
        // System.out.println(parse("CMLXXXVII"));
        // System.out.println(parse("XV"));
        // System.out.println(parse("VIII"));
        // for (int i = 1; i <= 3999; i++)
        // {
        // System.out.println(toString(i) + " " + i);
        // }
    }

}
