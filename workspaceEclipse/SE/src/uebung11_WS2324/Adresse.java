package uebung11_WS2324;

public class Adresse
{
	private String vorname;
	private String nachname;
	private String firmenname;
	private String postfach;
	private String straﬂe;
	private Integer hausnummer;
	private String postleitzahl;
	private String ort;

	public static class Builder{
		private Adresse adresse = new Adresse();
		public Adresse build() throws Exception {
			if(adresse.straﬂe == null && adresse.hausnummer != null ||
			   adresse.straﬂe != null && adresse.hausnummer == null) {
				throw new Exception("Wenn Straﬂe, dann auch Hausnr. und umgekehrt");
			}
			if(adresse.postfach != null && adresse.firmenname == null) {
				throw new Exception("Wenn Postfach, dann auch Firmenname");
			}
			if(adresse.postleitzahl instanceof String &&
			   adresse.postleitzahl.length() == 5 &&
			   adresse.postleitzahl.matches("\\d+")) {
				//
			}
			else {
				throw new Exception("Postleitzahl ung¸ltig");
			}
			return adresse;
		}
		
//		public void setVorname(String vorname)
//		{
//			adresse.vorname = vorname;
//		}
//		public void setNachname(String nachname)
//		{
//			adresse.nachname = nachname;
//		}
//		public void setFirmenname(String firmenname)
//		{
//			adresse.firmenname = firmenname;
//		}
//		public void setPostfach(String postfach)
//		{
//			adresse.postfach = postfach;
//		}
//		public void setStraﬂe(String straﬂe)
//		{
//			adresse.straﬂe = straﬂe;
//		}
//		public void setHausnummer(int hausnummer)
//		{
//			adresse.hausnummer = hausnummer;
//		}
//		public void setPostleitzahl(String postleitzahl)
//		{
//			adresse.postleitzahl = postleitzahl;
//		}
//		public void setOrt(String ort)
//		{
//			adresse.ort = ort;
//		}
		
		public Builder setVorname(String vorname)
		{
			adresse.vorname = vorname;
			return this;
		}
		public Builder setNachname(String nachname)
		{
			adresse.nachname = nachname;
			return this;
		}
		public Builder setFirmenname(String firmenname)
		{
			adresse.firmenname = firmenname;
			return this;
		}
		public Builder setPostfach(String postfach)
		{
			adresse.postfach = postfach;
			return this;
		}
		public Builder setStraﬂe(String straﬂe)
		{
			adresse.straﬂe = straﬂe;
			return this;
		}
		public Builder setHausnummer(int hausnummer)
		{
			adresse.hausnummer = hausnummer;
			return this;
		}
		public Builder setPostleitzahl(String postleitzahl)
		{
			adresse.postleitzahl = postleitzahl;
			return this;
		}
		public Builder setOrt(String ort)
		{
			adresse.ort = ort;
			return this;
		}
	}
	
	public static void main(String[] args) {
//		Adresse.Builder builder = builder();
//		builder.setVorname("Hans");
//		builder.setNachname("Flocke");
//		builder.setFirmenname("Stiebel Eltron");
//		builder.setPostfach("123");
//		builder.setStraﬂe("Kronprinzenstraﬂe");
//		builder.setHausnummer(5);
//		builder.setPostleitzahl("54295");
//		builder.setOrt("Trier");
//		
//		try
//		{
//			Adresse adresse = builder.build();
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
		
		Adresse.Builder builder = builder()
				.setVorname("Hans")
				.setNachname("Flocke")
				.setFirmenname("Stiebel Eltron")
				.setPostfach("123")
				.setStraﬂe("Kronprinzenstraﬂe")
				.setHausnummer(5)
				.setPostleitzahl("54295")
				.setOrt("Trier");
		
		try
		{
			Adresse adresse = builder.build();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Adresse() {
		
	}
	
	public static Builder builder() {
		return new Adresse.Builder();
	}
}
