/**
 *
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        TicTacToeBoard gameBoard = new TicTacToeBoard();
        TicTacToe game = new TicTacToe(gameBoard, new TicTacToeAi(gameBoard));
        game.play();
    }

}
