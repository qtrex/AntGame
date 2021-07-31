package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {

	private GameWorld target;
	
	public HelpCommand() {
		super("Help");
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.help();
	}
}
