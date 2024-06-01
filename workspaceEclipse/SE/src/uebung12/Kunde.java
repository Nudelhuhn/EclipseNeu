package uebung12;

public class Kunde
{
	private String vorname;
	private String nachname;
	private String firmenname;
	private String steuernummer;
	private String finanzamt;

	public static class Builder
	{
		private Kunde kunde = new Kunde();
		
		public Kunde build() throws Exception {
			if(kunde.steuernummer != null && kunde.finanzamt == null) {
				throw new Exception("Wenn Steuernummer, dann auch Finanzamt");
			}
			
			if(kunde.vorname != null && kunde.nachname != null) {
				if(kunde.firmenname != null) {
					throw new Exception("Entweder nur Vor- und Nachname oder nur Firmenname");
				}
			}else {
				if(kunde.vorname == null && kunde.vorname == null && kunde.firmenname == null) {
					throw new Exception("Weder Vor- und Nachname oder Firmenname gesetzt");
				}else if(kunde.vorname == null && kunde.vorname == null && kunde.firmenname != null) {
					//
				} else {
					throw new Exception("Entweder nur Vor- und Nachname oder nur Firmenname(2)");
				}
			}
			
			if(kunde.firmenname != null && (kunde.steuernummer == null || kunde.finanzamt == null)) {
				throw new Exception("Wenn ein Firmenname gesetzt ist, müssen Steuernummer und "
						+ "Finanzamt gesetzt sein");
			}
			if(kunde.vorname != null && kunde.nachname != null &&
			   (kunde.steuernummer != null || kunde.finanzamt != null)) {
				throw new Exception("wenn ein Vor- und Nachname gesetzt ist,"
						+ "dürfen Steuernummer und Finanzamt nicht gesetzt sein");
			}
			
			return kunde;
		}
		
		public void setVorname(String vorname)
		{
			kunde.vorname = vorname;
		}

		public void setNachname(String nachname)
		{
			kunde.nachname = nachname;
		}

		public void setFirmenname(String firmenname)
		{
			kunde.firmenname = firmenname;
		}

		public void setSteuernummer(String steuernummer)
		{
			kunde.steuernummer = steuernummer;
		}

		public void setFinanzamt(String finanzamt)
		{
			kunde.finanzamt = finanzamt;
		}
	}
	
	public static Builder builder() {
		return new Kunde.Builder();
	}
	
	
	public static void main(String[] args) {
		Kunde.Builder builder = builder();
		builder.setVorname("Franz");
		builder.setNachname("Müller");
		builder.setFirmenname(null);
		builder.setSteuernummer(null);
		builder.setFinanzamt(null);
		
		try {
			Kunde kunde = builder.build();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
