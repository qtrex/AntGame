package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Dimension;

public abstract class Moveable extends GameObject {
	private int heading;        //Direction moveable object is heading
	private int speed = 10;     //Speed of moveable object
	
	//Function that moves moveable objects based on the heading of the object
	public void move(int eTime, Dimension worldSize) {
		double deltaX = ((Math.cos(Math.toRadians(90-heading)))*(eTime/speed));
		double deltaY = ((Math.sin(Math.toRadians(90-heading)))*(eTime/speed));
		
		this.setLocation((this.getLocation().getX()+deltaX), (this.getLocation().getY()+deltaY));//} //Sets location to new
	}
	//Returns heading
	public int getHeading() {
		return heading;
	}
	//Returns speed
	public int getSpeed() {
		return speed;
	}
	//Sets speed
	public void setSpeed(int speed) {
		if (this.speed <= 5) {
			this.speed = 0;
		} else {
			this.speed = speed;
		}
	}
	//Sets heading
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
}
