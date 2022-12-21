package components;

public class King extends Piece {

	public King(Color color, Location location, Board board) {
		super(color, location, "K", board);
	}

	//throws exception if the moves are invalid
	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.KING);
		}

	}
	
	//checks if the King can make these moves
	//king can go one step at a time,vertical,horizontal,diagonal,anti-diagonal
	@Override
	public boolean isValidMove(Location locTo) {

		if (location.isVerticalTo(locTo)
				&& ((locTo.getRow() == 1 + location.getRow()) || (locTo.getRow() == location.getRow() - 1))) {
			return true;
		} else if (location.isHorizontalTo(locTo)
				&& (locTo.getColumn() == 1 + location.getColumn() || locTo.getColumn() == location.getColumn() - 1)) {
			return true;
		} else if (location.isAntiDiagonalTo(locTo)
				&& (locTo.getColumn() == 1 + location.getColumn() || locTo.getColumn() == location.getColumn() - 1)) {
			return true;
		} else if (location.isDiagonalTo(locTo)
				&& (locTo.getColumn() == 1 + location.getColumn() || locTo.getColumn() == location.getColumn() - 1)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toStringType() {
		return "King ";
	}

}
