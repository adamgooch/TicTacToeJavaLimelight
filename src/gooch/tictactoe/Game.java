package gooch.tictactoe;

public class Game {
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';

    protected BoardChecker checker;
    private IO io;
    private GameRunner runner;

    public Game(IO io, Board board) {
        this.io = io;
        MiniMaxAI ai = new MiniMaxAI(board);
        PlayType type = io.getPlayType();
        this.runner = new GameRunner(ai, io, this, type);
        io.addActionListener(runner);
        ai.addActionListener(runner);
        checker = new BoardChecker(board);
    }

    public void begin() {
        runner.start();
    }

    public boolean gameOver() {
        if(checker.thereIsAWinner() || checker.boardIsFull())
            return true;
        return false;
    }

    private String getWinnerMessage() {
        if(checker.getWinner() == PLAYER_ONE) {
            return IO.PLAYER_ONE_WINS;
        } else if(checker.getWinner() == PLAYER_TWO) {
            return IO.PLAYER_TWO_WINS;
        } else {
            return IO.NOBODY_WINS;
        }
    }

    public void end() {
        io.doEndOfGameTasks(getWinnerMessage(), checker.getWinningSquares());
    }
}
