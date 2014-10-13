package tp0;

import java.util.EventListener;

public interface RangeSliderListener extends EventListener {
	void rangeSliderChanged(RangeSliderModel model);
	void minBoundChanged(int oldValue, int newValue);
	void maxBoundChanged(int oldValue, int newValue);
}
