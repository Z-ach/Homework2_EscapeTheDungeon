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

import edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun;

/**
 *
 *
 * @author Zach
 */
public class ActiveAgent {
	
	private int health;
		
	private Gun gun;
	
	public ActiveAgent(boolean isPlayer){
		if(isPlayer)
			health = 20;
		else
			health = 5;
	}
	
	public void assignGun(Gun gun){
		this.gun = gun;
	}
	
	public Gun getGun(){
		return gun;
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
