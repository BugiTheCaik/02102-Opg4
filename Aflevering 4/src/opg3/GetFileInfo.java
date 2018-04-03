package opg3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetFileInfo {
	private int CountX = 0;
	private int CountY = 0;
	private String FileName;
	boolean [][] GameArray;
	
	public boolean [][] GetGameArray() {
		return this.GameArray;	
	}

	
	// Læser spillefil og indsætter det i spille array.
	public void Game() {
		
	this.GameArray = new boolean[this.CountX][this.CountY];
	int X = 0;
	int Y = 0;
	
	try  {
		
		Scanner input = new Scanner(new File(this.FileName));
		
		// Kør indtil der ikke er flere linjer.
		while (input.hasNextLine()) {
			X = 0;
			
			// Læs linje.
			String linje = input.nextLine();
			
			// Læs tokens (tonehoved) - Læs linje.
			Scanner linjeScan = new Scanner(linje);
			
			while (linjeScan.hasNext()) {
				
				// Læs token.
				int token = linjeScan.nextInt();
				
				if (token == 1) {
					// Levende celle.
					this.GameArray[X][Y] = true;
				}
				else {
					// Død celle.
					this.GameArray[X][Y] = false;	
				}
				
				X ++;
			}
			
			Y ++;
			
		}
	}
    catch (FileNotFoundException e)    {
    	
    }

	

		
	}
	
	public boolean LoadGame(String filename) {
		// Sætter filnavn.
		this.FileName = filename;
		
		
		// Hvis der kan hentes informationer om filen.
		if (GetFileInfo()) {
			// Læs spillefil og lav spil.
			Game();
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	//Henter informationer fra spilfilen om størrelsen af det kommende spil.
	private boolean GetFileInfo() {
		
	try  {
			
			File f = new File(this.FileName);
			boolean readX = true;
		
			
			// Hvis filen eksisterer.
			if (f.exists() == true) {
				
				Scanner input = new Scanner(new File(this.FileName));
				
				// Kør indtil der ikke er flere linjer.
				while (input.hasNextLine()) {
					
					// Læs linje.
					String linje = input.nextLine();
					
					
					// Læs tokens (tonehoved) - Læs linje.
					Scanner linjeScan = new Scanner(linje);
					
					while (linjeScan.hasNext() & readX) {
						
						// Læs token.
						int token = linjeScan.nextInt();
						
						// Tæl.
						this.CountX ++;
						
					}
					readX = false;
					
					// Tæl antal linjer.
					this.CountY ++;
					
				}
				
				return true;
				
			}
			// Hvis ikke fil eksiterer.
			else {
				return false;
			}
		
	}
	
    catch (FileNotFoundException e)    {
    	return false;
    }
		
	}
	
}

