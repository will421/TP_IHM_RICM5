package tp0;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class testRangeSlider {

	
	public static void main(String[] args) {
	    JFrame fenetre = new JFrame();
        
	    //D�finit un titre pour notre fen�tre
	    fenetre.setTitle("Ma premi�re fen�tre Java");
	    //D�finit sa taille : 400 pixels de large et 100 pixels de haut
	    fenetre.setSize(400, 400);
	    //Nous demandons maintenant � notre objet de se positionner au centre
	    fenetre.setLocationRelativeTo(null);
	    //Termine le processus lorsqu'on clique sur la croix rouge
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Et enfin, la rendre visible        
	    fenetre.setVisible(true);
	    
	    RangeSlider slider = new RangeSlider(0,Options.MAX_PRICE_GEN,0,Options.MAX_PRICE_GEN , 150, "Titre");
	    slider.setVisible(true);
	    //slider.setPreferredSize(new Dimension(300, 200));
	    fenetre.getContentPane().add(slider);
	    //fenetre.pack();

	}

}
