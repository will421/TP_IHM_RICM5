package tp0;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
    
	public Fenetre(){
    	
		super();
	    
		Carte carte =  new Carte();
		
		
	    setTitle("Cartes des biens immobiliers");
	    setSize(Options.DIM_CARTE,Options.DIM_CARTE+75);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    
  
	    JPanel contentPane = new JPanel(new BorderLayout());
	    JPanel contentPaneSouth = new JPanel(new GridLayout(0,2));
	    
	    this.setContentPane(contentPane);
	    
	    
		contentPane.add(carte,BorderLayout.CENTER);
		contentPane.add(contentPaneSouth,BorderLayout.SOUTH);
	    
	    RangeSlider slider_prix = new RangeSlider(carte.getMinPrice(),carte.getMaxPrice(), carte.getMinPrice(), carte.getMaxPrice(),1,"Selection des prix : ",1);
	    slider_prix.setPreferredSize(new Dimension(125, 75));
	    RangeSlider slider_nb_pieces = new RangeSlider(carte.getMinPiece(),carte.getMaxPiece(),carte.getMinPiece(),carte.getMaxPiece(),1,"Nombre de pièces :",2);
	    slider_nb_pieces.setPreferredSize(new Dimension(125, 75));
	 
	    
	    slider_prix.getModel().addRangeSliderListener(carte);
	    slider_nb_pieces.getModel().addRangeSliderListener(carte);
	    
	    contentPaneSouth.add(slider_prix);
	    contentPaneSouth.add(slider_nb_pieces);
	   
	    setVisible(true);
	    
	    setResizable(false);
	}
}