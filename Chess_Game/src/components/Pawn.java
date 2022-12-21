package components;

public class Pawn extends Piece{
	
	public Pawn(Color color, Location location, Board board) {
		super(color, location, "P", board);
	}

	@Override
	public void moveToLocation(Location newLocation) throws InvalidMoveException {
		if (isValidMove(newLocation)) {
			//board.movePiece(location, newLocation);
		} else {
			throw new InvalidMoveException(InvalidMoveException.PAWN);
		}
		
	}

	@Override
	protected boolean isValidMove(Location To) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toStringType() {
		return "Pawn ";
	}



}
