/*
 * 
 * TO DO:-
 * - figure out alignment of chess board: black pawn, empty spaces, letter coordinates
 * 
 */

import java.util.Scanner;
import java.io.*;

public class ChessMain {

	private static boolean play = true;													// keeps game running
	
	public static void main(String[] args) throws IOException {
		boolean turnPrinter = false;													// to not print turn color in repeated entries
		boolean turn = true; 															// true = white, false = black
		Scanner in = new Scanner(System.in);
		
		@SuppressWarnings("unused")
		Board board = new Board();														// chess board with static methods
		
		System.out.println("Welcome to Rajveer's Java Chess Program!");
		Board.display();
		
		while (play) {
			if (turnPrinter != turn) System.out.println("\n" + (turn? "White's turn -": "Black's turn -"));
			turnPrinter = turn;
			System.out.print("\n" + "Select coordinates of piece to move: ");			// ends game
			String selectInput = in.nextLine();
			if (selectInput.equalsIgnoreCase("RESIGN")) {
				setPlay(false);
				System.out.println((turn? "Black": "White") + " wins!");
				System.out.println("Program ended."); }
			else if (selectInput.equalsIgnoreCase("DRAW")) {							// ends game
				System.out.print((turn? "White": "Black") + " is calling for a draw. Does " + (turn? "Black": "White") + " agree? (type 'draw' if yes): ");
				if (in.nextLine().equalsIgnoreCase("DRAW")) {
					setPlay(false);
					System.out.println("The game ended in a draw.");
					System.out.println("Program ended.");
				}
				else System.out.println((turn? "Black": "White") + " did not agree. The game continues.");
			}
			else if (selectInput.equalsIgnoreCase("RULES")) printRules();				// prints rules
			else {
				if (Board.select(selectInput, turn)) {									// plays turn
					while (true) {
						System.out.print("\n" + "Select coordinates to move this piece to: ");
						String selectDestinationInput = in.nextLine();
						if (selectDestinationInput.equalsIgnoreCase("BACK")) break;		// goes back to initial coordinate selection
						else if (Board.selectDestination(selectDestinationInput)) {
							if (Board.isStalemate()) {									// checks for stale mate
								System.out.println( "\n" + (turn? "Black": "White") + " is in stalemate. The game ends in a draw.");
								setPlay(false);
								break;
							}
							turn ^= true; 												// changes turn color
							break;
						}
					}
				}
			}		
		}
		
		in.close();
	}
	
	public static void setPlay(boolean p) {												// setter for play
		play = p;
	}
	
	public static boolean getPlay() {													// getter for play
		return play;
	}
	
	public static void printRules() throws IOException {								// prints rules to play
		BufferedReader in = new BufferedReader(new FileReader("Rules.txt"));
		String s;
		while ((s = in.readLine()) != null) System.out.println(s);
		in.close();
	}

}