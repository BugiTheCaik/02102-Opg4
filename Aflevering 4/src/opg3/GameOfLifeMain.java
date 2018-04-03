package opg3;
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class GameOfLifeMain {
	private static Scanner console = new Scanner(System.in);

	public static void main(String[] arg) throws FileNotFoundException {

		boolean Running = true;
		boolean Auto = false;
		int GameNo = 0;

		GameOfLife GameData;

		
		// Hvis et fastsat spil vælges.
		GameData = new GameOfLife(3);
		
		
		/**
		// Hvis spillefil skal indlæses.
		GetFileInfo g = new GetFileInfo();
		
		if (g.LoadGame("pulsar.gol")) {
		GameData = new GameOfLife(g.GetGameArray());
		}
		else {
			GameData = new GameOfLife(0);
		}
		**/
		
		// Hvis spil er klar.
		if (GameData.GameReady()) {
			
			System.out.println("Antal levende celler: " + GameData.GetNoCells());
			System.out.print("For automatisk spil, indtast \'a\': ");
			String Input = console.nextLine();
			
			// Hvis input er 'a'.
			if (Input.equals("a")) {
				Auto = true;
			}
			
			while (Running) {
				// Hvis autospil ikke er valgt.
				if (!Auto) {
					System.out.print("Tryk enter for næste spil (nummer: " + GameNo + ")");
					Input = console.nextLine();
					
					
				}
				
				GameData.nextState();
				System.out.println("Spilnummer " + GameNo + ". Antal levende celler: " + GameData.GetNoCells());
				
				// Hvis der ikke er flere levende celler.
				if (GameData.GetNoCells() == 0) {
					Running = false;
					System.out.println("Spillet er stoppet. Der ikke er flere levende celler.");
				}
				
				GameNo ++;
			}
			
		}
		// Hvis spil ikke er klar.
		else {
			System.out.println("Der er sket en fejl. Spillet er ikke klar.");
		}
		
	
	}
	
	

}
