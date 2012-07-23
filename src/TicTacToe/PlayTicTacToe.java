package TicTacToe;

/**
 *
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        Board gameBoard = new Board();
        ConsoleIO io = new ConsoleIO(gameBoard);
        AI ai = new UnbeatableAI(gameBoard);
        Game game = new Game(ai, io);
        game.play();
    }

}
