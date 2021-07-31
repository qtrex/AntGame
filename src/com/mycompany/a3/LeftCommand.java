package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftCommand extends Command {

	private GameWorld target;
	
	public LeftCommand() {
		super("Left");
		// TODO Auto-generated constructor stub
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.left();
	}
}
