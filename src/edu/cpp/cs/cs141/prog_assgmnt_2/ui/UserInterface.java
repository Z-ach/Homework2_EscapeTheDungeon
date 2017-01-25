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
 *
 *
 * @author Zach
 */
public class UserInterface {
	
	private Scanner input;
	
	public UserInterface(){
		input = new Scanner(System.in);
	}
	
	public String getInput(){
		return input.nextLine();
	}
	
	public void startMessage(){
		System.out.println("Welcome to the Dungeon!");
		System.out.println("Each step you take you will have a chance to encounter an enemy.");
		System.out.println("You can choose to shoot the enemy, or try to run.");
		System.out.println("You will have the opportunity to choose your weapon:");
		System.out.println("There are only 10 steps to the exit. Good luck.");
	}
	
	public void promptForWeapon(){
		System.out.println("\nWhat weapon will you choose?");
		System.out.println("Pistol:\t\tHit Chance: 75%\t\tDamage: 1hp\tMax Ammo: 15");
		System.out.println("Rifle:\t\tHit Chance: 65%\t\tDamage: 2hp\tMax Ammo: 10");
		System.out.println("Shotgun:\tHit Chance: 40%\t\tDamage: 5hp\tMax Ammo: 5\n");
		System.out.println("Type the name of the weapon you desire.");
	}
	
	public void stepMessage(int steps){
		System.out.println("You take a step forward. You now have " + steps + " steps left.");
	}
	
	public void enemyAppear(String gun){
		System.out.println("An enemy appears. He points his weapon at you. It's a " + gun + ".");
	}
	
	public void decisionTime(){
		System.out.println("Do you want to shoot, or attempt to flee? Enter \"shoot\" or \"flee\"");
	}
	
	public void dropMessage(boolean gun){
		if(gun)
			System.out.println("An ammo cartridge drops from the enemy. It's full.");
		else
			System.out.println("He was carrying an health pack with him. You stick yourself up and gain 5 health.");
	}
	
	public void fleeMessage(boolean flee){
		System.out.println("You attempt to flee");
		if(flee)
			System.out.println("You manage to escape with your life. You go back 1 step.");
		else
			System.out.println("The thing in the darkness grabs you and pulls you back. Flee failed.");
	}
	
	public void shootMessage(boolean hit){
		if(hit)
			System.out.println("The shot hits it mark.");
		else
			System.out.println("The shot misses.");
	}
	
	public void pause(){
		System.out.println("Press the enter key to take a step.");
		input.nextLine();
	}
	
	public void shot(){
		System.out.println("You're being shot at!");
	}
	
	public void displayBullets(int bullets){
		System.out.println("You now have " + bullets + " bullets remaining.");
	}
	
	public void win(){
		System.out.println("You made it to the exit. Congratulations.");
	}
	
	public void lose(){
		System.out.println("You died. Game over.");
	}
	
	public void displayHealth(int health, boolean isPlayer){
		if(isPlayer)
			System.out.println("You now have " + health + " health left.");
		else
			System.out.println("The enemy now has " + health + " health left.");
	}
	
}
