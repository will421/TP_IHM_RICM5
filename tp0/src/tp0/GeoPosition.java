package tp0;

public class GeoPosition {
	
	double X;
	double Y;
	
	
	public GeoPosition(double x, double y) {
		super();
		X = x;
		Y = y;
	}
	
	
	public double getX() {
		return X;
	}


	public void setX(double x) {
		X = x;
	}


	public double getY() {
		return Y;
	}


	public void setY(double y) {
		Y = y;
	}


	public String toString(){
		
		String value;
		value = "[X : " + X + " Y : " + Y +"]" ;
	
		return value;
		
	}
	
	
}
