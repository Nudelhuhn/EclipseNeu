package oop.primefactors;

import java.util.ArrayList;
import java.util.List;

public class Utilities
{
    public static ArrayList<Integer> sieveOfEratosthenes(int sieveRange)
    {
        boolean[] markingsOfPrimeNumbers = new boolean[sieveRange + 1];
        // Stop when the square of an unmarked number is larger than
        // the sieveRange, as no more new marks are made after the
        // lower loop anyway
        for (int i = 2; i * i <= sieveRange; i++)
        {
            if (!markingsOfPrimeNumbers[i])
            {
                // start at the square of the number since every multiple of
                // the prime number before were already marked as not prime
                // numbers
                // e.g. all multiples of 2 are marked,
                // multiples of 3 are 6, 9, ...
                // => 6 is already marked
                // => start at 3³ = 9
                for (int j = i * i; j <= sieveRange; j += i)
                {
                    markingsOfPrimeNumbers[j] = true;
                }
            }
        }
        // fill the prime number list
        ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
        for (int i = 2; i <= sieveRange; i++)
        {
            if (!markingsOfPrimeNumbers[i])
            {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static List<Integer> getPrimeFactors(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException("input must be higher than zero");
        }
        if (n == 1)
        {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> primeNumbers = sieveOfEratosthenes(n);
        if (primeNumbers.contains(n))
        {
            List<Integer> nIsPrimeNumberAsList = new ArrayList<Integer>();
            nIsPrimeNumberAsList.add(n);
            return nIsPrimeNumberAsList;
        }

        int maxLoopRange = primeNumbers.size();
        List<Integer> primeFactorsOfN = new ArrayList<Integer>();
        // initialize variable before loop for more efficiency
        int primeNumberAtIndex = 0;
        for (int i = 0; i < maxLoopRange;)
        {
            // call get method only once in ever round in loop for more
            // efficiency
            primeNumberAtIndex = primeNumbers.get(i);
            if (n % primeNumberAtIndex == 0)
            {
                primeFactorsOfN.add(primeNumberAtIndex);
                n /= primeNumberAtIndex;
            }
            else
            {
                i++;
            }
        }
        return primeFactorsOfN;
    }

    public static int gcd(int x, int y)
    {
        if (x <= 0 || y <= 0)
        {
            throw new IllegalArgumentException("input must be higher than zero");
        }

        List<Integer> primeFactorsX = getPrimeFactors(x);
        List<Integer> primeFactorsY = getPrimeFactors(y);

        int sizeX = primeFactorsX.size();
        int sizeY = primeFactorsY.size();

        int result = 1;

        int j = 0;
        for (int i = 0; i < sizeX; i++)
        {
            for (; j < sizeY;)
            {
                if (primeFactorsX.get(i) < primeFactorsY.get(j))
                {
                    break;
                }
                else if (primeFactorsX.get(i) == primeFactorsY.get(j))
                {
                    result *= primeFactorsX.get(i);
                    j++;
                    break;
                }
                j++;
            }
        }
        return result;
    }

    public static int lcm(int x, int y)
    {
        if (x <= 0 || y <= 0)
        {
            throw new IllegalArgumentException("input must be higher than zero");
        }
        return (x * y) / gcd(x, y);

        // List<Integer> primeFactorsX = getPrimeFactors(x);
        // List<Integer> primeFactorsY = getPrimeFactors(y);
        //
        // int sizeX = primeFactorsX.size();
        // int sizeY = primeFactorsY.size();
        //
        // int greatestSize = sizeX;
        // // fill the smaller list with ones ("1") for better handling
        // if (sizeX > sizeY)
        // {
        // greatestSize = sizeX;
        // List<Integer> temp = new ArrayList<Integer>();
        // for (int i = 0; i < sizeX - sizeY; i++)
        // {
        // temp.add(1);
        // }
        // temp.addAll(primeFactorsY);
        // primeFactorsY = temp;
        // }
        // else if (sizeX < sizeY)
        // {
        // greatestSize = sizeY;
        // List<Integer> temp = new ArrayList<Integer>();
        // for (int i = 0; i < sizeY - sizeX; i++)
        // {
        // temp.add(1);
        // }
        // temp.addAll(primeFactorsX);
        // primeFactorsX = temp;
        // }
        //
        // int result = 1;
        //
        // int numAtXi = 0;
        // int numAtYi = 0;
        // for (int i = 0; i < greatestSize; i++)
        // {
        // numAtXi = primeFactorsX.get(i);
        // numAtYi = primeFactorsY.get(i);
        // if (numAtXi != numAtYi)
        // {
        // result *= numAtXi * numAtYi;
        // }
        // else if (numAtXi == numAtYi)
        // {
        // result *= numAtXi;
        // }
        // }
        // return result;
    }

    public static void main(String[] args)
    {
        // for (int i = 1; i < 10; i++)
        // {
        // for (int j = 1; j < 10; j++)
        // {
        // System.out.println(gcd(i, j));
        // }
        // }
        System.out.println(getPrimeFactors(2));
        System.out.println(getPrimeFactors(6));
        System.out.println(lcm(2, 6));// 6
        System.out.println(lcm(735, 345));// 16905
        System.out.println(lcm(845, 15015));// 195195
        // 955 und 1050 ist 200550
    }
}
