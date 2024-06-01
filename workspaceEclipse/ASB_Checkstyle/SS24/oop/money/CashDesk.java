package oop.money;

import java.util.ArrayList;
import java.util.List;

public class CashDesk
{
    public static List<Double> pay(double price, double amount)
    {
        if (price < 0 || amount < 0)
        {
            throw new IllegalArgumentException("both must be above 0");
        }
        if (amount < price)
        {
            throw new IllegalArgumentException("amount smaller than price");
        }
        // floating point numbers dont have 100% precision
        // e.g. 0.1 == 0.09998
        // => * 100 + 0.1
        double[] values =
        { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };
        // 50€, 20€, 10€, ... , 0.01€
        int i = 0;
        List<Double> change = new ArrayList<Double>();
        double decrement = (int) (amount * 100 + 0.1) - (int) (price * 100 + 0.1);
        System.out.println(decrement);
        while (decrement > 0)
        {
            if (decrement - values[i] < 0)
            {
                i++;
                continue;
            }
            decrement -= values[i];
            change.add(values[i] / 100);
        }
        return change;
    }

    public static void main(String[] args)
    {
        // List<Double> list = pay(4.89, 10);
        // System.out.println(list);
        List<Double> list = pay(40.05, 50);
        System.out.println(list);
        // List<Double> list = pay(60.59, 70);
        // System.out.println(list);
        // // [5.0, 2.0, 2.0, 0.2, 0.2, 0.01]
        // for (double i = 0; i < 1000; i++)
        // {
        // System.out.println(i + " " + pay((i / 100), 10));
        // }
    }
}
