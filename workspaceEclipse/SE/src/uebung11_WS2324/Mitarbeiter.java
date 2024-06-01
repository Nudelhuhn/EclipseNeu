package uebung11_WS2324;

class Angestellter
{
	String name;
	double gehalt;
	int dienstjahre;
	
	public Angestellter(String name, double gehalt, int dienstjahre)
	{
		this.name = name;
		this.gehalt = gehalt;
		this.dienstjahre = dienstjahre;
	}
}

interface Bonus
{
	double berechneBonus(Angestellter angestellter);
}

class Nullbonus implements Bonus
{
	public double berechneBonus(Angestellter angestellter)
	{
		return angestellter.gehalt;
	}
}

class Fuehrungskraftbonus implements Bonus
{
	public double berechneBonus(Angestellter angestellter) {
		return angestellter.gehalt * (1 + angestellter.dienstjahre / 20.0);
	}
}



class Mitarbeiter
{
	private Angestellter angestellter;
	private Bonus bonus = new Nullbonus();

	public Mitarbeiter(Angestellter angestellter)
	{
		this.angestellter = angestellter;
	}

	public double gehaltGesamt()
	{
		return bonus.berechneBonus(angestellter);
	}
	
	public static void main(String[] args) {
		Mitarbeiter m = new Mitarbeiter(new Angestellter("Hubert", 10, 2));
		Fuehrungskraft f = new Fuehrungskraft(new Angestellter("Amadeus", 10, 20));
		
		System.out.println(m.gehaltGesamt());
		System.out.println(f.gehaltGesamt());
	}
}

class Fuehrungskraft
{
	private Angestellter angestellter;
	private Bonus bonus = new Fuehrungskraftbonus();

	public Fuehrungskraft(Angestellter angestellter)
	{
		this.angestellter = angestellter;
	}

	public double gehaltGesamt()
	{
		return bonus.berechneBonus(angestellter);
	}
}