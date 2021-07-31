package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command {
	
	private GameWorld target;
	
	public TickCommand() {
		super("Tick");
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		//target.tick(20, target.getWidth(), target.getHeight());
	}
}
