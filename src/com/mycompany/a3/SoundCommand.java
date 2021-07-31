package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	
	private GameWorld target;

	public SoundCommand() {
		super("Sound");
		// TODO Auto-generated constructor stub
	}

	public void setTarget(GameWorld gw) {
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.sound();
	}
}
