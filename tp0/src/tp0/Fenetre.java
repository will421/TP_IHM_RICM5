package tp0;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
    
	public Fenetre(){
    	
		super();
	    
	    setTitle("Cartes des biens immobiliers");
	    setSize(Options.DIM_CARTE,Options.DIM_CARTE+75);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    
	    
	    JPanel contentPane = new JPanel(new BorderLayout());
	    this.setContentPane(contentPane);
	    contentPane.add(new Carte(),BorderLayout.CENTER);
	   
	    
	    RangeSlider slider_prix = new RangeSlider(0,Options.MAX_PRICE_GEN, 0, Options.MAX_PRICE_GEN, 1,"Selection des prix : ");
	  
	    
	    slider_prix.setPreferredSize(new Dimension(250, 75));

	    

	    contentPane.add(slider_prix,BorderLayout.SOUTH);
	    
	    
	    setVisible(true);
	    
	    setResizable(false);
	}
}