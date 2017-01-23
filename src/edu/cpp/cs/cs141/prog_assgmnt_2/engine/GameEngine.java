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

	private UserInterface ui;

	private String weaponChoice;

	private String decision;

	private Gun playerGun, enemyGun;

	private ActiveAgent player, enemy;

	private boolean playing;

	private final int[] gunSpawnChance = { 50, 35, 15 };

	private Random rand;

	public GameEngine() {
		ui = new UserInterface();
		rand = new Random();
		player = new ActiveAgent(true);
		playing = true;
	}

	public void start() {
		ui.startMessage();
		ui.promptForWeapon();
		giveGun(true);

		while (playing) {
			playing = keepGoing();

			if (takeStep()) {
				createEnemy();
				while (enemy.getHealth() > 0) {
					ui.decisionTime();
					decision = ui.getInput();
					switch (decision) {
					case "flee":
						ui.fleeMessage(flee());
						break;
					case "shoot":
						ui.shootMessage(shoot(true));
					}
				}
			}

		}
	}

	private boolean shoot(boolean isPlayer) {
		if (isPlayer) {
			if (rand.nextInt(100) + 1 <= playerGun.getAccuracy()) {
				enemy.takeDamage(playerGun.shoot());
			}
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

	private boolean keepGoing() {
		return steps > 0 && player.getHealth() > 0;
	}

	private boolean takeStep() {
		steps--;
		ui.stepMessage(steps);
		if (rand.nextInt(100) + 1 <= 15) {
			return true;
		}
		return false;
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
			player.assignGun(playerGun);
		} else {
			if (rand.nextInt(100) + 1 >= gunSpawnChance[0])
				enemyGun = new Gun("Pistol");
			else if (rand.nextInt(100) + 1 >= gunSpawnChance[1])
				enemyGun = new Gun("Rifle");
			else
				enemyGun = new Gun("Shotgun");
			enemy.assignGun(enemyGun);
		}
	}

	private void createEnemy() {
		enemy = new ActiveAgent(false);
		giveGun(false);
		ui.enemyAppear(enemyGun.getName());
	}

}
