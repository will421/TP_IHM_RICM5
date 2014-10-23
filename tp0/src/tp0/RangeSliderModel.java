package tp0;

public interface RangeSliderModel {


	// check minS<=minInt<=maxInter <= maxS
	
	public int get_ID();
	
	public int getMinSlide();

	public int getMaxSlide();
	
	public int getMinInterval();
	
	public int getMaxInterval();
	
	public int getStep();
	
	public void setMinSlide(int val);

	public void setMaxSlide(int val);
	
	public void setMinInterval(int val);
	
	public void setMaxInterval(int val);
	
	public void setStep(int val);
	
	public void setInterval(int min, int max) throws Exception;
	
	public void addRangeSliderListener(RangeSliderListener listener);
	
	public void removeRangeSliderListener(RangeSliderListener listener);

}
