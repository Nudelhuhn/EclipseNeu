package oop.recursion;

import java.util.Arrays;

public class CombinationLock
{
    private int[] combination;
    
    public CombinationLock(int size)
    {
        if(size < 1)
        {
            throw new IllegalArgumentException();
        }
        combination = new int[size];
    }
    
    public int[] findCombination()
    {
        return findCombination(0);
    }
    
    private int[] findCombination(int index)
    {
        for(int i = 0; i < 10; i++)
        {
            combination[index] = i;
            if(index < combination.length-1)
            {
                int[] result = findCombination(index+1);
                if(result != null)
                {
                    return result;
                }
            }
            else if(canOpen())
            {
                return combination.clone();
            }

        }
        return null;
    }
    
    private boolean canOpen()
    {
        int checksum = 0;
        for(int i = 0; i < combination.length; i++)
        {
            checksum += combination[i];
        }
        return checksum == 41;
    }
    
    public static void main(String[] args)
    {
        CombinationLock cl = new CombinationLock(5);
        int[] result = cl.findCombination();
        System.out.println(Arrays.toString(result));
    }
}
