/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #2
 *
 * Mini game of escape the dungeon. Take steps forward and risk random encounters, get to the exit to win.
 *
 * Zach Kaufman
 */
package edu.cpp.cs.cs141.prog_assgmnt_2.engine;

import edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun;
import edu.cpp.cs.cs141.prog_assgmnt_2.ui.UserInterface;

/**
 *
 *
 * @author Zach
 */
public class GameEngine {
	
	/**
	 * This field represents the number of steps left to take to win the game. Initially {@code 10}, will be 
	 * incremented down by {@code 1} each time the player progresses forward by a step.
	 */
	private int steps = 10;
	
	private UserInterface ui;
	
	private String weaponChoice;
	
	private Gun gun;
	
	
	public GameEngine(){
		ui = new UserInterface();
	}
	
	public void start(){
		ui.startMessage();
		ui.promptForWeapon();
		weaponChoice = ui.getInput();
		switch(weaponChoice.toLowerCase()){
		case "pistol": 
			gun = new Gun();
		case "rifle":
			gun = new Gun();
		case "shotgun":
			gun = new Gun();
		}
	}
	
	
	
}
