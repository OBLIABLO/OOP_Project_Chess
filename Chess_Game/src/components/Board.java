package components;
import java.util.ArrayList;
import java.util.List;


public class Board {
	public Location[][] locations;
	List<Piece> whiteCaptured;
	List<Piece> blackCaptured;
	boolean isKingCaptured;
	
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
	
	public Location getLocation(String str) {
		Location currentLoc = new Location(str);
		return locations[currentLoc.getRow()][currentLoc.getColumn()];
	}

}
