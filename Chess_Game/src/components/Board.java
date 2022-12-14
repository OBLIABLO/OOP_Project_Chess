package components;
import java.util.ArrayList;
import java.util.List;


public class Board {
	public Location[][] locations;
	List<Piece> whiteCaptured;
	List<Piece> blackCaptured;
	Boolean isKingCaptured;
	
	public Board() {
		locations = new Location[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				locations[i][j] = new Location(i , j);
			}
		}
		whiteCaptured = new ArrayList<>();
		blackCaptured = new ArrayList<>();
		isKingCaptured = false;
	}
	
	public void printTheBoard() {

		System.out.println("     " + "a b c d e f g h");
		for (int i = 0; i < 8; i++) {
			System.out.print("   " + (8 - i));
			for (int j = 0; j < 8; j++) {

				if (locations[i][j].getPiece() != null) {
					System.out.print(" " + locations[i][j].getPiece().toString());
				} else {
					System.out.print(" -");
				}
			}
			System.out.print(" " + (8 - i));
			System.out.println();
		}
		System.out.println("     " + "a b c d e f g h");
	}
	
	
	public Location getLocation(String str) {
		Location currentLoc = new Location(str);
		return locations[currentLoc.getRow()][currentLoc.getColumn()];
	}
	
	public Piece getPieceAt(Location loc) {
		return locations[loc.getRow()][loc.getColumn()].getPiece();
	}
	
	public void movePieceWithoutCapturing(Location from, Location to) {
		to.setPiece(getPieceAt(from));
		from.setPiece(null);
	}
	
	public void movePieceCapturing(Location from, Location to) {
		// the captured piece
		Piece captured = getPieceAt(to);
		if (captured.color == Color.BLACK) {
			blackCaptured.add(captured);
		} else {
			whiteCaptured.add(captured);
		}
		
		System.out.println(" ! ! ! " +captured.toStringType() + "was captured.");
		if (captured.symbol.equals("K")) {
			
			if (captured.color == Color.BLACK) {
				System.out.println("\n ============== White wins!  ==================");
			} else {
				System.out.println(" ============== Black wins! ==================");
			}
			System.out.println(" ============== End of Game. ==================");
			isKingCaptured = true;
		}
		captured.setLocation(null);

		to.setPiece(getPieceAt(from));
		from.setPiece(null);
	}
	
	public void movePiece(Location from, Location to) throws InvalidMoveException {
		if (getPieceAt(to) == null) {
			movePieceWithoutCapturing(from, to);
		} else if (getPieceAt(from).color != getPieceAt(to).color) {
			movePieceCapturing(from, to);
		} else {
			throw new InvalidMoveException(InvalidMoveException.YOU_CAN_NOT_CAPTURE_YOUR_PIECE);
		}
	}
	
	public Boolean freeHorizontalPath(Location from, Location to) throws InvalidMoveException {
		// constant row- increasing column
		if (from.getColumn() < to.getColumn()) {
			for (int i = from.getColumn() + 1; i < to.getColumn(); i++) {
				if (locations[from.getRow()][i].getPiece() == null) {
					continue;
				} else {
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
			}
		}
		// constant row- decreasing column
		else if (from.getColumn() > to.getColumn())
			for (int i = to.getColumn() + 1; i < from.getColumn(); i++) {
				if (locations[from.getRow()][i].getPiece() == null) {
					continue;
				} else {
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
			}

		return true;

	}
	
	public Boolean freeVerticalPath(Location from, Location to) throws InvalidMoveException {
		if (from.getRow() < to.getRow()) {
			for (int i = from.getRow() + 1; i < to.getRow(); i++) {
				if (locations[i][from.getColumn()].getPiece() == null) {
					continue;
				} else {
					
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
			}
		} else if (from.getRow() > to.getRow())
			for (int i = to.getRow() + 1; i < from.getRow(); i++) {
				if (locations[i][from.getColumn()].getPiece() == null) {
					continue;
				} else {
					System.out.println("B");
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
			}

		return true;

	}
	
	public Boolean freeDiagonalPath(Location from, Location to) throws InvalidMoveException {

		if (from.getColumn() < to.getColumn()) {

			Integer row = from.getRow() - 1;
			Integer col = from.getColumn() + 1;

			while (col < to.getColumn()) {

				if (locations[row][col].getPiece() == null) {
					row--;
					col++;
					continue;
				} else {
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
				
			} return true;
		} else {
			Integer row = from.getRow() + 1;
			Integer col = from.getColumn() - 1;

			while (col > to.getColumn()) {

				if (locations[row][col].getPiece() == null) {
					row++;
					col--;
					continue;
				} else {
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
			}return true;

		}
	}
	
	public Boolean freeAntidiagonalPath(Location from, Location to) throws InvalidMoveException {
		if (from.getColumn() < to.getColumn()) {

			Integer row = from.getRow() + 1;
			Integer col = from.getColumn() + 1;

			while (col < to.getColumn()) {

				if (locations[row][col].getPiece() == null) {
					row++;
					col++;
					continue;
				} else {
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
				
			} return true;
		} else {
			Integer row = from.getRow() - 1;
			Integer col = from.getColumn() - 1;

			while (col > to.getColumn()) {

				if (locations[row][col].getPiece() == null) {
					row--;
					col--;
					continue;
				} else {
					throw new InvalidMoveException(InvalidMoveException.OBSTACLE);
				}
			}
			return true;
		}
	}
	
	
	public void init() {
		// BLACK
		new Rook(Color.BLACK, locations[0][0], this);
		new Rook(Color.BLACK, locations[0][7], this);
		new Knight(Color.BLACK, locations[0][1], this);
		new Knight(Color.BLACK, locations[0][6], this);
		new Bishop(Color.BLACK, locations[0][2], this);
		new Bishop(Color.BLACK, locations[0][5], this);
		new Queen(Color.BLACK, locations[0][3], this);
		new King(Color.BLACK, locations[0][4], this);
		for (int i = 0; i < 8; i++) {
			new Pawn(Color.BLACK, locations[1][i], this);
		}

		// WHITE
		new Rook(Color.WHITE, locations[7][0], this);
		new Rook(Color.WHITE, locations[7][7], this);
		new Knight(Color.WHITE, locations[7][1], this);
		new Knight(Color.WHITE, locations[7][6], this);
		new Bishop(Color.WHITE, locations[7][2], this);
		new Bishop(Color.WHITE, locations[7][5], this);
		new Queen(Color.WHITE, locations[7][3], this);
		new King(Color.WHITE, locations[7][4], this);
		for (int i = 0; i < 8; i++) {
			new Pawn(Color.WHITE, locations[6][i], this);
		}
	}




}
