package src;

import src.Board;
import src.Piece;

public class Rook extends Piece {

	// constructor
	public Rook(boolean color, int row, int column) { super(color, row, column); rep = getColor()? (char) 9814: (char) 9820; }

	// methods
	@Override
	public boolean canMove() {								// if any of the following squares can be moved to, return true
		for (int i = -1; i < 2; i += 2) {
			if (canMoveToSquare(Board.getSquare(getRow() + i, getColumn()))) return true;
			else if (canMoveToSquare(Board.getSquare(getRow(), getColumn() + i))) return true;
		}
		return false;
	}

	@Override
	public boolean isLegal(int newRow, int newColumn) {		// checks if destination is any of the following valid ones
		return ((newColumn == getColumn() || newRow == getRow()) &&
				canMoveToSquare(Board.getSquare(newRow, newColumn)) &&
				isStraightClear(newRow, newColumn));
	}
	
	// getters
	@Override
	public String getName() {
		return (getColor()? "White ": "Black ") + "src.Rook";
		}

}