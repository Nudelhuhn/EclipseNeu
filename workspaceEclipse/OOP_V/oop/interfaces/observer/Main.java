package oop.interfaces.observer;

public class Main
{
    public static void main(String[] args)
    {
        Observable observable = new Observable();
        
        System.out.println("keine Ausgabe erwartet:");
        observable.setValue(1);
        observable.setValue(2);
        System.out.println("==========");
        
        System.out.println("ein MonitorAlarm erwartet:");
        MonitorAlarm ma = new MonitorAlarm();
        observable.addChangeObserver(ma);
        observable.setValue(3);
        observable.setValue(3);
        System.out.println("==========");
        
        System.out.println("MonitorAlarm, SMSAlarm und DBLogger erwartet (je 2 Mal):");
        SMSAlarm sa = new SMSAlarm();
        observable.addChangeObserver(sa);
        DatabaseLogger dbl = new DatabaseLogger();
        observable.addChangeObserver(dbl);
        observable.setValue(4);
        observable.setValue(5);
        observable.setValue(5);
        System.out.println("==========");
        
        System.out.println("DBLogger erwartet (2 Mal):");
        observable.removeChangeObserver(ma);
        observable.removeChangeObserver(sa);
        observable.setValue(6);
        observable.setValue(7);
        observable.setValue(7);
        System.out.println("==========");
    }
}
