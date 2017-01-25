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
 *
 *
 * @author Zach
 */
public abstract class ItemDrops {
	
	
	public ItemDrops(Gun gun){
		gun.reload();
	}
	
	public ItemDrops(ActiveAgent player){
		player.restoreHealth();
	}

}
