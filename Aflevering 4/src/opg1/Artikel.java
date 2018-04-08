package opg1;

public class Artikel {
	
	String[] forfattere;
	String titel;
	Tidskrift tidskrift;
	Artikel[] referenceliste;
	
	// Constructor to generate Artikel
	public Artikel(String[] forfattere, String titel, Tidskrift tidskrift) {
		this.forfattere = forfattere;
		this.titel = titel;
		this.tidskrift = tidskrift;
	}
	
	// Function to set a reference list.
	public void setReferenceliste(Artikel[] referenceliste) {
		this.referenceliste = referenceliste;
	}
	
	// Generate string containing all information of Artikel object.
	public String toString() {
		String str1 = "";
		if (this.referenceliste != null) { // Avoid nullpointer exception (Is there a better way?)
			for (int x = 0; x < this.referenceliste.length; x++) {
				str1 += this.referenceliste[x].titel;
			}
		}
		else {
			str1 = "null";
		}
		return "Forfattere: " + String.join(", ", this.forfattere) + "\nTitel: " + this.titel + "\n" + this.tidskrift.toString() + "\nReferencer: " + str1;
	}
}
