/*
 * 
 * TO DO:-
 * - figure out alignment of chess board: black pawn, empty spaces, letter coordinates
 * 
 */

import java.util.Scanner;

public class ChessMain {

	private static boolean play = true;													// keeps game running
	
	public static void main(String[] args) {
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
	
	public static void printRules() {													// prints rules to play
		System.out.println("=== Game rules are as follows: ===\n");
		System.out.println("--- Goal of the Game: ---\n"
			+ "Putting the opponent's king in a checkmate - a position from\n"
			+ "which it is impossible to escape attack from your pieces.\n"
			+ "\n"
			+ "--- General Rules: ---\n"
			+ "- White always moves first. Movement is required.\n"
			+ "- With the exception of the knight, a piece may not move over any of the other pieces.\n"
			+ "- When a king is threatened with capture (but can protect himself/escape), it´s called check.\n"
			+ "- If a king is in check, then the player must eliminate the threat and cannot leave the king in check.\n"
			+ "- If a player isn't under check but has no legal moves, the game is in stalemate and ends in a draw.\n"
			+ "- Checkmate happens when a king is in check with no legal move to escape. This ends the game.\n"
			+ "\n"
			+ "--- Movement Rules: ---\n"
			+ "- King can move one vacant square in any direction. It may castle once per game.\n"
			+ "- Queen can move any number of vacant squares in any direction.\n"
			+ "- Rook can move any number of vacant squares vertically or horizontally. It also is moved while castling.\n"
			+ "- Bishop can move any number of vacant squares in any diagonal direction.\n"
			+ "- Knight can move one square along any rank/file and then 2 perpendicularly. Its movement can also be viewed as an L.\n"
			+ "- Pawns can move forward one vacant square. If not yet moved, it may move 2 vacant squares.\n"
			+ "  It cannot move backward. It kills diagonally forward. It can also perform en passant and promotion.\n"
			+ "- En Passant occurs when a pawn is moved 2 squares on its initial movement.\n"
			+ "  The opponent can take the moved pawn “en passant” as if it had only moved one square,\n"
			+ "  but only in the very next move from this opponent's play."
			+ "- If a pawn reaches the opponent's edge of the table, it  may be promoted to a queen, rook, bishop or knight.\n"
			+ "- During the castling, the king moves two squares towards the rook he intends to castle with,\n"
			+ "  and the rook moves to the square through which the king passed.\n"
			+ "  Castling is only permissible if neither king nor rook involved in castling may have\n"
			+ "  moved from the original position and there are no pieces between the rook and king.\n"
			+ "  The king may not currently be in check.\n"
			+ "");
		System.out.println("=== Program rules are as follows: ===\n");
		System.out.println("- Enter coordinates of pieces by first mentioning the row and then the column (without any spaces) and press 'return'.\n"
			+ "  Capitalisation does not matter.\n"
			+ "  For example: 2g, 4E, 8f, 1D\n"
			+ "- If a player's king is under checkmate, or if he wishes to give up, he may type in \"resign\" where he is\n"
			+ "  asked to enter the coordinates of the piece he wants to move.\n"
			+ "- If you wish to go back to selecting the initial coordinates of the piece, type \"back\" where you are to type\n"
			+ "  in the new coordinates.\n"
			+ "- If a game ends in a draw, any of the players can enter \"draw\" where he's asked to enter the coordinates of\n"
			+ "  the piece he wants to move. This call for draw will require a confirmation from the other player.\n"
			+ "  If the other player does not agree, the game will continue.\n"
			+ "- If there is a stalemate, the game will automatically end in a draw."
			+ "- These rules can be viewed any time by typing \"rules\" where players are asked to enter coordinates of the piece to move.");
	}

}