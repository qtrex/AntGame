package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodStationCollideCommand extends Command {
	
	private GameWorld target;
	
	public FoodStationCollideCommand() {
		super("Collide with Food Stations");
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//target.food();
	}
}
