package components;

public class Pawn extends Piece{
	
	public Pawn(Color color, Location location, Board board) {
		super(color, location, "P", board);
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.PAWN);
		}
		
	}

	
	@Override
	public Boolean isValidMove(Location locTo) throws InvalidMoveException {
		if (isMoveForward(locTo)) {
			if (locTo.getPiece() == null && isValidWhenNotCapturing(locTo)) {
				return true;
			} else if (locTo.getPiece() != null && isValidWhenCapturing(locTo)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new InvalidMoveException(InvalidMoveException.PAWN_ONLY_FORWARD);
		}
	}
	
	//Checks if the pawn moves on a diagonal or anti diagonal path when it is capturing a piece
	private Boolean isValidWhenCapturing(Location locTo) throws InvalidMoveException {

		if (((location.getColumn() - locTo.getColumn() == 1) || (location.getColumn() - locTo.getColumn() == -1))
				&& (location.getRow() - locTo.getRow() == 1)) {
			return true;
		} else {
			throw new InvalidMoveException(InvalidMoveException.PAWN_CAPTURE_ONLY_DIAGONALLY);
		}
	}
	
	//Checks if the pawn moves forwards when it isn't capturing a piece
	private Boolean isValidWhenNotCapturing(Location locTo) throws InvalidMoveException {
		if (location.getColumn() == locTo.getColumn()) {
			if ((location.getRow() - locTo.getRow() == 1) || location.getRow() - locTo.getRow() == -1) {
				return true;
			}

			else if (((location.getRow() - locTo.getRow() == 2) || (location.getRow() - locTo.getRow() == -2))
					&& isTheFirstMove() && board.freeVerticalPath(location, locTo)) {
				return true;
			}
		}

		return false;

	}
	
	//Checks if the pawn moves forwards
	private Boolean isMoveForward(Location locTo) throws InvalidMoveException {
		if (this.color.equals(color.BLACK) && locTo.getRow() > location.getRow()) {
			return true;
		} else if (this.color.equals(color.WHITE) && locTo.getRow() < location.getRow()) {
			return true;
		} else {
			throw new InvalidMoveException(InvalidMoveException.PAWN_ONLY_FORWARD);
		}
	}
	
	//Checks if this is the first move of the Pawn, so checks if it can move one or two squares front
	private Boolean isTheFirstMove() throws InvalidMoveException {
		if (this.color.equals(color.BLACK) && location.getRow() == 1) {
			return true;
		}
		if (this.color.equals(color.WHITE) && location.getRow() == 6) {
			return true;
		} else {
			throw new InvalidMoveException(InvalidMoveException.PAWN_NOT_FIRSTMOVE);

		}
	}
	
	@Override
	public String toStringType() {
		return "Pawn ";
	}



}
