package src.model;

public abstract class Piece {

	// attributes
	private boolean color;
	public char rep;
	private int row, column, moves;
	
	// constructor
	Piece(boolean color, int row, int column) { this.color = color; setCoordinates(row, column); moves = 0; }
	
	// methods
	public boolean isOpponent(Piece other) {							// checks if passed player is opponent
		return other.getColor() != getColor();
		}
	
	public boolean canMoveToSquare(Object other) {						// checks if destination square is empty or has opponent
		if (other instanceof Piece) return isOpponent((Piece) other);
		else if (other == null) return true;
		else return false;
	}
	
	public boolean isStraightClear(int newRow, int newColumn) {			// blockage checker for rook and queen
	    if (newRow == getRow()) {
	    	for (int col = (newColumn > getColumn()? getColumn(): newColumn) + 1; col < (newColumn > getColumn()? newColumn: getColumn()); col++)
	    		if (!Board.squareIsEmpty(newRow, col)) return false;
	    }
	    else if (newColumn == getColumn()) {
	    	for (int row = (newRow > getRow()? getRow(): newRow) + 1; row < (newRow > getRow()? newRow: getRow()); row++)
	    		if (!Board.squareIsEmpty(row, newColumn)) return false;
	    }
		return true;
	}
		
	public boolean isDiagonalClear(int newRow, int newColumn) {			// blockage checker for bishop and queen
		int rowChange = (newRow - getRow()) < 0? -1: 1, colChange = (newColumn - getColumn()) < 0? -1: 1, change = Math.abs(newRow - getRow());
		for (int i = 1; i < change; i++) if (!Board.squareIsEmpty(getRow() + i*rowChange, getColumn() + i*colChange)) return false;
		return true;
	}	
	
	// abstract methods
	public abstract boolean canMove();									// checks if piece has any available moves
	
	public abstract String getName();									// returns team color and piece type
	
	public abstract boolean isLegal(int newRow, int newColumn);			//checks if move is legal
	
	// getters
	public char getRep() {
		return rep;
		}
	
	public boolean getColor() {
		return color;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getMoves() {
		return moves;
	}
	
	// setters
	public void setCoordinates(int newRow, int newColumn) {
		this.row = newRow;
		this.column = newColumn;
	}
	
	public void incrementMoves() {
		moves++;
	}
	
}