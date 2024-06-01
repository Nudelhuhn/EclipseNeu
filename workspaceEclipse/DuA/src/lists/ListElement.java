package lists;

public class ListElement
{
   private int daten;
   private ListElement naechstes;

   ListElement(int daten, ListElement naechstes)
   {
      this.daten = daten;
      this.naechstes = naechstes;
   }

   ListElement(int daten)
   {
      this(daten, null);
   }

   public int getDaten()
   {
      return daten;
   }

   public ListElement getNaechstes()
   {
      return naechstes;
   }

   public void setNaechstes(ListElement naechstes)
   {
      this.naechstes = naechstes;
   }

   public void setDaten(int daten)
   {
      this.daten = daten;
   }
}
