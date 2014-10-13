package tp0;

public class DefaultRangeSliderModel implements RangeSliderModel {

	
	int _minS;
	int _maxS;
	int _minInterval;
	int _maxInterval;
	int _step;
	
	
	
	public DefaultRangeSliderModel(int minS, int maxS, int minInitInterval,
			int maxInitInterval,int step) {
		_minS = minS;
		_maxS = maxS;
		_minInterval = minInitInterval;
		_maxInterval = maxInitInterval ;
		_step = step;
	}

	@Override
	public int getMinSlide() {
		return _minS;
	}

	@Override
	public int getMaxSlide() {
		return _maxS;
	}

	@Override
	public int getMinInterval() {
		return _minInterval;
	}

	@Override
	public int getMaxInterval() {
		return _maxInterval;
	}

	@Override
	public int getStep() {
		return _step;
	}

	@Override
	public void setMinSlide(int val) {
		_minS = val;
	}

	@Override
	public void setMaxSlide(int val) {
		_maxS = val;
	}

	@Override
	public void setMinInterval(int val) {
		_minInterval = val;
	}

	@Override
	public void setMaxInterval(int val) {
		_maxInterval = val;
	}

	@Override
	public void setStep(int val) {
		_step = val;
	}

}
