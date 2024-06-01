package uebungVorlesung;

public class PlanetSelection {
	private String[] planets = {"Merkur", "Venus", "Erde", "Mars", "Jupiter"};
	
	public String getPlanet() {
		return planets[(int) (Math.random() * planets.length)];
	}
}
