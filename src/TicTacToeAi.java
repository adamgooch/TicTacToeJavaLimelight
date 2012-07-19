/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/17/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeAi {
    private TicTacToeBoard gameBoard;
    private int openSquare;


    public TicTacToeAi(TicTacToeBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public void move() {
        if(TicTacToe.movesMade <= 1) {
            makeFirstMove();
        } else if(!possibleWin() && !possibleThreat() && !createThreat()) {
            takeFirstAvailableSquare();
        }
        TicTacToe.movesMade++;
    }

    protected boolean possibleWin() {
        for(int i = 0; i < 3; i++) {
            if(canWinInRow('O', i))
                return gameBoard.putMarkInSquare('O', openSquare);
            if(canWinInColumn('O', i))
                return gameBoard.putMarkInSquare('O', openSquare);
        }
        if(canWinInDiagonal('O'))
            return gameBoard.putMarkInSquare('O', openSquare);
        return false;
    }

    private boolean canWinInRow(char mark, int row) {
        int count = 0;
        for(int column = 0; column < 3; column++) {
            int square = getSquare(row, column);
            count = count + incOrDecCount(square);
        }
        if(mark == 'O' && count == 2)
            return true;
        else if(mark == 'X' && count == -2)
            return true;
        return false;
    }

    private boolean canWinInColumn(char mark, int column) {
        int count = 0;
        for(int row = 0; row < 3; row++) {
            int square = getSquare(row, column);
            count = count + incOrDecCount(square);
        }
        if(mark == 'O' && count == 2)
            return true;
        else if(mark == 'X' && count == -2)
            return true;
        return false;
    }

    private boolean canWinInDiagonal(char mark) {
        int count = 0;
        for(int i = 0; i < 9; i += 4) {
            count = count + incOrDecCount(i);
        }
        if(mark == 'O' && count == 2) {
            return true;
        } else if(mark == 'X' && count == -2){
            return true;
        }
        count = 0;
        for(int i = 2; i < 8; i += 2) {
            count = count + incOrDecCount(i);
        }
        if(mark == 'O' && count == 2) {
            return true;
        } else if(mark == 'X' && count == -2){
            return true;
        }
        return false;
    }

    private int incOrDecCount(int square) {
        if(gameBoard.getMarkInSquare(square) == 'O') {
            return 1;
        } else if(gameBoard.getMarkInSquare(square) == 'X') {
            return -1;
        } else {
            openSquare = square;
            return 0;
        }
    }

    protected boolean possibleThreat() {
        for(int i = 0; i < 3; i++) {
            if(canWinInRow('X', i))
                return gameBoard.putMarkInSquare('O', openSquare);
            if(canWinInColumn('X', i))
                return gameBoard.putMarkInSquare('O', openSquare);
        }
        if(canWinInDiagonal('X'))
            return gameBoard.putMarkInSquare('O', openSquare);
        return false;
    }

    private int getSquare(int row, int column) {
        return row * 3 + column;
    }

    private boolean createThreat() {
        if( gameBoard.getMarkInSquare(4) == 'O' &&
                gameBoard.getMarkInSquare(1) != 'O'){
            gameBoard.putMarkInSquare('O', 1);
            return true;
        }
        return false;
    }

    private void makeFirstMove() {
        if(gameBoard.getMarkInSquare(0) == 'X' ||
           gameBoard.getMarkInSquare(2) == 'X' ||
           gameBoard.getMarkInSquare(6) == 'X' ||
           gameBoard.getMarkInSquare(8) == 'X') {
            gameBoard.putMarkInSquare('O', 4);
        } else if(gameBoard.getMarkInSquare(7) == 'X' ||
                gameBoard.getMarkInSquare(5) == 'X') {
            gameBoard.putMarkInSquare('O', 8);
        } else {
            takeFirstAvailableSquare();
        }
    }

    private void takeFirstAvailableSquare() {
        boolean flag = true;
        for(int i = 0; i < TicTacToeBoard.NUMBER_OF_SQUARES; i++) {
            if(gameBoard.putMarkInSquare('O', i)){
                flag = false;
                break;
            }
        }
    }

}
