package gooch.tictactoe;

public class Game {
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    public static final String PLAYER_ONE_WINS = "X WINS!";
    public static final String PLAYER_TWO_WINS = "O WINS!";
    public static final String NOBODY_WINS = "Nobody Wins.";

    private AI ai;
    private IO io;
    private BoardChecker checker;
    private int movesMade;
    private PlayType gameType;
    private boolean playerOnesTurn;

    public Game(AI ai, IO io, BoardChecker checker, PlayType playType) {
        this.ai = ai;
        this.io = io;
        this.checker = checker;
        this.gameType = playType;
        movesMade = 0;
        playerOnesTurn = true;
    }

    public void play() {
        io.displayBoard();
        getPlayerMove();
    }

    public void moveGameForward() {
        io.displayBoard();
        playerOnesTurn = !playerOnesTurn;
        movesMade++;
        if(gameOver()) {
            io.displayMessage(getWinnerMessage());
            io.highlightWin(checker.getWinningSquares());
            io.playAudioMessage(getWinnerMessage());
        } else {
            getPlayerMove();
        }
    }

    public boolean gameOver() {
        if(checker.thereIsAWinner() || movesMade >= Board.NUMBER_OF_SQUARES)
            return true;
        return false;
    }

    public String getWinnerMessage() {
        if(checker.getWinner() == PLAYER_ONE) {
            return PLAYER_ONE_WINS;
        } else if(checker.getWinner() == PLAYER_TWO) {
            return PLAYER_TWO_WINS;
        } else {
            return NOBODY_WINS;
        }
    }

    public void reset() {
        movesMade = 0;
        playerOnesTurn = true;
        checker = new NineSquareChecker(GameMaker.board);
    }

    private void getPlayerMove() {
        if(playerOnesTurn)
            playerOneMove();
        else
            playerTwoMove();
    }

    private void playerOneMove() {
        if(gameType != PlayType.AI_VS_AI)
            io.getPlayerMove(PLAYER_ONE); // human player always goes first
        else
            ai.move(PLAYER_ONE);
    }

    private void playerTwoMove() {
        if(gameType != PlayType.PLAYER_VS_PLAYER)
            ai.move(PLAYER_TWO);
        else
            io.getPlayerMove(PLAYER_TWO);
    }
}
