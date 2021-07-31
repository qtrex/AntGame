package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class GameModeCommand extends Command {
	
	private GameWorld target;
	
	public GameModeCommand() {
		super("Pause");
		// TODO Auto-generated constructor stub
	}
	public void setTarget(GameWorld gw) {
		// TODO Auto-generated method stub
		target = gw;
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.pause();
	}
}
