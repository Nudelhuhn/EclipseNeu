package oop.minesweeper;

public class Game
{

    private int[][] numberGrid = null;

    private boolean[][] mineGrid = null;

    private int numberOfCells = 0;

    private int numberOfCellsToSolve = 0;

    private int numberOfCheckedCells = 0;

    private int rows = 0;

    private int columns = 0;

    private MoveResult gameState = MoveResult.NOT_STARTED;

    public void startGame(boolean[][] bombGrid)
    {
        gameState = MoveResult.NOT_STARTED;
        // verfiy grid
        if (bombGrid == null)
        {
            throw new IllegalArgumentException("grid is null!");
        }
        rows = bombGrid.length;
        if (rows == 0)
        {
            throw new IllegalArgumentException("zero rows!");
        }
        int temp = 0;
        boolean columnsSetted = false;
        for (boolean[] boolArr : bombGrid)
        {
            if (boolArr == null)
            {
                throw new IllegalArgumentException("first dimension of the grid must be an array and not null!");
            }

            temp = boolArr.length;
            if (!columnsSetted)
            {
                columns = temp;
                columnsSetted = true;
            }

            if (columns == 0)
            {
                throw new IllegalArgumentException("zero columns in row!");
            }
            if (columns != temp)
            {
                throw new IllegalArgumentException("no rectangular field!");
            }
        }

        // fill startingGrid
        numberGrid = new int[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                numberGrid[i][j] = -1;
            }
        }

        mineGrid = bombGrid;
        numberOfCells = rows * columns;
        numberOfCellsToSolve = numberOfCells - countMines(bombGrid);
        numberOfCheckedCells = 0;
    }

    public MoveResult move(int rowIndex, int colIndex)
    {
        if (numberGrid == null)
        {
            return MoveResult.NOT_STARTED;
        }
        // Check if the game has already ended
        if (gameState == MoveResult.LOST)
        {
            System.out.println("You already lost the game, start a new one");
            return MoveResult.NOT_STARTED;
        }
        if (gameState == MoveResult.WON)
        {
            System.out.println("You already won the game, start a new one");
            return MoveResult.NOT_STARTED;
        }
        if (numberGrid[rowIndex][colIndex] != -1)
        {
            System.out.println("This cell is already revealed, choose another one");
            return MoveResult.USELESS;
        }

        // If this cell has a mine, reveal all mines and end the game
        if (mineGrid[rowIndex][colIndex])
        {
            revealAllMines();
            gameState = MoveResult.LOST;
            return MoveResult.LOST;
        }

        // Count mines in the neighborhood
        int mines = countMinesInNeighborhood(rowIndex, colIndex);
        numberGrid[rowIndex][colIndex] = mines;
        numberOfCheckedCells++;

        // If this cell has no neighboring mines, recursively reveal its
        // neighbors
        if (mines == 0)
        {
            revealNeighbors(rowIndex, colIndex);
        }

        // Check if the game has been won
        if (numberOfCheckedCells == numberOfCellsToSolve)
        {
            gameState = MoveResult.WON;
        }
        else
        {
            gameState = MoveResult.CONTINUE;
        }
        return gameState;
    }

    public int[][] getBoard()
    {
        if (numberGrid == null)
        {
            return null;
        }
        for (int[] row : numberGrid)
        {
            for (int column : row)
            {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        return numberGrid;
    }

    private int countMinesInNeighborhood(int rowIndex, int colIndex)
    {
        int mines = 0;
        for (int row = rowIndex - 1; row <= rowIndex + 1; row++)
        {
            for (int col = colIndex - 1; col <= colIndex + 1; col++)
            {
                if (row >= 0 && row < rows && col >= 0 && col < columns)
                {
                    if (mineGrid[row][col])
                    {
                        mines++;
                    }
                }
            }
        }
        return mines;
    }

    private void revealNeighbors(int rowIndex, int colIndex)
    {
        for (int row = rowIndex - 1; row <= rowIndex + 1; row++)
        {
            for (int col = colIndex - 1; col <= colIndex + 1; col++)
            {
                if (row >= 0 && row < rows && col >= 0 && col < columns)
                {
                    if (numberGrid[row][col] == -1)
                    {
                        move(row, col);
                    }
                }
            }
        }
    }

    private void revealAllMines()
    {
        for (int row = 0; row < rows; row++)
        {
            for (int column = 0; column < columns; column++)
            {
                if (mineGrid[row][column])
                {
                    numberGrid[row][column] = 9;
                }
            }
        }
    }

    private static int countMines(boolean[][] mineGrid)
    {
        int mineCounter = 0;
        for (boolean[] row : mineGrid)
        {
            for (boolean column : row)
            {
                if (column)
                {
                    mineCounter++;
                }
            }
        }
        return mineCounter;
    }

    public static void main(String[] args)
    {
        boolean[][] mineGrid =
        {
            { true, true, false, false, false },
            { true, false, true, false, false },
            { false, false, false, false, false },
            { true, false, false, false, false } };

        Game game = new Game();

        game.startGame(mineGrid);
        game.getBoard();
        System.out.println();
        System.out.println(game.move(1, 1));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(3, 3));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(1, 3));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(0, 3));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(0, 2));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(2, 0));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(2, 0));

        System.out.println();
        System.out.println();

        game.startGame(mineGrid);
        game.getBoard();
        System.out.println();
        System.out.println(game.move(1, 1));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(3, 3));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(1, 3));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(0, 3));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(0, 2));
        game.getBoard();
        System.out.println();
        System.out.println(game.move(2, 0));
        game.getBoard();
        System.out.println();
    }

}
