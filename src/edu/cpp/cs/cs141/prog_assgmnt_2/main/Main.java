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
 * This is the main class for the game. There is not much in this class because everything is handled
 * elsewhere as an object. The only thing main will do is create an instance of the game engine, and then
 * start the game.
 * 
 * @author Zach Kaufman
 */
public class Main {
	
	/**
	 * This constructor creates and instance of {@link edu.cpp.cs.cs141.prog_assgmnt_2.engine.GameEngine}, and then
	 * starts the game by calling {@link edu.cpp.cs.cs141.prog_assgmnt_2.engine.GameEngine#start()}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.start();
	}

}
