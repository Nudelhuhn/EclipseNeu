package selectionSort;

public class SelSort
{ 
  static long vergleiche = 0;
  static long vertauschungen = 0;
  
  
  // SelctionSort
  static void selectionSort(int[] a)
  {
    int i=0, min=0, minIndex=0;
    while (i<a.length-1)
    { 
      // Bestimmung des i-kleinsten Elements
      min = a[i];
      minIndex = i;
      for (int j=i+1; j<a.length; j++)
      {
        vergleiche++;
        if (a[j]<min)
        {
          minIndex=j;
          min=a[j];
        }
      }
      // Gefundenes Element wird an Stelle a[i] geschrieben
      a[minIndex] = a[i];
      a[i]=min;
      i++;
      vertauschungen++;
    }
  }
  
  public static void main(String args[])
  { 
    vergleiche = 0;
    vertauschungen = 0; 
    // int a[] = {9,1,2,3,4,5,6,7,8};
  int a[] = {56,22,79,27,9,30,61,4,69,38,52,89,23,17,68,30};
  selectionSort(a);
  
  for (int i=0; i<a.length; i++)
  System.out.print( a[i]+", " );
  System.out.println();
  System.out.println("Anzahl Vergleiche: " + vergleiche );
  System.out.println("Anzahl Vertauschungen: " + vertauschungen );
  vergleiche = 0;
  vertauschungen = 0;
    
  int[] zahlenfolge = new int[100000];            
  for (int i=1; i< 100000; i++)                   
  //zahlenfolge[i] = (int) (Math.random() * 100000);  
  zahlenfolge[i] = i; 
  
  
//  MyTimer timer1 = new MyTimer(); 
//  //System.out.println(timer1.start);             
//  selectionSort(zahlenfolge);
//  System.out.println("Verbrauchte Zeit: " + timer1.getElapsed());
//  System.out.println("Es wurden "+vergleiche+" Vergleiche benoetigt");
//  System.out.println("Anzahl Vertauschungen: " + vertauschungen );
}
} 
