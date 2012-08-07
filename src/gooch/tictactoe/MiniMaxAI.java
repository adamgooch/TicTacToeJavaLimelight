package gooch.tictactoe;

import java.util.ArrayList;

public class MiniMaxAI implements AI {
    private static final int POSITION = 0;
    private static final int SCORE = 1;
    private Board board;

    public MiniMaxAI(Board board) {
        this.board = board;
    }

    @Override
    public void move(char playerMark) {
        int square = findBestMove(playerMark);
        board.putMarkInSquare(playerMark, square);
        GameMaker.game.moveGameForward();
    }

    private int findBestMove(char mark) {
        ArrayList<Integer> positions = board.getAvailableSquares();
        int[][] scores = new int[positions.size()][2];
        for(int i = 0; i < positions.size(); i++){
            int position = positions.get(i);
            Board child = board.clone();
            child.putMarkInSquare(mark, position);
            int currentScore;
            if(mark == Game.PLAYER_TWO) {
                currentScore = min(child);
            } else {
                currentScore = max(child);
            }
            scores[i][POSITION] = position;
            scores[i][SCORE] = currentScore;
        }
        return determineBestMove(scores, mark);
    }

    public int max(Board childBoard){
        NineSquareChecker checker = new NineSquareChecker(childBoard);
        if(checker.thereIsAWinner() && checker.getWinner() == Game.PLAYER_TWO)
            return 1;
        else if(checker.thereIsAWinner() && checker.getWinner() == Game.PLAYER_ONE)
            return -1;
        else if(childBoard.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = childBoard.getAvailableSquares();
        int best = Integer.MIN_VALUE;
        for(Integer pos : positions){
            Board newChild = childBoard.clone();
            newChild.putMarkInSquare(Game.PLAYER_TWO, pos);
            int move = min(newChild);
            if(move > best)
                best = move;
        }
        return best;
    }

    public int min(Board childBoard){
        NineSquareChecker checker = new NineSquareChecker(childBoard);
        if(checker.thereIsAWinner() && checker.getWinner() == Game.PLAYER_TWO)
            return 1;
        else if(checker.thereIsAWinner() && checker.getWinner() == Game.PLAYER_ONE)
            return -1;
        else if(childBoard.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = childBoard.getAvailableSquares();
        int best = Integer.MAX_VALUE;
        for(Integer pos : positions){
            Board newChild = childBoard.clone();
            newChild.putMarkInSquare(Game.PLAYER_ONE, pos);
            int move = max(newChild);
            if(move < best)
                best = move;
        }
        return best;
    }

    private int determineBestMove(int[][] scores, char mark) {
        int bestIndex = 0;
        int bestScore = scores[bestIndex][SCORE];
        for(int i = 0; i < scores.length; i++){
            if((mark == Game.PLAYER_TWO && scores[i][SCORE] > bestScore) ||
                    (mark == Game.PLAYER_ONE && scores[i][SCORE] < bestScore)) {
                bestScore = scores[i][SCORE];
                bestIndex = i;
            }
        }
        return scores[bestIndex][POSITION];
    }

}
