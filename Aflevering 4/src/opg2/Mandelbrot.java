package opg2;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class Mandelbrot {

	private static final int MAX = 255; // Keep at 255. Quality of calculation & Color size (255 Colors)
	private static final int Grid = 1024; // Amount of points in sLength*sLength square. Smaller values = Faster calculation
	private static double Slength = 2 ; // Side length. 2 captures first mandelbrot.
	private static double x0 = -0.5; // Start position X Coordinate
	private static double y0 = 0; //  Start position Y Coordinate
	private static int[][] ColorData = new int[256][3]; // Color data stored as 255*3 Array. Could change to Color[255] Array.
	private static final String filename = "mnd/blues.mnd"; // Files have to be kept in working directory in Eclipse.
	
	private static final boolean FileColor = false; // Use file color, if false use random color.
	
	public static void main(String[] args) throws IOException{
		StdDraw.setCanvasSize(1000,1000); 
		StdDraw.show(0);
		GetColorData(); // Extract or generate color data.
		DrawMandelbrot(); // Draw our first mandelbrot
		for (int i = 0; i < 100;) {						// Allows user to zoom in 100 times.
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
	
	public static void GetColorData() throws IOException { // Function open file for color data, or generates it.
		if (FileColor) { // Open file for colors
			Scanner file = new Scanner(new File(filename));
			int i = 1;
			while(file.hasNext() && i < 255) {
				ColorData[i][0] = file.nextInt();
				ColorData[i][1] = file.nextInt();
				ColorData[i][2] = file.nextInt();
				i++;
			}
			file.close();
		}
		else {	// Generate color data from HSV Gradient. (Cleaner results then random RGB Values)
			Random random = new Random()	;
			float hue = random.nextFloat();	// Random starting point
			final float saturation = 0.25f; // Default (0.25f, 1.0f) values give nice pastel gradient.
			final float luminance = 1.0f;  // 1.0f
			for (int i= 0; i < 255; i++) {
				Color color = Color.getHSBColor(hue, saturation, luminance);
				ColorData[i][0] = color.getRed();
				ColorData[i][1] = color.getBlue();
				ColorData[i][2] = color.getGreen();
				hue += 0.005f;				// Step between each color. Smal
				
			}
		}
	}
	
	public static int iterate(Complex z0) { // Function supplied by assignment
		Complex z = new Complex(z0);
		for (int i = 0; i < MAX; i++) {
			if (z.abs() > 2.0) {
				return i;
				}
			z = z.times(z).plus(z0);
			}
		return MAX;
		}
	
	public static void DrawMandelbrot() { 					// Draw the mandelbrot image.
		StdDraw.setXscale(-Slength/2+x0,Slength/2+x0); 			
		StdDraw.setYscale(-Slength/2+y0,Slength/2+y0);			// We refresh X/Y Scale, in case sLength changes as we zoom.
		double step = ((double)(Slength)/(Grid-1.0));;		// Step size between grid points.
		for(double j = 0; j <= Slength; j += step) {
			double x = x0 - Slength/2 + j;
			for(double k = 0; k <= Slength; k += step) { 		// Iterate through every point in our square.
				double y = y0 - Slength/2 + k;
				int iter = iterate(new Complex(x,y));			// Find iterate value of point
				if (iter==MAX) {								// If point is == MAX, its part of the mandelbrot.
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledCircle(x, y, step/2);
				}
				else {											// Else we can color it depending on how close it is to MAX
					StdDraw.setPenColor(ColorData[iter][0], ColorData[iter][1], ColorData[iter][2]);
					StdDraw.filledCircle(x, y, step/2);
				}
			}
			StdDraw.show(0); // Consider this during long computations
		}
		//StdDraw.show(0);										// Show the new drawing (Neccesary to keep computing time low)
	}
}
