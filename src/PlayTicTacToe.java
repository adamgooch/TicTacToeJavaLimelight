/**
 *
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        TicTacToeBoard gameBoard = new TicTacToeBoard();
        TicTacToeGame game = new TicTacToeGame(new UnbeatableAI(gameBoard),
                new TicTacToeConsoleIO(gameBoard));
        game.play();
    }

}
