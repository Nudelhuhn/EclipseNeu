package uebung12;

public class Kunde_refactored
{
	private String vorname;
	private String nachname;
	
	
	public Kunde_refactored(String vorname, String nachname)
	{
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}

	public String getName() {
		return vorname + " " + nachname;
	}
}


class Privatkunde extends Kunde_refactored{
	
	public Privatkunde(String vorname, String nachname)
	{
		super(vorname, nachname);
	}
	public String getName() {
		return super.getName();
	}
}

class Firmenkunde extends Kunde_refactored{
	private String firmenname;
	private String steuernummer;
	private String finanzamt;

	public Firmenkunde(String vorname, String nachname, String firmenname, String steuernummer, String finanzamt)
	{
		super(vorname, nachname);
		this.firmenname = firmenname;
		this.steuernummer = steuernummer;
		this.finanzamt = finanzamt;
	}
	
	public void setSteuernummer(String steuernummer)
	{
		this.steuernummer = steuernummer;
	}

	public void setFinanzamt(String finanzamt)
	{
		this.finanzamt = finanzamt;
	}
	@Override
	public String getName() {
		return firmenname;
	}
}