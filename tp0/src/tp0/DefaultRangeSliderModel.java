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
	public int setMinSlide() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMaxSlide() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMinInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMaxInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setStep() {
		// TODO Auto-generated method stub
		return 0;
	}

}
