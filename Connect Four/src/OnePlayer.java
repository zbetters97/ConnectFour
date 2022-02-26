import java.util.ArrayList;
import java.util.Scanner;

public class OnePlayer {
	
	static Scanner input = new Scanner(System.in);
	public static String[][] array;
	public static int choice;
	public static int winner;
	
	
	
	/** DRIVER METHOD **/
	public static void play() throws InterruptedException {
		
		System.out.println("\nYou have chosen ONE PLAYER MODE");
		winner = 0;
		
		array = new String[6][7]; 															//2D array to store board spaces
		for (int i = 0; i < array.length; i++) {					
			
	        for (int j = 0; j < array[i].length; j++) 										//assign each space in board "o"
	              array[i][j] = "o";             
	        
		}
		
		printBoard(); 

		for (int i = 0; i < 42; i++) { 													//loop through each space on board
		
			if (i % 2 == 0) 
				playerTurn();			
			else 
				computerTurn();
			
			printBoard();	
			
			isWinner(); 			
			
			if (winner != 0) 																//if someone wins (winner 1 or 2)
				break;	
		}
		
		if (winner == 0)											
			System.out.println("It's a draw!");
		else if (winner == 1)
			System.out.println("Player 1, you are the winner!");
		else if (winner == 2)
			System.out.println("Computer, you are the winner!");
		
		input.nextLine(); input.nextLine();
	}
	
	
	
	/** PLAYER ONE TURN METHOD **/
	public static void playerTurn() throws InterruptedException {							

		System.out.println("TURN: Player 1");
			
		while (!input.hasNextInt()) { 														//cycle until player enters an integer
			System.out.println("Enter a number for your column of choice:");
			input.next();
		}

		choice = input.nextInt();
		
		while (!isValid()) { 																//cycle until player enters valid integer
			
			while (!input.hasNextInt()) {
				System.out.println("Enter a number for your column of choice:");
				input.next();
			}		
			
			choice = input.nextInt();		
		}
		
		dropPiece("R"); 																	//insert red piece in board
	}
	
	
	
	/** CHECK IF COLUMN SELECTION IS VALID METHOD **/
	public static boolean isValid() {
		
		if (choice > 7 || choice < 1) { 													//ask if selection corresponds to column 
			System.out.println("This column does not exist! Please select a different one.");
			return false;
		}
		else if (!isEmpty()) { 																//ask if selected column is full
			System.out.println("This column is already full! Please select a different one.");
			return false;
		}
		else
			return true;	
	}
	
	
	
	/** CHECK IF SELECTED COLUMN IS EMPTY METHOD **/
	public static boolean isEmpty() {
		
		int counter = 0;
		
		for (int i = 5; i >= 0; i--) { 

			if (array[i][choice-1].equals("R") || array[i][choice-1].equals("Y") ) { 
				counter++;
				
				if (counter == 6) 
					return false; 
			}
		
			else 
				return true;							
		}
		
		return false;
	}
	
	
	
	/** FILL BOARD METHOD **/
	public static void dropPiece(String value) {
		
		for (int i = 5; i >= 0; i--) { 														//cycle through all spaces in selected column
			
			if (!array[i][choice-1].contains("R") && !array[i][choice-1].contains("Y")) { 	//if space is not taken, fill space and end method
				array[i][choice-1] = value;		
				break;
			}																				/*no alt needs to be coded since method is not called
																								unless pre-req are met*/
		}
	}
	
	
	
	/** COMPUTER TURN METHOD **/
	public static void computerTurn() throws InterruptedException {
		
		System.out.println("TURN: Computer");
		
		Thread.sleep(1000); 																//pretend computer is thinking

		if (newChoice() != 0) 																//if potential winning spot is found, computer uses this choice
			choice = newChoice();		
		
		else {
			choice = 1 + (int)(Math.random() * ((7 - 1) + 1));								//otherwise generate random number between 1-7
			
			while (!isValid())  															//loop until random number selects a valid column
				choice = 1 + (int)(Math.random() * ((7 - 1) + 1));
		}		
		
		dropPiece("Y");
		
		System.out.println("Computer has chosen column " + choice);							/*it may get confusing for the player to see 
																								what column the computer chose*/
	}		
	
	
	
	/** CHECK IF PLAYER OR COMPUTER WON METHOD **/ 
	public static void isWinner() {
		
		String result = "";																//result store color combinations
		
		for (int n = 0; n <= 3; n++) { 														//loops through horizontal winning combinations
			
			for (int r = 0; r < array.length; r++) {										//rows get incremented first
				
		        for (int c = n; c <= n+3; c++) 												//columns get incremented next
		        	result += array[r][c]; 													
		        
		        if (checkWin(result) != 0) 													//if result holds winning combination 
		        	winner = checkWin(result);												
		        
		        result = "";																//results get reset
			}
		}

		for (int n = 0; n <= 2; n++) { 														//loops through vertical winning combinations
			
			for (int c = 0; c <= 6; c++) {													//therefore columns get incremented first
				
		        for (int r = 5; r >= 2; r--) 												//then rows
		        	result += array[r][c];	       
		        	     
		        if (checkWin(result) != 0) 
		        	winner = checkWin(result);
		        
		        result = "";
			}
		}	
	}
	public static int checkWin(String result) {
		
		if (result.equals("RRRR")) 															//ask if sent result is all one color
			return 1;
		else if (result.equals("YYYY"))
			return 2; 																		//1 = player 1, 2 = computer, 0 = draw
		else
			return 0; 
	}	
	
	
	
	/** NEW CHOICE FOR COMPUTER TO STOP POTENTIAL WIN METHOD **/
	public static int newChoice() {
		
		String result = "";
		ArrayList<Integer> firstSpot = new ArrayList<>();									//firstSpot holds the first entry in the potential winning row
		
		for (int n = 0; n <= 3; n++) { 														//loop through horizontal winning combinations
			
			for (int r = 0; r < array.length; r++) {
							
		        for (int c = n; c <= n+3; c++) {
		        	
		        	firstSpot.add(c);      													//firstSpot receives each spot so ordered from first to last
		        	result += array[r][c];			        		        		        	 	
		        	
		        	if (canWin(result) && isOpen(result))  									//if potential winning row and fourth spot is open
			        	return firstSpot.get(0) + whatColumn(result); 						//column selection is first entry the row + what column 
		        }   																			//the empty spot is located in
		        	    																	//example: if first spot is column 2 and open spot is the third spot,	        
		        firstSpot.clear();																//then selection is 5 because 2+3=5
		        result = "";
			}
		}
		
		for (int n = 0; n <= 2; n++) {														//this section stops potential winning columns not rows
			
			for (int c = 0; c <= 6; c++) {
				
		        for (int r = 5; r >= 2; r--) {
		        	
		        	result += array[r][c];	 
		        	
		        	if (canWin(result) && isOpen(result))     								
		        		return c + 1;		    				   	        				//return c+1 because numbers are -1 in an array
		        }	        	     
		        
		        result = "";
			}
		}
		
		return 0;																			//return 0 if no potential winning rows are found
	}
	
	
	
	/** CHECK IF CAN WIN ON NEXT TURN METHOD **/
	public static boolean canWin(String result) {
		
		if (result.length() == 4 && (result.matches(".*R.*R.*R.*") || result.matches(".*Y.*Y.*Y"))) 
			return true; 																	//asks if there are 3 R's or Y's found in a row or column
		else
			return false;
	}	

	
	
	/** CHECK WHICH COLUMN THE OPEN SPOT IS IN METHOD **/
	public static int whatColumn(String result) {
				
		int position = 1;
		
		for (char c : result.toCharArray()) {												//cycle through each spot in given potential winning pattern
						
            if (!(c == 'R') && !(c == 'Y')) 												//if an empty spot is found, send its position 
            	return position;  
            
            position++; 
        }		
		
		return 0; 																			
	}
	
	/** CHECK IF FOURTH SPOT ISN'T TAKEN METHOD **/
	public static boolean isOpen(String result) {   										//isOpen is similar to whatColumn except it only 
																								//asks if there is an empty spot (true or false)
	    boolean isOpen = false;

		for (char c : result.toCharArray()) {
						
            if (!(c == 'R') && !(c == 'Y')) 
            	isOpen = true;                
        }	
		
	    return isOpen;
	}
	
	
	
	/** DISPLAY BOARD METHOD **/
	public static void printBoard() { 
		
		System.out.println();
		System.out.println("1\t2\t3\t4\t5\t6\t7");
		
		for (int r = 0; r < array.length; r++) { 
			
	        for (int c = 0; c < array[r].length; c++) 
	            System.out.print(array[r][c] + "\t");		    
	        	        
	        System.out.println("\n");
		}
	}
}