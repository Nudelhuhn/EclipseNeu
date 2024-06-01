package oop.lockcracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationLock
{
    protected int[] combination;

    public CombinationLock(int size)
    {
        if (size < 1)
        {
            throw new IllegalArgumentException();
        }
        combination = new int[size];
    }

    // Wegen der Schönheit, wird in der Main-Methode keine Liste und kein Index
    // im
    // Aufruf von findAllCombinations übergeben.
    // Und damit zum Schluss nur eine Liste zurückgegeben wird.
    public List<int[]> findAllCombinations()
    {
        List<int[]> combinations = new ArrayList<>();
        findAllCombinations(0, combinations);
        return combinations;
    }

    private void findAllCombinations(int index, List<int[]> combinations)
    {
        for (int i = 0; i < 10; i++)
        {
            combination[index] = i;
            if (index < combination.length - 1)
            {
                findAllCombinations(index + 1, combinations);
            }
            else if (canOpen())
            {
                combinations.add(combination.clone());
            }
        }
    }

    protected boolean canOpen()
    {
        int checksum = 0;
        for (int i = 0; i < combination.length; i++)
        {
            checksum += combination[i];
        }
        return checksum == 41;
    }

    public static void main(String[] args)
    {
        CombinationLock cl = new CombinationLock(5);
        List<int[]> combinations = cl.findAllCombinations();
        for (int[] combination : combinations)
        {
            System.out.println(Arrays.toString(combination));
        }
    }
}