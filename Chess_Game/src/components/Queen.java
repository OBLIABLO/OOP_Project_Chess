package components;


public class Queen extends Piece {

	public Queen(Color color, Location location, Board board) {
		super(color, location, "Q", board);
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.QUEEN);
		}
	}

	//checks if the Queen can make these moves
	//Queen can go vertical,horizontal,diagonal,anti-diagonal
	@Override
	public Boolean isValidMove(Location locTo) throws InvalidMoveException {

		if (location.isDiagonalTo(locTo) && board.freeDiagonalPath(location, locTo)) {
			return true;
		} else if (location.isAntiDiagonalTo(locTo) && board.freeAntidiagonalPath(location, locTo)) {
			return true;
		} else if (location.isHorizontalTo(locTo) && board.freeHorizontalPath(location, locTo)) {
			return true;
		} else if (location.isVerticalTo(locTo) && board.freeVerticalPath(location, locTo)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toStringType() {
		return "Queen ";
	}

}
