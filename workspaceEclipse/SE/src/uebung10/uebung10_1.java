package uebung10;

public class uebung10_1
{

}

////Single Responsibility Principle
///*
// * Es sollte nur einen Grund geben eine Klasse zu verändern
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
//class Schüler extends Person {
//    void lernen() {
//        /* Lernlogik tagsüber */
//    }
//}
//
//
////Interface Segregation Principle
///*
// * Ein Interface sollte nur eine Funktionalität abbilden
// */
//interface Produzent {
//    // Methoden, die Produzent benötigt
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
//interface AlarmAuslöser {
//    void alarmAuslösen();
//}
//
//interface MailSender {
//    void mailVersenden(String inhalt, Adresse empfänger);
//}
//
//class Adresse {
//    // Adresse Klasse
//}
//
//class LeitstandImpl implements KennzahlenProvider, DiagrammErsteller, AlarmAuslöser, MailSender {
//    // Implementierung für die spezifischen Interfaces
//}
//
//
//
////Liskov Substitution Principle
///*
// * Objekte einer Unterklasse müssen die Schnittstelle ihrer Oberklasse erfüllen
// * 
// * In diesem Fall darf die überschriebene Funktion remove() nicht einfach eine
// * "UnsupportedOperationException()" werfen, da sonst die Objekte dieser Klasse
// * nicht mehr die vollständige Funktionalität des Interfaces List einhalten würde,
// * denn List hat in diesem Fall nicht solch eine Exception.
// * Genauer gesagt ist eine Exception keine Erweiterung des Codes, sie schränkt Code
// * eher ein, weswegen bestimmten Funktionalitäten nicht mehr gewährleistet wären.
// */
//interface List<T> {
//    void add(T element);
//    int size();
//    void remove(int index);
//    /* … */
//}
//
//class ArrayList<T> implements List<T> {
//    /* … */ 
//
//    @Override
//    public void remove(int index) {
//        // Implementierung der Entfernungslogik
//    }
//}
