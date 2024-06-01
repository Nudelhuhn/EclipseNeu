package oop.interfaces.game;

interface Game
{
    public String getNameOfGame();
    public int getMinNumberOfPlayers();
    public int getMaxNumberOfPlayers();
    public void initialize(int players);
    public String getBoard() throws NotInitializedException;
    public int getNextPlayer() throws NotInitializedException, GameFinishedException;
    public String getHintForInput();
    public void makeMove(int player, String input) 
           throws NotInitializedException, GameFinishedException, 
                  WrongPlayerException, BadInputException;
    public boolean isFinished();
    public boolean hasWinner();
    public int getWinner() throws NoWinnerException;
}
