package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command{
	
	private GameWorld target;

	public AccelerateCommand() {
		super("Accelerate");
		// TODO Auto-generated constructor stub
	}
	
	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.accelerate();
	}

}
