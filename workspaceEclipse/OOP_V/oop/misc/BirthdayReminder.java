package oop.misc;

import java.time.*;
import java.time.format.*;
import java.util.*;

@SuppressWarnings("preview")
record Birthday(String name, LocalDate date) {}

public class BirthdayReminder
{
    private static final int DEFAULT_FROM = -2;
    private static final int DEFAULT_TO = 3;
    
    private int from;
    private int to;
    
    private Set<Birthday> data;
    private LocalDate today;
    private DateTimeFormatter formatter;
    
    public BirthdayReminder(int from, int to)
    {
        checkParams(from, to);
        this.from = from;
        this.to = to;
        this.today = LocalDate.now();
        this.formatter = DateTimeFormatter.ofPattern("d.M.y");
        this.readData();
    }
    
    public BirthdayReminder()
    {
        this(DEFAULT_FROM, DEFAULT_TO);
    }
    
    private void checkParams(int from, int to)
    {
        if(Math.abs(from) > 10 || Math.abs(to) > 10 || from > to)
        {
            throw new IllegalArgumentException();
        }
    }

    private void readData()
    {
        String[] rawData = 
        {
            "Angelina Jolie / 4.6.1975",
            "Jürgen Sparwasser / 4.6.1948",
            "Lukasz Piszczek / 3.6.1985",
            "Rafael Nadal / 3.6.1986",
            "Graham Bonney / 2.6.1943",
            "Charlie Watts / 2.6.1941",
            "Ken Follett / 5.6.1949",
            "Fritzi Haberlandt / 6.6.1975",
            "Dunja Hayali / 6.6.1974",
            "Van Morrison / 31.8.1945",
            "Janina Fautz / 31.5.1995",
            "Marco Reus / 31.5.1989",
            "Alanis Morissette / 1.6.1974",
            "Tom Jones / 7.6.1940",
            "Tim Berners-Lee / 8.6.1955",
            "Katja Suding / 30.12.1975",
            "Claudia Kleinert / 31.12.1969",
            "Sven Regener / 1.1.1961",
            "Hanno Balitsch / 2.1.1981"
        };
        data = new HashSet<>();
        for(String line: rawData)
        {
            String[] parts = line.split("/");
            if(parts.length != 2)
            {
                System.out.println("Eintrag \"" + line + "\" kann nicht verwendet werden, " +
                                   "da falsche Struktur.");
                continue;
            }
            String name = parts[0].trim();
            try
            {
                LocalDate date = LocalDate.parse(parts[1].trim(), formatter);
                data.add(new Birthday(name, date));
            }
            catch(DateTimeParseException e)
            {
                System.out.println("Eintrag \"" + line + "\" kann nicht verwendet werden, " +
                                   "da Datum nicht geparst werden kann.");
            }
        }
    }
    
    public void nextDay()
    {
        today = today.plusDays(1);
    }
    
    public void previousDay()
    {
        today = today.plusDays(-1);
    }
    
    public void today()
    {
        today = LocalDate.now();
    }
    
    public void setDate(String dateString) throws DateTimeParseException
    {
        today = LocalDate.parse(dateString, formatter);
    }
    
    public String checkAll(List<String> output)
    {
        for(int distance = from; distance <= to; distance++)
        {
            checkAllForGivenDistance(distance, output);
        }
        String headline = "Geburtstagserinnerungen für den " + today.format(formatter);
        return headline;
    }
    
    private void checkAllForGivenDistance(int distanceOfDays, List<String> output)
    {
        for(Birthday b: data)
        {
            check(b, distanceOfDays, output);
        }
    }

    private void check(Birthday b, int distanceOfDays, List<String> output)
    {
        LocalDate celebrationDate = b.date().withYear(today.getYear());
        Period distancePeriod = Period.between(today, celebrationDate);
        if(distancePeriod.getMonths() >= 6)
        {
            celebrationDate = celebrationDate.minusYears(1);
            distancePeriod = Period.between(today, celebrationDate);
        }
        else if(distancePeriod.getMonths() <= -6)
        {
            celebrationDate = celebrationDate.plusYears(1);
            distancePeriod = Period.between(today, celebrationDate);
        }
        Period expectedDistancePeriod = Period.ofDays(distanceOfDays);
        if(distancePeriod.equals(expectedDistancePeriod))
        {
            String line = b.name() + " " + tense(distanceOfDays) + " " +
                          toDistanceExpression(distanceOfDays) + " den " +
                          (celebrationDate.getYear()-b.date().getYear()) + ". Geburtstag.";
            line += " (Zum Test: Geburtstag ist " + b.date().format(formatter) + ")";
            output.add(line);
        }
    }

    private static String tense(int distanceOfDays)
    {
        return distanceOfDays >= 0 ? "hat" : "hatte";
    }

    private static String toDistanceExpression(int distanceOfDays)
    {
        return switch(distanceOfDays)
        {
            case 0 -> "heute";
            case -1 -> "gestern";
            case -2 -> "vorgestern";
            case 1 -> "morgen";
            case 2 -> "übermorgen";
            default ->
            {
                if(distanceOfDays > 0)
                {
                    yield "in " + distanceOfDays + " Tagen";
                }
                else
                {
                    yield "vor " + (-distanceOfDays) + " Tagen";
                }
            }
       };
    }
    
    public static void main(String[] args)
    {
        BirthdayReminder reminder = new BirthdayReminder();
        UserInterface ui = new UserInterface(reminder);
        ui.run();
    }
}

class UserInterface
{
    private BirthdayReminder reminder;
    private List<String> output;

    public UserInterface(BirthdayReminder reminder)
    {
        this.reminder = reminder;
        this.output = new ArrayList<>();
    }
    
    public void run()
    {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        update();
        while(running)
        {
            System.out.print(">");
            String line = input.nextLine();
            switch(line)
            {
                case "" -> update();
                case "+" -> next();
                case "-" -> previous();
                case "today", "heute" -> today();
                case "exit", "quit", "ende" -> running = false;
                default -> setDateOrHelp(line);
            }
        }
        input.close();
    }

    private void update()
    {
        output.clear();
        String title = reminder.checkAll(output);
        System.out.println(title);
        System.out.println("-".repeat(title.length()));
        output.forEach(System.out::println);
        System.out.println("=".repeat(80));
    }

    private void next()
    {
        reminder.nextDay();
        update();
    }

    private void previous()
    {
        reminder.previousDay();
        update();
    }

    private void today()
    {
        reminder.today();
        update();
    }
    
    private void setDateOrHelp(String s)
    {
        try
        {
            reminder.setDate(s);
            update();
        }
        catch(DateTimeParseException e)
        {
            help(s);
        }
    }

    private void help(String s)
    {
        System.out.println("Ungültiges Kommando: " + s);
        System.out.println("    Gültige Kommandos sind:");
        System.out.println("    + => ein Tag vorwärts");
        System.out.println("    - => ein Tag rückwärts");
        System.out.println("    <Datum> => aktuelles Datum auf Eingabe setzen");
        System.out.println("    today|heute => aktuelles Datum auf das heutige Datum setzen");
        System.out.println("    <keine Eingabe> => erneute Ausgabe der aktuellen Erinnerung");
        System.out.println("    exit|quit|ende => Beendigung des Programms");
    }
}
