package opg2;

import java.awt.Color;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class Mandelbrot {

	private static final int MAX = 255;
	private static final int Grid = 1024;
	private static double Slength = 2 ;
	private static double x0 = -0.5;
	private static double y0 = 0;
	private static HashMap<Integer, Color> hashMap= new HashMap<Integer, Color>();
	private static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		StdDraw.setCanvasSize(1000,1000);
		StdDraw.show(0);
		for (int i= 0; i < 255; i++) {
			hashMap.put(i, RandColor());
		}
		DrawMandelbrot();
		for (int i = 0; i < 100;) {
			if (StdDraw.mousePressed()) {
				StdDraw.clear();
				x0 = StdDraw.mouseX();
				y0 = StdDraw.mouseY();
				Slength -= Slength/2;
				DrawMandelbrot();
				i++;
			}
		}
	}
	
//	public static void menu() {
//		if (console.hasNextInt()) {
//			Grid = Math.abs(console.nextInt());
//		}
//		else {
//			System.out.print("Grid can only be integer values.");
//			return;
//		}
//	}
	
	
	
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
	
	public static Color RandColor() {
		//to get rainbow, pastel colors
		Random random = new Random();
		final float hue = random.nextFloat();
		final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
		final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
		Color color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}
	
	public static void DrawMandelbrot() {
		StdDraw.setXscale(-Slength/2+x0,Slength/2+x0);
		StdDraw.setYscale(-Slength/2+y0,Slength/2+y0);
		double step = ((double)(Slength)/(Grid-1.0));;
		for(double j = 0; j <= Slength; j += step) {
			double x = x0 - Slength/2 + j;
			for(double k = 0; k <= Slength; k += step) {
				double y = y0 - Slength/2 + k;
				int iter = iterate(new Complex(x,y));
				if (iter==MAX) {
					//System.out.println(x+" "+y);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledCircle(x, y, step/2);
				}
				else {
					StdDraw.setPenColor(hashMap.get(iter));
					StdDraw.filledCircle(x, y, step/2);
				}
			}
		}
		StdDraw.show(0);
	}
}
