package test;

public class hoch2
{
  public static long recursion (int n)
    { if  (n <= 0)  
        return  1;
      else
        return  ( recursion(n-1) + recursion(n-1) );
    }
  public static long recursion_iter (int n)
    { long erg = 1; 
      for (int i=1; i<=n; i++)
          erg*=2;
      return erg;
    }    
    
  public static void main(String args[])
  {     

  for (int i=0; i<=10; i++)
    System.out.println( "Wert bei Eingabe von " +  i  + " ist: " + recursion(i));
      
    
  for (int j=0; j<=10; j++)
    System.out.println( "Wert bei Eingabe von " +  j  + " ist: " + recursion_iter(j));
      
    System.out.println( "Wert bei Eingabe von x"  + " ist: " + recursion_iter(33));
    System.out.println( "Wert bei Eingabe von x"  + " ist: " + recursion(33));
      
      
  }
} 

