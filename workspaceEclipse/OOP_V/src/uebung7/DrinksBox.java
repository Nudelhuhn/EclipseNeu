package uebung7;

import java.util.*;

//b)
public class DrinksBox<T extends Drink>
{
	private List<Bottle<Beer>> beerBox;
	private List<Bottle<Wine>> wineBox;
	private int beerBottleCount = 0;
	private int wineBottleCount = 0;

	public DrinksBox(T drink)
	{
		if (drink instanceof Beer)
		{
			beerBox = new ArrayList<Bottle<Beer>>();
		} else
		{
			wineBox = new ArrayList<Bottle<Wine>>();
		}
	}

	public void addBottle(T drink)
	{
		if (drink instanceof Beer && beerBottleCount <= 24)
		{
			beerBox.add(new Bottle<Beer>());
			beerBottleCount++;
		} else if (drink instanceof Wine && wineBottleCount <= 12)
		{
			wineBox.add(new Bottle<Wine>());
			wineBottleCount++;
		}
	}
}

//c)
class DrinksBoxMixed<T extends Drink>{
	private List<Bottle<T>> mixedBox;
	private int bottleCount = 0;
	
	public DrinksBoxMixed() {
		mixedBox = new ArrayList<Bottle<T>>();
	}
	
	public void addBottle(T drink){
		if(bottleCount <= 15) {
			mixedBox.add(new Bottle<T>());
		}
	}
}

//d)
class DrinksBoxD<T extends Bottle<Drink>>
{
	private List<Bottle<Beer>> beerBox;
	private List<Bottle<Wine>> wineBox;
	private int beerBottleCount = 0;
	private int wineBottleCount = 0;

	public DrinksBoxD(Bottle<Drink> bottle)
	{
		if (bottle instanceof Bottle<Beer>)
		{
			beerBox = new ArrayList<Bottle<Beer>>();
		} else
		{
			wineBox = new ArrayList<Bottle<Wine>>();
		}
	}

	public void addBottle(Bottle<Drink> bottle)
	{
		if (bottle instanceof Bottle<Beer> && beerBottleCount <= 24)
		{
			beerBox.add(new Bottle<Beer>());
			beerBottleCount++;
		} else if (bottle instanceof Bottle<Wine> && wineBottleCount <= 12)
		{
			wineBox.add(new Bottle<Wine>());
			wineBottleCount++;
		}
	}
}
