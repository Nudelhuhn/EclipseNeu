package uebung10;

public class uebung10_1
{

}

////Single Responsibility Principle
///*
// * Es sollte nur einen Grund geben eine Klasse zu ver�ndern
// */
//class Person {
//    void tagesablauf() {
//        schlafen();
//        essen();
//        freizeit();
//    }
//
//    /* ... */
//}
//
//class Mitarbeiter extends Person {
//    void arbeiten() {
//        /* Arbeitslogik */
//    }
//}
//
//class Sch�ler extends Person {
//    void lernen() {
//        /* Lernlogik tags�ber */
//    }
//}
//
//
////Interface Segregation Principle
///*
// * Ein Interface sollte nur eine Funktionalit�t abbilden
// */
//interface Produzent {
//    // Methoden, die Produzent ben�tigt
//}
//
//interface KennzahlenProvider {
//    double[] kennzahlenAbholen(Produzent p);
//}
//
//interface DiagrammErsteller {
//    void balkendiagramm(double[] werte);
//    void histogramm(double[] werte);
//}
//
//interface AlarmAusl�ser {
//    void alarmAusl�sen();
//}
//
//interface MailSender {
//    void mailVersenden(String inhalt, Adresse empf�nger);
//}
//
//class Adresse {
//    // Adresse Klasse
//}
//
//class LeitstandImpl implements KennzahlenProvider, DiagrammErsteller, AlarmAusl�ser, MailSender {
//    // Implementierung f�r die spezifischen Interfaces
//}
//
//
//
////Liskov Substitution Principle
///*
// * Objekte einer Unterklasse m�ssen die Schnittstelle ihrer Oberklasse erf�llen
// * 
// * In diesem Fall darf die �berschriebene Funktion remove() nicht einfach eine
// * "UnsupportedOperationException()" werfen, da sonst die Objekte dieser Klasse
// * nicht mehr die vollst�ndige Funktionalit�t des Interfaces List einhalten w�rde,
// * denn List hat in diesem Fall nicht solch eine Exception.
// * Genauer gesagt ist eine Exception keine Erweiterung des Codes, sie schr�nkt Code
// * eher ein, weswegen bestimmten Funktionalit�ten nicht mehr gew�hrleistet w�ren.
// */
//interface List<T> {
//    void add(T element);
//    int size();
//    void remove(int index);
//    /* � */
//}
//
//class ArrayList<T> implements List<T> {
//    /* � */ 
//
//    @Override
//    public void remove(int index) {
//        // Implementierung der Entfernungslogik
//    }
//}
