package opg1;

public class Tidskrift {
	
	String titel; // Title
	Forlag forlag; // Publisher
	String issn; // ISSN Number (Default = NULL)
	
	// Construct Tidskrift with title.
	public Tidskrift(String titel) {
		this.titel = titel;
	}
	
	// Function to set ISSN number.
	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	// Function to set publisher
	public void setForlag(Forlag forlag) {
		this.forlag = forlag;
	}
	
	// Generate String containing all data.
	public String toString() {
		return "Tidskrift: " + this.titel + "\n" + this.forlag.toString() + "\nISSN: " + this.issn;
	}
	
}
