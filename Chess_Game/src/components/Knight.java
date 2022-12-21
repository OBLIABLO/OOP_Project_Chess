package components;

public class Knight extends Piece {
	
	public Knight(Color color, Location location, Board board) {
		super(color, location, "N", board);
	}
	
	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.KNIGHT);
		}
	}
	
	@Override
	protected Boolean isValidMove(Location locTo) {

		Integer twoSquaresBack = this.location.getRow() - 2;
		Integer twoSquaresFront = this.location.getRow() + 2;
		Integer twoSquaresRight = this.location.getColumn() - 2;
		Integer twoSquaresLeft = this.location.getColumn() + 2;
		Integer oneSquareBack = this.location.getRow() - 1;
		Integer oneSquareFront = this.location.getRow() + 1;
		Integer oneSquareRight = this.location.getColumn() - 1;
		Integer oneSquareLeft = this.location.getColumn() + 1;

		Integer targetRow = locTo.getRow();
		Integer targetCol = locTo.getColumn();

		if (targetRow == twoSquaresBack && (targetCol == oneSquareLeft || targetCol == oneSquareRight)) {
			return true;
		} else if (targetRow == twoSquaresFront && (targetCol == oneSquareLeft || targetCol == oneSquareRight)) {
			return true;
		} else if (targetCol == twoSquaresLeft && (targetRow == oneSquareBack || targetRow == oneSquareFront)) {
			return true;
		} else if (targetCol == twoSquaresRight && (targetRow == oneSquareBack || targetRow == oneSquareFront)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toStringType() {
		return "Knight ";
	}

}
