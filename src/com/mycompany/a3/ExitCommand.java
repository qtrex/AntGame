package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	
	private GameWorld target;
	
	public ExitCommand() {
		super("Exit");
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.exit();
	}
}
