package components;

public enum Color {
	
	BLACK, WHITE;

	Color nextColor() {

		return this.equals(BLACK) ? WHITE : BLACK;
	}

}
