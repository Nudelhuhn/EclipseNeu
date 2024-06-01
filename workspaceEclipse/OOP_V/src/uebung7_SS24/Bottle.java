package uebung7_SS24;

import java.util.ArrayList;
import java.util.List;

class Drink{}
class Beer extends Drink{}
class Wine extends Drink{}
class WhiteWine extends Wine{}
class RedWine extends Wine{}

// a)
public class Bottle<T extends Drink>
{
	
	private boolean isFilled;
	
	public Bottle() {
		emptyBottle();
	}
	
	public void fillBottle(T drink) {
		if(!isFilled) {			
			isFilled = true;
		}
		else {			
			System.out.println("Bottle already filled");
		}
	}
	
	public void emptyBottle() {
		isFilled = false;
	}
	
	
	public static void main(String[] args) {
		Drink drink = new Drink();
		Beer beer = new Beer();
		Wine wine = new Wine();
		
		Bottle<Beer> beerBottle = new Bottle<Beer>();
//		beerBottle.fillBottle(drink); // ergibt Fehler
//		beerBottle.fillBottle(wine); // ergibt Fehler
		beerBottle.fillBottle(beer);
		beerBottle.fillBottle(beer); // "Bottle already filled"
		beerBottle.emptyBottle();
		beerBottle.fillBottle(beer);
		
		Bottle<Wine> wineBottle = new Bottle<Wine>();
		wineBottle.fillBottle(wine);
		
		BottleCrate<Bottle<Beer>> beerBottleCrate = new BottleCrate<Bottle<Beer>>(20);
		beerBottleCrate.addBottle(beerBottle);
		beerBottleCrate.addBottle(beerBottle);
//		beerBottleCrate.addBottle(wine); // ergibt Fehler
		
		BottleCrateMixed bottleCrateMixed = new BottleCrateMixed(20);
		bottleCrateMixed.addBottle(wineBottle);
		bottleCrateMixed.addBottle(beerBottle);
	}
}

// b)
class BottleCrate<T extends Bottle<?>>
{
	
	private int crateCapacity = 20;
	private int bottleCount = 0;
	private List<T>	bottles = new ArrayList<T>();
	
	public BottleCrate(int capacity) {
		crateCapacity = capacity;
	}
	
	public void addBottle(T bottle) {
		if(bottleCount < crateCapacity) {			
			bottles.add(bottle);
			bottleCount++;
		}else {
			System.out.println("Crate is full");
		}
	}
	
	public void removeBottle() {
		if(bottleCount > 0) {			
			bottles.remove(bottleCount);
			bottleCount--;
		}else {
			System.out.println("Crate is already empty");
		}
	}
}

// c)
class BottleCrateMixed
{
	
	private int crateCapacity = 20;
	private int bottleCount = 0;
	private List<Bottle> bottles = new ArrayList<Bottle>();
	
	public BottleCrateMixed(int capacity) {
		crateCapacity = capacity;
	}
	
	public void addBottle(Bottle bottle) {
		if(bottleCount < crateCapacity) {			
			bottles.add(bottle);
			bottleCount++;
		}else {
			System.out.println("Crate is full");
		}
	}
	
	public void removeBottle() {
		if(bottleCount > 0) {			
			bottles.remove(bottleCount);
			bottleCount--;
		}else {
			System.out.println("Crate is already empty");
		}
	}
}

