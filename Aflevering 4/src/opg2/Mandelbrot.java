package opg2;

public class Mandelbrot {

	private static final int MAX = 255;
	private static final int Grid = 512;
	private static final int Slength = 2 ;
	private static final double x0 = -0.5;
	private static final double y0 = 0;
	
	public static void main(String[] args) {
		StdDraw.setCanvasSize(1000,1000);
		StdDraw.setXscale(-Slength+x0,Slength+x0);
		StdDraw.setYscale(-Slength+y0,Slength+y0);
		StdDraw.setPenRadius(0.1/Slength);
		System.out.println(iterate(new Complex(0.5,0.5)));
		double step = ((double)(Slength)/(Grid-1.0));;
		for(double j = 0; j <= Slength; j += step) {
			double x = x0 - Slength/2 + j;
			for(double k = 0; k <= Slength; k += step) {
				double y = y0 - Slength/2 + k;
				System.out.println(iterate(new Complex(x,y)));
				if (iterate(new Complex(x,y))==MAX) {
					//System.out.println(x+" "+y);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledCircle(x, y, step/2);
					//System.out.println(iterate(new Complex(x,y)));
				}
				//StdDraw.setPenColor(StdDraw.BLACK);
				//StdDraw.point(x,y);
				//System.out.println(x + "," + y);
				//StdDraw.text(x,y+0.1,"("+x+","+y+")");
			}
		}
	}
	
	
	
	public static int iterate(Complex z0) {
		Complex z = new Complex(z0);
		for (int i = 0; i < MAX; i++) {
			if (z.abs() > 2.0) {
				return i;
				}
			z = z.times(z).plus(z0);
			}
		return MAX;
		}
}
