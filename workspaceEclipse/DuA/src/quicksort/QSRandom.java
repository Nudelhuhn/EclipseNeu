package quicksort;

class QSRandom
{
  private static long vergleiche;
  
  public static void quicksort(int[] a)
  {
    // Daten an den Indizes 0 bis n des Feldes
    int n = a.length - 1;
    zerlege (a, 0, n);
  }
  
  private static void zerlege (int[] a, int links, int rechts)
  {
    int x;                 // Zwischenspeicher für ein Objekt
    int r=rechts;           // Index von links
    int l=links;            // Index von rechts
    
    // Vergleichsschlüssel beliebig
    int random = links + (int)Math.round(Math.random()*(rechts-links));
    int key = a[random]; 
    
    // Pivotelement anzeigen
    // System.out.println("Pivotelement: " + key +  " an Position " + random);
    
    while (l<=r)
    {
      // von rechts ein kleineres Element suchen
      while (key<a[r])
      {  
        vergleiche++;
        r--;
      }
      vergleiche++; //Abbruch der While-Schleife
      // von links ein größeres Element suchen
      while (a[l]<key)
      {
        vergleiche++;
        l++;
      }
      vergleiche++; //Abbruch der While-Schleife
      
      //System.out.println("l: " + l + " r: " + r);
      if (l<=r)
      {
        if (l<r)
        {  // vertauschen
          x = a[r]; a[r] = a[l]; a[l] = x;
          //System.out.println("Tausche a[l] mit a[r], d.h. " + a[r] + "mit " + x);
        }
        // weiterrücken
        r--; 
        l++;
        //System.out.println("Weiterruecken");
      }
    }
    
    //feldAnzeigen(a);
    
    if (links < r)
    {
      //System.out.println("Rekursion(links<r): " + links + "  " + r);
      zerlege(a, links, r);
    }
    if (l < rechts)
    {
      //System.out.println("Rekursion(l<rechts): " + l + "  " + rechts);
      zerlege(a, l, rechts);
    }
  }
  
  
  public static void feldAnzeigen(int[] a)
  {
    for (int i = 0; i < a.length; i++)
    System.out.print ( a[i] + " ");
    System.out.println();
  }
  
  public static void main(String[] args)
  {
    int[] feld = {2,6,1,5,3,4};
    
    // unsortiertes Feld anzeigen
    feldAnzeigen(feld);
    
    // Feld sortieren
    quicksort(feld);
    
    // sortiertes Feld anzeigen
    feldAnzeigen(feld);
    
    vergleiche = 0; 
    int[] zahlenfolge = new int[100000];
    for (int i = 0; i < zahlenfolge.length; i++)
    zahlenfolge[i] = (int) (Math.random() * 100000);
    //zahlenfolge[i] = i;
    //feldAnzeigen(zahlenfolge);
    
//    MyTimer timer1 = new MyTimer(); 
//    quicksort(zahlenfolge);
//    System.out.println("Verbrauchte Zeit QS mit Random-PE in MilliSek.: " + timer1.getElapsed());
//    //feldAnzeigen(zahlenfolge);
//    System.out.println("Anzahl Vergleiche: " + vergleiche);
    
    /*
    int[] test1 = {14,15,15,15};
    quicksort(test1);
    feldAnzeigen(test1);
    
    int[] test2 = {15, 15, 14, 15, 15};
    quicksort(test2);
    feldAnzeigen(test2);
    
    int[] test3 = {1, 15, 32, 15, 15};
    quicksort(test3);
    feldAnzeigen(test3);
    
    int[] test4 = {1, 43, 33, 22, 1};
    quicksort(test4);
    feldAnzeigen(test4);
    
    */
    
    
  }
}
