package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	private GameWorld target;
	
	public AboutCommand() {
		super("About");
		// TODO Auto-generated constructor stub
	}
	
	public void setTarget(GameWorld gw) {
		// TODO Auto-generated method stub
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		target.about();
	}
}
