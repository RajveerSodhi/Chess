package src;

import src.Board;
import src.Piece;

public class Queen extends Piece {
	
	// constructor
	public Queen(boolean color, int row, int column) { super(color, row, column); rep = getColor()? (char) 9813: (char) 9819; }

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
		boolean validSquare = false;
		if (newColumn == getColumn() || newRow == getRow()) validSquare = true;
		for (int i = -7; i < 8; i++) {
			if ((newRow == getRow() + i && newColumn == getColumn() + i) ||
				(newRow == getRow() + i && newColumn == getColumn() - i)) { validSquare = true; break; }
		}
		return (validSquare &&
				canMoveToSquare(Board.getSquare(newRow, newColumn)) &&
				isStraightClear(newRow, newColumn) &&
			    isDiagonalClear(newRow, newColumn));
	}

	// getters
	@Override
	public String getName() {
		return (getColor()? "White ": "Black ") + "src.Queen";
		}

}