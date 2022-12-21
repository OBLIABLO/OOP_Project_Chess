
package MainProgram;

import components.Board;
import components.Game;

public class ClientApp {
	public static void main(String[] args){


		Board board = new Board();
		board.init();
		Game game = new Game(board);
		System.out.println("*******  ***  ***  ********  **********  ********** ");
		System.out.println("*******  ***  ***  ********  **********  ********** ");
		System.out.println("**       ********  ***       ****        *****      ");
		System.out.println("**       ********  ********  **********  ********** ");
		System.out.println("**       ********  ********  **********  ********** ");
		System.out.println("*******  ***  ***  ***              ***         *** ");
		System.out.println("*******  ***  ***  ********  **********  ********** ");
		System.out.println("*******  ***  ***  ********  **********  ********** ");
		game.play();


	}

}
