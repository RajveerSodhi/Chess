package src.model;

import src.ui.ChessMain;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	
	// attributes
	public static Piece[][] chessBoard = new Piece[8][8];			// 2D array of the chess board
	private static int currentRow, currentColumn, moves;			// row and column of piece to move. moves: number of moves made in the game
	private static Piece currentPiece;
	private static String currentCoordinates, newCoordinates;
	private static ArrayList<Piece> whiteAlive = new ArrayList<Piece>(16), blackAlive = new ArrayList<Piece>(16);		// lists of alive pieces
	private static Scanner in = new Scanner(System.in);
	private static boolean enPassant = false, castle = false;		// enPasasant checks if move made is en passant, castle checks for castling
	private static boolean whiteCheck = false, blackCheck = false;
	
	// constructor
	Board() { initialize(); }
	
	//  general methods
	private static void initialize() {															// sets the game board
		chessBoard[0][0] = new Rook(false, 0, 0);
		chessBoard[0][1] = new Knight(false, 0, 1);
		chessBoard[0][2] = new Bishop(false, 0, 2);
		chessBoard[0][3] = new Queen(false, 0, 3);
		chessBoard[0][4] = new King(false, 0, 4);
		chessBoard[0][5] = new Bishop(false, 0, 5);
		chessBoard[0][6] = new Knight(false, 0, 6);
		chessBoard[0][7] = new Rook(false, 0, 7);
		chessBoard[1][0] = new Pawn(false, 1, 0);
		chessBoard[1][1] = new Pawn(false, 1, 1);
		chessBoard[1][2] = new Pawn(false, 1, 2);
		chessBoard[1][3] = new Pawn(false, 1, 3);
		chessBoard[1][4] = new Pawn(false, 1, 4);
		chessBoard[1][5] = new Pawn(false, 1, 5);
		chessBoard[1][6] = new Pawn(false, 1, 6);
		chessBoard[1][7] = new Pawn(false, 1, 7);
		
		for (int r = 0; r < 2; r++) for (int c = 0; c < 8; c++)									// creates list of alive players
		blackAlive.add(chessBoard[r][c]);
		
		chessBoard[7][0] = new Rook(true, 7, 0);
		chessBoard[7][1] = new Knight(true, 7, 1);
		chessBoard[7][2] = new Bishop(true, 7, 2);
		chessBoard[7][3] = new Queen(true, 7, 3);
		chessBoard[7][4] = new King(true, 7, 4);
		chessBoard[7][5] = new Bishop(true, 7, 5);
		chessBoard[7][6] = new Knight(true, 7, 6);
		chessBoard[7][7] = new Rook(true, 7, 7);
		chessBoard[6][0] = new Pawn(true, 6, 0);
		chessBoard[6][1] = new Pawn(true, 6, 1);
		chessBoard[6][2] = new Pawn(true, 6, 2);
		chessBoard[6][3] = new Pawn(true, 6, 3);
		chessBoard[6][4] = new Pawn(true, 6, 4);
		chessBoard[6][5] = new Pawn(true, 6, 5);
		chessBoard[6][6] = new Pawn(true, 6, 6);
		chessBoard[6][7] = new Pawn(true, 6, 7);
		
		for (int r = 6; r < 8; r++) for (int c = 0; c < 8; c++)
			whiteAlive.add(chessBoard[r][c]);
	}
		
	public static void display() {																// prints the chess board
		System.out.println();																	// **!! MISALIGNMENT & SPACING ERRORS !!**
		System.out.println("      A   B   C   D   E   F   G   H");
		System.out.println("    +-----------------------------------+");
		for (int row = 0; row < 8; row++) {
			System.out.print("  " + (8 - row) + " | ");
			for (int column = 0; column < 8; column++) {
				System.out.print(((chessBoard[row][column] != null)? chessBoard[row][column].getRep(): (char) 0x3000) + " | ");
			}
			System.out.println((8 - row));
			if (row < 7) System.out.println("    |-----------------------------------|");
		}
		System.out.println("    +-----------------------------------+");
		System.out.println("      A   B   C   D   E   F   G   H");
	}
		
	private static void pawnUpgrade(String pieceType, int row, int column, boolean color) {		// upgrades pawn that reaches end of board
		if (pieceType.equalsIgnoreCase("BISHOP")) {
			System.out.println("src.model.Pawn upgraded to src.model.Bishop.");
			chessBoard[row][column] = new Bishop(color, row, column);
		}
		else if (pieceType.equalsIgnoreCase("ROOK")) {
			System.out.println("src.model.Pawn upgraded to src.model.Rook.");
			chessBoard[row][column] = new Rook(color, row, column);
		}
		else if (pieceType.equalsIgnoreCase("KNIGHT")) {
			System.out.println("src.model.Pawn upgraded to src.model.Knight.");
			chessBoard[row][column] = new Knight(color, row, column);
		}
		else if (pieceType.equalsIgnoreCase("QUEEN")) {
			System.out.println("src.model.Pawn upgraded to src.model.Queen.");
			chessBoard[row][column] = new Queen(color, row, column);
		}
		else {
			if (pieceType.equalsIgnoreCase("KING")) System.out.print("Cannot upgrade src.model.Pawn to src.model.King. Try again: ");
			else if (pieceType.equalsIgnoreCase("PAWN")) System.out.print("The piece is already a pawn. Try again: ");
			else System.out.print("Invalid input. Try again: ");
			pawnUpgrade(in.nextLine(), row, column, color);
		}
	}
	
	// helper methods
	public static boolean select(String coordinates, boolean turn) {							// helper method
		try {
			int row = 8 - Integer.parseInt(String.valueOf(coordinates.charAt(0)));
			int column = (int) coordinates.toUpperCase().charAt(1) - 65; 
			if (row < 0 || row > 7) {
				System.out.println("Invalid row. Try again.");
				return false;
			}
			if (column < 0 || column > 7) {
				System.out.println("Invalid column. Try again.");
				return false;
			}
			if (chessBoard[row][column] instanceof Piece) {
				if (chessBoard[row][column].getColor() != turn) {
					System.out.println("This is your opponent's piece. Try again.");
					return false;
				}
			}
			if (chessBoard[row][column] == null) {
				System.out.println("This is a blank square. Try again.");
				return false;
			}
			currentCoordinates = coordinates.toUpperCase();
			return select(row, column);
		}
		catch (Exception e) {
			System.out.println("Invalid input format. Try again.");
			return false;
		}
	}
	
	public static boolean selectDestination(String coordinates) {								// helper method
		try {
			int newRow = 8 - Integer.parseInt(String.valueOf(coordinates.charAt(0)));
			int newColumn = (int) coordinates.toUpperCase().charAt(1) - 65; 
			if (newRow < 0 || newRow > 7) {
				System.out.println("Invalid row. Try again.");
				return false;
			}
			if (newColumn < 0 || newColumn > 7) {
				System.out.println("Invalid column. Try again.");
				return false;
			}
			newCoordinates = coordinates.toUpperCase();
			return selectDestination(newRow, newColumn);
		}
		catch (Exception e) {
			System.out.println("Invalid input. Try again.");
			return false;
		}
	}

	// piece selection methods
	private static boolean select(int row, int column) {										// selects piece to be moved
		 if (chessBoard[row][column].canMove()) {												// checks if piece has even one available move
			 currentRow = row;
			 currentColumn = column;
			 currentPiece = chessBoard[currentRow][currentColumn];
			 System.out.println("You have selected " + currentPiece.getName());
			 return true;
		 }
		 else {
			 System.out.println("This piece cannot move. Choose another.");
			 return false;
		 }
	}
		
	private static boolean selectDestination(int newRow, int newColumn) {						// selects location to move the piece to
		if (currentPiece.isLegal(newRow, newColumn)) {											// checks is the move is valid
			if (checkTestMovePassed(newRow, newColumn)) {
				System.out.println("This move is legal.");
				move(newRow, newColumn);															// moves the piece
				return true;
			}
			else {
				System.out.println("Your king is in check. Choose another destination.");
				return false;
			}
		}
		else {
			System.out.println("This move is illegal. Choose another desitnation.");
			return false;
		}
	}
	
	// movement methods
	private static void move(int newRow, int newColumn) {										// deals with moving the piece
		currentPiece.setCoordinates(newRow, newColumn);
		if (castle) castleMove(newRow, newColumn);												// moving for casting
		else {
			if (enPassant) enPassantMove(newRow, newColumn);									// moving for en passant
			else if (chessBoard[newRow][newColumn] instanceof Piece) {							// moving if a piece is killed
				Piece other = chessBoard[newRow][newColumn];
				System.out.println(currentPiece.getName() + " (" + currentCoordinates + ") killed " + other.getName() + " (" + newCoordinates + ")");
				(other.getColor()? whiteAlive: blackAlive).remove(other);
				if (other instanceof King) {
					System.out.println(currentPiece.getColor()? "White": "Black" + " wins the game.");
					ChessMain.setPlay(false);
				}
			}
			chessBoard[newRow][newColumn] = currentPiece;
			chessBoard[currentRow][currentColumn] = null;
			if ((currentPiece instanceof Pawn) && ((currentPiece.getColor() && newRow == 0) || (!currentPiece.getColor() && newRow == 7))) {	// pawn promotion
				System.out.print("What would you like to promote " + currentPiece.getName() + " (" + newCoordinates + ") to?: ");
				pawnUpgrade(in.nextLine(), newRow, newColumn, currentPiece.getColor());
			}
		}
		display();
		moves++;
		currentPiece.incrementMoves();
		if (ChessMain.getPlay()) {
			Piece oppKing = null;
			ArrayList<Piece> oppAliveList = currentPiece.getColor()? blackAlive: whiteAlive;
			for (int i = 0; i < oppAliveList.size(); i++) if (oppAliveList.get(i) instanceof King) oppKing = oppAliveList.get(i);
			if (isCheck(oppKing)) {
				if (currentPiece.getColor()) setBlackCheck(true);
				else setWhiteCheck(true);
			}
			else {
				if (currentPiece.getColor()) setBlackCheck(false);
				else setWhiteCheck(false);
			}
			if (whiteCheck) System.out.println("White king is in check!");
			if (blackCheck) System.out.println("Black king is in check!");
		}
	}
	
	private static void castleMove(int newRow, int newColumn) { 								// movement for castling
		Piece rook = chessBoard[newRow][newColumn];
		chessBoard[newRow][newColumn] = currentPiece;
		chessBoard[currentRow][currentColumn] = rook;
		System.out.println(currentPiece.getName() + " (" + currentCoordinates + ") castled with " + rook.getName() + " (" + newCoordinates + ")");
		rook.incrementMoves();
		setCastle(false);
	}
	
	private static void enPassantMove(int newRow, int newColumn) {								// movement and piece killing for en passant
		int move = currentPiece.getColor()? -1: 1;
		Piece other = chessBoard[newRow - move][newColumn];
		newCoordinates = (7 - newRow) + String.valueOf((char) (65 + newColumn));
		System.out.println(currentPiece.getName() + " (" + currentCoordinates + ") killed " + other.getName() + " (" + newCoordinates + ") with En Passant");
		(other.getColor()? whiteAlive: blackAlive).remove(other);
		chessBoard[newRow - move][newColumn] = null;
		setEnPassant(false);
	}
	
	// boolean return methods
	public static boolean isStalemate() {														// true if opponent has no available moves
		ArrayList<Piece> alive = currentPiece.getColor()? blackAlive: whiteAlive;
		for (int i = 0; i < alive.size(); i++) if (alive.get(i).canMove()) return false;
		return true;
	}
		
	private static boolean isCheck(Piece king) {												// returns true if king is in check
		int oppPawnMove = king.getColor()? -1: 1;												// pawn
		for (int i = -1; i < 2; i += 2) {
			Object pawn = null;
			try { pawn = chessBoard[king.getRow() + oppPawnMove][king.getColumn() + i]; } catch (Exception e) {}
			if (pawn instanceof Pawn && king.isOpponent((Piece) pawn)) return true;
		}
		
		for (int i = 0; i < 8; i++) {															// rook and (straight) queen
			Object rowPiece = chessBoard[i][king.getColumn()], columnPiece = chessBoard[king.getRow()][i];
			if ((rowPiece instanceof Rook || rowPiece instanceof Queen) &&
				king.isOpponent((Piece) rowPiece) &&
				king.isStraightClear(i, king.getColumn())) return true;
			else if ((columnPiece instanceof Rook || columnPiece instanceof Queen) &&
				king.isOpponent((Piece) columnPiece) &&
				king.isStraightClear(king.getRow(), i)) return true;
		}
		
		for (int i = -7; i < 8; i++) {															// bishop and (diagonal) queen
			Object plusPlus = null, plusMinus = null, minusPlus = null, minusMinus = null;
			try { plusPlus = chessBoard[king.getRow() + i][king.getColumn() + i]; } catch (Exception e) {}
			try { plusMinus = chessBoard[king.getRow() + i][king.getColumn() - i]; } catch (Exception e) {}
			
			if ((plusPlus instanceof Bishop || plusPlus instanceof Queen) &&
				king.isOpponent((Piece) plusPlus) &&
				king.isDiagonalClear(king.getRow() + i, king.getColumn() + i)) return true;
			else if ((plusMinus instanceof Bishop || plusMinus instanceof Queen) &&
				king.isOpponent((Piece) plusMinus) &&
				king.isDiagonalClear(king.getRow() + i, king.getColumn() - i)) return true;
		}
		
		for (int i = -1; i < 2; i += 2) {														// king
			Object plusCol = null, rowPlus = null, plusPlus = null, plusMinus = null;
			try { plusCol = chessBoard[king.getRow() + i][king.getColumn()]; } catch (Exception e) {}
			try { rowPlus = chessBoard[king.getRow()][king.getColumn() + i]; } catch (Exception e) {}
			try { plusPlus = chessBoard[king.getRow() + i][king.getColumn() + i]; } catch (Exception e) {}
			try { plusMinus = chessBoard[king.getRow() + i][king.getColumn() - i]; } catch (Exception e) {}
			
			if ((plusPlus instanceof King && king.isOpponent((Piece) plusPlus)) ||
				(plusMinus instanceof King && king.isOpponent((Piece) plusMinus)) ||
				(plusCol instanceof King && king.isOpponent((Piece) plusCol)) ||
				(plusMinus instanceof King && king.isOpponent((Piece) plusMinus))) return true;
		}
		
		for (int i = -2; i < 3; i += 4) for (int j = -1; j < 2; j += 2) {						// knight
			Object ij = null, ji = null;
			try { ij = chessBoard[king.getRow() + i][king.getColumn() + j]; } catch (Exception e) {}
			try { ji = chessBoard[king.getRow() + j][king.getColumn() + i]; } catch (Exception e) {}
			if ((ij instanceof Knight && king.isOpponent((Piece) ij)) ||
				(ji instanceof Knight && king.isOpponent((Piece) ji))) return true;
		}		
		return false;
	}
	
	public static boolean squareIsEmpty(int row, int column) {									// checks if passed coordinate is empty
		return chessBoard[row][column] == null;
 	}
	
	private static boolean checkTestMovePassed(int newRow, int newColumn) {						// plays move, checks for check, moves piece back
		boolean returnValue = true;
		Piece myKing = null;
		ArrayList<Piece> AliveList = currentPiece.getColor()? whiteAlive: blackAlive;
		currentPiece.setCoordinates(newRow, newColumn);
		if (enPassant) {
			int move = currentPiece.getColor()? -1: 1;
			Piece tempPiece = chessBoard[newRow - move][newColumn];
			chessBoard[newRow - move][newColumn] = null;
			chessBoard[currentRow][currentColumn] = null;
			chessBoard[newRow][newColumn] = currentPiece;
			for (int i = 0; i < AliveList.size(); i++) if (AliveList.get(i) instanceof King) myKing = AliveList.get(i);
			if (isCheck(myKing)) {
				setEnPassant(false);
				returnValue = false;
			}
			chessBoard[currentRow][currentColumn] = currentPiece;
			chessBoard[newRow - move][newColumn] = tempPiece;
			chessBoard[newRow][newColumn] = null;
			currentPiece.setCoordinates(currentRow, currentColumn);
		}
		else if (castle) {
			Piece rook = chessBoard[newRow][newColumn];
			chessBoard[newRow][newColumn] = currentPiece;
			chessBoard[currentRow][currentColumn] = rook;
			for (int i = 0; i < AliveList.size(); i++) if (AliveList.get(i) instanceof King) myKing = AliveList.get(i);
			if (isCheck(myKing)) {
				setCastle(false);
				returnValue = false;
			}
			currentPiece.setCoordinates(currentRow, currentColumn);
			chessBoard[newRow][newColumn] = rook;
			chessBoard[currentRow][currentColumn] = currentPiece;
		}
		else {
			Piece tempPiece = chessBoard[newRow][newColumn];
			chessBoard[newRow][newColumn] = currentPiece;
			chessBoard[currentRow][currentColumn] = null;
			for (int i = 0; i < AliveList.size(); i++) if (AliveList.get(i) instanceof King) myKing = AliveList.get(i);
			if (isCheck(myKing)) returnValue = false;
			chessBoard[currentRow][currentColumn] = currentPiece;
			chessBoard[newRow][newColumn] = tempPiece;
			currentPiece.setCoordinates(currentRow, currentColumn);
		}     	
		return returnValue;
	}
	
	// setters
	public static void setEnPassant(boolean enP) {
		enPassant = enP;
	}
	
	public static void setCastle(boolean c) {
		castle = c;
	}

	public static void setWhiteCheck(boolean check) {
		whiteCheck = check;
	}
	
	public static void setBlackCheck(boolean check) {
		blackCheck = check;
	}
	
	// getters
	public static Object getSquare(int row, int column) {

		try {
			return chessBoard[row][column];
		}
		catch (ArrayIndexOutOfBoundsException e) { return 'x'; }
	}
	
	public static int getMoves() {
		return moves;
	}
	
	public static boolean getWhiteCheck() {
		return whiteCheck;
	}
	
	public static boolean getBlackCheck() {
		return blackCheck;
	}
	
}
