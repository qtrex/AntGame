package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private Point location;
	private int color = ColorUtil.rgb(0, 0, 0);
	private Vector<GameObject> collObj = new Vector<GameObject>();
	
	public int getSize() {
		return this.size;
	}
	public Point getLocation() {
		return location;
	}
	public int getColor() {
		return color;
	}
//	public void addCollObj(GameObject obj) {
//		this.collObj.add(obj);
//	}
	public Vector<GameObject> getCollObj() {
		return collObj;
	}
	//Sets location and checks to make sure that it is not out of the game bounds
	public void setLocation(double dX, double dY) {
		
//		if (dX > 0 && dX <= 1000.0) {
//			float x = (float)dX;
//			if (dY >= 0 && dY <= 1000.0) {
//				float y = (float)dY;
//				location = new Point(x, y);
//			} else {
//				float y = 1000;
//				location = new Point(x, y);
//			}
//		} else {
//			float x = 1000;
//			if (dY > 0 && dY >= 1000.0) {
//				float y = (float)dY;
//				location = new Point(x, y);
//			} else {
//				float y = 1000;
//				location = new Point(x, y);
//			}
//		}
		float x = (float)dX, y = (float)dY;
		this.location = new Point(x,y);
	}
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}
	public String toString() {
		return null;
	}
	public boolean collidesWith(GameObject otherObj) {
		boolean result = false;
		
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2));
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2));
		int otherCenterX = (int) (otherObj.getLocation().getX() + (otherObj.getSize()/2));
		int otherCenterY = (int) (otherObj.getLocation().getY() + (otherObj.getSize()/2));
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = ((dx*dx)+(dy*dy));
		
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObj.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);

		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;
	}
	
	public void handleCollision(GameObject otherObj, GameWorld gw) {
	
		
		//if (!collObj.contains(otherObj)) {


			if (this instanceof Ant) {
				if (otherObj instanceof FoodStation) {
					gw.food(otherObj);
				}
				if (otherObj instanceof Flag) {
					gw.collide(otherObj);
				}
				if (otherObj instanceof Spider) {
					System.out.println("Ant and Spider collision");
					gw.gotten();
				}
			}
//			if (otherObj instanceof Spider) {
//				gw.gotten();
//			}

//			collObj.add(otherObj);
//		}
//		collObj.add(otherObj);


	}
}
