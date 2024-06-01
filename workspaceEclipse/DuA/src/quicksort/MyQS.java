package quicksort;

public class MyQS{
  private static long anzahl;
  private static long vergleiche;
  private static long vertauschungen;
  
  public static void quicksort (int list[], int low, int high)
  {
    int i,j;
    int hilf;
    
    if(low < high)
    {
      i = low;
      j = high-1;
      do
      { 
        while (list[i] < list [high])
        {
          i++;
          vergleiche++;       
        }
        vergleiche++;
        
        while ((list[j] >= list[high]) && (i<j))
        {
          j--;
          vergleiche++;
        }
        if (i < j) vergleiche++;
        
        if (i<j)
        {
          hilf = list[i];
          list[i] = list[j];
          list[j] = hilf;
          i++;                   // Performanzsteigerung (weniger Vergleiche>)
          if (j > i+1) j--;            //!!! Fehler im Script!!! Optimierung
          vertauschungen++;
        }
      }
      while (i<j);
      
      // ein Vergleich fehlt für Fall i = j
      //if (i == j) vergleiche++;
      
      hilf = list[high];
      list[high] = list[i];
      list[i] = hilf;
      vertauschungen++;
      /*
      for (int z=0; z<list.length; z++)
      System.out.print( list[z]+", " );
      System.out.println();
      */
      quicksort(list, low, i-1);anzahl++;
      quicksort(list, i+1, high);anzahl++;
    }
  }
  
  public static void main(String args[])
  { anzahl=0;
    vergleiche = 0;
    vertauschungen = 0;
    int a[] = {15,27,12,3,32,4,8,6,5,1,45,35,13};
    //int a[] = {7,1,2,3,4,5,6};
    //int a[] = {6,4,5,2,3,1,7};
    //int a[] = {12,4,3,11};
    //int a[] = {56,22,79,27,9,30,61,4,69,38,52,89,23,17,68,30};
    //int a[] = {3,5};
    //int a[] = {5,3};
    //int a[] = {3};
    //int a[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    
    /*             
    System.out.println("Worst Case: aufsteigend sortiert mit Pivotelement ganz rechts:");
    
    int a[]= new int[1000];
    for (int i=0; i<a.length; i++)
    // System.out.print( a[i]+", " );
    a[i] = i;
    
    System.out.println();
    
    */          
    quicksort (a, 0, a.length-1);anzahl++;
    
    System.out.println("Anzahl Aufrufe: " + anzahl );
    System.out.println("Anzahl Vergleiche: " + vergleiche );
    System.out.println("Anzahl Vertauschungen: " + vertauschungen);
    
    
    anzahl=0;
    vergleiche = 0;
    vertauschungen = 0;
    
    System.out.println("Avg Case: Random-Feld mit Pivotelement ganz rechts:");  
    int[] zahlenfolge = new int[10000];
    for (int i = 0; i < zahlenfolge.length; i++)
    zahlenfolge[i] = (int) (Math.random() * 100000);
    //zahlenfolge[i] = i;  //Stack-Overflow durch Rekursion bei 100.000er Feld !!!
    
//    MyTimer timer1 = new MyTimer(); 
//    quicksort (zahlenfolge, 0, zahlenfolge.length-1);anzahl++;
//    System.out.println("Verbrauchte Zeit QuickSort Random-Feld: " + timer1.getElapsed());
//    
//    System.out.println("Anzahl Aufrufe: " + anzahl );
//    System.out.println("Anzahl Vergleiche: " + vergleiche );
//    System.out.println("Anzahl Vertauschungen: " + vertauschungen);
    
  }
}
  
