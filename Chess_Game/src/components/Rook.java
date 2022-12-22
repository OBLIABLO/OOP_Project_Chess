package components;

public class Rook extends Piece {

	public Rook(Color color, Location location,Board board) {
		super(color, location, "R", board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.ROOK);
		}
	}

	@Override
	protected Boolean isValidMove(Location locTo) throws InvalidMoveException {

		if (location.isHorizontalTo(locTo) && board.freeHorizontalPath(location, locTo)) {
			return true;
		} else if (location.isVerticalTo(locTo) && board.freeVerticalPath(location, locTo)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toStringType() {
		return "Rook ";
	}



}
