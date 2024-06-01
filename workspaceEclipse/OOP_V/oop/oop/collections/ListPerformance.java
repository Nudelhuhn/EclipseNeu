package oop.collections;

import java.time.Duration;
import java.util.*;

interface ListOperation
{
    public void applyToList(List<Object> list);
}

public class ListPerformance
{
    private static final int REPETITIONS = 200_000;
    private static final int INITIAL_ELEMENTS = 0;

    private static void repeat(List<Object> list, ListOperation operation, String description)
    {
        long startTime = System.currentTimeMillis();
        Optional<Duration> cpuStartTime = ProcessHandle.current().info().totalCpuDuration();
        for(int i = 0; i < REPETITIONS; i++)
        {
            operation.applyToList(list);
        }
        Optional<Duration> cpuEndTime = ProcessHandle.current().info().totalCpuDuration();
        long endTime = System.currentTimeMillis();
        Duration cpuTime = null;
        if(cpuStartTime.isPresent() && cpuEndTime.isPresent())
        {
            cpuTime = cpuEndTime.get().minus(cpuStartTime.get());
        }
        long time = endTime - startTime;
        System.out.print("Dauer der Aktion \"" + description + "\": " + time  + " ms");
        if(cpuTime != null)
        {
            System.out.println(" (reine Rechenzeit: " + cpuTime.toMillis() + ")");
        }
        else
        {
            System.out.println();
        }
    }

    private static void testSuite(List<Object> list)
    {
        repeat(list, l -> l.add(0, new Object()), "Einfügen vorne");
        repeat(list, l -> l.add(new Object()), "Einfügen hinten");
        repeat(list, l -> l.get(l.size()/2), "Zugriff auf mittleres Element");
        repeat(list, l -> l.remove(0), "Löschen von vorne");
    }
    
    public static void main(String[] args)
    {
        List<Object> arrayList = new ArrayList<>();
        List<Object> linkedList = new LinkedList<>();
        for(int i = 0; i < INITIAL_ELEMENTS; i++)
        {
            Object o = new Object();
            arrayList.add(o);
            linkedList.add(o);
        }
        
        System.out.println("Ergebnisse für ArrayList:");
        testSuite(arrayList);
        System.out.println("===========================");
        System.out.println("Ergebnisse für LinkedList:");
        testSuite(linkedList);
    }
}
