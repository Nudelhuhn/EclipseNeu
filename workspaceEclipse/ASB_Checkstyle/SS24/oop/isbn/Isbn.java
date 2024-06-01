package oop.isbn;

public class Isbn
{
    public static String generateIsbn10(String string)
    {
        // help variable for easier testing
        String onlyNumbersStr = "";
        int length = string.length();
        // if the starting string contains other than numbers or "-" throw
        // exception
        // otherwise fill the help variable "onlyNumbersStr"
        for (int i = 0; i < length; i++)
        {
            if (Character.isDigit(string.charAt(i)))
            {
                onlyNumbersStr += string.charAt(i);
            }
            else
            {
                if (string.charAt(i) != '-')
                {
                    throw new IllegalArgumentException("other than numbers or \"-\" not allowed");
                }
            }
        }

        // also, if the starting string has less or more than 12 numbers throw
        // exception
        int onlyNumbersStrLength = onlyNumbersStr.length();
        if (onlyNumbersStrLength != 9)
        {
            throw new IllegalArgumentException("not exactly 9 numbers included");
        }

        // calculate weighted sum, as it is needed to determine the checkNumber
        // of Isbn10-numbers
        int weightedSum = 0;
        int weightFactor = 10;
        for (int i = 0; i < onlyNumbersStrLength; i++)
        {
            weightedSum += Character.getNumericValue(onlyNumbersStr.charAt(i)) * (weightFactor - i);
        }

        // checkNumber = last number to add for the Isbn-number
        int checkNumber = 11 - weightedSum % 11;
        if (checkNumber == 10)
        {
            return string + "-X";
        }
        else if (checkNumber == 11)
        {
            return string + "-0";
        }
        return string + '-' + Character.forDigit(checkNumber, 10);
    }

    public static String generateIsbn13(String string)
    {
        // help variable for easier testing
        String onlyNumbersStr = "";
        int length = string.length();
        // if the starting string contains other than numbers or "-" throw
        // exception
        // otherwise fill the help variable "onlyNumbersStr"
        for (int i = 0; i < length; i++)
        {
            if (Character.isDigit(string.charAt(i)))
            {
                onlyNumbersStr += string.charAt(i);
            }
            else
            {
                if (string.charAt(i) != '-')
                {
                    throw new IllegalArgumentException("other than numbers or \"-\" not allowed");
                }
            }
        }

        // also, if the starting string has less or more than 12 numbers throw
        // exception
        int onlyNumbersStrLength = onlyNumbersStr.length();
        if (onlyNumbersStrLength != 12)
        {
            throw new IllegalArgumentException("not exactly 12 numbers included");
        }

        // calculate the sum of numbers at even and at uneven positions
        // 1., 3., ..., 11. digit
        int sumOfNumbersAtUnevenPositions = 0;
        // 2., 4., ..., 12. digit
        int sumOfNumbersAtEvenPositions = 0;
        for (int i = 0; i < onlyNumbersStrLength; i++)
        {
            int numericValueOfChar = Character.getNumericValue(onlyNumbersStr.charAt(i));
            if (i % 2 == 0)
            {
                sumOfNumbersAtUnevenPositions += numericValueOfChar;
            }
            else
            {
                sumOfNumbersAtEvenPositions += numericValueOfChar;
            }
        }

        // calculation formula of the for Isbn13-numbers
        int endSum = 3 * sumOfNumbersAtEvenPositions + sumOfNumbersAtUnevenPositions;
        // checkNumber = last number to add for the Isbn-number
        int checkNumber = 10 - endSum % 10;
        if (checkNumber == 10)
        {
            return string + "-0";
        }
        return string + '-' + Character.forDigit(checkNumber, 10);
    }

    public static void main(String[] args)
    {
        System.out.println(generateIsbn10("016678230"));
        System.out.println(generateIsbn13("1-040149-58-020"));
    }
}
