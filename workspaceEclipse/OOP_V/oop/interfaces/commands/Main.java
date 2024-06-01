package oop.interfaces.commands;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        String start = "abcdef";
        List<Command<String>> commands = new ArrayList<>();
        Insert i0 = new Insert("0", 0);
        Insert i1 = new Insert("1", 1);
        Insert i2 = new Insert("2", 2);
        Insert i3 = new Insert("3", 3);
        Append a = new Append("999999999999999");
        Insert i10 = new Insert("10", 10);
        Delete d = new Delete(11, 12);
        commands.add(i3);
        commands.add(i3);
        commands.add(i2);
        commands.add(i1);
        commands.add(i0);
        commands.add(a);
        commands.add(i10);
        commands.add(d);
        String end = Commands.executeAll(start, commands);
        System.out.println(start + " => " + end + " durch folgende Operationen:");
        Commands.printAll(commands);
        
    }
}
