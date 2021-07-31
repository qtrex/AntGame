package com.mycompany.a3;

public abstract class Fixed extends GameObject implements ISelectable {
//All fixed objects not allowed to move after creation
//Fixed location
	public void setLocation(int x, int y) {
		double dx = (double) x;
		double dy = (double) y;
		
		super.setLocation(dx, dy);
	}
	public boolean posChange() {
		return false;
	}
	public void changePosition() {
		// TODO Auto-generated method stub
		
	}
}
