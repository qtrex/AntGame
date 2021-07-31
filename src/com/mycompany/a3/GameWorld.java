package com.mycompany.a3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;

import com.codename1.charts.models.Point;
import com.codename1.components.MediaPlayer;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;

public class GameWorld extends Observable {
	private int currentLives = 3;                                             //Current Lives
	private int clock = 0;                                                    //Clock timer
	private int consumedFood;                                                 //Food that is to be consumed by a station
	private boolean gameOverFlag = false;                                     //Flag for if a life is lost
	private boolean gameWonFlag = false;                                      //Flag for if the final flag is collided with
	private int highestFlag = 1;                                              //Highest flag in worldObjects
	private int width;
	private int height;
	private boolean soundFlag = false;
	private GameObjectCollection worldObjects;
	private int totalTime = 0;
	private Dimension worldSize;
	private boolean pause = false;
	
	
	public void init(Dimension worldSize) {
		worldObjects = new GameObjectCollection();
		//Creates the initial game objects
		//Adds the flags with custom locations to world objects
		worldObjects.add(new Flag(1, 200.0, 200.0));
		worldObjects.add(new Flag(2, 350.0, 400.0));
		worldObjects.add(new Flag(3, 700.0, 150.0));
		worldObjects.add(new Flag(4, 400.0, 950.0));
		worldObjects.add(new Flag(5, 1000.0, 950.0));
		worldObjects.add(new Flag(6, 1500.0, 650.0));
	
		//Adds one ant to world objects
		worldObjects.add(Ant.getAnt(200.0, 200.0));
		
		//Adds two spiders to world objects
		worldObjects.add(new Spider(worldSize));
		worldObjects.add(new Spider(worldSize));
		
		//Adds two food stations to world objects
		worldObjects.add(new FoodStation());
		worldObjects.add(new FoodStation());
		
		displayCollection();
	}
	
	public void displayCollection() {
		IIterator theElements = worldObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject gob = ((GameObject) theElements.getNext());
			System.out.println(gob);
		}
	}
	
	public GameObjectCollection getCollection() {
		return this.worldObjects;
	}
	
	//Additional methods to manipulate world objects
	//Function that tells the ant to speed up
	public void accelerate() {
		if (!pause) {
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				if (gob instanceof Ant) {
					((Ant) gob).accel();
				}
			}
			System.out.println("Ant has accelerated");
		}
	}
	//Function that tells the ant to apply brakes
	public void brake() {
		if (!pause) {
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				if (gob instanceof Ant) {
					((Ant) gob).braking();
				}
			}
			System.out.println("Ant has slowed down");
		}
	}
	//Function that tells the ant to turn left
	public void left() {
		if (!pause) {
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				if (gob instanceof Ant) {
					((Ant) gob).changeHeading('l');
				}
			}
			System.out.println("Ant turned left");
		}
	}
	//Function that tells the ant to turn right
	public void right() {
		if (!pause) {
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				if (gob instanceof Ant) {
					((Ant) gob).changeHeading('r');
				}
			}
			System.out.println("Ant turned right");
		}
	}
	//Function that handles a flag collision
	public void collide(GameObject flag) { //Have to make it recognize the last flag
		if (!pause) {
			System.exit(1);
			int inc = 0;                //Increments to count number of flags
			Command cOk = new Command("Ok");
			Command cCancel = new Command("Cancel");
			Command[] cmds = new Command[]{cOk, cCancel};
			TextField myTF = new TextField();
			Command c = Dialog.show("Enter the title:", myTF, cmds);
			
			if (c == cOk) {
				IIterator theElements = worldObjects.getIterator();
				while(theElements.hasNext()) {
					GameObject gob = ((GameObject) theElements.getNext());
					if (gob instanceof Flag) {
						inc++;
					}
				}
				setHighestFlag(inc);
				setChanged();
				notifyObservers();
				clearChanged();
				System.out.println(myTF.getText());
				IIterator secondElements = worldObjects.getIterator();
				while(secondElements.hasNext()) {
					GameObject gob = ((GameObject) secondElements.getNext());
					if (gob instanceof Ant) {
						if (((Ant) gob).getLastFlagReached()+1 !=  Integer.parseInt(myTF.getText())) {                                                           //If flag does equal the next flag that is supposed to be collided with
							Dialog.show("Incorrect Flag", "Wrong flag reached, please proceed to flag " + (((Ant) gob).getLastFlagReached()+1), "OK", null);
						} else { //If flag does equal the next flag that is supposed to be collided with
							((Ant) gob).setLastFlagReached(Integer.parseInt(myTF.getText()));                                                                    //Sets the lastflagReached to the collided one                           
							Dialog.show("Correct Flag Reached", "Proceed to flag numer " + (((Ant) gob).getLastFlagReached() + 1), "OK", null);
							setChanged();
							notifyObservers(gob);
							clearChanged();
							if (((Ant) gob).getLastFlagReached() == highestFlag) {
								Dialog.show("Congrats! You won!", "You have reached the final flag", "OK", null);
								setGameWonFlag();
							}
						}
					}
				}
			}
		}
	}
	//Function that handles colliding with a food station
	public void food(GameObject fs) {	
		if (!pause) {
			if (((FoodStation) fs).getCapacity() > 0) {
				setConsumedFood(((FoodStation) fs).getCapacity());
				((FoodStation) fs).setCapacityZero();
				worldObjects.add(new FoodStation());
			}
			IIterator secondElements = worldObjects.getIterator();
			while(secondElements.hasNext()) {
				GameObject gob = ((GameObject) secondElements.getNext());
				if (gob instanceof Ant) {
					((Ant) gob).setFoodLevel(((Ant) gob).getFoodLevel()+consumedFood);
					setChanged();
					notifyObservers(gob);
					clearChanged();
				}
			}
		}
	}
	//Function that handles colliding with a spider
	public void gotten() {
		if (!pause) {
			System.out.println("Ant has collided with spider");
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				if (gob instanceof Ant) {                   //Looking for the ant object
					((Ant) gob).decreaseHealth();           //Decreases the health of the ant
					setChanged();
					notifyObservers(gob);
					clearChanged();
				}
			}
			String fileName = "pain.wav";
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			Media m = null;
			try {
				m = MediaManager.createMedia(is, "audio/wav");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (soundFlag) {
				m.play();
			}
			gameCheck();
			checkGameLife();
		}
	}
	//Function that handles the clock ticking
	public void tick(int elapsedTime) {
		
		if(!pause) {
			//clock++;//Increments clock
			setTotalTime(elapsedTime);
			//setClock(elapsedTime);
			setClock(totalTime/1000);
			
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
					GameObject gob = ((GameObject) theElements.getNext());
					
					if (gob instanceof Moveable) {       //Looking for the moveable objects
						((Moveable) gob).move(elapsedTime, this.worldSize);         //Applies move() to all moveable objects
					}
					
					
					if (gob instanceof Spider && totalTime % 100 == 0) {                              //Looking for the spider object
						((Spider) gob).alterHeading();                        //Alters the heading of the spider
					}
					
					if (gob instanceof Ant && totalTime % 500 == 0) {
						((Ant) gob).foodTick();         //Applies foodTick() to ant
						setChanged();
						notifyObservers(((Ant) gob));
						clearChanged();
					}
			}

			IIterator iter2 = worldObjects.getIterator();
			while(iter2.hasNext()) {
				GameObject curObj = (GameObject)iter2.getNext();
				IIterator iter3 = worldObjects.getIterator();
				while(iter3.hasNext()) {
					GameObject otherObj = (GameObject) iter3.getNext();
					if(otherObj != curObj) {
						if(curObj.collidesWith(otherObj)) {
							
							if(!curObj.getCollObj().contains(otherObj)) {
								curObj.handleCollision(otherObj, this);
								curObj.getCollObj().add(otherObj);
								if(otherObj instanceof GameObject) {
									otherObj.getCollObj().add(curObj);
								}
							}
						} else {
							curObj.getCollObj().remove(otherObj);
							otherObj.getCollObj().remove(curObj);
						}
					}
				}
			}
			setChanged();
			notifyObservers();
			clearChanged();
			map();
			gameCheck();
			checkGameLife(); //Checks game life
		}
	}
	//Function that handles checking health, foodlevel, and lastFlagReached
	public void gameCheck() {
		IIterator theElements = worldObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject gob = ((GameObject) theElements.getNext());
			if (gob instanceof Ant) {
				if (((Ant) gob).getHealthLevel() < 1) {      
					//Health reaches 0
					setGameOverFlag();
					Dialog.show("Life lost", "Ant has 0 health, starting over", "OK", null);
				} else if (((Ant) gob).getFoodLevel() < 1){
					//Food reaches 0
					Dialog.show("Life lost", "Ant has 0 food, starting over", "OK", null);
					setGameOverFlag();
				}
			}
		}
		//Checks the flags of the game
		checkGameLife();
	}
	//Function that handles losing a life and/or the game
	public void checkGameLife() {
		if (gameOverFlag) {         //If the player has died
			currentLives--;
			init(this.worldSize);
			System.out.println("Player has lost a life");
			IIterator theElements = worldObjects.getIterator();
			while(theElements.hasNext()) {
				GameObject gob = ((GameObject) theElements.getNext());
				if (gob instanceof Ant) {
					resetClock();
					((Ant) gob).resetHealth();
					((Ant) gob).resetFoodLevel();
					setChanged();
					notifyObservers(gob);
					clearChanged();
				}
			}
			setGameOverFlag();
		}
		if (currentLives == 0) {    //If the player is out of lives
			Dialog.show("Game Over", "Out of lives", "OK", null);
			Display.getInstance().exitApplication();
		}
	}
	
	public void sound() {
		setSoundFlag();
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	//Returns gameOverFlag
	public boolean getGameOverFlag() {
		return gameOverFlag;
	}
	//Sets gameOverFlag
	public void setGameOverFlag() {
		this.gameOverFlag = !gameOverFlag;
	}
	//Returns gameWonFlag
	public boolean getGameWonFlag() {
		return gameWonFlag;
	}
	//Sets gameWonFlag
	public void setGameWonFlag() {
		this.gameWonFlag = !gameWonFlag;
	}
	//Returns highestFlag
	public int getHighestFlag() {
		return highestFlag;
	}
	//Sets highestFlag
	public void setHighestFlag(int i) {
		this.highestFlag = i;
	}
	//Exit game initiated
	public void exit() {
		System.out.println("Exit Game initiated");
		Boolean eOk = Dialog.show("Confirm exit", "Are you sure you want to quit?", "Ok", "Cancel");
		if (eOk) {
			Display.getInstance().exitApplication();
		}
	}
	public void about() {
		Dialog.show("About", "Quixari Jones CSC 133", "OK", "Cancel");
	}
	public void help() {
		Dialog.show("Help", "A - Accelerate the Ant \n"
				+ " B - Brakes are applied on the Ant (slows down) \n"
				+ " L - Adjusts Ant to turn left \n"
				+ " R - Adjusts Ant to turn right \n"
				+ " F - Collide with a Food Station \n"
				+ " G - Collide with a Spider \n"
				+ " T - Game clock ticks", "OK", null);
	}
	public void inputError() {
		System.out.println("Error in recognizing input");
	}
	//Outputs map in text form
	public void map() {
		
		IIterator theElements = worldObjects.getIterator();
		while(theElements.hasNext()) {
			GameObject gob = ((GameObject) theElements.getNext());
			System.out.println(gob);
		}
	}
	public void setConsumedFood(int consumedFood) {
		this.consumedFood = consumedFood;
	}
	public void setCurrentLives(int newLives) {
		this.currentLives = newLives;
		
	}
	public int getCurrentLives() {
		return currentLives;
	}
	public int getClock() {
		return clock;
	}
	public void setClock(int c) {
		this.clock = c;
	}
	public void resetClock() {
		clock = 0;
	}
	public boolean getSoundFlag() {
		return soundFlag;
	}
	public void setSoundFlag() {
		soundFlag = !soundFlag;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int w) {
		width = w;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int h) {
		height = h;
	}
	public void setTotalTime(int eTime) {
		this.totalTime += eTime;
	}
	public void setWorldSize(Dimension worldSize) {
		this.worldSize = worldSize;
	}

	public void pause() {
		setPause();
		
	}
	public void setPause() {
		pause = !pause;
	}

	public void position() {
		if (pause) {
			IIterator iter = worldObjects.getIterator();
			while (iter.hasNext()) {
				GameObject fob = (GameObject) iter.getNext();
				if (fob instanceof Fixed && ((Fixed) fob).isSelected()) {
					((Fixed) fob).changePosition();
					System.out.println(((Fixed) fob).posChange());
					//((Fixed) iter).setLocation(); && ((Fixed) fob).isSelected()
				} 
			}
		}
	}
	
	public void createSounds() {
		String fileName = "Rusty-clock-01.wav";
		InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
		Media m = null;
		try {
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (soundFlag) {
			m.play();
		}
	}
}
