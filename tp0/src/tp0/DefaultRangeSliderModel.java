package tp0;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.event.EventListenerList;

public class DefaultRangeSliderModel implements RangeSliderModel {

	
	int _minS;
	int _maxS;
	int _minInterval;
	int _maxInterval;
	int _step;
	
	private final EventListenerList listeners = new EventListenerList();
	

	
	public DefaultRangeSliderModel(int minS, int maxS, int minInitInterval,
			int maxInitInterval,int step) {
		_minS = minS;
		_maxS = maxS;
		_minInterval = minInitInterval;
		_maxInterval = maxInitInterval ;
		_step = step;
	}

	private boolean check()
	{
		// check minS<=minInt<=maxInter <= maxS
		return (_minS <= _minInterval) && ( _minInterval <= _maxInterval ) && (_maxInterval <= _maxS ) ;
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
		setCheckMinSlide(val);
		//fire();
	}
	
	private void setCheckMinSlide(int val)
	{
		_minS = val ;
		if(!check())
		{
			if(!(_minS <= _maxS))
			{
				_minS = _maxS;
			}

			if(!(_minS <= _minInterval))
			{
				setCheckMinInterval(_minS);
			}
		}
	}
	
	
	@Override
	public void setMaxSlide(int val) {
		setCheckMaxSlide(val);
		fireRangeSliderChanged();
	}
	
	private void setCheckMaxSlide(int val)
	{
		_maxS = val;
		if(!check())
		{
			
			if(!(_minS <= _maxS))
			{
				_maxS = _minS;
			}
			
			if(!(_maxInterval <= _maxS ))
			{
				setCheckMaxInterval(_maxS);
			}
		}
	}

	@Override
	public void setMinInterval(int val) {
		setCheckMinInterval(val);
		fireRangeSliderChanged();
	}

	private void setCheckMinInterval(int val)
	{
		int temp = _minInterval;
		_minInterval = val;
		if(!( _minInterval <= _maxInterval ))
		{
			_minInterval = _maxInterval;
		} else if (!(_minS <= _minInterval))
		{
			_minInterval = _minS;
		}
		else
		{
			System.err.println("setCheckMinInterval error");
		}
		fireMinBoundChanged(temp, _minInterval);
	}
	
	@Override
	public void setMaxInterval(int val) {
		setCheckMaxInterval(val);
		fireRangeSliderChanged();
	}

	private void setCheckMaxInterval(int val)
	{
		int temp = _maxInterval;
		_maxInterval = val;
		if(!( _minInterval <= _maxInterval ))
		{
			_maxInterval = _minInterval;
		}
		else if (!(_maxInterval <= _maxS ))
		{
			_maxInterval = _maxS;
		}else
		{
			System.err.println("setCheckMaxInterval error");
		}
		fireMaxBoundChanged(temp, _maxInterval);
	}
	
	@Override
	public void setStep(int val) {
		_step = val;
		fireRangeSliderChanged();
	}

	
	
	
	private void fireRangeSliderChanged()
	{
		for(RangeSliderListener listener : getRangeSliderListener())
		{
			listener.rangeSliderChanged(this);
		}
	}
	
	private void fireMinBoundChanged(int oldValue,int newValue)
	{
		for(RangeSliderListener listener : getRangeSliderListener())
		{
			listener.minBoundChanged(oldValue, newValue);
		}
	}
	
	private void fireMaxBoundChanged(int oldValue,int newValue)
	{
		for(RangeSliderListener listener : getRangeSliderListener())
		{
			listener.maxBoundChanged(oldValue, newValue);
		}
	}

	
	public RangeSliderListener[] getRangeSliderListener(){
		return listeners.getListeners(RangeSliderListener.class);
	}
	
	@Override
	public void addRangeSliderListener(RangeSliderListener listener) {
		listeners.add(RangeSliderListener.class,listener);
	}

	@Override
	public void removeRangeSliderListener(RangeSliderListener listener) {
		listeners.remove(RangeSliderListener.class,listener);
	}
	
	
}
