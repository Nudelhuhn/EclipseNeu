package oop.retries;

public class Video
{
    public static final double OVERLOAD_PROBABILITY = 0.9;

    public static final int PLAY_TIME = 5; // Sekunden

    private void waitSomeTime(int seconds)
    {
        try
        {
            // Thread.sleep only approximately let the Thread sleep the given
            // time and not it exactly
            Thread.sleep(seconds * 1000L);
        }
        catch (InterruptedException e)
        {
        }
    }

    public void play() throws OverloadException
    {
        if (Math.random() < OVERLOAD_PROBABILITY)
        {
            // mit Wahrscheinlichkeit OVERLOAD_PROBABILITY
            // wird eine Überlast simuliert
            throw new OverloadException();
        }

        // ansonsten wird das Abspielen eines Videos simuliert
        waitSomeTime(PLAY_TIME);
    }
}

class OverloadException extends Exception
{

}
