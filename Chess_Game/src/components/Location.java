package components;

public class Location {
	private Integer row;
	private Integer column;
	
	private Piece piece;
	


	public Location(Integer r, Integer c) {
		row = r;
		column = c;
	}
	///--------------------------
	
	public Location(String loc) {
// loc contains 2 char  column - alphabet ,row-number
//		alphabet	column		number		row
//		a	->		0			'1'		->	7
//		b	->		1			'2'		->	6
//		c	->		2			'3'		->	5
//		d	->		3			'4'		->	4
//		e	->		4			'5'		->	3
//		f	->		5			'6'		->	2
//		g	->		6			'7'		->	1
//		h	->		7			'8'		->	0
		
		Character  columnChar = loc.charAt(0);
		Character  rowChar = loc.charAt(1);
		row = rowChar - '0';
		row = 8-row;
		column = columnChar - 'a';
		
	}

	public Integer getRow() {
		return row;
	}


	public Integer getColumn() {
		return column;
	}
	
	//get piece on the location
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
		if(piece!= null)
		{
			piece.setLocation(this);
		}
	}


	//a location l1 is vertical to location l2 if they lie in same column
	public Boolean isVerticalTo(Location to)
	{
		return this.column == to.column;
	}
	
	
	// a location l1 is horizontal to location l2 if they lie in same row
	public Boolean isHorizontalTo(Location to)
	{
		return this.row == to.row;
	}
	
	
	// a location l1 is diagonal to location l2 if the sum of row and column of l1 is equal to sum
	//of row and column of l2
	public Boolean isDiagonalTo(Location to)
	{
		return (this.row + this.column) == (to.row + to.column);
	}
	
	
	public Boolean isAntiDiagonalTo(Location to)
	{	
		return (this.row-to.row) == this.column - to.column;
	}
	
	@Override
	public String toString() {
		//String rowString = String.valueOf(8-row);
		int columnAsciiValue=column+97;
		char c= (char)(columnAsciiValue); 
		
		return "" + c + (8-row); 
	}
}
