//package tp0;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Polygon;
//import java.awt.Rectangle;
//
//import javax.swing.JComponent;
//import javax.swing.UIManager;
//import javax.swing.plaf.ComponentUI;
//import javax.swing.plaf.basic.BasicGraphicsUtils;
//
//
//public class CancelledDefaultRangerSliderUI extends ComponentUI {
//
//
//	private Color shadowColor;
//	private Color highlightColor;
//	private Rectangle trackRect;
//	private Rectangle tickRect;
//	
//	protected RangeSlider slider;
//
//	public CancelledDefaultRangerSliderUI(JComponent c) {
//		shadowColor = UIManager.getColor("Slider.shadow");
//		highlightColor = UIManager.getColor("Slider.highlight");
//		trackRect = new Rectangle(50,50);
//		tickRect = new Rectangle(10,10);
//		slider = (RangeSlider)c;
//	}
//
//
//
//	public void paint( Graphics g, JComponent c )   {
//		//recalculateIfInsetsChanged();
//		//recalculateIfOrientationChanged();
//		Rectangle clip = g.getClipBounds();
//
//		//if ( !clip.intersects(trackRect) && slider.getPaintTrack())
//		//    calculateGeometry();
//
//		//if ( slider.getPaintTrack() && clip.intersects( trackRect ) ) {
//		paintTrack( g );
//		//}
//		//if ( slider.getPaintTicks() && clip.intersects( tickRect ) ) {
//		paintTicks( g );
//		//}
//		//if ( slider.getPaintLabels() && clip.intersects( labelRect ) ) {
//		paintLabels( g );
//		//}
//		//if ( slider.hasFocus() && clip.intersects( focusRect ) ) {
//		paintFocus( g );
//		//}
//		//if ( clip.intersects( thumbRect ) ) {
//		paintThumb( g );
//		//}
//	}
//
//
//	public void paintTrack(Graphics g)  {
//
//		Rectangle trackBounds = trackRect;
//		
//		//if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
//		if (true) {
//			int cy = (trackBounds.height / 2) - 2;
//			int cw = trackBounds.width;
//
//			g.translate(trackBounds.x, trackBounds.y + cy);
//
//			g.setColor(shadowColor);
//			g.drawLine(0, 0, cw - 1, 0);
//			g.drawLine(0, 1, 0, 2);
//			g.setColor(highlightColor);
//			g.drawLine(0, 3, cw, 3);
//			g.drawLine(cw, 0, cw, 3);
//			g.setColor(Color.black);
//			g.drawLine(1, 1, cw-2, 1);
//
//			g.translate(-trackBounds.x, -(trackBounds.y + cy));
//		}
//		/*else {
//			int cx = (trackBounds.width / 2) - 2;
//			int ch = trackBounds.height;
//
//			g.translate(trackBounds.x + cx, trackBounds.y);
//
//			g.setColor(getShadowColor());
//			g.drawLine(0, 0, 0, ch - 1);
//			g.drawLine(1, 0, 2, 0);
//			g.setColor(getHighlightColor());
//			g.drawLine(3, 0, 3, ch);
//			g.drawLine(0, ch, 3, ch);
//			g.setColor(Color.black);
//			g.drawLine(1, 1, 1, ch-2);
//
//			g.translate(-(trackBounds.x + cx), -trackBounds.y);
//		}*/
//	}
//
//	public void paintTicks(Graphics g)  {
//		Rectangle tickBounds = tickRect;
//		int i;
//		int maj, min, max;
//		int w = tickBounds.width;
//		int h = tickBounds.height;
//		int centerEffect, tickHeight;
//
//		//g.setColor(DefaultLookup.getColor(slider, this, "Slider.tickColor", Color.black));
//		g.setColor(Color.black);
//		
//		maj = slider.getMajorTickSpacing();
//		min = slider.getMinorTickSpacing();
//
//		//if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
//		if (true ) {
//			g.translate( 0, tickBounds.y);
//
//			int value = slider.getMinimum();
//			int xPos = 0;
//
//			if ( slider.getMinorTickSpacing() > 0 ) {
//				while ( value <= slider.getMaximum() ) {
//					xPos = xPositionForValue( value );
//					paintMinorTickForHorizSlider( g, tickBounds, xPos );
//					value += slider.getMinorTickSpacing();
//				}
//			}
//
//			if ( slider.getMajorTickSpacing() > 0 ) {
//				value = slider.getMinimum();
//
//				while ( value <= slider.getMaximum() ) {
//					xPos = xPositionForValue( value );
//					paintMajorTickForHorizSlider( g, tickBounds, xPos );
//					value += slider.getMajorTickSpacing();
//				}
//			}
//
//			g.translate( 0, -tickBounds.y);
//		/*}
//		else {
//			g.translate(tickBounds.x, 0);
//
//			int value = slider.getMinimum();
//			int yPos = 0;
//
//			if ( slider.getMinorTickSpacing() > 0 ) {
//				int offset = 0;
//				if(!BasicGraphicsUtils.isLeftToRight(slider)) {
//					offset = tickBounds.width - tickBounds.width / 2;
//					g.translate(offset, 0);
//				}
//
//				while ( value <= slider.getMaximum() ) {
//					yPos = yPositionForValue( value );
//					paintMinorTickForVertSlider( g, tickBounds, yPos );
//					value += slider.getMinorTickSpacing();
//				}
//
//				if(!BasicGraphicsUtils.isLeftToRight(slider)) {
//					g.translate(-offset, 0);
//				}
//			}
//
//			if ( slider.getMajorTickSpacing() > 0 ) {
//				value = slider.getMinimum();
//				if(!BasicGraphicsUtils.isLeftToRight(slider)) {
//					g.translate(2, 0);
//				}
//
//				while ( value <= slider.getMaximum() ) {
//					yPos = yPositionForValue( value );
//					paintMajorTickForVertSlider( g, tickBounds, yPos );
//					value += slider.getMajorTickSpacing();
//				}
//
//				if(!BasicGraphicsUtils.isLeftToRight(slider)) {
//					g.translate(-2, 0);
//				}
//			}
//			g.translate(-tickBounds.x, 0);
//		}*/
//	}
//
//	public void paintLabels( Graphics g ) {
//		Rectangle labelBounds = labelRect;
//
//		Dictionary dictionary = slider.getLabelTable();
//		if ( dictionary != null ) {
//			Enumeration keys = dictionary.keys();
//			int minValue = slider.getMinimum();
//			int maxValue = slider.getMaximum();
//			boolean enabled = slider.isEnabled();
//			while ( keys.hasMoreElements() ) {
//				Integer key = (Integer)keys.nextElement();
//				int value = key.intValue();
//				if (value >= minValue && value <= maxValue) {
//					Component label = (Component)dictionary.get( key );
//					if (label instanceof JComponent) {
//						((JComponent)label).setEnabled(enabled);
//					}
//					if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
//						g.translate( 0, labelBounds.y );
//						paintHorizontalLabel( g, value, label );
//						g.translate( 0, -labelBounds.y );
//					}
//					else {
//						int offset = 0;
//						if (!BasicGraphicsUtils.isLeftToRight(slider)) {
//							offset = labelBounds.width -
//									label.getPreferredSize().width;
//						}
//						g.translate( labelBounds.x + offset, 0 );
//						paintVerticalLabel( g, value, label );
//						g.translate( -labelBounds.x - offset, 0 );
//					}
//				}
//			}
//		}
//
//	}
//
//	public void paintFocus(Graphics g)  {
//		g.setColor( getFocusColor() );
//
//		BasicGraphicsUtils.drawDashedRect( g, focusRect.x, focusRect.y,
//				focusRect.width, focusRect.height );
//	}
//
//
//
//	public void paintThumb(Graphics g)  {
//		Rectangle knobBounds = thumbRect;
//		int w = knobBounds.width;
//		int h = knobBounds.height;
//
//		g.translate(knobBounds.x, knobBounds.y);
//
//		if ( slider.isEnabled() ) {
//			g.setColor(slider.getBackground());
//		}
//		else {
//			g.setColor(slider.getBackground().darker());
//		}
//
//		Boolean paintThumbArrowShape =
//				(Boolean)slider.getClientProperty("Slider.paintThumbArrowShape");
//
//		if ((!slider.getPaintTicks() && paintThumbArrowShape == null) ||
//				paintThumbArrowShape == Boolean.FALSE) {
//
//			// "plain" version
//			g.fillRect(0, 0, w, h);
//
//			g.setColor(Color.black);
//			g.drawLine(0, h-1, w-1, h-1);
//			g.drawLine(w-1, 0, w-1, h-1);
//
//			g.setColor(highlightColor);
//			g.drawLine(0, 0, 0, h-2);
//			g.drawLine(1, 0, w-2, 0);
//
//			g.setColor(shadowColor);
//			g.drawLine(1, h-2, w-2, h-2);
//			g.drawLine(w-2, 1, w-2, h-3);
//		}
//		//else if ( slider.getOrientation() == JSlider.HORIZONTAL ) {
//		else if ( true ) {
//			int cw = w / 2;
//			g.fillRect(1, 1, w-3, h-1-cw);
//			Polygon p = new Polygon();
//			p.addPoint(1, h-cw);
//			p.addPoint(cw-1, h-1);
//			p.addPoint(w-2, h-1-cw);
//			g.fillPolygon(p);
//
//			g.setColor(highlightColor);
//			g.drawLine(0, 0, w-2, 0);
//			g.drawLine(0, 1, 0, h-1-cw);
//			g.drawLine(0, h-cw, cw-1, h-1);
//
//			g.setColor(Color.black);
//			g.drawLine(w-1, 0, w-1, h-2-cw);
//			g.drawLine(w-1, h-1-cw, w-1-cw, h-1);
//
//			g.setColor(shadowColor);
//			g.drawLine(w-2, 1, w-2, h-2-cw);
//			g.drawLine(w-2, h-1-cw, w-1-cw, h-2);
//		}
//		else {  // vertical
//			int cw = h / 2;
//		if(BasicGraphicsUtils.isLeftToRight(slider)) {
//			g.fillRect(1, 1, w-1-cw, h-3);
//			Polygon p = new Polygon();
//			p.addPoint(w-cw-1, 0);
//			p.addPoint(w-1, cw);
//			p.addPoint(w-1-cw, h-2);
//			g.fillPolygon(p);
//
//			g.setColor(highlightColor);
//			g.drawLine(0, 0, 0, h - 2);                  // left
//			g.drawLine(1, 0, w-1-cw, 0);                 // top
//			g.drawLine(w-cw-1, 0, w-1, cw);              // top slant
//
//			g.setColor(Color.black);
//			g.drawLine(0, h-1, w-2-cw, h-1);             // bottom
//			g.drawLine(w-1-cw, h-1, w-1, h-1-cw);        // bottom slant
//
//			g.setColor(shadowColor);
//			g.drawLine(1, h-2, w-2-cw,  h-2 );         // bottom
//			g.drawLine(w-1-cw, h-2, w-2, h-cw-1 );     // bottom slant
//		}
//		else {
//			g.fillRect(5, 1, w-1-cw, h-3);
//			Polygon p = new Polygon();
//			p.addPoint(cw, 0);
//			p.addPoint(0, cw);
//			p.addPoint(cw, h-2);
//			g.fillPolygon(p);
//
//			g.setColor(highlightColor);
//			g.drawLine(cw-1, 0, w-2, 0);             // top
//			g.drawLine(0, cw, cw, 0);                // top slant
//
//			g.setColor(Color.black);
//			g.drawLine(0, h-1-cw, cw, h-1 );         // bottom slant
//			g.drawLine(cw, h-1, w-1, h-1);           // bottom
//
//			g.setColor(shadowColor);
//			g.drawLine(cw, h-2, w-2,  h-2 );         // bottom
//			g.drawLine(w-1, 1, w-1,  h-2 );          // right
//		}
//		}
//
//		g.translate(-knobBounds.x, -knobBounds.y);
//	}
//
//}
