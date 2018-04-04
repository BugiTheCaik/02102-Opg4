package opg1;

public class Forlag {
	String navn;
	String sted;

	public Forlag(String navn, String sted) {
		this.navn = navn;
		this.sted = sted;
	}
	
	public String toString() {
		return "Navn: " + this.navn + "\nSted: " + this.sted;
	}
	
}
