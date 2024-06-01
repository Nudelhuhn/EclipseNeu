package oop.interfaces.observer;

public class DatabaseLogger implements ChangeObserver
{
    public void valueHasChanged(int oldValue, int newValue)
    {
        System.out.println("Änderung von " + oldValue + " zu " + newValue + " wird in Datenbank eingetragen");
    }
}
