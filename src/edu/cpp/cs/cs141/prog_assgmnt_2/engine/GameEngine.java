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

import java.util.Random;

import edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent;
import edu.cpp.cs.cs141.prog_assgmnt_2.Items.ItemDrops;
import edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun;
import edu.cpp.cs.cs141.prog_assgmnt_2.ui.UserInterface;

/**
 * This class handles all of the logic for the game. Every random encounter is calculated through this class,
 * and all decision making is handled through this class. This class contains the main game loop that is run
 * until the game is over.
 *
 * @author Zach
 */
public class GameEngine {

	/**
	 * This field represents the number of steps left to take to win the game.
	 * Initially {@code 10}, will be incremented down by {@code 1} each time the
	 * player progresses forward by a step.
	 */
	private int steps = 10;

	/**
	 * This field will hold an object of type {@link edu.cpp.cs.cs141.prog_assgmnt_2.ui.UserInterface},
	 * and is used to make calls to the console and take input.
	 */
	private UserInterface ui;

	/**
	 * This field represents the weapon that the user chooses. This field will be set when the {@link #start()}
	 * method is called, and will be used to determine what type of {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun}
	 * the player will use.
	 */
	private String weaponChoice;
	
	/**
	 * This field represents whether or not the {@link #flee()} method has worked. If it has, then no drops will be given.
	 * If the {@link flee} method doesn't work, and the enemy is killed, then the drops will still be given.
	 */
	private boolean flee;

	/**
	 * This field represents a String that holds the decision that determines whether the player flees or shoots. This
	 * field is set through the user interface.
	 */
	private String decision;

	/**
	 * These fields represent the {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun} object. The playerGun represents the player's
	 * gun, and enemyGun represents the enemy's gun.
	 */
	private Gun playerGun, enemyGun;

	/**
	 * These fields represent an {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent}, a living entity. The player field
	 * represents the player itself, and the enemy field represents the enemy.
	 */
	private ActiveAgent player, enemy;

	/**
	 * This field is an array that represents the chance that an enemy will spawn with a certain gun. Each index in the array 
	 * correlates to a type of {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun}. The index of {@code 0} represents the chance that
	 * a pistol will spawn. {@code 1} represents the chance that a rifle will spawn. {@code 2} represents the chance that a shotgun
	 * will appear.
	 */
	private final int[] gunSpawnChance = { 50, 35, 15 };

	/**
	 * This field is used to access the prewritten {@link java.util.Random} class. This is used to do the random number logic
	 * in the game.
	 */
	private Random rand;

	/**
	 * This is the constructor for the {@link GameEngine} class. It only instantiates the {@link #ui}, {@link #rand}, and 
	 * {@link #player} objects.
	 */
	public GameEngine() {
		ui = new UserInterface();
		rand = new Random();
		player = new ActiveAgent(true);
	}

	/**
	 * This method contains the main loop that the game will be run from. This method handles the structure of the game,
	 * and makes calls to other methods to make sure this method stays concise and readable. This method starts by using
	 * the user interface to print to the screen, and then takes input and provides the user with a weapon. The game loop
	 * then starts, and handles the general process of running the game.
	 */
	public void start() {
		ui.startMessage();
		ui.promptForWeapon();
		giveGun(true);

		while (keepGoing()) {
			ui.pause();
			if (takeStep()) {
				createEnemy();
				flee = false;
				while (enemy.getHealth() > 0) {
					keepGoing();
					ui.shot();
					ui.shootMessage(shoot(false));
					ui.displayHealth(player.getHealth(), true);
					ui.decisionTime();
					decision = ui.getInput();
					switch (decision) {
					case "flee":
						flee = flee();
						ui.fleeMessage(flee);
						break;
					case "shoot":
						ui.shootMessage(shoot(true));
						ui.displayBullets(playerGun.getCurrentAmmo());
						ui.displayHealth(enemy.getHealth(), false);
					}
				}
				giveDrop(flee);
			}
		}
		ui.win();
	}

	/**
	 * This method attempts to shoot the gun. The gun will fire only if there is ammo in the gun, and then has a set 
	 * percent chance to hit based on the gun's preset properties. If the random number generated is within the bounds of
	 * the gun's chance to hit, then the Agent being shot will take damage. If not, the gun has missed.
	 * 
	 * @param isPlayer {@code true} if the player is the one shooting, {@code false} if it is an enemy.
	 * @return {@code true} if the shot hits the target, {@code false} if it does not hit the target.
	 */
	private boolean shoot(boolean isPlayer) {
		boolean hit;
		if (isPlayer && playerGun.getCurrentAmmo() > 0) {
			hit = rand.nextInt(100) + 1 <= playerGun.getAccuracy();
			enemy.takeDamage(playerGun.shoot(hit));
			return hit;
		} else if (!isPlayer && enemyGun.getCurrentAmmo() > 0) {
			hit = rand.nextInt(100) + 1 <= enemyGun.getAccuracy();
			player.takeDamage(enemyGun.shoot(hit));
			return hit;
		}
		return false;
	}

	/**
	 * This method attempts to flee from the enemy. There is a 10% chance that it will be successful. If it does work, 
	 * then the enemy will be killed, but the steps from the exit will increment up by {@code 1}.
	 * 
	 * @return {@code true} if the flee was successful {@code false} if it failed
	 */
	private boolean flee() {
		if (rand.nextInt(10) + 1 == 1) {
			steps++;
			enemy.takeDamage(enemy.getHealth());
			return true;
		}
		return false;
	}
	
	
	/**
	 * This method gives the player an item based on whether or not an enemy was killed. If the enemy was killed, then an
	 * item will drop. There is a 30% chance that a health pack will drop, and a 70% chance that an ammo cartridge drops.
	 * If the player flees, then they receive no drops.
	 * 
	 * @param flee whether or not the player flees, will not get a drop if they have fled. If {@code true}, then no drops.
	 */
	private void giveDrop(boolean flee){
		if(flee)
			return;
		if(rand.nextInt(100) + 1 >= 30){
			new ItemDrops(playerGun);
			ui.dropMessage(true);
		}
		else{
			new ItemDrops(player);
			ui.dropMessage(false);
		}
	}

	/**
	 * This method checks to make sure all necessary parameters are true before the game continues. If the player's health
	 * is equal to {@code 0} or less, then the game is over. Also, if the {@link #steps} are less than or equal to {@code 0},
	 * then the game is over as well. 
	 * 
	 * @return
	 */
	private boolean keepGoing() {
		if (player.getHealth() <= 0) {
			ui.lose();
			System.exit(0);
		}
		return steps > 0;
	}

	/**
	 * This method takes a step forward in the dungeon. Each time this is called, the {@link #steps} field is incremented
	 * down by {@code 1}. The player then has a 15% chance of encountering an enemy. If the 15% threshold is met, the enemy
	 * will appear.
	 * 
	 * @return whether or not an enemy will be encountered. {@code true} for enemy, {@code false} for no enemy.
	 */
	private boolean takeStep() {
		steps--;
		ui.stepMessage(steps);
		if (rand.nextInt(100) + 1 <= 15) {
			return true;
		}
		return false;
	}

	/**
	 * This method gives an {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent} a gun to use. If the
	 * Agent is a player, then it will take input from the user interface through the method
	 * {@link edu.cpp.cs.cs141.prog_assgmnt_2.ui.UserInterface#getInput()}. If the Agent is not a player, then
	 * the gun is based on the {@link #gunSpawnChance} defined by the assignment's parameters.
	 * 
	 * @param isPlayer whether or not the gun is being assigned to a player. {@code true} for yes, {@code false} for no
	 */
	private void giveGun(boolean isPlayer) {
		if (isPlayer) {
			weaponChoice = ui.getInput();
			switch (weaponChoice.toLowerCase()) {
			case "pistol":
				playerGun = new Gun("Pistol");
				break;
			case "rifle":
				playerGun = new Gun("Rifle");
				break;
			case "shotgun":
				playerGun = new Gun("Shotgun");
			}
		} else {
			if (rand.nextInt(100) + 1 >= gunSpawnChance[0])
				enemyGun = new Gun("Pistol");
			else if (rand.nextInt(100) + 1 >= gunSpawnChance[1])
				enemyGun = new Gun("Rifle");
			else
				enemyGun = new Gun("Shotgun");
		}
	}

	/**
	 * This method creates a new enemy, which is an {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent}.
	 * It then gives it a weapon using {@link #giveGun(boolean)}, and notifies the player of the game
	 * through a call to the ui: {@link edu.cpp.cs.cs141.prog_assgmnt_2.ui.UserInterface#enemyAppear(String)}.
	 */
	private void createEnemy() {
		enemy = new ActiveAgent(false);
		giveGun(false);
		ui.enemyAppear(enemyGun.getName());
	}

}
