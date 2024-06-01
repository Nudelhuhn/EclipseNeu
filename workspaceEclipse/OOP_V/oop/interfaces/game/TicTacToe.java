package oop.interfaces.game;

import java.util.Scanner;

class TicTacToe implements Game
{
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final int UNDEFINED = NUMBER_OF_PLAYERS;
    private static String[] SYMBOL = {"X", "O", " "};
    
    //0 = Markierung des Spielers 0 (X), 1 = Markierung des Spielers 1 (O)
    //2 = UNDEFINED (leeres Feld)
    private int[][] board;

    private int next;
    private int winner;
    
    @Override
    public String getNameOfGame()
    {
        return "Tic Tac Toe";
    }

    @Override
    public int getMinNumberOfPlayers()
    {
        return NUMBER_OF_PLAYERS;
    }

    @Override
    public int getMaxNumberOfPlayers()
    {
        return NUMBER_OF_PLAYERS;
    }

    @Override
    public void initialize(int players)
    {
        if(players != NUMBER_OF_PLAYERS)
        {
            throw new IllegalArgumentException("Es dürfen genau " + NUMBER_OF_PLAYERS + " Personen teilnehmen.");
        }
        board = new int[3][3];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = UNDEFINED;
            }
        }
        next = (int)Math.round(Math.random());
        winner = UNDEFINED;
    }

    @Override
    public String getBoard() throws NotInitializedException
    {
        if(board == null)
        {
            throw new NotInitializedException();
        }
        StringBuffer text = new StringBuffer();
        String line = "-------";
        text.append(line + "\n");
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                text.append("|" + SYMBOL[board[i][j]]);                
            }
            text.append("|\n");
            text.append(line);
            if(i != board.length-1)
            {
                text.append("\n");
            }
        }
        return text.toString();
    }

    @Override
    public int getNextPlayer() throws NotInitializedException, GameFinishedException
    {
        if(board == null)
        {
            throw new NotInitializedException();
        }
        if(isFinished())
        {
            throw new GameFinishedException();
        }
        return next;
    }

    @Override
    public String getHintForInput()
    {
        return "Geben Sie die Nummer einer Zeile (1-3) und einer Spalte (1-3) eines noch freien Felds ein!\n" +
               "   Beispiel: 1 1 => links oben\n" +
               "   Beispiel: 1 3 => rechts oben\n" +
               "   Beispiel: 2 2 => Mitte";
    }

    @Override
    public void makeMove(int player, String input) 
           throws NotInitializedException, GameFinishedException, 
                  WrongPlayerException, BadInputException
    {
        if(board == null)
        {
            throw new NotInitializedException();
        }
        if(isFinished())
        {
            throw new GameFinishedException();
        }
        if(player != next)
        {
            throw new WrongPlayerException();
        }
        Scanner scanner = new Scanner(input);
        try
        {
            int row = scanner.nextInt()-1;
            int column = scanner.nextInt()-1;
            if(board[row][column] != UNDEFINED)
            {
                throw new BadInputException("Position schon belegt!");
            }
            board[row][column] = player;
            next = 1 - next; //0 => 1 oder 1 => 0;
        }
        catch(Exception e)
        {
            throw new BadInputException("Beachten Sie die Hinweise!");
        }
        finally
        {
            scanner.close();
        }
    }

    @Override
    public boolean isFinished()
    {
        if(hasWinner())
        {
            return true;
        }
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == UNDEFINED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkForWinner(int player)
    {
        boolean hasWinner;
        
        //check rows
        for(int i = 0; i < board.length; i++)
        {
            hasWinner = true;
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] != player)
                {
                    hasWinner = false;
                    break;
                }
            }
            if(hasWinner)
            {
                return true;
            }
        }
        
        //check columns
        for(int j = 0; j < board[0].length; j++)
        {
            hasWinner = true;
            for(int i = 0; i < board.length; i++)
            {
                if(board[i][j] != player)
                {
                    hasWinner = false;
                    break;
                }
            }
            if(hasWinner)
            {
                return true;
            }
        }
        
        //check diagonals
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
        {
            return true;
        }
        if(board[0][2] == player && board[1][1] == player && board[2][0] == player)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasWinner()
    {
        boolean hasWinner = checkForWinner(0);
        if(hasWinner)
        {
            winner = 0;
            return true;
        }
        hasWinner = checkForWinner(1);
        if(hasWinner)
        {
            winner = 1;
            return true;
        }
        return false;
    }

    @Override
    public int getWinner() throws NoWinnerException
    {
        if(!hasWinner())
        {
            throw new NoWinnerException();
        }
        return winner;
    }    
}
