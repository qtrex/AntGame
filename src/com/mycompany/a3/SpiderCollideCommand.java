package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpiderCollideCommand extends Command {
	
	private GameWorld target;
	
	public SpiderCollideCommand() {
		super("Collide with Spider");
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.gotten();
	}
}
