package tp0;

public interface RangeSliderModel {

	/*	-listeners
	 * 	-min/max slide
	 * 	-min/max interval
	 * 	
	 * minS<=minInt<=MaxInt<=MaxS
	 * 
	 * 
	 */
	
	// check minS<=minInt<=maxInter <= maxS
	
	public int getMinSlide();

	public int getMaxSlide();
	
	public int getMinInterval();
	
	public int getMaxInterval();
	
	public int getStep();
}
