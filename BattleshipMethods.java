
//Made by Armando Russo (40076164)
//COMP 249
//Assignment #1
//Due Date: September 24, 2018
//This is the methods file, where the the main procedure of the Battleship game happens. 
//The more important mechanics are coded here.

import java.util.Random;

public class BattleshipMethods {
	private char grid[][] = new char[8][8];
	private int row;
	private int col;
	private int player1;
	private int player2;
	private boolean winner;

	public BattleshipMethods() {
		row = 0;
		col = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = '_';
			}
		}
	}

	public BattleshipMethods(BattleshipMethods copyGrid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = copyGrid.grid[i][j];
			}
		}
	}

	public int isRow(String coordinate) {
		switch (coordinate.charAt(0)) {
		case 'A':
		case 'a':
			row = 0;
			break;
		case 'B':
		case 'b':
			row = 1;
			break;
		case 'C':
		case 'c':
			row = 2;
			break;
		case 'D':
		case 'd':
			row = 3;
			break;
		case 'E':
		case 'e':
			row = 4;
			break;
		case 'F':
		case 'f':
			row = 5;
			break;
		case 'G':
		case 'g':
			row = 6;
			break;
		case 'H':
		case 'h':
			row = 7;
			break;

		}

		return (row);
	}

	public int isCol(String coordinate) {
		switch (coordinate.charAt(1)) {
		case '1':
			col = 0;
			break;
		case '2':
			col = 1;
			break;
		case '3':
			col = 2;
			break;
		case '4':
			col = 3;
			break;
		case '5':
			col = 4;
			break;
		case '6':
			col = 5;
			break;
		case '7':
			col = 6;
			break;
		case '8':
			col = 7;
			break;

		}
		return (col);
	}

	public String toString() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		return "";
	}

	public void setShip() {
		grid[this.col][this.row] = 's';
	}

	public void setCShip() {
		grid[this.col][this.row] = 'S';
	}

	public void setGrenade() {
		grid[this.col][this.row] = 'g';
	}

	public void setCGrenade() {
		grid[this.col][this.row] = 'G';
	}

	public boolean isOut(String coordinate) {
		coordinate.toLowerCase();
		char first = coordinate.charAt(0);
		char second = coordinate.charAt(1);
		return (first >= 'A' && first <= 'H' && second >= '1' && second < '9');

	}

	public boolean checkTile() {
		return (grid[this.col][this.row] == 'G' || grid[this.col][this.row] == 'S' || grid[this.col][this.row] == 'g'
				|| grid[this.col][this.row] == 's');
	}

	public boolean countInput(String coordinate) {
		return (coordinate.length() > 2);
	}

	public int randomRow() {
		this.row = (int) (Math.random() * ((8 - 1) + 1) - 1);
		return row;
	}

	public int randomCol() {
		this.col = (int) (Math.random() * ((8 - 1) + 1) - 1);
		return col;
	}

	public void randomMove(BattleshipMethods gridCopy) {
		int k = 0;
		randomRow();
		randomCol();
		boolean valid = alreadyEnt();
		if (valid == true) {
			randomMove(gridCopy); // checking to see if the coordinate is already entered and if it is, then
									// randomize again
			return;
		}
		if (gridCopy.grid[this.col][this.row] == 'S' || gridCopy.grid[this.col][this.row] == 'G') {
			randomRow();
			randomCol();
		}
		switch (this.row) {
		case 0: {
			System.out.print("position of my rocket: A");
			break;
		}
		case 1: {
			System.out.print("position of my rocket: B");
			break;
		}
		case 2: {
			System.out.print("position of my rocket: C");
			break;
		}

		case 3: {
			System.out.print("position of my rocket: D");
			break;
		}
		case 4: {
			System.out.print("position of my rocket: E");
			break;
		}
		case 5: {
			System.out.print("position of my rocket: F");
			break;
		}
		case 6: {
			System.out.print("position of my rocket: G");
			break;
		}
		case 7: {
			System.out.print("position of my rocket: H");
			break;
		}
		}

		switch (this.col) {
		case 0: {
			System.out.println("1");
			break;
		}
		case 1: {
			System.out.println("2");
			break;
		}
		case 2: {
			System.out.println("3");
			break;
		}

		case 3: {
			System.out.println("4");
			break;
		}
		case 4: {
			System.out.println("5");
			break;
		}
		case 5: {
			System.out.println("6");
			break;
		}
		case 6: {
			System.out.println("7");
			break;
		}
		case 7: {
			System.out.println("8");
			break;
		}
		}

	}

	public void board() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void board(BattleshipMethods realGrid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < realGrid.grid[i].length; j++) {
				System.out.print(realGrid.grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean alreadyEnt() {
		return (grid[this.col][this.row] == '*' || grid[this.col][this.row] == 'G' || grid[this.col][this.row] == 'S'
				|| grid[this.col][this.row] == 's' || grid[this.col][this.row] == 'g');
	}

	public void launchRocket(BattleshipMethods realGrid) {
		if (realGrid.grid[this.col][this.row] == '_') {
			System.out.println("nothing.");
			grid[this.col][this.row] = '*';
			board();
		} else if (realGrid.grid[this.col][this.row] == 's' || realGrid.grid[this.col][this.row] == 'S') {
			System.out.println("ship hit.");
			this.grid[this.col][this.row] = realGrid.grid[this.col][this.row];
			if (realGrid.grid[this.col][this.row] == 'S') {
				this.player1++;
				if (this.player1 == 6) {
					System.out.println("You win!");
					board(realGrid);
					System.exit(0);
				}

			} else if (realGrid.grid[this.col][this.row] == 's') {
				this.player2++;
				if (this.player2 == 6) {
					System.out.println("You lose!");
					board(realGrid);
					System.exit(0);
				}
			}
			board();
		}

		else if (realGrid.grid[this.col][this.row] == 'g' || realGrid.grid[this.col][this.row] == 'G') {
			System.out.println("boom! grenade.");
			this.grid[this.col][this.row] = realGrid.grid[this.col][this.row];
			board();
		}
	}

	public void restartGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = '_';
			}
		}
	}

	public boolean hitGrenade(BattleshipMethods gridCopy) {
		return (gridCopy.grid[this.col][this.row] == 'G' || gridCopy.grid[this.col][this.row] == 'g');

	}

	public boolean equals(BattleshipMethods gridCopy) {
		return (this.grid == gridCopy.grid);
	}
}
