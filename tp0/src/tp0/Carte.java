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


public class Carte extends JPanel  {
	
	 static public final int NB_MAISONS = 10;
	 
	 ArrayList<Home> HomeList = new ArrayList<Home>();
	 
	 
	 
	 public Carte(){
		 
		 fillHomeList();
		 
		 
		 
	 }
	 
	 public void fillHomeList(){
		 
		    //generation of Random houses with their values
		    for (int i=1;i<= NB_MAISONS;i++) {
		
		    	GeoPosition pos = new GeoPosition(randomClass.randomDouble(),randomClass.randomDouble());
		    	int price = randomClass.randomInt();
		    	Home maison = new Home(i, 5, pos, price);
		    	HomeList.add(maison);
		    }
	 }
	
	 
	public void paintComponent(Graphics g){
	    
		 try {
		      Image img = ImageIO.read(new File("background.jpg"));
		      g.drawImage(img, 0, 0, this);

		    } catch (IOException e) {
		      e.printStackTrace();
		    }                
		 
		fillHomeList();
		Iterator<Home> itr  = HomeList.iterator();
	    
	    // displaying with iterator
	    while(itr.hasNext()){
	    	 
	    	Home maison = (Home) itr.next();
	    	maison.Display();
	    	
	    	double posX = maison.getPos().getX();
	    	double posY = maison.getPos().getY();
	    	
	    	int X = (int) Math.round(posX);
	    	int Y = (int) Math.round(posY);
	    	
	    	if( maison.getValue()<=Options.MAX_PRICE_SELEC && maison.getValue() >= Options.MIN_PRICE_SELEC) {
	    		
	    		g.setColor(Color.BLACK);
		    	g.fillOval(X,Y,20,20);
		    	g.setColor(Color.WHITE);
		    	g.fillOval(X+1,Y+1,18,18);
		    	g.setColor(Color.BLACK);
		   
		    	g.drawString(Integer.toString(maison.getID()),X+5,Y+15);
	    		
	    	}
	    	

	    }
	
	   
	    
		
	}
}
