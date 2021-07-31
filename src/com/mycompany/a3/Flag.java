package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {
	private int sequenceNumber = 1;
	private int size = 80;
	private int color = ColorUtil.rgb(0, 0, 255);
	private boolean selected;
	private boolean positionChange = false;
	
	public Flag(int i, double x, double y) {      //Sets sequence number and location on init
		setSequenceNumber(i);
		super.setLocation(x, y);
	}
	//Returns sequence number
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	//Sets sequence number
	private void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String toString() {
		String s = ("Flag: loc=" + this.getLocation().getX() + "," + this.getLocation().getY() + " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] size=" + size + " seqNum=" + this.getSequenceNumber());
		return s;
	}
	public void setColor() {
		
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		int x = (int) (pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocation().getY());
		
		int[] arrX = new int [3]; 
		arrX[0] = x-(size/2);
		arrX[1] = x;
		arrX[2] = x+(size/2);
		
		int[] arrY = new int [3];
		arrY[0] = y+(size/2);
		arrY[1] = y-(size/2);
		arrY[2] = y+(size/2);
		
		if (this.isSelected()) {
			g.setColor(getColor());
			g.drawPolygon(arrX, arrY, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(String.valueOf(sequenceNumber), x, y);
		} else {
			g.setColor(getColor());
			g.fillPolygon(arrX, arrY, 3);
			g.setColor(ColorUtil.WHITE);
			g.drawString(String.valueOf(sequenceNumber), x, y);
		}
	}
	@Override
	public boolean collidesWith(GameObject otherObj) {
		// TODO Auto-generated method stub
		return super.collidesWith(otherObj);
	}
	@Override
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
		int xLoc = (int) (pCmpRelPrnt.getX() + this.getLocation().getX() - (size/2)); 
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getLocation().getY() - (size/2));
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
		return size;
	}
}
