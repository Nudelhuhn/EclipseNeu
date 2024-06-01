package oop.interfaces.game;

enum RPSOption
{
    UNDEFINED, ROCK, PAPER, SCISSORS;

    public String toString()
    {
        switch(name())
        {
            case "UNDEFINED":
                return "Noch keinen Zug gemacht";
            case "ROCK":
                return "Stein";
            case "PAPER":
                return "Papier";
            case "SCISSORS":
                return "Schere";
            default:
                return "---";
        }
    }
}

class RockPaperScissors implements Game
{
    private static final int NUMBER_OF_PLAYERS = 2;
    
    private RPSOption[] playerMoves;
    private int next;
    
    @Override
    public String getNameOfGame()
    {
        return "Schere - Stein - Papier";
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
        playerMoves = new RPSOption[NUMBER_OF_PLAYERS];
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++)
        {
            playerMoves[i] = RPSOption.UNDEFINED;
        }
        next = (int)Math.round(Math.random());
    }

    @Override
    public String getBoard() throws NotInitializedException
    {
        if(playerMoves == null)
        {
            throw new NotInitializedException();
        }
        if(!isFinished())
        {
            return "Spielstand: Noch kann nichts angezeigt werden!";
        }
        else
        {
            StringBuffer text = new StringBuffer("Spielstand:\n");
            for(int i = 0; i < playerMoves.length; i++)
            {
                text.append("   Person " + i + ": " + playerMoves[i]);
                if(i != playerMoves.length-1)
                {
                    text.append("\n");
                }
            }
            return text.toString();
        }
    }

    @Override
    public int getNextPlayer() throws NotInitializedException, GameFinishedException
    {
        if(playerMoves == null)
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
        return "Geben Sie bitte ein: Schere ODER Stein ODER Papier.\n" +
               "(Gross-/Kleinschreibung spielt keine Rolle)";
    }

    @Override
    public void makeMove(int player, String input) 
           throws NotInitializedException, GameFinishedException, 
                  WrongPlayerException, BadInputException
    {
        if(playerMoves == null)
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
        input = input.toUpperCase().trim();
        switch(input)
        {
            case "SCHERE":
                playerMoves[player] = RPSOption.SCISSORS;
                break;
            case "STEIN":
                playerMoves[player] = RPSOption.ROCK;
                break;
            case "PAPIER":
                playerMoves[player] = RPSOption.PAPER;
                break;
            default:
                throw new BadInputException("Sie haben sich nicht an die Vorgaben gehalten!");
        }
        next = 1 - next; //0 => 1 oder 1 => 0;
    }

    @Override
    public boolean isFinished()
    {
        for(RPSOption option: playerMoves)
        {
            if(option == RPSOption.UNDEFINED)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasWinner()
    {
        if(!isFinished())
        {
            return false;
        }
        if(playerMoves.length != 2)
        {
            throw new IllegalStateException();
        }
        return playerMoves[0] != playerMoves[1];
    }

    @Override
    public int getWinner() throws NoWinnerException
    {
        if(!hasWinner())
        {
            throw new NoWinnerException();
        }
        if(playerMoves[0] == RPSOption.SCISSORS)
        {
            if(playerMoves[1] == RPSOption.PAPER)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if(playerMoves[0] == RPSOption.ROCK)
        {
            if(playerMoves[1] == RPSOption.SCISSORS)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if(playerMoves[0] == RPSOption.PAPER)
        {
            if(playerMoves[1] == RPSOption.ROCK)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        throw new IllegalStateException();
    }    
}
