package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command {
	private GameWorld target;
	
	public PositionCommand() {
		super("Position");
	}
	
	public void setTarget(GameWorld gw) {
		// TODO Auto-generated method stub
		target = gw;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {

		target.position();
	}
}
