package opg1;

public class Artikel {
	
	String[] forfattere;
	String titel;
	Tidskrift tidskrift;
	Artikel[] referenceliste;
	
	public Artikel(String[] forfattere, String titel, Tidskrift tidskrift) {
		this.forfattere = forfattere;
		this.titel = titel;
		this.tidskrift = tidskrift;
	}
	
	public void setReferenceliste(Artikel[] referenceliste) {
		this.referenceliste = referenceliste;
	}
	
	
	public String toString() {
		String str1 = "";
		for (int x = 0; x < this.referenceliste.length; x++) {
			str1 += this.referenceliste[x].titel;
		}
		return "Forfattere: " + String.join(", ", this.forfattere) + "\nTitel: " + this.titel + "\n" + this.tidskrift.toString() + "\nReferencer: " + str1;
	}
}
