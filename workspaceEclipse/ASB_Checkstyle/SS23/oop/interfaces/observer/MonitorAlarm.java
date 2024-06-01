package oop.interfaces.observer;

public class MonitorAlarm implements ChangeObserver
{
    @Override
    public void valueHasChanged(int oldValue, int newValue)
    {
        System.out.println("�nderung von " + oldValue + " zu " + newValue + " wird auf Bildschirm ausgegeben");
    }
}
