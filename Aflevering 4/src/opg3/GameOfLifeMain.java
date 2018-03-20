package opg3;
import java.util.Scanner;
public class GameOfLifeMain {
	private static Scanner console = new Scanner(System.in);
	
	public static void main(String[] arg) {
		boolean Running = true;
		GameOfLife GameData = new GameOfLife(4);
		
		while (Running) {
			System.out.println("Indtast");
			String CheckNumber = console.nextLine();
	
		GameData.nextState();
		}
		
	
	}
	
	

}
