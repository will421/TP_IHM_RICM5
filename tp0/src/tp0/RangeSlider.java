package tp0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.text.LayeredHighlighter;



public class RangeSlider extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener {

	protected RangeSliderModel rangeSliderModel;
	
	private int handleHeight = 15;
	private int handleWidth = 5;
	private int trackHeight = 10;
	private int slideHeight = 20;
	private int padding = 10;
	private Color trackColor = Color.RED;
	private Color intervalColor = Color.BLUE;
	private Color boundColor = Color.GREEN;
	
	
	private Rectangle trackRect;
	private Rectangle intervalRect;
	private Rectangle minBoundRect;
	private Rectangle maxBoundRect;
	
	public RangeSlider(int minS,int maxS,int minInitInterval,int maxInitInterval,int step) {
		
		rangeSliderModel = new DefaultRangeSliderModel(minS,maxS,minInitInterval,maxInitInterval,step);
		trackRect = new Rectangle();
		intervalRect = new Rectangle();
		minBoundRect = new Rectangle();
		maxBoundRect = new Rectangle();
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	
	
	private void updateRectangles()
	{
		RangeSliderModel model = rangeSliderModel;
		
		//int minPx = 0;
		int maxPx = this.getWidth()-2*padding;
		//int minTrack = model.getMinSlide();
		int maxTrack = model.getMaxSlide();
		float rapport = (float)maxPx/maxTrack;
		
		//track
		int slideLength = maxPx;
		trackRect.setBounds(0, 0,slideLength, trackHeight);
		
		//interval
		int xpos =  (int) (model.getMinInterval()*rapport);
		int sWidth = (int) ((model.getMaxInterval()-model.getMinInterval())*rapport);
		intervalRect.setBounds(xpos, (trackHeight-slideHeight)/2, sWidth, slideHeight);
		
		//minBound
		int bWidth = (int) (model.getMinInterval()*rapport);
		minBoundRect.setBounds(bWidth,(trackHeight-handleHeight)/2,handleWidth,handleHeight);
		
		//maxBound
		bWidth = (int) ((model.getMaxInterval())*rapport -handleWidth);
		maxBoundRect.setBounds(bWidth,(trackHeight-handleHeight)/2,handleWidth,handleHeight);
	}
	
	//dessin sliderUI
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g);
		
		updateRectangles();
		
		RangeSliderModel model = rangeSliderModel;
		
		/*int minPx = 0;
		int maxPx = this.getWidth()-2*padding;
		int minTrack = model.getMinSlide();
		int maxTrack = model.getMaxSlide();
		float rapport = (float)maxPx/maxTrack;*/
		
		//minPx = 
		//border
		g.drawRect(0, 0, getWidth(), getHeight());
		g.translate(padding, padding);
		
		//track
		g.setColor(trackColor);
		g2.fill(trackRect);
		
		//interval
		g.setColor(intervalColor);
		g2.fill(intervalRect);
		
		//minBound
		g.setColor(boundColor);
		//g.fillRect(bWidth,(trackHeight-handleHeight)/2,handleWidth,handleHeight);
		g2.fill(minBoundRect);
		g.setColor(Color.black);
		//g2.drawString(Integer.toString(model.getMinInterval()), minBoundRect.x, minBoundRect.y); 
		//-> Create an exception
		
		//maxBound
		g.setColor(boundColor);
		g2.fill(maxBoundRect);
		
		
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
