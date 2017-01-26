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
package edu.cpp.cs.cs141.prog_assgmnt_2.gun;

/**
 * This class represents the gun object within the game. 
 *
 * @author Zach
 */
public class Gun {
	
	private String[] name = {"Pistol", "Rifle", "Shotgun"};
	private final int[] accuracy = {75, 65, 40};
	private final int[] damage = {1, 2, 5};
	private final int[] maxAmmo = {15, 10, 5};
	private int gunChoice;
	private int currentAmmo;
	
	/**
	 * When this class is instantiated, {@link #Gun(String)} uses the argument passed in to determine the type of 
	 * {@link Gun} to create. {@link #gunChoice} is then set to {@code 0}, {@code 1}, or {@code 2} depending on the
	 * correspond
	 * 
	 * @param name the name of the gun to create
	 */
	public Gun(String name){
		if(name.equals("Pistol")){
			gunChoice = 0;
		}
		else if(name.equals("Rifle")){
			gunChoice = 1;
		}
		else if(name.equals("Shotgun")){
			gunChoice = 2;
		}
		currentAmmo = maxAmmo[gunChoice];
	}
	
	public int shoot(boolean hit){
		currentAmmo--;
		if (hit)
			return damage[gunChoice];
		return 0;
	}
	
	public int getCurrentAmmo(){
		return currentAmmo;
	}
	
	public void reload(){
		currentAmmo = maxAmmo[gunChoice];
	}
	
	public String getName(){
		return name[gunChoice];
	}
	
	public int getAccuracy(){
		return accuracy[gunChoice];
	}

}
