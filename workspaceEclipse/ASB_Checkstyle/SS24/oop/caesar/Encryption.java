package oop.caesar;

public class Encryption
{
    public static String encode(String string, int offset)
    {
        string = string.toLowerCase();
        if (offset == 0)
        {
            return string;
        }
        else if (offset < 0)
        {
            offset = 26 + offset % 26;
        }
        String result = "";
        int length = string.length();
        for (int i = 0; i < length; i++)
        {
            char shiftedCharacter = string.charAt(i);
            if (shiftedCharacter >= 97 && shiftedCharacter <= 122)
            {
                shiftedCharacter = (char) (((string.charAt(i) - 97 + offset) % 26) + 97);
            }
            result += shiftedCharacter;
        }
        return result;
    }

    public static void main(String[] args)
    {
        System.out.println(encode("8iNA64i;ga0MUnwq CSGzyR", -202));
    }
}
