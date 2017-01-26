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
package edu.cpp.cs.cs141.prog_assgmnt_2.Items;

import edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent;
import edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun;

/**
 * This class is used to represent all of the various things that could drop from an enemy.
 * This class is not abstract because there is no need for it to be. There are only two things
 * that can drop from an enemy. The first thing that can drop is ammo, and the second thing
 * that can drop is a healthpack. 
 *
 * @author Zach
 */
public class ItemDrops {
	
	
	/**
	 * This constructor will be called if the item that is dropped is an ammo cartridge. It takes a 
	 * {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun} as a parameter, and then uses 
	 * {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun#reload()} to reload the ammo.
	 * 
	 * @param gun the {@link edu.cpp.cs.cs141.prog_assgmnt_2.gun.Gun} that will be reloaded.
	 */
	public ItemDrops(Gun gun){
		gun.reload();
	}
	
	/**
	 * This constructor will be called if a health pack is dropped. In this case, the player will be healed
	 * a set amount equal to {@code 5}.
	 * 
	 * @param player the player that will be healed, which is an {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent}
	 */
	public ItemDrops(ActiveAgent player){
		player.restoreHealth();
	}

}
