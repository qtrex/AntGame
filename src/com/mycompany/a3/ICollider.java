package com.mycompany.a3;

public interface ICollider {

	//Detection
	public boolean collidesWith(GameObject otherObj);
	
	//Response
	public void handleCollision(GameObject otherObj, GameWorld gw);
}
