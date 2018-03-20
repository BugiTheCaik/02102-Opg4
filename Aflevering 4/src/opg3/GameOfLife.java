package opg3;


import java.util.*;


import opg2.StdDraw;

public class GameOfLife {
	// Spille array.
	private boolean [][][] GameArray;
	private boolean [][][] nextGameArray;
	// Array Const;
	private static final int aLive = 0;
	private static final int aLone = 1;
	private static final int aSpace = 2;
	private static final int aGen = 3;
	private int gamesize;

	
	public GameOfLife(int n) {
		// Spille størrelse.
		gamesize = n;
		
		// Initialisere spil.
		iniGame();

	}
	
	public GameOfLife(boolean[][] initialState) {

	}
	
	public void nextState() {
			
		// Kør spilleregler.
		nextGameArray = GameRule();
		
		// Tegn spil.
		DrawGame();
		GameArray = nextGameArray;
	}
	
	// Køre spilleregler
	private boolean [][][] GameRule() {
		int liveNeighboursCell = 0;
		
		// Kør y-rækken. 
		for (int y=0; y<= GameArray[1].length - 1; y++) {
			
			// Kør x-rækken.
			for (int x=0; x<= GameArray[0].length - 1; x++) {
				// Antal levende naboceller.
				liveNeighboursCell = liveNeighbours(x,y);
				
				// Celle dør af ensomhed, hvis der er mindre end 2 levende naboceller.
				if (liveNeighboursCell < 2) {
					nextGameArray[x][y][aLive] = false;
					GameArray[x][y][aLone] = true;
					System.out.println(x + " " + y + " - " + "ensom");
				}
				
				// Celle dør af pladsmangel, hvis der er mere end 3 levende naboceller.
				else if (liveNeighboursCell > 3) {
					nextGameArray[x][y][aLive] = false;
					GameArray[x][y][aSpace] = true;
					System.out.println(x + " " + y + " - " + "plads");
				}
				
				// Celler genopliver, hvis den er død og der er mere end 3 levende naboceller.
				else if (liveNeighboursCell == 3 && !GameArray[x][y][aLive]) {
					nextGameArray[x][y][aLive] = true;
					GameArray[x][y][aGen] = true;
					System.out.println(x + " " + y + " - " + "gen");
				}
				else {
					System.out.println(x + " " + y + " - " + "ok");
				}
				
		
			}
			
	
		}
		// Erstatter det tidligere spil med det nye.
		return nextGameArray;
		
		
	}
	
	// Bestemmer levende nabo celler.
	private int liveNeighbours(int x, int y)  {

		int livecount = 0;
		
		// Kontrollér venstre nabo.
		if (x-1 >= 0) {
			// Hvis celle er levende.
			if (GameArray[x-1][y][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér højre nabo.
		if (x+1 <= GameArray[0].length - 1) {
			// Hvis celle er levende
			if (GameArray[x+1][y][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér oppe nabo.
		if (y-1 >= 0) {
			// Hvis celle er levende.
			if (GameArray[x][y-1][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér nede nabo.
		if (y+1 <= GameArray[1].length - 1) {
			// Hvis celle er levende.
			if (GameArray[x][y+1][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-venstre-oppe nabo.
		if (x-1 >= 0 && y-1 >=0) {
			// Hvis celle er levende.
			if (GameArray[x-1][y-1][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-højre-oppe nabo.
		if (x+1 <= GameArray[0].length - 1 && y-1 >=0) {
			// Hvis celle er levende.
			if (GameArray[x+1][y-1][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-højre-nede nabo.
		if (x+1 <= GameArray[0].length - 1 && y+1 <= GameArray[1].length - 1) {
			// Hvis celle er levende.
			if (GameArray[x+1][y+1][aLive]) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-venstre-nede nabo.
		if (x-1 >= 0 && y+1 <= GameArray[1].length - 1) {
			// Hvis celle er levende.
			if (GameArray[x-1][y+1][aLive]) {
				livecount ++;	
			}
		}
		
		
		return livecount;
		
		
	}
	
	// Initialisere spil.
	private void iniGame() {

		CreateGameArray();

		//StdDraw.setPenColor(StdDraw.WHITE); // Draw Race course edges and player.
		StdDraw.setCanvasSize(300,300);
		StdDraw.setScale(-1, 1);
		//StdDraw.setYscale(-1,1);
		//StdDraw.setXscale(-(GameArray[0].length * CircleSize + (GameArray[0].length-1) * CircleSize)/2, (GameArray[0].length * CircleSize + (GameArray[0].length-1) * CircleSize)/2);
		//StdDraw.setYscale(-(GameArray[1].length * CircleSize + (GameArray[1].length-1) * CircleSize)/2, (GameArray[1].length * CircleSize + (GameArray[1].length-1) * CircleSize)/2);
		//StdDraw.setCanvasSize(1000, CircleSize * 200);
		//StdDraw.clear();
		//GameArray[0][0] = true;
		//StdDraw.setXscale(-10,10);
		//StdDraw.setYscale(-10,10); 
		// Kør spilleregler.
		nextGameArray = GameRule();
		DrawGame();
		GameArray = nextGameArray;
	}
	
	// Tegner levende celler.
	private void DrawGame() {
		StdDraw.clear();
		double Step=1.0/gamesize;
		
		double xCoor = -1.0 + Step;
		double yCoor= 1 - Step;
		
		StdDraw.setPenColor(StdDraw.BLACK);
		
		// Kør y-rækken. 
		for (int y=0; y<= GameArray[1].length - 1; y++) {
			
			// Kør x-rækken.
			for (int x=0; x<= GameArray[0].length - 1; x++) {
				
				
				
				if (GameArray[x][y][aSpace]) {
					StdDraw.setPenColor(StdDraw.RED);
				}
				
				if (GameArray[x][y][aLone]) {
					StdDraw.setPenColor(StdDraw.BLUE);
				}
				
				if (GameArray[x][y][aGen]) {
					StdDraw.setPenColor(StdDraw.GREEN);
				}
				// Tegn celle, hvis den er levende.
				if (GameArray[x][y][aLive]) {
					StdDraw.filledCircle(xCoor, yCoor, Step);	
				}
				
				xCoor += 2*Step;
			}
			
			yCoor += -2*Step;
			xCoor = -1.0 + Step;
		}
		
		

	}
	
	// Sætter størrelse af spil array og alle celler til levende.
	private void CreateGameArray() {
		// Sætter størrelsen af GameArray.
		this.GameArray = new boolean[gamesize][gamesize][4];
		this.nextGameArray = new boolean[gamesize][gamesize][4];
		
		// Tilfældig konstruktør.
		Random r = new Random();
		
		// Kør y-rækken. 
		for (int y=0; y<= GameArray[1].length - 1; y++) {
			
			// Kør x-rækken.
			for (int x=0; x<= GameArray[0].length - 1; x++) {
				// Sætter tilfældige celler til levende og døde.
				this.GameArray[x][y][aLive] = r.nextBoolean();
			}
			
		}
		
	}
	
	

}
