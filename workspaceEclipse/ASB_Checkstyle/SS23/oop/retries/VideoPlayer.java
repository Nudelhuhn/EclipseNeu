package oop.retries;

public class VideoPlayer
{
    public static boolean playWithRetries(Video video, int maxTries)
    {
        if (maxTries <= 0)
        {
            System.out.println("Video wasn´t played (to low maxTries).");
            return false;
        }
        if (video == null && maxTries > 0)
        {
            throw new NullPointerException();
        }

        int counter = 1;
        while (counter <= maxTries)
        {
            // you jump in the catch block at the moment an Exception is
            // recognized in the try block
            try
            {
                video.play();
                System.out.println("Video could be played after " + counter + " tries");
                return true;
            }
            catch (OverloadException e)
            {
                counter++;
            }
        }
        System.out.println("Video couldn´t be played after " + maxTries + " tries");
        return false;
    }

    public static void main(String[] args)
    {
        playWithRetries(new Video(), 10);
    }
}
