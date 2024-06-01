package oop.intro;

import java.util.Scanner;

public class ScannerExample
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            System.out.print(">");
            String line = input.nextLine();
            System.out.println("Eingabe war: " + line);
            if(line.equals("exit"))
            {
                break;
            }
        }
        input.close();
        
        Scanner input2 = new Scanner("14 93,45");
        int i = input2.nextInt();
        double d = input2.nextDouble();
        System.out.println(i + " --- " + d);
        input2.close();
    }
}
