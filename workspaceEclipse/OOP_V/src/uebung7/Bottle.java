package uebung7;

import java.util.*;

public class Bottle<T extends Drink>
{
	/*
	 * Es war nicht notwendig zwei Arten zu definieren, da Generics zur Laufzeit immer
	 * den Datentyp beibehalten den Ihnen als erstes zugewiesen wird. Das hei�t wenn eine Falsche
	 * mit Bier aufgef�llt wird, kann danach sowieso kein Wein mehr eingef�llt werden.
	 */
	private T wineBottle;
	private T beerBottle;

	public void fill(T drink)
	{
		if(drink instanceof Beer) {
			if(beerBottle == null)
			beerBottle = drink;
		}else {
			if(wineBottle == null)
			wineBottle = drink;
		}
	}

	public void empty(T drink)
	{
		if(drink instanceof Beer) {
			beerBottle = null;
		}else {
			wineBottle = null;
		}
	}
	
	public T getBeerBottle() {
		return beerBottle;
	}
	public T getWineBottle() {
		return wineBottle;
	}
}
