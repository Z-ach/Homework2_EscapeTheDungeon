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
package edu.cpp.cs.cs141.prog_assgmnt_2.ui;

import java.util.Scanner;

/**
 * This class handles all interactions with the player of the game. All print statements are written here,
 * and all input is handled here as well. This class contains various methods that communicate with the player.
 *
 * @author Zach
 */
public class UserInterface {
	
	/**
	 * This field represents the {@link java.util.Scanner} object, and is used to take user input when prompted.
	 */
	private Scanner input;
	
	/**
	 * This is the constructor for the {@link UserInterface}. The only thing it does is instantiate the Scanner, which
	 * is used later in this class in a different method to take input from the player of the game.
	 */
	public UserInterface(){
		input = new Scanner(System.in);
	}
	
	/**
	 * This method is used to get the input from the player of the game.
	 * 
	 * @return the String value of the user's input
	 */
	public String getInput(){
		return input.nextLine();
	}
	
	/**
	 * This method prints out when the game starts. It is used to welcome the player to the game and to explain the rules
	 * that will be used during the game.
	 */
	public void startMessage(){
		System.out.println("Welcome to the Dungeon!");
		System.out.println("Each step you take you will have a chance to encounter an enemy.");
		System.out.println("You can choose to shoot the enemy, or try to run.");
		System.out.println("You will have the opportunity to choose your weapon:");
		System.out.println("There are only 10 steps to the exit. Good luck.");
	}
	
	/**
	 * This method prints out all {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun}s in the game, and displays the relevant 
	 * statistics associated with each weapon.
	 */
	public void promptForWeapon(){
		System.out.println("\nWhat weapon will you choose?");
		System.out.println("Pistol:\t\tHit Chance: 75%\t\tDamage: 1hp\tMax Ammo: 15");
		System.out.println("Rifle:\t\tHit Chance: 65%\t\tDamage: 2hp\tMax Ammo: 10");
		System.out.println("Shotgun:\tHit Chance: 40%\t\tDamage: 5hp\tMax Ammo: 5\n");
		System.out.println("Type the name of the weapon you desire.");
	}
	
	/**
	 * This method tells the player of the game how many steps they have remaining until they reach the exit.
	 * 
	 * @param steps the number of {@link edu.cpp.cs.cs141.prog_assgmnt_2.engine.GameEngine#steps} remaining.
	 */
	public void stepMessage(int steps){
		System.out.println("You take a step forward. You now have " + steps + " steps left.");
	}
	
	/**
	 * This method notifies the player of the game that an enemy has appeared, and that he is pointing his weapon at them.
	 * 
	 * @param gun the type of weapon that the enemy is pointing at the player.
	 */
	public void enemyAppear(String gun){
		System.out.println("An enemy appears. He points his weapon at you. It's a " + gun + ".");
	}
	
	/**
	 * This method is called to prompt the user to make a decision, either to shoot the enemy or flee from the enemy.
	 */
	public void decisionTime(){
		System.out.println("Do you want to shoot, or attempt to flee? Enter \"shoot\" or \"flee\"");
	}
	
	/**
	 * This method notifies the player that they have just gotten a drop from the enemy that has been killed. The drop can
	 * either be an ammo cartridge or a health pack, and is dependent on what is passed in as an argument. 
	 * 
	 * @param gun {@code true} if it is an ammo cartridge, {@code false} if it is a health pack.
	 */
	public void dropMessage(boolean gun){
		if(gun)
			System.out.println("An ammo cartridge drops from the enemy. It's full.");
		else
			System.out.println("He was carrying an health pack with him. You stick yourself up and gain 5 health.");
	}
	
	/**
	 * This method is to notify the player of the result of them trying to flee. The message is determined by the success of
	 * the flee attempt. 
	 * 
	 * @param flee {@code true} if the flee works, {@code false} if the flee failed.
	 */
	public void fleeMessage(boolean flee){
		System.out.println("You attempt to flee");
		if(flee)
			System.out.println("You manage to escape with your life. You go back 1 step.");
		else
			System.out.println("The thing in the darkness grabs you and pulls you back. Flee failed.");
	}
	
	/**
	 * This message notifies the player of the result of a gun firing. This method can be used for both the enemy firing a gun and
	 * the player firing a gun.
	 * 
	 * @param hit {@code true} if the shot hits, {@code false} if the shot misses.
	 */
	public void shootMessage(boolean hit){
		if(hit)
			System.out.println("The shot hits it mark.");
		else
			System.out.println("The shot misses.");
	}
	
	/**
	 * This method pauses the game until the player presses enter. This is to allow the player to feel like they are interacting 
	 * and are in more control.
	 */
	public void pause(){
		System.out.println("Press the enter key to take a step.");
		input.nextLine();
	}
	
	/**
	 * This method notifies the player that they are being shot at.
	 */
	public void shot(){
		System.out.println("You're being shot at!");
	}
	
	/**
	 * This method notifies the player of the remaining amount of ammo they have in their gun.
	 * 
	 * @param bullets the number of bullets in the gun
	 */
	public void displayBullets(int bullets){
		System.out.println("You now have " + bullets + " bullets remaining.");
	}
	
	/**
	 * This prints the win message if the player wins the game.
	 */
	public void win(){
		System.out.println("You made it to the exit. Congratulations.");
	}
	
	/**
	 * If the player's health reaches 0, the game is over, and they lose.
	 */
	public void lose(){
		System.out.println("You died. Game over.");
	}
	
	/**
	 * This is used to display the health value associated with a player or an enemy. 
	 * 
	 * @param health the amount of health remaining.
	 * @param isPlayer {@code true} if the health is for the player, {@code false} if it is the enemy's health.
	 */
	public void displayHealth(int health, boolean isPlayer){
		if(isPlayer)
			System.out.println("You now have " + health + " health left.");
		else
			System.out.println("The enemy now has " + health + " health left.");
	}
	
}
