package com.mycompany.a3;

import java.io.IOException;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.util.UITimer;



public class Game extends Form implements Runnable{
	//Creates the various parts of GUI to hold commands and creates the views
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private Container westContainer;
	private Container eastContainer;
	private Container southContainer;
	private Toolbar myToolbar;
	
	public Game() {
		myToolbar = new Toolbar();              
		this.setLayout(new BorderLayout());       //Sets layout of the Form
		this.setToolbar(myToolbar);
		this.setTitle("The Path Gamee");
		
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		
		//Adds the map view and score view to be observers
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		//Creates a new west container, sets the layout, adds a border and padding
		westContainer = new Container();
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getUnselectedStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		//Creates a new east container, sets the layout, adds a border and padding
		eastContainer = new Container();
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getUnselectedStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		//Creates a new south container, sets the layout, adds a border and padding
		southContainer = new Container();
		southContainer.setLayout(new FlowLayout(Component.CENTER));
		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		southContainer.getAllStyles().setPadding(Component.BOTTOM, 10);
		
		//Creates button for accelerate, Creates command for accelerate and sets the target
		//Then adds the command to the button, key listener, and side menu
		Button accelerateButton = new StyledButton();
		AccelerateCommand myAccelerateCommand = new AccelerateCommand();
		myAccelerateCommand.setTarget(gw);
		accelerateButton.setCommand(myAccelerateCommand);
		addKeyListener('a', myAccelerateCommand);
		
		//Creates button for left, Creates command for left and sets the target
		//Then adds the command to the button and key listener
		Button leftButton = new StyledButton();
		LeftCommand myLeftCommand = new LeftCommand();
		myLeftCommand.setTarget(gw);
		leftButton.setCommand(myLeftCommand);
		addKeyListener('l', myLeftCommand);
		
		
		Button brakeButton = new StyledButton();
		BrakeCommand myBrakeCommand = new BrakeCommand();
		myBrakeCommand.setTarget(gw);
		brakeButton.setCommand(myBrakeCommand);
		addKeyListener('b', myBrakeCommand);
		
		Button rightButton = new StyledButton();
		RightCommand myRightCommand = new RightCommand();
		myRightCommand.setTarget(gw);
		rightButton.setCommand(myRightCommand);
		addKeyListener('r', myRightCommand);
		
		Button flagCollideButton = new StyledButton();
		FlagCollideCommand myFlagCollideCommand = new FlagCollideCommand();
		myFlagCollideCommand.setTarget(gw);
		flagCollideButton.setCommand(myFlagCollideCommand);
		
		Button spiderCollideButton = new StyledButton();
		SpiderCollideCommand mySpiderCollideCommand = new SpiderCollideCommand();
		mySpiderCollideCommand.setTarget(gw);
		spiderCollideButton.setCommand(mySpiderCollideCommand);
		addKeyListener('g', mySpiderCollideCommand);
		
		Button foodStationCollideButton = new StyledButton();
		FoodStationCollideCommand myFoodStationCollideCommand = new FoodStationCollideCommand();
		myFoodStationCollideCommand.setTarget(gw);
		foodStationCollideButton.setCommand(myFoodStationCollideCommand);
		addKeyListener('f', myFoodStationCollideCommand);
		
		Button tickButton = new StyledButton();
		TickCommand myTickCommand = new TickCommand();
		myTickCommand.setTarget(gw);
		tickButton.setCommand(myTickCommand);
		addKeyListener('t', myTickCommand);
		
		CheckBox soundCheckBox = new CheckBox();
		SoundCommand mySoundCommand = new SoundCommand();
		mySoundCommand.setTarget(gw);
		soundCheckBox.setCommand(mySoundCommand);
		myToolbar.addComponentToSideMenu(soundCheckBox);
		
		AboutCommand myAboutCommand = new AboutCommand();
		myAboutCommand.setTarget(gw);
		myToolbar.addCommandToSideMenu(myAboutCommand);
		
		ExitCommand myExitCommand = new ExitCommand();
		myExitCommand.setTarget(gw);
		myToolbar.addCommandToSideMenu(myExitCommand);
		
		HelpCommand myHelpCommand = new HelpCommand();
		myHelpCommand.setTarget(gw);
		myToolbar.addCommandToRightBar(myHelpCommand);
		
		Button gameModeButton = new StyledButton();
		GameModeCommand myGameModeCommand = new GameModeCommand();
		myGameModeCommand.setTarget(gw);
		gameModeButton.setCommand(myGameModeCommand);
		
		Button positionButton = new StyledButton();
		PositionCommand myPositionCommand = new PositionCommand();
		myPositionCommand.setTarget(gw);
		positionButton.setCommand(myPositionCommand);
		
		
		//Adds the accelerate and left button to the west container
		westContainer.addComponent(accelerateButton);
		westContainer.addComponent(leftButton);
		
		//Adds the brake and right button to the east container
		eastContainer.add(brakeButton);
		eastContainer.add(rightButton);
		
		//Adds the various collides and tick buttons to the south container

		southContainer.add(gameModeButton);
		southContainer.add(positionButton);
		
		//Adds the containers and views to the correct parts of the form
		this.getContentPane().add(BorderLayout.SOUTH, southContainer);
		this.getContentPane().add(BorderLayout.WEST, westContainer);
		this.getContentPane().add(BorderLayout.EAST, eastContainer);
		this.getContentPane().add(BorderLayout.CENTER, mv);
		this.getContentPane().add(BorderLayout.NORTH, sv);
		this.show();
		
		//Query for height
//		gw.setHeight(mv.getHeight());
//		gw.setWidth(mv.getWidth());
		Dimension worldSize = new Dimension(mv.getWidth(), mv.getHeight());

		//System.out.println(mv.getWidth() + "," + mv.getHeight());
		
		gw.init(worldSize);           // initialize world
		//gw.createSounds();
		
		revalidate();
		
		UITimer myTimer = new UITimer(this);
		myTimer.schedule(20, true, this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		gw.tick(20);
	}
	

}
