package opg1;

public class ArtikelTest {

	public static void main(String[] args) {
		Forlag UniversityPress = new Forlag("University Press", "Denmark");
		Tidskrift Journal = new Tidskrift("Journal of Logic");
		Journal.setForlag(UniversityPress);
		Tidskrift Brain = new Tidskrift("Brain");
		Brain.setForlag(UniversityPress);
		Artikel artikelA = new Artikel(new String[] {"A. Abe", "A. Turing"}, "A", Journal);
		Artikel artikelB = new Artikel(new String[] {"B. Bim"}, "B", Journal);
		artikelA.setReferenceliste(new Artikel[] {artikelB});
		
		System.out.println(artikelA.toString()); // toString

	}

}
