package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FlagCollideCommand extends Command {
	
	private GameWorld target;
	
	public FlagCollideCommand() {
		super("Collide with Flag");
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//target.collide();
	}
}
