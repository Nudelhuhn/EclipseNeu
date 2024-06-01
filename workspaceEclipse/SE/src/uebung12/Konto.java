package uebung12;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Konto {
    private int kontostand;
    private Stack<Buchung> buchungen = new Stack<>();

    public static void main(String[] args) {
        Konto konto = new Konto();
        
        System.out.println(konto);
        
        // Ein paar Buchungen mit unterschiedlichen Beträgen vornehmen
        List<Integer> beträge = Arrays.asList(1000, 100, -500, 200);
        for (int betrag: beträge) {
            konto.buchen(betrag);
            System.out.println(konto);
        }
        
        // Alle Buchungen wieder stornieren; wir stornieren noch ein paar Mal
        // mehr, um zu prüfen, dass das auch mit einem leeren Stack funktioniert
        for (int i = 0; i < beträge.size() + 5; i++) {
            konto.stornieren();
            System.out.println(konto);
        }
    }
    
    private class Buchung implements Runnable {
        private int wert;

        public Buchung(int wert) {
            this.wert = wert;
        }

        @Override
        public void run() {
            System.out.println("Verändere den Kontostand um " + wert);
            kontostand += wert;
        }
        
        public void undo() {
            System.out.println("Korrigiere den Kontostand um " + (-wert));
            kontostand -= wert;            
        }
    }
    
    public void buchen(int wert) {
        Buchung buchung = new Buchung(wert);
        buchungen.push(buchung);
        buchung.run();
    }
    
    public void stornieren() {
        if (buchungen.isEmpty()) {
            return;
        }
        
        Buchung letzteBuchung = buchungen.pop();
        letzteBuchung.undo();
    }

    @Override
    public String toString() {
        return "Konto [kontostand=" + kontostand + "]";
    }
}
