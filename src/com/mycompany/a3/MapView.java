package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	private Graphics myGraphics;
	private GameWorld gwRef;	

	public MapView() {
		getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		myGraphics = g;
		
		if (gwRef != null) {
			
			Point pCmpRelPrnt = new Point(getX(), getY());
			
			IIterator theElements = gwRef.getCollection().getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				gob.draw(myGraphics, pCmpRelPrnt);
			}
		}
		
		
//		myGraphics.setColor(ColorUtil.BLACK);
//		myGraphics.drawRect(getX(), getY(), 20, 40);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console
		setGwRef((GameWorld) o);
		repaint();
		
	}
	
	public void pointerPressed(int x, int y) {
		
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator iter2 = gwRef.getCollection().getIterator();
		while(iter2.hasNext()) {
			GameObject fob = ((GameObject) iter2.getNext());
			if (fob instanceof Fixed) {
				//System.out.println("Change attempt");
				if (((Fixed) fob).isSelected()) {
					if (((Fixed) fob).posChange()) {
						System.out.println("Change attempt");
						((GameObject) fob).setLocation(x - pCmpRelPrnt.getX(), y - pCmpRelPrnt.getY());
						((Fixed) fob).changePosition();
					}
				}
			}
		}
		
		IIterator iter = gwRef.getCollection().getIterator();
		while(iter.hasNext()) {
			GameObject fob = ((GameObject) iter.getNext());
			if (fob instanceof Fixed) {
				if (((ISelectable) fob).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((ISelectable) fob).setSelected(true);
					System.out.println("Set true");
				} else {
					((ISelectable) fob).setSelected(false);
					System.out.println("Set false: " + pPtrRelPrnt.getX() + " " + pPtrRelPrnt.getY() + " "+ pCmpRelPrnt.getX() + " " + pCmpRelPrnt.getY());
				}
			}
		}
		
		repaint();
	}
	
	private void setGwRef (GameWorld gwRef) {
		this.gwRef = gwRef;
	}
}
