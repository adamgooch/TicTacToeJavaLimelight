/**
 *
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        TicTacToeBoard gameBoard = new TicTacToeBoard();
        TicTacToeConsoleIO io = new TicTacToeConsoleIO(gameBoard);
        TicTacToeAI ai = new UnbeatableAI(gameBoard);
        TicTacToeGame game = new TicTacToeGame(ai, io);
        game.play();
    }

}
