package tp0;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
    
	public Fenetre(){
    	
		super();
	    
	    setTitle("Cartes des biens immobiliers");
	    setSize(Options.HAUTEUR_FENETRE,Options.LARGEUR_FENETRE);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	}
}