package util.math;

import java.util.Random;

import tp0.Options;

public class randomClass {
	
  static public final double min_double = 0;
  static public final double max_double = Options.DIM_CARTE;
	
	
	static public double randomDouble(){
		
		Random r = new Random();
    	double randomValue = min_double + (max_double-min_double) * r.nextDouble();
		
    	return randomValue;
		
	}
	 
	static public int randomInt(int min, int max){
		
		Random rand = new Random();
		
		int randomNum = rand.nextInt((max-min)+1)+ min;
		
		return randomNum;
		
	}
	
}
