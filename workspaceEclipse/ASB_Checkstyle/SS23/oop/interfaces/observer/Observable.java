package oop.interfaces.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable
{
    private int value;

    private List<Observers> observers;

    public Observable(int initialValue)
    {
        value = initialValue;
        observers = new ArrayList<Observers>();
    }

    // ruft den Konstruktor mit einem int Wert auf und setzt diesen auf 0
    public Observable()
    {
        this(0);
    }

    public void addChangeObserver(ChangeObserver obs, int from, int to)
    {
        if (obs == null || from > to)
        {
            throw new IllegalArgumentException("Objekt darf nicht null sein. Der untere Wert darf nicht größer als der obere Wert sein!");
        }
        Observers newOb = new Observers(obs, from, to);
        observers.add(newOb);
    }

    public void removeChangeObserver(ChangeObserver obs, int from, int to)
    {
        for (Observers a : observers)
        {
            if (a.compare(obs, from, to))
            {
                observers.remove(a);
                break;
                /*
                 * Änderungen an der Struktur der Liste während einer Iteration
                 * führt zu einer ConcurrentModificationException, deswegen
                 * "break" um die Iteration zu stoppen und das Element zu
                 * entfernen.
                 */
            }
        }
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        if (this.value != value)
        {
            int oldValue = this.value;
            int newValue = value;
            this.value = value;
            for (Observers obs : observers)
            {
                obs.valueHasChanged(oldValue, newValue);
            }
        }
    }

    public static void main(String[] args)
    {
        Observable o = new Observable(100);
        SMSAlarm sa = new SMSAlarm();

        o.addChangeObserver(sa, 5, 15);
        o.setValue(14);
        o.addChangeObserver(sa, 0, 20);
        o.setValue(12);
        // o.addChangeObserver(sa, 0, 20);
        // o.setValue(15);
        //
        o.removeChangeObserver(sa, 0, 20);
        o.setValue(14);
    }
}
