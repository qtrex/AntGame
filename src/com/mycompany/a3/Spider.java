package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

public class Spider extends Moveable {
	private Random ran = new Random();
	private int size = (20 + ran.nextInt(40));
	//Sets location and heading randomly
	public Spider(Dimension worldSize) {
		super.setLocation((Math.round(ran.nextInt(worldSize.getWidth())+ran.nextDouble())/10.0), (Math.round(ran.nextInt(worldSize.getHeight())+ran.nextDouble())/10.0));
		super.setHeading(ran.nextInt(359));
		super.setColor(0, 0, 0);
	}
	//Alters the heading of spider randomly
	public void alterHeading() {
		if (ran.nextBoolean()) {
			setHeading(getHeading()+5);
		} else {
			setHeading(getHeading()-5);
		}
	}
	public String toString() {
		return ("Spider: loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]"
				+ " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size:" + size);
	}
	public void setColor() {
		
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		int x = (int) (pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocation().getY());
		g.setColor(getColor());
		
		int[] arrX = new int [3]; 
		arrX[0] = x-(size/2);
		arrX[1] = x;
		arrX[2] = x+(size/2);
		
		int[] arrY = new int [3];
		arrY[0] = y+(size/2);
		arrY[1] = y-(size/2);
		arrY[2] = y+(size/2);
		
		if (arrX[2] >= 999  || arrX[0] < 0 ) {
			setHeading(-getHeading());
			System.out.println("Hit 999 x");
		}
		if (arrY[0] >= 999 || arrY[1] < 0) {
			setHeading(-getHeading());
		}
		
		g.drawPolygon(arrX, arrY, 3);
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
	public int getSize() {
		return this.size;
	}
}
