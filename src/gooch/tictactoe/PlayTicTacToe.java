package gooch.tictactoe;

public class PlayTicTacToe {
    public static void main (String[] args) {
        Board board = new Board();
        ConsoleIO io = new ConsoleIO(board);
        Game game = new Game(io, board);
        game.begin();
    }

}
