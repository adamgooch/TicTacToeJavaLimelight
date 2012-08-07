package gooch.tictactoe;

public class GameMaker {
    public static Game game;
    public static Board board;

    public static void makeGame (IO io) {
        board = new Board();
        AI ai = new MiniMaxAI(board);
        PlayType type = io.getPlayType();
        game = new Game(ai, io, board, type);
        game.play();
    }
}
