package uebung8;

public class uebung8_3
{
	public static void main(String[] args) {
		K�chenger�te toaster = new Toaster();
		toaster.ger�t_ein_ausschalten(true);
		toaster.ger�t_ein_ausschalten(false);
		
		System.out.println();
		
		K�chenger�te K�chenmaschine = new K�chenmaschine();
		K�chenmaschine.ger�t_ein_ausschalten(true);
	}
}

interface K�chenger�te
{
	void ger�t_ein_ausschalten(boolean ein_aus);
}

abstract class Signalleuchte implements K�chenger�te
{
	void leuchte_einschalten() {
		System.out.println("Leuchte eingeschaltet");
	}
	void leuchte_ausschalten() {
		System.out.println("Leuchte ausgeschaltet");
	}
}

abstract class BeheizbareGer�te extends Signalleuchte
{
	void heizelement_einschalten() {
		System.out.println("Heizelement eingeschaltet");
	}
	void heizelement_ausschalten() {
		System.out.println("Heizelement ausgeschaltet");
	}
	
	public void ger�t_ein_ausschalten(boolean einschalten)
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

abstract class MotorbetriebeneGer�te extends Signalleuchte
{
	void motor_einschalten() {
		System.out.println("Motor eingeschaltet");
	}
	void motor_ausschalten() {
		System.out.println("Motor ausgeschaltet");
	}
	
	public void ger�t_ein_ausschalten(boolean einschalten)
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


class Toaster extends BeheizbareGer�te{
}
class Ofen extends BeheizbareGer�te{	
}
class Kaffeemaschine extends BeheizbareGer�te{	
}

class Handr�hrger�t extends MotorbetriebeneGer�te{	
}
class K�chenmaschine extends MotorbetriebeneGer�te{	
}