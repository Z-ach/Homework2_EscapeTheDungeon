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
 *
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

	private String decision;

	private Gun playerGun, enemyGun;

	private ActiveAgent player, enemy;

	private final int[] gunSpawnChance = { 50, 35, 15 };

	private Random rand;

	public GameEngine() {
		ui = new UserInterface();
		rand = new Random();
		player = new ActiveAgent(true);
	}

	public void start() {
		ui.startMessage();
		ui.promptForWeapon();
		giveGun(true);

		while (keepGoing()) {
			ui.pause();
			if (takeStep()) {
				createEnemy();
				while (enemy.getHealth() > 0) {
					keepGoing();
					ui.shot();
					ui.shootMessage(shoot(false));
					ui.displayHealth(player.getHealth(), true);
					ui.decisionTime();
					decision = ui.getInput();
					switch (decision) {
					case "flee":
						ui.fleeMessage(flee());
						break;
					case "shoot":
						ui.shootMessage(shoot(true));
						ui.displayBullets(playerGun.getCurrentAmmo());
						ui.displayHealth(enemy.getHealth(), false);
					}
				}
				giveDrop();
			}

		}

		ui.win();
	}

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

	private boolean flee() {
		if (rand.nextInt(10) + 1 == 1) {
			steps++;
			return true;
		}
		return false;
	}
	
	private void giveDrop(){
		if(rand.nextInt(100) + 1 >= 30){
			new ItemDrops(playerGun);
			ui.dropMessage(true);
		}
		else{
			new ItemDrops(player);
			ui.dropMessage(false);
		}
	}

	private boolean keepGoing() {
		if (player.getHealth() <= 0) {
			ui.lose();
			System.exit(0);
		}
		return steps > 0;
	}

	private boolean takeStep() {
		steps--;
		ui.stepMessage(steps);
		if (rand.nextInt(100) + 1 <= 15) {
			return true;
		}
		return true;
	}

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
