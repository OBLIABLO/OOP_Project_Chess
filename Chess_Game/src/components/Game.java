package components;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

	private Board board;
	private Integer turn;
	private Boolean gameInAction;
	Scanner sc = new Scanner(System.in);
	
	public Game(Board board) {
		super();
		this.board = board;
		gameInAction = true;
		turn = 0;

	}
	
	public void play() {

		System.out.println("\nThis is the board of your chess game:\n");
		board.printTheBoard();
		printHelp();

		while (gameInAction == true) {
			if(board.isKingCaptured == true)
			{
				break;
			}

			if (turn == 0) {
				System.out.println("\nWhite plays. Please insert your move: ");
			} else {
				System.out.println("\nBlack plays. Please insert your move: ");
			}

			String round = sc.nextLine();
			handleInput(round);
		}
	}
	
	private void changeTurn() {
		turn = (turn + 1) % 2;
	}

	/**
	 * Handles player's input. Identifies if it is a command or a movement.
	 * 
	 * @param moveString
	 */
	
	
	public void handleInput(String moveString) {
		moveString = moveString.trim();
		Pattern patternCommand = Pattern.compile(":[hsox]", Pattern.CASE_INSENSITIVE);
		Pattern patternMovement = Pattern.compile("[a-h][1-8][a-h][1-8]", Pattern.CASE_INSENSITIVE);

		Matcher commandMatcher = patternCommand.matcher(moveString);
		Matcher movementMatcher = patternMovement.matcher(moveString);

		Boolean commandMatchFound = commandMatcher.find();
		Boolean movementMatchFound = movementMatcher.find();

		if (commandMatchFound) {
			System.out.println("\n");
				handleCommand(moveString);
		} else if (movementMatchFound && moveString.length() == 4) {
			System.out.println("\n");
			handleMove(moveString);
		} else {
			System.out.println("\n!!! This is not a valid input.\n"
					+ "(You can type':h' for the help Menu.)\n");
			
		}

	}

	public void handleCommand(String moveString){
		switch (moveString) {
		case ":h":
			printHelp();
			break;
		case ":x":
			if (exitGame()) {
				this.gameInAction = false;
			}
			break;
		default:
			break;
			
		}
	}
	
	public void handleMove(String moveString) {

		Location from = board.getLocation(moveString.substring(0, 2));
		Location to = board.getLocation(moveString.substring(2, 4));

		Piece pieceToMove = from.getPiece();

		try {
			if (!thereIsPieceInLoc(from)) {
				throw new InvalidMoveException(InvalidMoveException.THERE_IS_NO_PIECE_THERE_TO_MOVE);
			}
			if (!checkIfPieceToMoveMatchesPlayer(from)) {
				throw new InvalidMoveException(InvalidMoveException.NOT_YOUR_PIECE);
			}

			pieceToMove.moveToLocation(to);
			changeTurn();
		} catch (InvalidMoveException e) {
			System.out.println(e.getMessage());
		}

		board.printTheBoard();
	}
	
	/**
	 * Checks if there is a piece in the selected location
	 * @param loc
	 */
	private Boolean thereIsPieceInLoc(Location loc) {
		if (board.getPieceAt(loc) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the color of the piece matches player's color
	 * 
	 */
	
	private Boolean checkIfPieceToMoveMatchesPlayer(Location locTo) {
		if ((turn == 0 && board.getPieceAt(locTo).color.equals(Color.WHITE))
				|| (turn == 1 && board.getPieceAt(locTo).color.equals(Color.BLACK))) {
			return true;
		} else {
			return false;
		}
	}


	public Boolean exitGame() {
		System.out.print("Are you sure you want to exit this game? \nPlease type 'yes' or 'no': ");
		String answer = sc.nextLine().trim();
		if (answer.toLowerCase().equals("yes")) {
			System.out.println("Sayonara Loser");
			return true;
		} else if (answer.toLowerCase().equals("no")) {
			board.printTheBoard();
			return false;
		} else {
			System.out.println("You typed neither yes, nor no. You can keep playing.\n");
			board.printTheBoard();
			return false;
		}
	}
	
	public void printHelp() {
		System.out.println("\n");
		System.out.println("==============================================================================================================\n"
				+ "| To move a piece you have to write the coordinates                                                           |\n"
				+ "| (the letter of the column and the number of the row) of its current position.                               |\n"
				+ "| Then, (without a space) you have to write the coordinates of the location you want to move this piece to.   |\n"
				+ "|  ==> For example to move a pawn from the location b2 to location b3, you have to write ==>  b2b3.           |\n"
				+ "|                                                                                                             |\n"
				+ "|Furthermore, you can choose one of the following commands.                                                   |\n" 
				+ "|:h � Show the help menu                                                                                      |\n"
			    + "|:x � Exit                                                                                                    |\n"
				+ " ==============================================================================================================\n");
	}
}
