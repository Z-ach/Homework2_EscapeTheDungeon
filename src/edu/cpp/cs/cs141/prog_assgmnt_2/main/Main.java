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
package edu.cpp.cs.cs141.prog_assgmnt_2.main;

import edu.cpp.cs.cs141.prog_assgmnt_2.engine.GameEngine;

/**
 * 
 * 
 * @author Zach Kaufman
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.start();
	}

}
