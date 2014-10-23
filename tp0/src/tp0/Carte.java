package tp0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Style;
import javax.swing.JPanel;

import util.math.randomClass;


public class Carte extends JPanel implements RangeSliderListener {


	
	private int minPrice = 0;
	private int maxPrice = 0;
	private int minPiece = 0;
	private int maxPiece = 0;



	public ArrayList<Home> HomeList = new ArrayList<Home>();

	public Carte(){

		fillHomeList();
		setMinPrice(Options.MIN_PRICE_GEN);
		setMaxPrice(Options.MAX_PRICE_GEN);
		setMinPiece(Options.MIN_PIECE_GEN);
		setMaxPiece(Options.MAX_PIECE_GEN);
		
	}

	public void fillHomeList(){

		//generation of Random houses with their values
		for (int i=1;i<= Options.NB_MAISONS ;i++) {

			GeoPosition pos = new GeoPosition(randomClass.randomDouble(),randomClass.randomDouble());
			int price = randomClass.randomInt(Options.MIN_PRICE_GEN,Options.MAX_PRICE_GEN);
			int nb_pieces = randomClass.randomInt(Options.MIN_PIECE_GEN, Options.MAX_PIECE_GEN);
			Home maison = new Home(i, nb_pieces, pos, price);
			HomeList.add(maison);
			maison.Display();
		}
	}


	public void paintComponent(Graphics g){

		try {
			Image img = ImageIO.read(new File("background.jpg"));
			g.drawImage(img, 0, 0, this);

		} catch (IOException e) {
			e.printStackTrace();
		}                

		drawHouseInBounds(g,getMinPrice(),getMaxPrice(),getMinPiece(),getMaxPiece());

	}


	public void drawHouseInBounds(Graphics g,int min_value_price, int max_value_price, int min_value_piece, int max_value_piece){

		Iterator<Home> itr  = HomeList.iterator();

		while(itr.hasNext()){

			Home maison = (Home) itr.next();
			double posX = maison.getPos().getX();
			double posY = maison.getPos().getY();

			int X = (int) Math.round(posX);
			int Y = (int) Math.round(posY);



			if( maison.getValue()<=max_value_price && maison.getValue() >=  min_value_price && maison.getNb_pieces() >= min_value_piece && maison.getNb_pieces() <= max_value_piece) {

				g.setColor(Color.BLACK);
				g.fillOval(X,Y,20,20);
				g.setColor(Color.WHITE);
				g.fillOval(X+1,Y+1,18,18);
				g.setColor(Color.BLACK);

				g.drawString(Integer.toString(maison.getID()),X+5,Y+15);

			}

		}

	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrince) {
		this.maxPrice = maxPrince;
	}
	
	
	public int getMinPiece() {
		return minPiece;
	}

	public void setMinPiece(int minPiece) {
		this.minPiece = minPiece;
	}

	public int getMaxPiece() {
		return maxPiece;
	}

	public void setMaxPiece(int maxPiece) {
		this.maxPiece = maxPiece;
	}

	@Override
	public void rangeSliderChanged(RangeSliderModel model) {
		
		if (model.get_ID() == 1){
			minPrice =model.getMinInterval();
			maxPrice = model.getMaxInterval();
		}
		else if(model.get_ID() ==2){
			minPiece =model.getMinInterval();
			maxPiece = model.getMaxInterval();
		}

		repaint();
	}

	@Override
	public void minBoundChanged(int oldValue, int newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maxBoundChanged(int oldValue, int newValue) {
		// TODO Auto-generated method stub
		
	}


}

