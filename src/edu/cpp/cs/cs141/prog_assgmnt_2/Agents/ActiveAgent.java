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
 * and to create all enemies the player will face. The {@link ActiveAgent} can take damage or 
 * heal.
 *
 * @author Zach
 */
public class ActiveAgent {
	
	/**
	 * This field represents the amount of health the {@link ActiveAgent} has. If this value reaches 
	 * {@code 0}, the {@link ActiveAgent} will be dead. 
	 */
	private int health;

	/**
	 * The sole job of this constructor is to set the health of the {@link ActiveAgent}. The amount of
	 * health to set is determined by whether or not the {@link ActiveAgent} is a player or an enemy. If
	 * the {@link ActiveAgent} is a player, then the health is set to {@code 20}. If it is not, then health
	 * is set to {@code 5}.
	 * 
	 * @param isPlayer whether or not the {@link ActiveAgent} is a player. {@code true} for yes, {@code false} for no
	 */
	public ActiveAgent(boolean isPlayer){
		if(isPlayer)
			health = 20;
		else
			health = 5;
	}
	
	/**
	 * This method restores health to the {@link ActiveAgent}. There is no parameter to pass in because health kits 
	 * restore a set amount of health, equal to {@code 5}, and there is no other time or instance where an 
	 * {@link ActiveAgent} would regain health.
	 */
	public void restoreHealth(){
		health += 5;
	}
	
	/**
	 * This method deals damage to the {@link ActiveAgent}. This will be called when the {@link ActiveAgent} is shot, which is
	 * {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun#shoot(boolean)}, and can only be done by another {@link ActiveAgent}. 
	 * The amount of damage dealt depends on the {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun} being used.
	 * 
	 * @param damage the amount of damage the {@link ActiveAgent} will take
	 */
	public void takeDamage(int damage){
		health -= damage;
	}
	
	/**
	 * This is the getter for the {@link ActiveAgent}'s health.
	 * 
	 * @return the {@link ActiveAgent}'s {@link #health}.
	 */
	public int getHealth(){
		return health;
	}
	
}
