/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/17/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeAi {
    TicTacToeBoard gameBoard;

    public TicTacToeAi(TicTacToeBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public void move() {
        for(int i = 0; i < TicTacToeBoard.NUMBER_OF_SQUARES; i++){
            if(gameBoard.putMarkInSquare('O', i)){
                TicTacToe.movesMade++;
                break;
            }
        }
    }
}
