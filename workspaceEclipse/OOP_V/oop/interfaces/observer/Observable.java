package oop.interfaces.observer;

import java.util.*;

public class Observable
{
    private int value;
    private List<ChangeObserver> observers;
    
    public Observable(int initialValue)
    {
        value = initialValue;
        observers = new ArrayList<ChangeObserver>();
    }
    
    public Observable()
    {
        this(0);
    }
    
    public void addChangeObserver(ChangeObserver obs)
    {
        observers.add(obs);
    }
    
    public void removeChangeObserver(ChangeObserver obs)
    {
        observers.remove(obs);
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        if(this.value != value)
        {
            int oldValue = this.value;
            int newValue = value;
            this.value = value;
            for(ChangeObserver obs: observers)
            {
                obs.valueHasChanged(oldValue, newValue);
            }
        }
    }
}
