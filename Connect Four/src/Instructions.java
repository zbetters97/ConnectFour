import java.util.Scanner;

public class Instructions {
	
	static Scanner input = new Scanner (System.in);
	
	public static void instructions() {
		
		System.out.println("\nABOUT:\nThis game is called Connect Four. "
				
				+ "This is a computer only version of the game 'Connect Four.'\n"
				+ "Players are presented with a 6x7 board. Each column on the board is assigned a number 1-7.\n"
				+ "Players will take turns entering their assigned color ('R' or 'Y') into the columns.\n"
				+ "When a column is chosen, the color 'drops' into that column. "
				+ "In other words, it will be inserted to the lowest spot in that column.\n\n"
				
				
				+ "If, at any point in the game, a straight line can connect four of the same color "
						+ "then the player with that color wins.\n"
						+ "For example, 4 Y's across the bottom results in a victory for player 2.\n"
						+ "4 R's vertically results in a victory for player 1.\n"
						+ "Unlike the real game, 4 colors aligned diagnally does not count as a victory. "
				+ "If all slots on the board are filled without this happening, then the game ends in a draw\n\n"
			
				
				+ "ONE PLAYER MODE:\nThe user goes first and types in a number from 1-7 to decide "
					+ "which colum their color gets dropped in (the player to go first will always use symbol 'R').\n"
				+ "After the player decides, the computer will perform the same action.\n"
				+ "Both the player and the computer will swap turns until either "
					+ "four colors align or all slots are filled.\n\n"
				
					
				+ "TWO PLAYER MODE:\nThe user to go first is decided by the players themselves.\n"
				+ "Each player will take a turn entering a number between 1-7 to drop their color in a column.\n"
				+ "Whoever aligns 4 of their their colors wins, and if all slots are "
					+ "filled without this happening, it's a draw.\n"
					
				+ "\nHIT THE ENTER KEY TO EXIT INSTRUCTIONS");
		
		input.nextLine();
	}
}