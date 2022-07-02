
public class Knight extends Piece {
	
	// constructor
	public Knight(boolean color, int row, int column) { super(color, row, column); rep = getColor()? (char) 9816: (char) 9822; }

	// methods
	@Override
	public boolean canMove() {								// if any of the following squares can be moved to, return true
		for (int i = -2; i < 3; i += 4) {
			for (int j = -1; j < 2; j += 2) {
				if (canMoveToSquare(Board.getSquare(getRow() + i, getColumn() + j))) return true;
				else if (canMoveToSquare(Board.getSquare(getRow() + j, getColumn() + i))) return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLegal(int newRow, int newColumn) {		// checks if destination is any of the following valid ones
		boolean validSquare = false;
		for (int i = -2; i < 3; i += 4) {
			for (int j = -1; j < 2; j += 2) {
				if ((newRow == getRow() + i && newColumn == getColumn() + j) ||
				(newRow == getRow() + j && newColumn == getColumn() + i)) { validSquare = true; break; }
			}
		}
		return (validSquare && canMoveToSquare(Board.getSquare(newRow, newColumn)));
	}

	// getters
	@Override
	public String getName() {
		return (getColor()? "White ": "Black ") + "Knight";
	}

}