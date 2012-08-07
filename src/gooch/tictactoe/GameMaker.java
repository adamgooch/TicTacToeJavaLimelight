package gooch.tictactoe;

public class GameMaker {

    public static Game game;
    public static Board board;

    public GameMaker (IO io) {
        board = new Board();
        BoardChecker checker = new NineSquareChecker(board);
        AI ai = new MiniMaxAI(board);
        PlayType type = io.getPlayType();
        game = new Game(ai, io, checker, type);
    }
}
