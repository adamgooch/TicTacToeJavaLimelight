package gooch.tictactoe;

public class Game {
    public static final int PLAYER_VS_PLAYER = 0;
    public static final int PLAYER_VS_AI = 1;
    public static final int AI_VS_AI = 2;
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    private static final String PLAYER_ONE_WINS = "X WINS!";
    private static final String PLAYER_TWO_WINS = "O WINS!";
    private static final String NOBODY_WINS = "Nobody Wins.";

    private AI ai;
    private IO io;
    private BoardAnalyzer analyzer;
    private int movesMade;
    private int gameType;
    private boolean playerOnesTurn;

    public Game(AI ai, IO io, Board board, int type) {
        this.ai = ai;
        this.io = io;
        this.analyzer = new BoardAnalyzer(board);
        this.gameType = type;
        movesMade = 0;
        playerOnesTurn = true;
    }

    public void play() {
        io.displayBoard();
        while(!gameOver()) {
            playerMove();
            playerOnesTurn = !playerOnesTurn;
            io.displayBoard();
            movesMade++;
        }
        io.displayMessage(getWinnerMessage() + "\n");
    }

    public boolean gameOver() {
        if(analyzer.thereIsAWinner() || movesMade >= Board.NUMBER_OF_SQUARES)
            return true;
        return false;
    }

    protected String getWinnerMessage() {
        if(analyzer.getWinner() == PLAYER_ONE) {
            return PLAYER_ONE_WINS;
        } else if(analyzer.getWinner() == PLAYER_TWO) {
            return PLAYER_TWO_WINS;
        } else {
            return NOBODY_WINS;
        }
    }

    private void playerMove() {
        if(playerOnesTurn)
            playerOneMove();
        else
            playerTwoMove();
    }

    private void playerOneMove() {
        if(gameType != AI_VS_AI)
            io.getPlayerMove(PLAYER_ONE);
        else
            ai.move(PLAYER_ONE);
    }

    private void playerTwoMove() {
        if(gameType != PLAYER_VS_PLAYER)
            ai.move(PLAYER_TWO);
        else
            io.getPlayerMove(PLAYER_TWO);
    }

}
