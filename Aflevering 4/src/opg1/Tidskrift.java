package opg1;

public class Tidskrift {
	
	String titel;
	Forlag forlag;
	String issn;
	
	public Tidskrift(String titel) {
		this.titel = titel;
	}
	
	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	public void setForlag(Forlag forlag) {
		this.forlag = forlag;
	}
	
	public String toString() {
		return "Tidskrift: " + this.titel + "\n" + this.forlag.toString() + "\nISSN: " + this.issn;
	}
	
}
