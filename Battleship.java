// -------------------------------------------------------
// Wu Wen Tang 40028075
// Kasthurie Paramasivampillai 40025088
// COMP249
// Assignment 1
// Due Date February 1, 2017
// --------------------------------------------------------

import java.util.Scanner;
/**
 * This program simulates a variation of the game BATTLESHIP 
 * between a human player and the computer.
 * 
 * @author Wu Wen Tang, Kasthurie Paramasivampillai
 * @version 1.4
 */
public class Battleship {

	Scanner input = new Scanner(System.in);
	boolean isValid;
	
	private static int playerHits = 0;
	private static int compHits = 0;
	private static int turn = 2;
	private static int missedTurnsPlayer = 0;
	private static int missedTurnsComp = 0;

	public static boolean hideCoordinates = true;

	/**
	 * Initializes board so that every place is an underscore.
	 * @param board 8x8 2D array for storing strings 
	 */
	
	public static void createBoard(String[][] board){

		for(int row = 0; row< board.length; row++){
			for (int column = 0; column<board[0].length; column++){

				board [row][column] = "_";
			}
		}
	}

	/**
	 * Prints out board for game play
	 * At first, hides ship & grenade coordinates
	 * Then display board after ships & grenades have been hit
	 * @param board 8x8 2D array for storing strings 
	 */
	public static void showBoard(String[][] board){

		// inverse row & column to get correct orientation for rows & columns
		// display/hide coordinates for coding/playing
		
		for (int column = 0; column <board.length; column++){
			if (hideCoordinates == false){
				for (int row = 0; row < board[0].length; row++){
					System.out.print(" " + board[row][column]);
				}	
				System.out.println("");

			}
			else{
				for (int row = 0; row < board[0].length; row++){
					if(board[row][column].equals("S")){
						System.out.print(" " + "_");
					}
					else if(board[row][column].equals("G")){
						System.out.print(" " + "_");
					}
					else if(board[row][column].equals("s")){
						System.out.print(" " + "_");
					}
					else if(board[row][column].equals("g")){
						System.out.print(" " + "_");
					}
					else if(board[row][column].equals("A")){
						System.out.print(" " + "S");
					}
					else if(board[row][column].equals("B")){
						System.out.print(" " + "s");
					}
					else if(board[row][column].equals("C")){
						System.out.print(" " + "G");
					}
					else if(board[row][column].equals("D")){
						System.out.print(" " + "g");
					}
					else if(board[row][column].equals("*")){
						System.out.print(" " + "*");
					}
					else{
						System.out.print(" " + board[row][column]);
					}
				} System.out.println("");
			} 
		}
	}	

	/**
	 * Asks user to place 6 ships while making sure that the input is valid.
	 * @param board 8x8 2D array for storing strings  
	 * @param numCountShip number of ships need to be placed
	 */
	public static void createShipHuman(String[][] board, int numCountShip){

		Scanner input = new Scanner(System.in);
		String shipCoordinate;
		int row, column;

		for (int i=1; i<=numCountShip; i++){

			System.out.print("Enter the coordinates of your ship #" + i + ": ");
			shipCoordinate = input.next();

			row = (shipCoordinate.charAt(0) - 'A'); 
			column = (shipCoordinate.charAt(1) - '1');

			if ((shipCoordinate.charAt(0) < 'A') || 
					(shipCoordinate.charAt(0) > 'H') || 
					(shipCoordinate.charAt(1)) < '1' || 
					(shipCoordinate.charAt(1) > '8')){
				System.out.println("Sorry, coordinates outside the grid. Try again.");
				i--;
			} 
			else if (shipCoordinate.length() != 2){
				System.out.println("Sorry, coordinates are invalid. Try again.");
				i--;
			}
			else if (board[row][column] == "s"){
				System.out.println("Sorry, coordinates already used. Try again.");
				i--;	
			}
			else{ 
				System.out.print("");
				board[row][column] = "s";
			} 
		}
	}

	/**
	 * Asks user to place 4 grenades while making sure that the input is valid.
	 * @param board 8x8 2D array for storing strings 
	 * @param numCountGrenade number of grenades needed to be placed
	 */
	public static void createGrenadeHuman(String[][] board, int numCountGrenade){
		Scanner input = new Scanner(System.in);
		String shipCoordinate;
		int row, column;

		for (int i=1; i<=numCountGrenade; i++){

			System.out.print("Enter the coordinates of your grenade #" + i + ": ");
			shipCoordinate = input.next();

			row = (shipCoordinate.charAt(0) - 'A'); 
			column = (shipCoordinate.charAt(1) - '1');

			if ((shipCoordinate.charAt(0) < 'A') || 
					(shipCoordinate.charAt(0) > 'H') || 
					(shipCoordinate.charAt(1)) < '1' || 
					(shipCoordinate.charAt(1) > '8')){
				System.out.println("Sorry, coordinates outside the grid. Try again.");
				i--;
			} 
			else if (shipCoordinate.length() != 2){
				System.out.println("Sorry, coordinates are invalid. Try again.");
				i--;
			}
			else if (board[row][column] == "s"){
				System.out.println("Sorry, coordinates already used. Try again.");
				i--;
			} 
			else if (board[row][column] == "g"){
				System.out.println("Sorry, coordinates already used. Try again.");
				i--;
			} 
			else{ 
				System.out.print("");
				board[row][column] = "g";
			} 
		}
	}

	/**
	 * Places 6 ships at positions with randomly generated coordinates.
	 * @param number of ships needed to be placed 
	 */
	public static void createShipComp(String[][] board){

		//int numRow = 8, numColumn = 8;
		//boolean coordOverlap = false;

		for (int numShip=0; numShip<6; numShip++){
			//if (Math.random() < 20 /* boolean*/ ){
			int row = (int) (Math.random() * board.length);
			int column = (int) (Math.random() * 8);

			if ((board[row][column] == "s") || 
					(board[row][column] == "g") || 
					(board[row][column] == "S")){
				numShip--;
			} 
			else{			
				board [row][column]	= "S";
			}
		}
	}

	/**
	 * Places 6 grenades at positions with randomly generated coordinates.
	 * @param board 8x8 2D array for storing strings 
	 */
	public static void createGrenadeComp(String[][] board){

		for (int numGrenade=0; numGrenade<4; numGrenade++){

			int row = (int) (Math.random() * 8);
			int column = (int) (Math.random() * 8);

			if ((board[row][column] == "s") || 
					(board[row][column] == "g") || 
					(board[row][column] == "S") ||
					(board[row][column] == "G")){
				numGrenade--;
			}
			else{
				board [row][column]	= "G";
			}
		}
	}
	
	/**
	 * Shoots a rocket at a position based on user input.
	 * @param board 8x8 2D array for storing strings 
	 */
	public static void playerRocket(String[][] board){

		Scanner input = new Scanner(System.in);
		String shipCoordinate;
		int row, column;

		System.out.println("\nPosition of your rocket: ");
		shipCoordinate = input.next();

		row = (shipCoordinate.charAt(0) - 'A'); 
		column = (shipCoordinate.charAt(1) - '1');

		if (shipCoordinate.length() != 2){
			System.out.println("Sorry, coordinates are invalid. Try again.");
			showBoard(board);
			System.out.println("\nPosition of your rocket: ");
			shipCoordinate = input.next();
		}
		while ( (shipCoordinate.charAt(0) < 'A') || 
				(shipCoordinate.charAt(0) > 'H') || 
				(shipCoordinate.charAt(1) < '1') || 
				(shipCoordinate.charAt(1) > '8') ){
			System.out.println("Sorry, coordinates outside the grid. Try again.");		
			System.out.println("\nPosition of your rocket: ");	
			shipCoordinate = input.next();
			row = (shipCoordinate.charAt(0) - 'A'); 
			column = (shipCoordinate.charAt(1) - '1');
		} 
		if ( 	(board[row][column].equals("A")) ||
				(board[row][column].equals("B")) ||
				(board[row][column].equals("C")) ||
				(board[row][column].equals("D")) ||
				(board[row][column].equals("*"))){
			System.out.println("Position already called.");
			showBoard(board);
			System.out.println("\nPosition of your rocket: ");
			shipCoordinate = input.next();
			showBoard(board);
		}
		else if (board[row][column].equals("S")){
			System.out.println("Ship hit.");
			board[row][column] = "A";
			playerHits++;
			turn++;
		}
		else if (board[row][column].equals("s")){
			System.out.println("Own ship hit.");
			board[row][column] = "B";
			compHits++;
			turn++;
		}
		else if (board[row][column].equals("G")){
			System.out.println("Boom! Grenade.");
			board[row][column] = "C";
			missedTurnsPlayer++;
			showBoard(board);
			compRocket(board);
		}
		else if (board[row][column].equals("g")){
			System.out.println("Boom! Own grenade.");
			board[row][column] = "D";
			missedTurnsPlayer++;
			showBoard(board);
			compRocket(board);
		}
		else{ 
			System.out.println("Nothing.");
			board[row][column] = "*";
			turn++;
		}
	}
	
	/**
	 * Shoots a rocket at a position with randomly generated coordinates.
	 * @param board 8x8 2D array for storing strings 
	 */
	public static void compRocket(String[][] board){

		int row = (int) ((Math.random() * 8));
		int column = (int) (Math.random() * 8);

		int newRow = row + 65;
		char rowChar = (char) newRow;

		while ( (row < 1) || 
				(row > 8) || 
				(column < 1) || 
				(column > 8)){

			row = (int) ((Math.random() * 8));
			column = (int) (Math.random() * 8);
		}
		while (	(board[row][column].equals("A")) ||
				(board[row][column].equals("B")) ||
				(board[row][column].equals("C")) ||
				(board[row][column].equals("D")) ||
				(board[row][column].equals("*"))){
			// System.out.println("already called");
			row = (int) (Math.random() * 8);
			column = (int) (Math.random() * 8);
		}
		if (board[row][column].equals("s")){
			System.out.println("\nPosition of my rocket: " + rowChar + (column+1));	
			System.out.println("Ship hit.");
			board[row][column] = "B";
			compHits++;
			turn++;
		}
		else if (board[row][column].equals("S")){
			System.out.println("\nPosition of my rocket: " + rowChar + (column+1));	
			System.out.println("Own ship hit.");
			board[row][column] = "A";
			playerHits++;
			turn++;
		}
		else if (board[row][column].equals("g")){
			System.out.println("\nPosition of my rocket: " + rowChar + (column+1));	
			System.out.println("Boom! Grenade.");
			board[row][column] = "D";
			showBoard(board);
			playerRocket(board);
			missedTurnsComp++;
		}
		else if (board[row][column].equals("G")){
			System.out.println("\nPosition of my rocket: " + rowChar + (column+1));	
			System.out.println("Boom! Own grenade.");
			board[row][column] = "C";
			showBoard(board);
			playerRocket(board);
			missedTurnsComp++;
		} else{ 
			System.out.println("\nPosition of my rocket: " + rowChar + (column+1));	
			System.out.println("Nothing.");
			board[row][column] = "*";
			turn++;
		}
	}

	/**
	 * Displays result based on number of hits.
	 */
	public static void result(){

		if(playerHits>=6 && compHits<=6){
			System.out.println("You Win!");
		}
		else{
			System.out.println("You Lose.");
		}
		System.out.println("You missed " + missedTurnsPlayer + " turns.");
		System.out.println("The computer missed " + missedTurnsComp + " turns.");
	}

	/**
	 * Displays board at the end of the game.
	 * @param board 8x8 2D array for storing strings 
	 */
	public static void endBoard(String[][] board){
		for (int row = 0; row <board.length; row++){
				for (int column = 0; column < board[0].length; column++){
					if(board[row][column].equals("S")){
						System.out.print(" " + "S");
					}
					else if(board[row][column].equals("G")){
						System.out.print(" " + "G");
					}
					else if(board[row][column].equals("s")){
						System.out.print(" " + "s");
					}
					else if(board[row][column].equals("g")){
						System.out.print(" " + "g");
					}
					else if(board[row][column].equals("A")){
						System.out.print(" " + "S");
					}
					else if(board[row][column].equals("B")){
						System.out.print(" " + "s");
					}
					else if(board[row][column].equals("C")){
						System.out.print(" " + "G");
					}
					else if(board[row][column].equals("D")){
						System.out.print(" " + "g");
					}
					else if(board[row][column].equals("*")){
						System.out.print(" " + "_");
					}
					//else if(board[row][column].equals("_")){
					//	System.out.print(" " + "_");		
					//}
					else{
						System.out.print(" " + board[row][column]);
					}
				} System.out.println("");
			} 
		}
	
	/*
	 * Drives the program 
	 */
	public void Battleship(){
		System.out.println("Hi, let’s play Battleship!");

		String[][] board = new String[8][8];
		createBoard(board);

		// *** ship = 6, grenade = 4
		createShipHuman(board, 6);
		createGrenadeHuman(board, 4);

		createShipComp(board);
		createGrenadeComp(board);
		System.out.println("OK, the computer placed its ships and grenades at random. Let’s play.");

		do {
			if(turn % 2 == 0){
				playerRocket(board);
				showBoard(board);
			}
			else{
				compRocket(board);
				showBoard(board);
			}
			System.out.println("");
		} // hits < 6
		while (playerHits < 6 && compHits < 6);
		
		result();
		endBoard(board);
	}
	
}