/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class Game {
    public static int PLAYER_VS_PLAYER = 0;
    public static int PLAYER_VS_AI = 1;
    public static int AI_VS_AI = 2;
    private AI ai;
    private IO console;
    private BoardAnalyzer analyzer;
    private int movesMade;
    private int gameType;

    public Game(AI ai, IO io, Board board, int type) {
        this.ai = ai;
        this.console = io;
        this.analyzer = new BoardAnalyzer(board);
        this.gameType = type;
        movesMade = 0;
    }

    public void play() {
        console.displayBoard();
        while(!gameOver()) {
            playerOneMove();
            console.displayBoard();
            movesMade++;
            if(!gameOver()) {
                playerTwoMove();
                console.displayBoard();
                movesMade++;
            }
        }
        console.displayMessage("GAME OVER!\n");
        console.displayMessage(getWinnerMessage() + "\n");
    }

    protected boolean gameOver() {
        if(analyzer.thereIsAWinner() || movesMade >= 9)
            return true;
        return false;
    }

    protected String getWinnerMessage() {
        if(analyzer.getWinner() == 'X') {
            return "X WINS!";
        } else if(analyzer.getWinner() == 'O') {
            return "O WINS!";
        } else {
            return "Nobody wins.";
        }
    }

    private void playerOneMove() {
        if(gameType != AI_VS_AI)
            console.getPlayerMove('X');
        else
            ai.move('X');
    }

    private void playerTwoMove() {
        if(gameType != PLAYER_VS_PLAYER)
            ai.move('O');
        else
            console.getPlayerMove('O');
    }
}
