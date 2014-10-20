package tp0;

public class Home {

	private int ID;	
	private int nb_pieces;
	private GeoPosition pos;
	private int value;

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public int getNb_pieces() {
		return nb_pieces;
	}
	
	public GeoPosition getPos() {
		return pos;
	}

	public void setPos(GeoPosition pos) {
		this.pos = pos;
	}

	public void setNb_pieces(int nb_pieces) {
		this.nb_pieces = nb_pieces;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Home(int ID, int nb_pieces,GeoPosition pos, int value) {
		super();
		this.ID = ID;
		this.pos = pos;
		this.nb_pieces = nb_pieces;
		this.value = value;
	}
	
	
   public void Display(){
	   System.out.println("[Maison : " + this.ID +"] : Nbpieces : " + this.nb_pieces + ",Position " + this.pos.toString() + ", Prix : " + this.value);
   }
   
   
}
