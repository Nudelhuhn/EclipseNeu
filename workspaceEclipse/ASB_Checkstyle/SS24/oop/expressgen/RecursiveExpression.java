package oop.expressgen;

public class RecursiveExpression
{
    public static String generateExpression(int x)
    {
        String result = "";
        if (x < 0)
        {
            result += "-";
            x = -x;
        }
        else if (result.equals("") && x == 0)
        {
            result = "0";
        }

        // anchor
        if (x == 0)
        {
            return result;
        }

        if (x % 2 == 0)
        {
            if (x <= 2)
            {
                return result + "2";
            }
            else
            {
                result += "2 * " + generateExpression(x / 2);
            }
        }
        else
        {
            if (x <= 1)
            {
                return result + "1";
            }
            else
            {
                result += "(" + generateExpression(x - 1) + " + 1) ";
            }
        }
        return result;
    }

    public static void main(String[] agrs)
    {
        for (int i = 0; i > -13; i--)
        {
            System.out.println(RecursiveExpression.generateExpression(i));
        }
    }
}
