package radix;

class Radix 
{
  
  static long vergleiche;
  
  static int m = 10; // m-adischer Schluessel
  static int l = 6;  // 6-stellige Werte
  //static int N = 15; // Laenge Testfeld
  static int N = 100000;  //Laenge Testfeld fuer Zeitberechnung
  
  static void feldAusgabe (int[] feld, int z)
  {
    for (int i = 0; i < z; i++)
    System.out.print(feld[i] + " ");
    System.out.println();
    System.out.println();
  } 
  
  static int zifferAnStelle (int m, int stelle, int wert)
  {
    for (int i = 0; i < stelle; i++)
    wert = wert/m;
    //System.out.println("Ziffer = " + (wert%m));
    return wert%m;
    
  } 
  
  
  static void radixsort (int[] feld)
  {
    int[] b; 
    int[] c; 
    int i,j,k,t;
    
    b = new int[N]; 
    c = new int[m+1]; 
    
    for (t = 0; t < l; t++)
    {
      // Verteilungsphase: Verteilungszahlen bestimmen
      for (i = 0; i <= m; i++)
      c[i] = 0;
      for (k = 0; k < N; k++)
      {
        j = zifferAnStelle(m, t, feld[k]);
        c[j] = c[j] +1;
        vergleiche++; 
      }
      //System.out.println("Feld c mit Anzahl von Zahlenwerten: ");
      //feldAusgabe(c,c.length-1);
      
      c[m] = N; // - c[m];
      
      for (i = 1; i <= m; i++)    
      c[m-i] = c[m-i+1] - c[m-i];
      
      //System.out.println("Feld c mit Indexpositionen von Zahlenwerten: ");
      //feldAusgabe(c,c.length-1); 
      
      
      for (i = 0; i < N; i++)
      {
        j = zifferAnStelle(m, t, feld[i]);
        b[c[j]] = feld[i];
        c[j] = c[j] +1;
      }
      
      //System.out.println("Feld b nach Verteilung: ");
      //feldAusgabe(b,b.length); 
      
      for (i = 0; i < N; i++)
      feld[i] = b[i];
      
      //System.out.println("Feld nach Sammeln: ");
      //feldAusgabe(feld, feld.length);
      
    }
  }
  
  
  public static void main(String args[])
  { 
    //int[] a = {40,13,22,54,15,28,76,4,77,38,16,18};
    
    /*
    int[]a = {56, 345, 7, 7, 46, 222, 321, 51, 75, 420, 339, 66, 77, 456789, 23456};
    feldAusgabe(a,a.length);
    radixsort(a);   
    feldAusgabe(a,a.length);
    */
    
    
    vergleiche = 0; 
    int[] zahlenfolge = new int[100000];
    for (int i = 0; i < zahlenfolge.length; i++)
    zahlenfolge[i] = (int) (Math.random() * 100000);
    System.out.println("Anzahl Operationen: " + vergleiche);
    
  }
}
