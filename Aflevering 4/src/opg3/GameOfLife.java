package opg3;



import java.awt.Color;
import java.util.*;
import java.io.*;

import opg2.StdDraw;

public class GameOfLife {
	// Spille array.
	private CellData [][] GameArray;


	private int gamesizeX;
	private int gamesizeY;
	private int CountLiveCells = 0;
	private int CountLiveCellsPre = 0;
	private boolean[][] initialState;
	private boolean CustominitialState = false;
	private boolean GameReady = false;
	
	public boolean GameReady() {
		return GameReady;
	}
	public GameOfLife(int n) {
		
		// Spille størrelse.
		gamesizeX = (n);
		gamesizeY = (n);
		
		// Initialisere spil.
		iniGame();
		GameReady = true;
	}

	

	
	public GameOfLife(boolean[][] initialStated) {
		// Spille størrelse.
		gamesizeX = initialStated.length;
		gamesizeY = initialStated[0].length;
		
		// Overfør celle status.
		this.initialState = initialStated;
		
		this.CustominitialState = true;
		
		// Initialisere spil.
		iniGame();
		
		GameReady = true;
	}
	
	public void nextState() {
		this.CountLiveCells = this.CountLiveCellsPre;
		
		// Kør spilleregler.
		GameRule();
		
		// Tegn spil.
		DrawGame();
	
	}
	
	public int GetNoCells() {
		return this.CountLiveCells;
	}
	
	// Køre spilleregler
	private  void GameRule() {
		int liveNeighboursCell = 0;
		
		// Kør y-rækken. 
		for (int y=0; y<= GameArray[1].length - 1; y++) {
			
			// Kør x-rækken.
			for (int x=0; x<= GameArray.length - 1; x++) {
				
				// Antal levende naboceller.
				liveNeighboursCell = liveNeighbours(x,y);
				
				// Celle dør af ensomhed, hvis der er mindre end 2 levende naboceller.
				if (liveNeighboursCell < 2 && GameArray[x][y].IsAlive()) {
					GameArray[x][y].SetAlone();
					//System.out.println(x + " " + y + " - " + "ensom");
				}
				
				// Celle dør af pladsmangel, hvis der er mere end 3 levende naboceller.
				else if (liveNeighboursCell > 3 && GameArray[x][y].IsAlive()) {
					GameArray[x][y].SetSpace();
					//System.out.println(x + " " + y + " - " + "plads");
				}
				
				// Celler genopliver, hvis den er død og der er mere end 3 levende naboceller.
				else if (liveNeighboursCell == 3 && !GameArray[x][y].IsAlive()) {
					GameArray[x][y].SetResus();
				//	System.out.println(x + " " + y + " - " + "gen");
				}
				else {
					//System.out.println(x + " " + y + " - " + "ok");
				}
				
		
			}
			
	
		}
		
		
	}
	
	// Bestemmer levende nabo celler.
	private int liveNeighbours(int x, int y)  {

		int livecount = 0;
		
		// Kontrollér venstre nabo.
		if (x-1 >= 0) {
			// Hvis celle er levende.
			if (GameArray[x-1][y].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér højre nabo.
		if (x+1 <= GameArray.length - 1) {
			// Hvis celle er levende
			if (GameArray[x+1][y].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér oppe nabo.
		if (y-1 >= 0) {
			// Hvis celle er levende.
			if (GameArray[x][y-1].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér nede nabo.
		if (y+1 <= GameArray[1].length - 1) {
			// Hvis celle er levende.
			if (GameArray[x][y+1].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-venstre-oppe nabo.
		if (x-1 >= 0 && y-1 >=0) {
			// Hvis celle er levende.
			if (GameArray[x-1][y-1].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-højre-oppe nabo.
		if (x+1 <= GameArray.length - 1 && y-1 >=0) {
			// Hvis celle er levende.
			if (GameArray[x+1][y-1].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-højre-nede nabo.
		if (x+1 <= GameArray.length - 1 && y+1 <= GameArray[1].length - 1) {
			// Hvis celle er levende.
			if (GameArray[x+1][y+1].IsAlive()) {
				livecount ++;	
			}
		}
		
		// Kontrollér skråt-venstre-nede nabo.
		if (x-1 >= 0 && y+1 <= GameArray[1].length - 1) {
			// Hvis celle er levende.
			if (GameArray[x-1][y+1].IsAlive()) {
				livecount ++;	
			}
		}
		
		
		return livecount;
		
		
	}
	
	// Initialisere spil.
	private void iniGame() {
		
		CreateGameArray();

		//StdDraw.setPenColor(StdDraw.WHITE); // Draw Race course edges and player.
		StdDraw.setCanvasSize(600,600);
		StdDraw.setScale(-1, 1);
		
 
		StdDraw.show(500);
		// Kør spilleregler.
		GameRule();
		DrawGame();
		//GameArray = nextGameArray;
	}
	

	// Returnere farven som cellen skal have.
	private Color GetCellColor(byte status) {
	

		if (status == 2) {
			return StdDraw.RED;
		}
		
		else if (status == 1) {
			return StdDraw.BLUE;
		}
		
		else if (status == 3) {
			return StdDraw.GREEN;
		}
		else {
			return StdDraw.BLACK;
		}

		
	}
	
	// Genopliv celle.
	private void ResuscitateCell(int x, int y){
		GameArray[x][y].ResuscitateCell();
		this.CountLiveCellsPre ++;
	}
	// Dræb celle.
	private void KillCell(int x, int y){
		GameArray[x][y].KillCell();
		this.CountLiveCellsPre --;
	}
	
	// Tegner levende celler.
	private void DrawGame() {
		StdDraw.clear();
		double StepX=1.0/gamesizeX;
		double StepY=1.0/gamesizeY;
		double CircleR = StepY;
		this.CountLiveCellsPre = this.CountLiveCells;
		
		// Cirkel radius.
		if (StepX < StepY) {
			CircleR = StepX;
			
		}
		
		double xCoor = -1.0 + StepX;
		double yCoor= 1 - StepY;
		boolean DrawNextStatus = false;
	
		
		// Kør y-rækken. 
		for (int y=0; y<= GameArray[1].length - 1; y++) {
			
			// Kør x-rækken.
			for (int x=0; x<= GameArray.length - 1; x++) {
				
				
				DrawNextStatus = false;
				
				// Henter farve ud fra celle status.
				StdDraw.setPenColor(GetCellColor(GameArray[x][y].GetNextMove()));
				
				if (GameArray[x][y].IsSpace() || GameArray[x][y].IsAlone()) {
					DrawNextStatus = true;
					KillCell(x,y);
				}
	
				
				if (GameArray[x][y].IsResus()) {
					DrawNextStatus = true;
					ResuscitateCell(x,y);
				}
				//StdDraw.filledCircle(xCoor, yCoor, CircleR);	
				// Tegn celle.
				if (GameArray[x][y].IsAlive() || DrawNextStatus == true) {
					StdDraw.filledCircle(xCoor, yCoor, CircleR);	
				}
				
				xCoor += 2*StepX;
			}
			
			yCoor += -2*StepY;
			
			xCoor = -1.0 + StepX;
		}
		
		StdDraw.show(500);
		
	}
	
	// Laver spil.
	private void CreateGameArray() {
		boolean Kill;
		
		// Sætter størrelsen af GameArray.
		this.GameArray = new CellData [gamesizeX][gamesizeY];
		// Antal levende celler.
		this.CountLiveCells = gamesizeX * gamesizeY;

		
		// Tilfældig konstruktør.
		Random r = new Random();
		
		// Kør y-rækken. 
		for (int y=0; y<= GameArray[0].length - 1; y++) {
			
			// Kør x-rækken.
			for (int x=0; x<= GameArray.length - 1; x++) {

				Kill = this.CustominitialState ? !this.initialState[x][y] : r.nextBoolean();
				this.GameArray[x][y] = new CellData();
				
				// Hvis celle skal dø.
				if (Kill) {
				// Sætter tilfældige celler til levende og døde.
					GameArray[x][y].KillCell();
					this.CountLiveCells --;
				}
			}
			
		}
		
	}
	
	

}
