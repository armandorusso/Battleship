//Made by Armando Russo (40076164)
//COMP 249
//Assignment #1
//Due Date: September 24, 2018
//This is a Battleship simulation where you are facing a computer opponent. The winner is declared once all ships are sunk
//Have fun and enjoy the game!

import java.util.Scanner;

public class BattleshipDriver {

	public static void main(String[] args) {
		BattleshipMethods grid = new BattleshipMethods();
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Hi, let's play Battleship!");
		System.out.println();
		int number = 1;
		int i = 0;
		int j = 0;
		while (i < 6) { // user enters their ship coordinates
			System.out.print("Enter the coordinates of your ship #" + number + ": ");
			String coordinate = keyboard.next();
			coordinate.toLowerCase(); // turning everything to lower case so if the user types capital or what not,
										// the method i am calling can easily distinguish both
			boolean isLong = grid.countInput(coordinate); // this is so that if the user types a big coordinate, itll
															// automatically be out of bounds
			if (isLong == true) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}
			boolean valid = grid.isOut(coordinate); // checking if the number they enter is greater than 8
			if (valid == false) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			} else {
				grid.isRow(coordinate);
				grid.isCol(coordinate);
				boolean tileCheck = grid.checkTile();
				// System.out.println(tileCheck); just to check if my program was working
				if (tileCheck == true) { // if the user already entered these coordinates
					System.out.println("sorry, coordinates already being used. try again.");
					continue;
				} else {
					grid.setShip();
					i++; // increment only if they put a valid coordinate
					number++;
				}
			}
		}
		System.out.println();
		number = 1;
		while (j < 4) { // user now enters grenade coordinates
			System.out.print("Enter the coordinates of your grenade #" + number + ": ");
			String coordinate2 = keyboard.next();
			coordinate2.toLowerCase(); // using this to make my method easily distinguish between capital and not
										// capital
			boolean isLong = grid.countInput(coordinate2); // if they enter a huge coordinate, make them retype it
			if (isLong == true) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}
			boolean valid = grid.isOut(coordinate2); // if the coordinate is out of bounds
			// System.out.println(valid);
			if (valid == false)
				System.out.println("sorry, coordinates out of the grid. try again.");
			else { // if all of these conditions are not met, then use the users input and put it
					// in the grid
				grid.isRow(coordinate2);
				grid.isCol(coordinate2);
				boolean tileCheck = grid.checkTile();
				//System.out.println(tileCheck); //seeing if it works...
				if (tileCheck == true) {
					System.out.println("sorry, coordinates already being used. try again.");
					continue;
				} else {
					grid.setGrenade();
					j++; // increment only if they put a valid coordinate
					number++;
				}
			}
		}
		System.out.println();
		// now im setting the coordinates for the computer
		int n = 0;
		int m = 0;
		while (n < 6) { // still using loops for ships and grenades because I don't want the computer to
						// enter a coordinate thats already being used or if the computer already
						// entered that coordinate
			grid.randomRow();
			grid.randomCol();
			boolean check = grid.checkTile();
			if (check == true) {
				continue;
			} else {
				grid.setCShip();
				n++;
			}
		}

		while (m < 4) { // same as above, but this is for grenades
			grid.randomRow();
			grid.randomCol();
			boolean check = grid.checkTile();
			if (check == true) {
				continue;
			} else {
				grid.setCGrenade();
				m++;
			}
		}
		BattleshipMethods gridCopy = new BattleshipMethods(grid); // now, im making direct copy of the object so that
																	// when a winner is declared, i can print out the
																	// grid easily and since i have to print a
																	// completely new grid at the start, i use this copy
																	// to determine if the user entered a coordinate
																	// where it hits a ship or grenade
		//grid.board(gridCopy); // checking to see if it worked...
		grid.restartGrid(); // completely deleting the initial grid since i already made a copy. im using
							// the initial object to print out a new fresh grid.
		System.out.println();
		System.out.println("OK, the computer placed its ships and grenades at random. Let's play.");
		System.out.println();
		int turns = 0;

		while (turns < 1000) { // now its time to play the game
			System.out.print("position of your rocket: "); // user enters a coordinate
			String pcoordinate = keyboard.next();
			boolean isLong = grid.countInput(pcoordinate); // see if they enter a coordinate way out of bounds
			if (isLong == true) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}
			boolean valid = grid.isOut(pcoordinate); // see if the user enters a coordinate out of bounds
			if (valid == false) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			} else { // otherwise, set the coordinates they entered
				grid.isRow(pcoordinate);
				grid.isCol(pcoordinate);
				boolean tileCheck = grid.alreadyEnt(); // checking if the user already entered these coordinates
				// System.out.println(tileCheck); just to check if my program was working
				if (tileCheck == true) {
					System.out.println("position already called.");
					grid.board();
					continue;
				} else { // otherwise, launch a rocket
					grid.launchRocket(gridCopy);
					boolean hitGrenade = grid.hitGrenade(gridCopy);
					if(hitGrenade == true) {
						grid.randomMove(gridCopy);
						grid.launchRocket(gridCopy);
					}

				}
				grid.randomMove(gridCopy); // now its the computer's turn
				grid.launchRocket(gridCopy);
				boolean hitG = grid.hitGrenade(gridCopy);
				if (hitG == true) { // if the computer hits a grenade, his turn must be skipped, so ask the user for
									// his coordinate twice. under here will be the first time
					while (turns < 1000) {
						System.out.print("position of your rocket: ");
						String p2coordinate = keyboard.next();
						boolean isLonge = grid.countInput(p2coordinate);
						if (isLonge == true) {
							System.out.println("This coordinate is out of bounds. Please enter another coordinate.");
							continue;
						}
						boolean out = grid.isOut(p2coordinate);
						if (out == false) {
							System.out.println("This coordinate is out of bounds. Please enter a valid coordinate.");
							continue;
						} else {
							grid.isRow(p2coordinate);
							grid.isCol(p2coordinate);
							boolean tileCheck2 = grid.alreadyEnt();
							if (tileCheck2 == true) {
								System.out.println("position already called");
								grid.board();
								continue;
							} else {
								grid.launchRocket(gridCopy);
								boolean hitGrenadeE = grid.hitGrenade(gridCopy);
								if(hitGrenadeE == true) {
									grid.randomMove(gridCopy);
									grid.launchRocket(gridCopy);
								}
							}

						}
						break;
					}
				}

			}
			turns++; // increment the number of turns
		}
	}
}
