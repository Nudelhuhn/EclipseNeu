package uebung11;

interface Bonus {
	public double getGehaltGesamt(double gehalt, int dienstjahre);
}

class NullBonus implements Bonus {
	public double getGehaltGesamt(double gehalt, int dienstjahre) {
		return gehalt;
	}
}

class Fuehrungskraft extends Mitarbeiter {
	public Fuehrungskraft(String name, double gehalt, int dienstjahre) {
		super(name, gehalt, dienstjahre);
	}
	
	public Bonus getBonus() {
		return new FuehrungskraftBonus();
	}
}

class FuehrungskraftBonus implements Bonus {
	public double getGehaltGesamt(double gehalt, int dienstjahre) {
		return gehalt * (1 + dienstjahre / 20);
	}
}

class Mitarbeiter {

	private String name;
	private double gehalt;
	private int dienstjahre;
	private Bonus bonus = getBonus();

	public Mitarbeiter(String name, double gehalt, int dienstjahre) {
		this.name = name;
		this.gehalt = gehalt;
		this.dienstjahre = dienstjahre;
	}

	public Bonus getBonus() {
		return new NullBonus();
	}
	
	public double gesamtGehalt() {
		return bonus.getGehaltGesamt(gehalt, dienstjahre);
	}
}