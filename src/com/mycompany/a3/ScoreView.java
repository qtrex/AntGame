package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	private Label timeData;
	private Label livesData;
	private Label lastFlagData;
	private Label foodLevelData;
	private Label healthLevelData;
	private Label soundData;

	public ScoreView() {
		setLayout(new FlowLayout(Component.CENTER));
		
		Label timeLabel = new Label("Time:   " );
		add(timeLabel);
		timeData = new Label("0");
		add(timeData);
		
		Label livesLabel = new Label("   Lives Left:   ");
		add(livesLabel);
		livesData = new Label("3");
		add(livesData);
		
		Label lastFlagLabel = new Label("    Last Flag Reached:   ");
		add(lastFlagLabel);
		lastFlagData = new Label("1");
		add(lastFlagData);
		
		Label foodLevelLabel = new Label("    Food Level: ");
		add(foodLevelLabel);
		foodLevelData = new Label("100");
		add(foodLevelData);
		
		Label healthLevelLabel = new Label("    Health Level:  ");
		add(healthLevelLabel);
		healthLevelData = new Label("10");
		add(healthLevelData);
		
		Label soundLabel = new Label("   Sound:   ");
		add(soundLabel);
		soundData = new Label("OFF");
		add(soundData);	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		 // code here to update labels from the game/ant state data 
		
		timeData.setText(((GameWorld) o).getClock()+"  ");		
		livesData.setText(((GameWorld) o).getCurrentLives()+"  ");
		if (arg != null) {
			lastFlagData.setText(((Ant) arg).getLastFlagReached()+"  ");
			foodLevelData.setText(((Ant) arg).getFoodLevel()+"  ");
			healthLevelData.setText(((Ant) arg).getHealthLevel()+"  ");
		}
		if (((GameWorld) o).getSoundFlag()) {
			soundData.setText("ON");
		} else {
			soundData.setText("OFF");
		}
		System.out.println("Score View updated");
	}
}
