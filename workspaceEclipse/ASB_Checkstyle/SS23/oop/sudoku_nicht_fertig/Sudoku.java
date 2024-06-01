package oop.sudoku_nicht_fertig;

public class Sudoku
{

    private static final int GRID_SIZE = 9;

    public static boolean isNumberInRow(int[][] matrix, int number, int row)
    {
        for (int i = 0; i < GRID_SIZE; i++)
        {
            if (matrix[row][i] == number)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInColumn(int[][] matrix, int number, int column)
    {
        for (int i = 0; i < GRID_SIZE; i++)
        {
            if (matrix[i][column] == number)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInGrid(int[][] matrix, int number, int row, int column)
    {
        // Um herauszufinden, in welcher Box man sich befindet, sucht man die
        // erste Zelle der Box also oben links
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        // Die Zellen der Box durchgehen
        for (int i = localBoxRow; i < localBoxRow + 3; i++)
        {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++)
            {
                if (matrix[i][j] == number)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidPlacement(int[][] matrix, int number, int row, int column)
    {
        return !isNumberInRow(matrix, number, row) && !isNumberInColumn(matrix, number, column) && !isNumberInGrid(matrix, number, row, column);
    }

    public static boolean solve(int[][] matrix)
    {
        for (int row = 0; row < GRID_SIZE; row++)
        {
            for (int column = 0; column < GRID_SIZE; column++)
            {
                if (matrix[row][column] == 0)
                {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++)
                    {
                        if (isValidPlacement(matrix, numberToTry, row, column))
                        {
                            matrix[row][column] = numberToTry;

                            if (solve(matrix))
                            {
                                return true;
                            }
                            else
                            {
                                matrix[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printMatrix(int[][] matrix)
    {
        for (int row = 0; row < GRID_SIZE; row++)
        {
            if (row % 3 == 0 && row != 0)
            {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++)
            {
                if (column % 3 == 0 && column != 0)
                {
                    System.out.print("|");
                }
                System.out.print(matrix[row][column]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int[][] nullmatrix =
        {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        int[][] matrix =
        {
            { 7, 0, 2, 0, 5, 0, 6, 0, 0 },
            { 0, 0, 0, 0, 0, 3, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 9, 5, 0, 0 },
            { 8, 0, 0, 0, 0, 0, 0, 9, 0 },
            { 0, 4, 3, 0, 0, 0, 7, 5, 0 },
            { 0, 9, 0, 0, 0, 0, 0, 0, 8 },
            { 0, 0, 9, 7, 0, 0, 0, 0, 5 },
            { 0, 0, 0, 2, 0, 0, 0, 0, 0 },
            { 0, 0, 7, 0, 4, 0, 2, 0, 3 } };

        int[][] tageszeitung =
        {
            { 4, 0, 0, 0, 2, 6, 0, 0, 0 },
            { 1, 8, 0, 0, 0, 3, 0, 6, 0 },
            { 0, 0, 2, 0, 0, 0, 7, 5, 3 },
            { 0, 0, 6, 0, 3, 4, 1, 0, 5 },
            { 2, 0, 0, 8, 0, 5, 0, 0, 7 },
            { 8, 0, 4, 1, 9, 0, 3, 0, 0 },
            { 7, 4, 9, 0, 0, 0, 2, 0, 0 },
            { 0, 6, 0, 7, 0, 0, 0, 9, 8 },
            { 0, 0, 0, 3, 1, 0, 0, 0, 4 } };

        System.out.println("Ausgangsmatrix\n");
        printMatrix(matrix);

        if (solve(matrix))
        {
            System.out.println("\nMatrix solved!\n");
        }
        else
        {
            System.out.println("\nMatrix not solvable\n");
        }

        printMatrix(matrix);

    }
}
