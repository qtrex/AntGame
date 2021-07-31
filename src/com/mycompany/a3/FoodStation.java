package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

public class FoodStation extends Fixed {
	private Random ran = new Random();
	private int capacity = (40 + ran.nextInt(40));
	private int size = capacity;
	private boolean selected;
	private boolean positionChange = false;
	
	public FoodStation() {      //Sets location randomly
		super.setLocation((Math.round(ran.nextInt(9999)+ran.nextDouble())/10.0), (Math.round(ran.nextInt(9999)+ran.nextDouble())/10.0));
		this.setColor(13, 154, 9);
	}
	//Returns capacity
	public int getCapacity() {
		return capacity;
	}
	//Sets capacity to zero and changes color to lighter green
	public void setCapacityZero() {
		this.capacity = 0;
		this.setColor(144, 238, 144);
	}
	public String toString() {
		return ("Food Station: loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]"
				+ " size=" + size + " capacity=" + capacity);
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		System.out.println("Drawing FS");
		
		int x = (int) (pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocation().getY());
		
		if (isSelected()) {
			g.setColor(getColor());
			g.drawRect(x-(size/2), y-(size/2), size, size);
			g.setColor(ColorUtil.GREEN);
			g.drawString(String.valueOf(capacity), x-(size/2), y-(size/2));
		} else {
			g.setColor(getColor());
			g.fillRect(x-(size/2), y-(size/2), size, size);
			g.setColor(ColorUtil.WHITE);
			g.drawString(String.valueOf(capacity), x-(size/2), y-(size/2));
		}

	}
	public boolean collidesWith(GameObject otherObj) {
		// TODO Auto-generated method stub
		return super.collidesWith(otherObj);
	}
	public void handleCollision(GameObject otherObj, GameWorld gw) {
		// TODO Auto-generated method stub
		super.handleCollision(otherObj, gw);
	}
	@Override
	public void setSelected(boolean b) {
		// TODO Auto-generated method stub
		this.selected = b;
	}
	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		int xLoc = (int) (pCmpRelPrnt.getX() + this.getLocation().getX() - (size/2)); // + this.getLocation().getX()
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getLocation().getY() - (size/2));
		System.out.println(xLoc + " " + yLoc + " " + size);
		System.out.println(this.getLocation().getX() + " " + this.getLocation().getX());
		if ((px >= xLoc) && (px <= xLoc + size) && (py >= yLoc) && (py <= yLoc + size))
				return true;
			else { return false;}
		
	}
	public boolean posChange() {
		return positionChange;
	}
	public void changePosition() {
		positionChange = !positionChange;
	}
	public void setLocation(double dX, double dY) {
		super.setLocation(dX, dY);
	}
	public int getSize() {
		return this.size;
	}
}
