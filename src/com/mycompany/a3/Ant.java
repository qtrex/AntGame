package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Moveable implements ISteerable {
	private int size = 40;
	private int maximumSpeed = 40;         //Upper limit of speed attribute cannot move faster
	private int foodLevel = 100;           //How hungry Ant is, if it hits zero, ant should not be able to move
	private int foodConsumptionRate = 5;  //How much ant needs to consume each clock tick
	private int healthLevel = 10;          //Health of ant
	private int lastFlagReached = 1;       //Last flag reached by ant
	private static Ant theAnt;
	private int speed = 10;

	private Ant(double x, double y) {
		//Sets location and color of ant on initialization
		super.setLocation(x, y);
		setColor(255, 0, 0);
	}
	
	public static Ant getAnt(double x, double y) {
		if (theAnt == null)
			theAnt = new Ant(x, y);
		return theAnt;
	}
	
	
	//Returns lastFlagReached
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	//Sets lastFlagReached
	public void setLastFlagReached(int nextFlag) {
		this.lastFlagReached = nextFlag;
	}
	//Returns foodLevel
	public int getFoodLevel() {
		return foodLevel;
	}
	//Sets foodLevel
	public void setFoodLevel(int consumedFood) {
		this.foodLevel = consumedFood;
	}
	//Returns healthLevel
	public int getHealthLevel() {
		return this.healthLevel;
	}
	//Sets healthLevel to decrement by one
	private void setHealthLevel() {
		this.healthLevel--;
	}
	public void resetHealth() {
		healthLevel = 10;
	}
	public void resetFoodLevel() {
		foodLevel = 100;
	}
	//Sets maximum speed of Ant
	private void setMaximumSpeed() {
		this.maximumSpeed = (int) (this.maximumSpeed*(getHealthLevel()-2*getHealthLevel()));
	}
	//Sets the foodLevel to a new value based on consumption rate
	public void foodTick() {
		setFoodLevel(getFoodLevel()-foodConsumptionRate);
	}
	//Sets the speed of ant based on healthLevel
	public void setSpeed() {
		this.speed = getHealthLevel() + 10;
	}
	//Decreases the healthLevel, sets the new speed, and maximum speed of the ant. Then alters the color of the ant to mirror the health
	public void decreaseHealth() {
		setHealthLevel();
		setSpeed();
		setMaximumSpeed();
		switch (getHealthLevel()) { //Depending on health, changes color of ant
			case 1:
				setColor(210, 0, 0);
				break;
			case 2:
				setColor(215, 0, 0);
				break;
			case 3:
				setColor(220, 0, 0);
				break;
			case 4:
				setColor(225, 0, 0);
				break;
			case 5:
				setColor(230, 0, 0);
				break;
			case 6:
				setColor(235, 0, 0);
				break;
			case 7:
				setColor(240, 0, 0);
				break;
			case 8:
				setColor(245, 0, 0);
				break;
			case 9:
				setColor(250, 0, 0);
				break;
		}
	}
	
	@Override
	//From ISteerable so that the ant can be steered
	public void changeHeading(char d) {
		// TODO Auto-generated method stub
		if (d == 'l') {
			this.setHeading(this.getHeading()-5);
		} else if (d == 'r') {
			this.setHeading(this.getHeading()+5);
		}
	}
	//Accelerates the ant and makes sure it does not go over max speed
	public void accel() {
		this.setSpeed(getSpeed()+5);
		if (getSpeed() >= maximumSpeed) {
			setSpeed(maximumSpeed);
			System.out.println("Speed cap reached");
		}
	}
	//Applies brakes to the ant's speed
	public void braking() {
		setSpeed(getSpeed()-5);
		System.out.println("Speed decreased");
	}
	//Prints out information about the ant
	public String toString() {
		return ("Ant: " + "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]"
				+ " heading=" + this.getHeading() + " speed=" + this.getSpeed() 
				//remove under line
				+ " foodlevel="+ this.getFoodLevel() + " healthLevel=" + this.getHealthLevel()
				+ " size=" + size + " maxSpeed=" + maximumSpeed + " foodConsumptionRate=" + foodConsumptionRate);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		int x = (int) (pCmpRelPrnt.getX() + this.getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocation().getY());
		g.setColor(getColor());
		g.fillArc(x-(size/2), y-(size/2), 2*(size/2), 2*(size/2), 0, 360);
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
