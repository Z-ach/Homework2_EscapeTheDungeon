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
package edu.cpp.cs.cs141.prog_assgmnt_2.Agents;

/**
 * This class represents living things within the game. The class can be used to create a player
 * and to create all enemies the player will face. The 
 *
 * @author Zach
 */
public class ActiveAgent {
	
	private int health;
		
	//private Gun gun;
	
	public ActiveAgent(boolean isPlayer){
		if(isPlayer)
			health = 20;
		else
			health = 5;
	}
	
	public void restoreHealth(){
		health += 5;
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	
	public int getHealth(){
		return health;
	}
	
}
