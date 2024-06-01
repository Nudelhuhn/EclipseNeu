package uebung13;

import java.util.ArrayList;
import java.util.List;

// Observer-Schnittstelle
interface Observer {
    void update(double newKontostand);
}

// Klasse Konto mit Observer-Muster
public class Konto {
    private double kontostand;
    private List<Observer> observers = new ArrayList<>();

    // Methode zum Hinzuf�gen eines Beobachters
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Methode zum Entfernen eines Beobachters
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Methode zum Benachrichtigen aller Beobachter �ber �nderungen am Kontostand
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(kontostand);
        }
    }

    // Methode zum Durchf�hren einer Buchung mit Benachrichtigung der Beobachter
    public void buchen(double betrag) {
        kontostand += betrag;
        notifyObservers();
    }

    // Weitere Methoden wie z.B. stornieren, toString, etc.

    public double getKontostand() {
        return kontostand;
    }

    public static void main(String[] args) {
        Konto konto = new Konto();

        // Beobachter erstellen
        Observer beobachter1 = new KontoBeobachter("Beobachter 1");
        Observer beobachter2 = new KontoBeobachter("Beobachter 2");

        // Beobachter dem Konto hinzuf�gen
        konto.addObserver(beobachter1);
        konto.addObserver(beobachter2);

        // Einige Buchungen durchf�hren
        konto.buchen(1000);
        konto.buchen(-500);

        // Beobachter wieder entfernen
        konto.removeObserver(beobachter1);

        // Weitere Buchungen durchf�hren
        konto.buchen(200);
    }
}

// Beispiel f�r einen Beobachter
class KontoBeobachter implements Observer {
    private String name;

    public KontoBeobachter(String name) {
        this.name = name;
    }

    @Override
    public void update(double newKontostand) {
        System.out.println(name + " wurde �ber eine �nderung am Kontostand informiert. Neuer Kontostand: " + newKontostand);
    }
}
