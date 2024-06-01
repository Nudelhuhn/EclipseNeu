package oop.interfaces.observer;

public class MonitorAlarm implements ChangeObserver
{
    public void valueHasChanged(int oldValue, int newValue)
    {
        System.out.println("Änderung von " + oldValue + " zu " + newValue + " wird auf Bildschirm angezeigt");
    }
}
