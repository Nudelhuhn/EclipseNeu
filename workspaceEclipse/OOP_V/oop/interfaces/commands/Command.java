package oop.interfaces.commands;

interface Command<S>
{
    public S execute(S s);
}