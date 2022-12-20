package components;

public abstract class Piece {
	
	protected Color color;
	protected Location location;
	protected String symbol;
	protected Board board;
	
	//constructor
	public Piece(Color color, Location location, String symbol, Board board) {
		super();
		this.color = color;
		this.location = location;
		
		this.location.setPiece(this);
		this.symbol = symbol;
		this.board = board;
	}
	
	//getter setters
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	//abstract methods to move a piece to some location and to check whether a moveis valid
	public abstract void moveToLocation(Location newLocation) throws InvalidMoveException;
	
	protected abstract boolean isValidMove(Location To) throws InvalidMoveException;
	
	

}
