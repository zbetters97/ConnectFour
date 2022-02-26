import java.util.Scanner;

public class TwoPlayer {
	
	static Scanner input = new Scanner(System.in);
	public static String[][] array;
	public static int choice;
	public static int winner;
	
	
	
	public static void play() throws InterruptedException {
		
		System.out.println("\nYou have chosen TWO PLAYER MODE");
		
		winner = 0; 																		//0 = no one, 1 = player 1, 2 = player 2 
		array = new String[6][7]; 															//two dimensional array created to hold board values
		
		for (int i = 0; i < array.length; i++) {	
										
	        for (int j = 0; j < array[i].length; j++) 
	              array[i][j] = "o";  		              
		}
		
		printBoard();																		
		
		for (int i = 0; i < 42; i++) { 													//loop for all spots on board, or until there is a winner
		
			if (i % 2 == 0) 																//player 1 is even, player 2 is odd
				playerTurn(1, "R");			
			else 
				playerTurn(2, "Y");
			
			printBoard();	
			
			isWinner(); 																	//ask if there is a winner after each turn
			
			if (winner != 0) 																//if there is, stop the loop
				break;
		}
		
		if (winner == 0)
			System.out.println("It's a draw!");
		else if (winner == 1)
			System.out.println("Player 1, you are the winner!");
		else if (winner == 2)
			System.out.println("Player 2, you are the winner!");
		
		input.nextLine(); input.nextLine();
	}
	
	
	
	public static void playerTurn(int playerNum, String playerVal) {
		
		System.out.println("TURN: Player " + playerNum);
		
		while (!input.hasNextInt()) {
			System.out.println("Enter a number for your column of choice:");
			input.next();
		}

		choice = input.nextInt();
		
		while (!isValid()) {
			
			while (!input.hasNextInt()) {
				System.out.println("Enter a number for your column of choice:");
				input.next();
			}		
			
			choice = input.nextInt();		
		}
		
		dropPiece(playerVal);
	}
	
	
	
	public static void dropPiece(String value) {
		
		for (int i = 5; i >= 0; i--) { //search through the board and drop piece
			
			if (!array[i][choice-1].contains("R") && !array[i][choice-1].contains("Y")) { 
				array[i][choice-1] = value;		
				break;
			}
		}
	}
	
	
	
	public static boolean isValid() {
		
		if (choice > 7 || choice < 0) {
			System.out.println("This column does not exist! Please select a different one.");
			return false;
		}
		else if (!isEmpty()) { 																	//if column is full, isValid is false
			System.out.println("This column is already full! Please select a different one.");
			return false;
		}
		else
			return true;	
	}
	
	
	
	public static boolean isEmpty() {
		
		int counter = 0;
		
		for (int i = 5; i >= 0; i--) {

			if (array[i][choice-1].equals("R") || array[i][choice-1].equals("Y") ) { 			//ask if selected spot already has color
				counter++;
				
				if (counter == 6) 																//if each slot is checked, isEmpty is false
					return false;	
			}		
			else 
				return true;							
		}
		
		return false;
	}
	
	
	
	public static void isWinner() {
		
		String result = "";
		
		for (int v = 0; v <= 3; v++) {
			
			for (int r = 0; r < array.length; r++) {
				
		        for (int c = v; c <= v+3; c++) 
		        	result += array[r][c];	    
		        
		        if (checkWin(result) != 0) 
		        	winner = checkWin(result);
		        
		        result = "";
			}
		}
		
		result = "";
		
		for (int v = 0; v <= 2; v++) {
			
			for (int c = 0; c <= 6; c++) {
				
		        for (int r = 5; r >= 2; r--) 
		        	result += array[r][c];	       
		        	     
		        if (checkWin(result) != 0) 
		        	winner = checkWin(result);
		        
		        result = "";
			}
		}	
	}
	
	
	
	public static int checkWin(String result) {
		
		if (result.equals("RRRR")) 																		//if four colors in a row
			return 1; 																					//player wins
		else if (result.equals("YYYY"))
			return 2; 																					//computer wins
		else
			return 0; 																					//no winner
	}	
	
	
	
	public static boolean isOpen(String slot) {
		
	    boolean isOpen = true;

	    if (slot == "R" || slot == "Y")  																//if extra slot is taken, isOpen is false
	    	isOpen = false;         	

	    return isOpen;
	}	
	
	
	
	public static void printBoard() { 									
		
		System.out.println();
		System.out.println("1\t2\t3\t4\t5\t6\t7");
		
		for (int i = 0; i < array.length; i++) {
			
	        for (int j = 0; j < array[i].length; j++) 
	            System.out.print(array[i][j] + "\t");		    
	        	        
	        System.out.println("\n");
		}
	}
}