package opg1;

public class Forlag {
	String navn; // Name
	String sted; // Place
	
	// Constructor for Forlag
	public Forlag(String navn, String sted) {
		this.navn = navn;
		this.sted = sted;
	}
	
	// Generate String data for object forlag
	public String toString() {
		return "Forlag: " + this.navn + "\nSted: " + this.sted;
	}
	
}
