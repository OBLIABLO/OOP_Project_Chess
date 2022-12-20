package components;

public class Location {
	private int row;
	private int column;
	
	public Location(int r, int c) {
		row = r;
		column = c;
	}
	
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
		
		char columnChar = loc.charAt(0);
		char rowChar = loc.charAt(1);
		row = rowChar - '0';
		row = 8-row;
		column = columnChar - 'a';
		
	}

	public int getRow() {
		return row;
	}


	public int getColumn() {
		return column;
	}


	//a location l1 is vertical to location l2 if they lie in same column
	public boolean isVerticalTo(Location to)
	{
		return this.column == to.column;
	}
	
	
	// a location l1 is horizontal to location l2 if they lie in same row
	public boolean isHorizontalTo(Location to)
	{
		return this.row == to.row;
	}
	
	
	// a location l1 is diagonal to location l2 if the sum of row and column of l1 is equal to sum
	//of row and column of l2
	public boolean isDiagonalTo(Location to)
	{
		return (this.row + this.column) == (to.row + to.column);
	}
	
}
