package oop.hanoi;

import java.util.ArrayList;
import java.util.List;

public class Towers
{
    private static int startingPosition = 0;

    private static int supportPosition = 1;

    private static int targetPosition = 2;

    public static void setPositions(int start, int supp, int target)
    {
        startingPosition = start;
        supportPosition = supp;
        targetPosition = target;
    }

    public static List<Move> computeMoves(int towerHeight)
    {
        if (towerHeight <= 0)
        {
            throw new IllegalArgumentException();
        }

        List<Move> moves = new ArrayList<Move>();
        recursiveMove(towerHeight, startingPosition, supportPosition, targetPosition, moves);

        return moves;
    }

    private static void recursiveMove(int height, int from, int supp, int to, List<Move> moves)
    {
        // recursion anchor
        if (height == 1)
        {
            moves.add(new Move(from, to));
        }
        // recursion step
        else
        {
            recursiveMove(height - 1, from, to, supp, moves);
            moves.add(new Move(from, to));
            recursiveMove(height - 1, supp, from, to, moves);
        }
    }

    public static void main(String[] args)
    {
        setPositions(1, 0, 2);
        System.out.println(computeMoves(1));
        System.out.println(computeMoves(2));
        System.out.println(computeMoves(3));
    }
}
