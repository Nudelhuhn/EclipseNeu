package oop.interfaces.game;

import java.util.Scanner;

class GamePlay
{
    public static void play(Game game, Scanner scanner)
    {
        System.out.println("Willkommen zum Spiel " + game.getNameOfGame() + "!");
        int min = game.getMinNumberOfPlayers();
        int max = game.getMaxNumberOfPlayers();
        int players = -1;
        if(min == max)
        {
            if(min == 1)
            {
                System.out.println("Das Spiel ist für eine einzige Person.");
            }
            else
            {
                System.out.println("Es müssen genau " + min + " Personen teilnehmen.");
            }
            players = min;
        }
        else
        {
            while(players < min || players > max)
            {
                System.out.println("Am Spiel können zwischen " + min + " und " + max + " Personen teilnehmen.");
                System.out.print("Bitte geben Sie die Anzahl der Spieler ein: ");
                players = scanner.nextInt();
            }
        }
        
        game.initialize(players);
        System.out.println("Das Spiel hat begonnen. Dies ist der Startzustand:");
        try
        {
            System.out.println(game.getBoard());
        }
        catch(NotInitializedException e)
        {
            System.out.println("Eine Ausnahme zur fehlenden Initialisierung für die erste Anzeige des Spielbretts hätte nicht passieren dürfen.");
            return;
        }
        try
        {
            boolean hintNeeded = true;
            while(!game.isFinished())
            {
                int next = game.getNextPlayer();
                while(true)
                {
                    try
                    {
                        System.out.println("Person Nr. " + next + " ist am Zug.");
                        if(hintNeeded)
                        {
                            System.out.println("Hinweis zur Eingabe: " + game.getHintForInput());
                            hintNeeded = false;
                        }
                        System.out.print(">>> ");
                        String input = scanner.nextLine();
                        game.makeMove(next, input);
                        break;
                    }
                    catch(BadInputException e)
                    {
                        System.out.println("Falsche Eingabe: " + e.getMessage());
                        hintNeeded = true;
                    }
                }
                System.out.println(game.getBoard());
            }
            System.out.println("Das Spiel ist beendet.");
            if(players == 1)
            {
                if(game.hasWinner())
                {
                    System.out.println("Die einzige spielende Person hat gewonnen. Glückwunsch!");
                }
                else
                {
                    System.out.println("Die einzige spielende Person hat verloren. Schade!");
                }
            }
            else //players > 1
            {
                if(game.hasWinner())
                {
                    int winner = game.getWinner();
                    System.out.println("Person Nr. " + winner + " hat gewonnen. Glückwunsch!");
                }
                else
                {
                    System.out.println("Es gibt niemanden, der das Spiel gewonnen hat.");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Sorry, da lief leider etwas falsch bei uns.");
        }
    }
}
