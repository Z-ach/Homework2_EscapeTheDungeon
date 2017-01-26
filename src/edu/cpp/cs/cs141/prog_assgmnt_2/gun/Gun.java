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
 * This class represents the {@link Gun} object within the game. Guns can either be Pistols, Rifles, or Shotguns in this
 * game. Each {@link Gun} traits specific to them that can be accessed through indexing an array. Guns can shoot only shoot
 * or reload. Because fields in this class are private, they require getters to access them.
 *
 * @author Zach
 */
public class Gun {
	
	/**
	 * This field represents the name of the {@link Gun} that is being created. There are only three options a {@link Gun} can be,
	 * and as such there are only three names. This field is final because it will not ever be changed. 
	 */
	private final String[] name = {"Pistol", "Rifle", "Shotgun"};
	
	/**
	 * This field represents the chance of landing hit on an enemy. The array has int values that are treated as the
	 * percentage of shots that will be made when firing the gun. There is no reason for accuracy of a weapon to change,
	 * so this field is final.
	 */
	private final int[] accuracy = {75, 65, 40};
	
	/**
	 * This field represents the amount of damage each {@link Gun} will deal if a shot hits the enemy. Cannot ever be changed,
	 * so this field is final. 
	 */
	private final int[] damage = {1, 2, 5};
	
	/**
	 * This field represents the maximum amount of ammo that the {@link Gun} can hold. The {@link Gun} can never hold more than
	 * this amount. This field cannot be changed, and is final.
	 */
	private final int[] maxAmmo = {15, 10, 5};
	
	/**
	 * This field represents what {@link Gun} is actually being used. {@code 0} for a Pistol, {@code 1} for a Rifle, and {@code 2}
	 * for a Shotgun. This field is used in later methods to ascertain what the {@link Gun}'s actual properties are. It is used
	 * as the index of all arrays created and used in this class. This field will be set when the class is instantiated, and there
	 * is not way to change the value later.
	 */
	private int gunChoice;
	
	/**
	 * This field represents the current amount of ammo the {@link Gun} holds. This amount can be changed to {@link #maxAmmo} when the
	 * {@link #reload()} method is called. If this value is {@code 0}, the {@link Gun} will be unable to {@link #shoot(boolean)}, and 
	 * thus will deal no damage to an {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent}.
	 */
	private int currentAmmo;
	
	/**
	 * When this class is instantiated, {@link #Gun(String)} uses the argument passed in to determine the type of 
	 * {@link Gun} to create. {@link #gunChoice} is then set to {@code 0}, {@code 1}, or {@code 2} depending on the
	 * corresponding values of the index that relate to the specific gun's properties. {@link #currentAmmo} is then
	 * set to the max ammo the particular type of {@link Gun} can hold.
	 * 
	 * @param name the name of the {@link Gun} to create
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
	
	/**
	 * This method effectively fires the {@link Gun}. If there are no bullets left, which means{@link #currentAmmo} is {@code 0},
	 * the {@link Gun} will not fire. If the parameter passed in is {@code true}, then the damage is returned and dealt to the
	 * {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent}. If it is {@code false}, then no damage is dealt to the enemy.
	 * 
	 * @param hit whether or not the {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent} will be hit
	 * @return the amount of damage to deal to the {@link edu.cpp.cs.cs141.prog_assgmnt_2.Agents.ActiveAgent}
	 */
	public int shoot(boolean hit){
		currentAmmo--;
		if (hit)
			return damage[gunChoice];
		return 0;
	}
	
	/**
	 * This is a getter for the {@link Gun}'s ammo. This is required because {@link #currentAmmo} is a private field, and so only
	 * this class can reference it.
	 * 
	 * @return the {@link #currentAmmo}
	 */
	public int getCurrentAmmo(){
		return currentAmmo;
	}
	
	/**
	 * This method is called when an ammo cartridge is found as an enemy's drop. The {@link #currentAmmo} is set to the corresponding
	 * {@link #maxAmmo}, which is dependent on the type of {@link Gun} being used. 
	 */
	public void reload(){
		currentAmmo = maxAmmo[gunChoice];
	}
	
	/**
	 * This method is a getter for the {@link Gun}'s name. The field is private, so it can only be referenced by something inside of the
	 * same class. The name will only be used in the UserInterface to display things.
	 * 
	 * @return the name of the {@link Gun}
	 */
	public String getName(){
		return name[gunChoice];
	}
	
	/**
	 * This field is the getter for the accuracy of a {@link Gun}. Primarily used in the logic portion of the Game Engine.
	 * 
	 * @return the accuracy of the current {@link Gun}.
	 */
	public int getAccuracy(){
		return accuracy[gunChoice];
	}

}
