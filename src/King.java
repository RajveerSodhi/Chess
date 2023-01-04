package src;

import src.Board;

public class King extends Piece {
	
	// constructor
	public King(boolean color, int row, int column) { super(color, row, column); rep = getColor()? (char) 9812: (char) 9818; }

	// methods
	@Override
	public boolean canMove() {								// if any of the following squares can be moved to, return true	
		for (int i = -1; i < 2; i += 2) {
			if (canMoveToSquare(Board.getSquare(getRow() + i, getColumn())) ||
				canMoveToSquare(Board.getSquare(getRow(), getColumn() + i)) ||
				canMoveToSquare(Board.getSquare(getRow() + i, getColumn() + i)) ||
				canMoveToSquare(Board.getSquare(getRow() + i, getColumn() - i))) return true;
		}
		return false;
	}

	@Override
	public boolean isLegal(int newRow, int newColumn) {		// checks if destination is any of the following valid ones
		boolean check = getColor()? Board.getWhiteCheck(): Board.getBlackCheck();
		if ((getMoves() == 0) &&							// for castling
				(Board.getSquare(newRow, newColumn) instanceof Rook) &&
				(((Piece) Board.getSquare(newRow, newColumn)).getMoves() == 0) &&
				(!check) &&
				(isStraightClear(newRow, newColumn))) {
				Board.setCastle(true);
				return true;
			}
		boolean validSquare = false;
		for (int i = -1; i < 2; i += 2) {
			if ((newRow == getRow() + i && newColumn == getColumn()) ||
			(newRow == getRow() && newColumn == getColumn() + i) ||
			(newRow == getRow() + i && newColumn == getColumn() + i) ||
			(newRow == getRow() + i && newColumn == getColumn() - i)) { validSquare = true; break; }
		}
		return (validSquare && canMoveToSquare(Board.getSquare(newRow, newColumn)));
	}

	// getters
	@Override
	public String getName() {
		return (getColor()? "White ": "Black ") + "src.King";
	}

}