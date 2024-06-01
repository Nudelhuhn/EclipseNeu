package uebung8;

public class uebung8_3
{
	public static void main(String[] args) {
		Küchengeräte toaster = new Toaster();
		toaster.gerät_ein_ausschalten(true);
		toaster.gerät_ein_ausschalten(false);
		
		System.out.println();
		
		Küchengeräte Küchenmaschine = new Küchenmaschine();
		Küchenmaschine.gerät_ein_ausschalten(true);
	}
}

interface Küchengeräte
{
	void gerät_ein_ausschalten(boolean ein_aus);
}

abstract class Signalleuchte implements Küchengeräte
{
	void leuchte_einschalten() {
		System.out.println("Leuchte eingeschaltet");
	}
	void leuchte_ausschalten() {
		System.out.println("Leuchte ausgeschaltet");
	}
}

abstract class BeheizbareGeräte extends Signalleuchte
{
	void heizelement_einschalten() {
		System.out.println("Heizelement eingeschaltet");
	}
	void heizelement_ausschalten() {
		System.out.println("Heizelement ausgeschaltet");
	}
	
	public void gerät_ein_ausschalten(boolean einschalten)
	{
		if(einschalten == true) {
			leuchte_einschalten();
			heizelement_einschalten();
		}else {
			leuchte_ausschalten();
			heizelement_ausschalten();
		}
	}
}

abstract class MotorbetriebeneGeräte extends Signalleuchte
{
	void motor_einschalten() {
		System.out.println("Motor eingeschaltet");
	}
	void motor_ausschalten() {
		System.out.println("Motor ausgeschaltet");
	}
	
	public void gerät_ein_ausschalten(boolean einschalten)
	{
		if(einschalten == true) {
			leuchte_einschalten();
			motor_einschalten();
		}else {
			leuchte_ausschalten();
			motor_ausschalten();
		}
	}
}


class Toaster extends BeheizbareGeräte{
}
class Ofen extends BeheizbareGeräte{	
}
class Kaffeemaschine extends BeheizbareGeräte{	
}

class Handrührgerät extends MotorbetriebeneGeräte{	
}
class Küchenmaschine extends MotorbetriebeneGeräte{	
}