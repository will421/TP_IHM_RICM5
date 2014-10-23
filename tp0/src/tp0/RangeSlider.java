package tp0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.text.LayeredHighlighter;



public class RangeSlider extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener,RangeSliderListener{


	protected RangeSliderModel rangeSliderModel;
	
	private int handleHeight = 20;
	private int handleWidth = 20;
	private int trackHeight = 12;
	private int slideHeight = 20;
	private int hPadding = 20;
	private int vPadding = 20;
	private String _title = "";
	private Color trackColor = Color.RED;
	private Color intervalColor = Color.BLUE;
	private Color intervalHighlight = Color.cyan;
	private boolean intervalHighlighted = false;
	private Point intervalGrabbedPoint;
	
	private Color boundColorMin = Color.GREEN;
	private Color boundColorMax = Color.GREEN;
	private Color boundHighlight = Color.cyan;
	private boolean boundMinHighlighted = false;
	private boolean boundMaxHighlighted = false;
	
	private Point oldPoint;
	
	public Color getBoundColorMin() {
		return boundColorMin;
	}


	public void setBoundColorMin(Color boundColorMin) {
		this.boundColorMin = boundColorMin;
	}


	public Color getBoundColorMax() {
		return boundColorMax;
	}


	public void setBoundColorMax(Color boundColorMax) {
		this.boundColorMax = boundColorMax;
	}

	
	public RangeSliderModel getModel()
	{
		return rangeSliderModel;
	}

	enum AUTOMATON_STATE {
		MOUSE_LAZY,
		MOUSE_OVER,
		MOUSE_GRABBED,
	}
	
	
	AUTOMATON_STATE stateMinBound = AUTOMATON_STATE.MOUSE_LAZY;
	AUTOMATON_STATE stateMaxBound = AUTOMATON_STATE.MOUSE_LAZY;
	AUTOMATON_STATE stateInterval = AUTOMATON_STATE.MOUSE_LAZY;
	AUTOMATON_STATE stateTrack = AUTOMATON_STATE.MOUSE_LAZY;
	
	
	private Rectangle trackRect;
	private Rectangle intervalRect;
	private Rectangle minBoundRect;
	private Rectangle maxBoundRect;
	
	public RangeSlider(int minS,int maxS,int minInitInterval,int maxInitInterval,int step,String title) {
		
		rangeSliderModel = new DefaultRangeSliderModel(minS,maxS,minInitInterval,maxInitInterval,step);
		trackRect = new Rectangle();
		intervalRect = new Rectangle();
		minBoundRect = new Rectangle();
		maxBoundRect = new Rectangle();
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		_title=title;
		rangeSliderModel.addRangeSliderListener(this);

	}
	
	
	private int toValue(int val)
	{
		int xA = rangeSliderModel.getMinSlide();
		int yA = hPadding;
		int xB = rangeSliderModel.getMaxSlide();
		int yB = this.getWidth() - 1*hPadding;
		float a = (float)(yB-yA)/(xB-xA);
		float b = yA-a*xA;
		int res = Math.round((float)(val-b)/a); 
		return res;
	}
	
	private int toPx(int val)
	{
		int xA = rangeSliderModel.getMinSlide();
		int yA = hPadding;
		int xB = rangeSliderModel.getMaxSlide();
		int yB = this.getWidth() - 1*hPadding;
		float a = (float)(yB-yA)/(xB-xA);
		float b = yA-a*xA;
		int res = Math.round(a*val+b);
		
		return res;
	}
	
	private void updateRectangles()
	{
		
		RangeSliderModel model = rangeSliderModel;
		
		//track
		trackRect.setBounds(toPx(rangeSliderModel.getMinSlide()),vPadding,toPx(model.getMaxSlide())-hPadding, trackHeight);
		
		intervalRect.setBounds(toPx(model.getMinInterval()),vPadding+(trackHeight-slideHeight)/2, toPx(model.getMaxInterval())-toPx(model.getMinInterval()), slideHeight);

		//minBound
		minBoundRect.setBounds(toPx(model.getMinInterval())-handleWidth/2,vPadding+(trackHeight-handleHeight)/2+5,handleWidth,handleHeight);
		
		//maxBound
		maxBoundRect.setBounds(toPx(model.getMaxInterval())-handleWidth/2,vPadding+(trackHeight-handleHeight)/2-5,handleWidth,handleHeight);
	}
	
	//dessin sliderUI
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g);
		int maxPx = this.getWidth();
		updateRectangles();
		
		RangeSliderModel model = rangeSliderModel;
		
		g.drawRect(0, 0, getWidth(), getHeight());
		//g.translate(padding, padding);
		
		//track
		g.setColor(trackColor);
		g2.fill(trackRect);
		
		//interval
		if(!intervalHighlighted)
			g.setColor(intervalColor);
		else
			g.setColor(intervalHighlight);
		g2.fill(intervalRect);
		
		//minBound
		g.setColor(boundMinHighlighted?boundHighlight:boundColorMin);
		//g.fillRect(bWidth,(trackHeight-handleHeight)/2,handleWidth,handleHeight);
		g2.fill(minBoundRect);
		g.setColor(Color.black);
		g2.drawString(_title, maxPx/8, 20);
		
		
		g2.drawString(Integer.toString(model.getMinInterval()), maxPx/4, 60);
		
		g2.drawString(Integer.toString(model.getMaxInterval()), 3*maxPx/4, 60);
		//-> Create an exception
		
		//maxBound
		g.setColor(boundMaxHighlighted?boundHighlight:boundColorMax);
		g2.fill(maxBoundRect);
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(stateMinBound == AUTOMATON_STATE.MOUSE_GRABBED)
		{
			rangeSliderModel.setMinInterval(toValue(e.getX()));	
		}
		else if(stateMaxBound == AUTOMATON_STATE.MOUSE_GRABBED)
		{
			rangeSliderModel.setMaxInterval(toValue(e.getX()));
		} else if(stateInterval == AUTOMATON_STATE.MOUSE_GRABBED/* && intervalRect.contains(new Point(e.getPoint().x,intervalRect.y))*/)
		{
			int intSize = toPx(rangeSliderModel.getMaxInterval())-toPx(rangeSliderModel.getMinInterval());
			int intervalSize = rangeSliderModel.getMaxInterval()-rangeSliderModel.getMinInterval();
			int newMin = e.getPoint().x-Math.round((float)intSize/2);
			int newMax = e.getPoint().x+Math.round((float)intSize/2);
			if(toValue(newMin)!=rangeSliderModel.getMinInterval())
				newMax = toPx(toValue(newMin)+intervalSize);
			else if (toValue(newMax)!=rangeSliderModel.getMaxInterval())
				newMin = toPx(toValue(newMax)-intervalSize);
			try {
				rangeSliderModel.setInterval(toValue(newMin),toValue(newMax));
			} catch (Exception e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
		}
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(stateMinBound == AUTOMATON_STATE.MOUSE_GRABBED)
		{
			stateMinBound = AUTOMATON_STATE.MOUSE_OVER;
		}
		
		if(stateMaxBound == AUTOMATON_STATE.MOUSE_GRABBED)
		{
			stateMaxBound = AUTOMATON_STATE.MOUSE_OVER;
		}
		
		if(stateInterval == AUTOMATON_STATE.MOUSE_GRABBED)
		{
			stateInterval = AUTOMATON_STATE.MOUSE_OVER;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
			
		if(minBoundRect.contains(e.getPoint()))
		{
			if(stateMinBound== AUTOMATON_STATE.MOUSE_LAZY)
			{ //mouseEntered
				stateMinBound = AUTOMATON_STATE.MOUSE_OVER;
				stateMaxBound = AUTOMATON_STATE.MOUSE_LAZY;
				stateInterval = AUTOMATON_STATE.MOUSE_LAZY;
				stateTrack = AUTOMATON_STATE.MOUSE_LAZY;
				boundMinHighlighted = true;
				boundMaxHighlighted = false;
				intervalHighlighted = false;
				repaint();
			}else if(stateMinBound == AUTOMATON_STATE.MOUSE_OVER) {} 
			else
			{
				System.err.println("MouseMoved && minBoundRect error");
			}
		} else if(maxBoundRect.contains(e.getPoint()))
		{
			if(stateMaxBound== AUTOMATON_STATE.MOUSE_LAZY)
			{//mouseEntered
				stateMinBound = AUTOMATON_STATE.MOUSE_LAZY;
				stateMaxBound = AUTOMATON_STATE.MOUSE_OVER;
				stateInterval = AUTOMATON_STATE.MOUSE_LAZY;
				stateTrack = AUTOMATON_STATE.MOUSE_LAZY;
				boundMinHighlighted = false;
				boundMaxHighlighted = true;
				intervalHighlighted = false;
				repaint();
			}else if(stateMaxBound == AUTOMATON_STATE.MOUSE_OVER) {} 
			else
			{
				System.err.println("MouseMoved && maxBoundRect error");
			}
		} else if(intervalRect.contains(e.getPoint()))
		{
			if(stateInterval== AUTOMATON_STATE.MOUSE_LAZY)
			{	//mouseEntered
				stateMinBound = AUTOMATON_STATE.MOUSE_LAZY;
				stateMaxBound = AUTOMATON_STATE.MOUSE_LAZY;
				stateInterval = AUTOMATON_STATE.MOUSE_OVER;
				stateTrack = AUTOMATON_STATE.MOUSE_LAZY;
				boundMinHighlighted = false;
				boundMaxHighlighted = false;
				intervalHighlighted = true;
				repaint();
			} else if(stateInterval == AUTOMATON_STATE.MOUSE_OVER) {}
			else
			{
				System.err.println("MouseMoved && intervalRect error");
			}
		}
		
		else if(trackRect.contains(e.getPoint()))
		{
			if(stateTrack==AUTOMATON_STATE.MOUSE_LAZY)
			{
				stateMinBound = AUTOMATON_STATE.MOUSE_LAZY;
				stateMaxBound = AUTOMATON_STATE.MOUSE_LAZY;
				stateInterval = AUTOMATON_STATE.MOUSE_LAZY;
				stateTrack = AUTOMATON_STATE.MOUSE_OVER;
				boundMinHighlighted = false;
				boundMaxHighlighted = false;
				intervalHighlighted = false;
				repaint();
			} else if(stateTrack == AUTOMATON_STATE.MOUSE_OVER) {}
			else
			{
				System.err.println("MouseMoved && trackRect error");
			}
		}
		else
		{
			stateMinBound= AUTOMATON_STATE.MOUSE_LAZY;
			stateMaxBound= AUTOMATON_STATE.MOUSE_LAZY;
			stateInterval= AUTOMATON_STATE.MOUSE_LAZY;
			stateTrack = AUTOMATON_STATE.MOUSE_LAZY;
			boundMinHighlighted = false;
			boundMaxHighlighted = false;
			intervalHighlighted = false;
			repaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(stateMinBound == AUTOMATON_STATE.MOUSE_OVER)
		{
			stateMinBound = AUTOMATON_STATE.MOUSE_GRABBED;
		} 
		
		if(stateMaxBound == AUTOMATON_STATE.MOUSE_OVER)
		{
			stateMaxBound = AUTOMATON_STATE.MOUSE_GRABBED;
		}
		
		if(stateInterval == AUTOMATON_STATE.MOUSE_OVER)
		{
			stateInterval = AUTOMATON_STATE.MOUSE_GRABBED;
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		int rotation = event.getWheelRotation();
		int value;
		if(stateTrack == AUTOMATON_STATE.MOUSE_OVER)
		{
			if(event.getPoint().x<minBoundRect.x) //On bouge la borne inferieur
			{
				value = rangeSliderModel.getMinInterval();
				value -= rotation*rangeSliderModel.getStep();
				rangeSliderModel.setMinInterval(value);
			} 
			else if(event.getPoint().x>minBoundRect.x+minBoundRect.width) //On bouge la borne superieur
			{
				value = rangeSliderModel.getMaxInterval();
				value -= rotation*rangeSliderModel.getStep();
				rangeSliderModel.setMaxInterval(value);
			}
		}
		else if(stateMinBound == AUTOMATON_STATE.MOUSE_OVER)
		{
			value = rangeSliderModel.getMinInterval();
			value -= rotation*rangeSliderModel.getStep();
			rangeSliderModel.setMinInterval(value);
		}
		else if (stateMaxBound == AUTOMATON_STATE.MOUSE_OVER)
		{
			value = rangeSliderModel.getMaxInterval();
			value -= rotation*rangeSliderModel.getStep();
			rangeSliderModel.setMaxInterval(value);
		}
	}



	@Override
	public void rangeSliderChanged(RangeSliderModel model) {
		updateRectangles();
		repaint();
	}


	@Override
	public void minBoundChanged(int oldValue, int newValue) {
	}
	@Override
	public void maxBoundChanged(int oldValue, int newValue) {
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}


	
	
	
	
}
