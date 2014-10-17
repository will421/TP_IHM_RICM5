package tp0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
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
	private int trackHeight = 10;
	private int slideHeight = 20;
	private int hPadding = 20;
	private int vPadding = 20;
	private String _title = "";
	private Color trackColor = Color.RED;
	private Color intervalColor = Color.BLUE;
	private Color boundColorMin = Color.GREEN;
	private Color boundColorMax = Color.GREEN;
	
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


	enum AUTOMATON_STATE {
		MOUSE_LAZY,
		MOUSE_OVER,
		MOUSE_GRABBED_MIN,
		MOUSE_GRABBED_MAX,
		MOUSE_MOVED,
		MOUSE_RELEASED, MOUSE_OVER_MIN, MOUSE_OVER_MAX
	}
	
	
	AUTOMATON_STATE currentState = AUTOMATON_STATE.MOUSE_LAZY;
	
	
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

	}
	
	private int toPx(int val)
	{
		//int xA = hPadding;
		int xA = rangeSliderModel.getMinSlide();
		int yA = 0;
		int xB = rangeSliderModel.getMaxSlide();
		//int yB = this.getWidth()-2*hPadding;
		int yB = this.getWidth();
		float a = (float)(yB-yA)/(xB-xA);
		float b = yA-a*xA;
		int res = Math.round(a*val+b);
		if(val==rangeSliderModel.getMaxSlide())
			b++;
		return res;
	}
	
	private void updateRectangles()
	{
		
		RangeSliderModel model = rangeSliderModel;
		
		//track
		trackRect.setBounds(toPx(0),vPadding,toPx(this.getWidth()), trackHeight);
		
		intervalRect.setBounds(toPx(model.getMinInterval()),vPadding+(trackHeight-slideHeight)/2, toPx(model.getMaxInterval()-model.getMinInterval()), slideHeight);
		intervalRect.setBounds(0,0,0,0);
		//minBound
		minBoundRect.setBounds(toPx(model.getMinInterval())-handleWidth,vPadding+(trackHeight-handleHeight)/2,handleWidth,handleHeight);
		minBoundRect.setBounds(0,0,0,0);
		
		//maxBound
		maxBoundRect.setBounds(toPx(model.getMaxInterval()),vPadding+(trackHeight-handleHeight)/2,handleWidth,handleHeight);
		maxBoundRect.setBounds(0,0,0,0);
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
		g.setColor(intervalColor);
		g2.fill(intervalRect);
		
		//minBound
		g.setColor(boundColorMin);
		//g.fillRect(bWidth,(trackHeight-handleHeight)/2,handleWidth,handleHeight);
		g2.fill(minBoundRect);
		g.setColor(Color.black);
		g2.drawString(_title, maxPx/8, 20);
		
		
		g2.drawString(Integer.toString(model.getMinInterval()), maxPx/4, 60);
		
		g2.drawString(Integer.toString(model.getMaxInterval()), 3*maxPx/4, 60);
		//-> Create an exception
		
		//maxBound
		g.setColor(boundColorMax);
		g2.fill(maxBoundRect);
		
		
		
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {

		if ( currentState == AUTOMATON_STATE.MOUSE_OVER_MIN){
			
			if (minBoundRect.contains(e.getPoint())){
				
				System.out.println("Grab Min Bound");
				currentState = AUTOMATON_STATE.MOUSE_GRABBED_MIN;
				
				
				}
			
			}
		
		if (currentState == AUTOMATON_STATE.MOUSE_GRABBED_MIN){
			
			int n_position;
			int maxPx = this.getWidth();
			float rapport = (float) rangeSliderModel.getMaxSlide() / maxPx;
			
			
			n_position = (int) (e.getX() * rapport) ;
			System.out.println(n_position);
			rangeSliderModel.setMinInterval(n_position );
			
		}
		
		if ( currentState == AUTOMATON_STATE.MOUSE_OVER_MAX){
			
			if (maxBoundRect.contains(e.getPoint())){
				
				System.out.println("Grab Max Bound");
				currentState = AUTOMATON_STATE.MOUSE_GRABBED_MAX;
				
				
				}
			
			}
		
		if (currentState == AUTOMATON_STATE.MOUSE_GRABBED_MAX){
			
			int n_position;
			int maxPx = this.getWidth();
			float rapport = (float) rangeSliderModel.getMaxSlide() / maxPx;
			
			
			n_position = (int) (e.getX() * rapport) ;
			System.out.println(n_position);
			rangeSliderModel.setMaxInterval(n_position );
			
		}
		
		System.out.println(currentState);
		
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (currentState == AUTOMATON_STATE.MOUSE_GRABBED_MIN || currentState == AUTOMATON_STATE.MOUSE_GRABBED_MAX  ){
			currentState = AUTOMATON_STATE.MOUSE_LAZY;
			
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
		if( currentState == AUTOMATON_STATE.MOUSE_LAZY ){
			
			if (minBoundRect.contains(e.getPoint())){
				
				currentState = AUTOMATON_STATE.MOUSE_OVER_MIN;
				setBoundColorMin(Color.CYAN);
				repaint();
			}

		}
		
		if( currentState == AUTOMATON_STATE.MOUSE_LAZY ){
			
			if (maxBoundRect.contains(e.getPoint())){
				
				currentState = AUTOMATON_STATE.MOUSE_OVER_MAX;
				setBoundColorMax(Color.CYAN);
				repaint();
			}

		}
		
		
		else if( currentState == AUTOMATON_STATE.MOUSE_OVER_MIN | currentState == AUTOMATON_STATE.MOUSE_OVER_MAX ){
			
			
			if ( ! minBoundRect.contains(e.getPoint()) & ! minBoundRect.contains(e.getPoint())){
			
				
			 setBoundColorMax(Color.GREEN);
			 setBoundColorMin(Color.GREEN);
			 repaint();	
		     currentState = AUTOMATON_STATE.MOUSE_LAZY;	
			
			}
		
		}	
		
		
	}



	@Override
	public void mousePressed(MouseEvent e) {


			


	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
		

		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void rangeSliderChanged(RangeSliderModel model) {
		updateRectangles();
		repaint();
	}


	@Override
	public void minBoundChanged(int oldValue, int newValue) {}


	@Override
	public void maxBoundChanged(int oldValue, int newValue) {}


	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
}
