package opg3;

public class CellData {
	public boolean Live = true;
	// NextMove: 0=Normal, 1=Ensom, 2=Pladsmangel, 3=Genoplivning.
	public byte NextMove = 0;
	
	public CellData() {


	}
	
	
	public void KillCell(){
		this.Live = false;
		this.NextMove = 0;
	}
	
	public void ResuscitateCell(){
		this.NextMove = 0;
		this.Live = true;
	}
	
	public void SetAlone(){
		this.NextMove = 1;
	}
	
	public void SetSpace(){
		this.NextMove = 2;
	}
	
	public void SetResus(){
		this.NextMove = 3;
	}
	
	public byte GetNextMove(){
		return this.NextMove;
	}
	
	public boolean IsAlone(){
		 if (NextMove == 1) {
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	public boolean IsSpace(){
		 if (NextMove == 2) {
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	public boolean IsResus(){
		 if (NextMove == 3) {
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	public boolean IsAlive(){
		 if (Live == true) {
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	

}
