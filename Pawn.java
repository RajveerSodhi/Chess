
public class Pawn extends Piece {
	
	// attributes
	private static int pawnLastDoubleMove;					// static variable to help with en passant
	
	// constructor
	public Pawn(boolean color, int row, int column) { super(color, row, column); rep = getColor()? (char) 9817: (char) 9823; }

	// methods
	@Override
	public boolean canMove() {								// if any of the following squares can be moved to, return true
		int move = getColor()? -1: 1;
		if (canMoveToSquare(Board.getSquare(getRow() + move, getColumn()))) return true;
		else if (Board.getSquare(getRow() + move, getColumn() + 1) != null)
			if (isOpponent((Piece) Board.getSquare(getRow() + move, getColumn() + 1))) return true;
		else if (Board.getSquare(getRow() + move, getColumn() - 1) != null)
			if (isOpponent((Piece) Board.getSquare(getRow() + move, getColumn() - 1))) return true;
		return false;
	}

	@Override
	public boolean isLegal(int newRow, int newColumn) {		// checks if destination is any of the following valid ones
		int move = getColor()? -1: 1;
		int startingRow = getColor()? 6: 1, startingMove = getColor()? -2: 2;
		int epRow = getColor()? 3: 4;
		if (getRow() == startingRow)
			if ((newRow == getRow() + startingMove) && (newColumn == getColumn()) && (Board.squareIsEmpty(newRow, newColumn))) {
				pawnLastDoubleMove = Board.getMoves();
				return true;
			}
		if ((newRow == getRow() + move) && (newColumn == getColumn()) && (Board.squareIsEmpty(newRow, newColumn))) return true;
		for (int i = -1; i < 2; i += 2) {
			if ((newRow == getRow() + move) && (newColumn == getColumn() + i)) {
				if ((getRow() == epRow) &&
					(Board.getSquare(epRow, newColumn) instanceof Pawn) &&
					(isOpponent((Piece) Board.getSquare(epRow, newColumn))) &&
					(Board.getSquare(newRow, newColumn) == null) &&
					(((Piece) Board.getSquare(epRow, newColumn)).getMoves() == 1) &&
					(Board.getMoves() == pawnLastDoubleMove + 1)) {
						Board.setEnPassant(true);
						return true;
				}
				else if (!Board.squareIsEmpty(newRow, newColumn) && isOpponent((Piece) Board.getSquare(newRow, newColumn))) return true;
			}
		}
		return false;
	}

	// getters
	@Override
	public String getName() {
		return (getColor()? "White ": "Black ") + "Pawn";
	}

}