package opg2;

public class Complex {

	private final double re; // Real del
	private final double im; // Imaginær del
	
	public Complex() { // Konstruktør til Complex objekt
		re= 0;
		im = 0;
	}
	
	public Complex(double real, double imag) { // Konstruktør til Complex objekt med realdel og imaginær del
		re = real;
		im = imag;
	}
	
	public Complex(Complex z) { // Konstruktør til Complex med tallet Z.
		re = z.getRe();
		im = z.getIm();
	}
	
	public double getRe() { // Returner realdel af Complex objekt
		return re;
	}
	
	public double getIm() { // Returner imaginærdel af Complex objekt
		return im;
	}
	
	public double abs() { // Returner modulus af Complex-objekt
		return Math.hypot(re,im); // Could be done better?
	}
	
	public Complex plus(Complex other) { // Returner summen af to complex objekter.
		double real = this.re + other.re;
		double imag = this.im + other.im;
		return new Complex(real, imag);
	}
	
	public Complex times(Complex other) { // Returner produktet af to Complex objekter
		double real = this.re * other.re - this.im * other.im;
		double imag = this.re * other.im + this.im * other.re;
		return new Complex(real, imag);
	}

	public String toString() {
		if (this.re==0) {
			return this.im + "i";
		}
		if (this.im==0) {
			return this.re + "";
		}
		if (this.im < 0) {
			return this.re + " - " + this.im + "i";
		}
		else {
			return this.re + " + " + this.im + "i";
		}
	}

}

