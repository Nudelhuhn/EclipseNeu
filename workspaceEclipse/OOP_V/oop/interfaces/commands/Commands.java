package oop.interfaces.commands;

import java.util.*;

class Commands
{
    public static <S> S executeAll(S s, List<Command<S>> commands)
    {
        S object = s;
        for(Command<S> command: commands)
        {
            object = command.execute(object);
        }
        return object;
    }
    
    public static <S> void printAll(List<Command<S>> commands)
    {
        for(Command<S> command: commands)
        {
            System.out.println(command);
        }
    }
}
