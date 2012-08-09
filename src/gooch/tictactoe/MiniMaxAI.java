package gooch.tictactoe;

import java.util.ArrayList;

public class MiniMaxAI extends InputReceiver implements AI {
    private static final int POSITION = 0;
    private static final int SCORE = 1;

    private int depth; //lower depth = faster move made = more dangerous
    private Board board;
    private BoardChecker checker;

    public MiniMaxAI(Board board) {
        super(board);
        this.board = board;
        checker = new BoardChecker(board);
        if(board.dimension > 3)
            depth = 3;
        else
            depth = 9;
    }

    @Override
    public void move(char playerMark) {
        if(board.countSquaresAvailable() >= 13) {
            if(board.getMarkInSquare(5) == ' ')
                board.putMarkInSquare('O', 5);
            else if(board.getMarkInSquare(6) == ' ')
                board.putMarkInSquare('O', 6);
            else if(board.getMarkInSquare(9) == ' ')
                board.putMarkInSquare('O', 9);
            else
                board.putMarkInSquare('O', 10);
        } else {
            int square = findBestMove(playerMark);
            board.putMarkInSquare(playerMark, square);
        }
        notifyListeners();
    }

    private int findBestMove(char mark) {
        ArrayList<Integer> positions = board.getAvailableSquares();
        int[][] scores = new int[positions.size()][2];
        for(int i = 0; i < positions.size(); i++){
            int position = positions.get(i);
            board.putMarkInSquare(mark, position);
            int currentScore;
            int beta = Integer.MIN_VALUE;
            int alpha = Integer.MAX_VALUE;
            if(mark == Game.PLAYER_TWO) {
                currentScore = minimax(false, alpha, beta, depth);
            } else {
                currentScore = minimax(true, alpha, beta, depth);
            }
            board.removeMarkInSquare(position);
            scores[i][POSITION] = position;
            scores[i][SCORE] = currentScore;
        }
        return determineBestMove(scores, mark);
    }

    public int minimax(boolean needMax, int alpha, int beta, int local_depth){
        if(checker.thereIsAWinner() || checker.boardIsFull() || local_depth == 0)
            return getScore();
        ArrayList<Integer> positions = board.getAvailableSquares();
        for(Integer pos : positions){
            if(needMax) {
                board.putMarkInSquare(Game.PLAYER_TWO, pos);
                int moveScore = minimax(!needMax, alpha, beta, local_depth);
                board.removeMarkInSquare(pos);
                if(moveScore > beta) {
                    beta = moveScore;
                    if(alpha < beta)
                        return beta;
                }
            } else {
                board.putMarkInSquare(Game.PLAYER_ONE, pos);
                int moveScore = minimax(!needMax, alpha, beta, local_depth--);
                board.removeMarkInSquare(pos);
                if(moveScore < alpha) {
                    alpha = moveScore;
                    if(alpha < beta)
                        return alpha;
                }
            }
        }
        if(needMax)
            return beta;
        else
            return alpha;
    }

    private int getScore() {
        if(checker.getWinner() == Game.PLAYER_TWO)
            return 1;
        else if(checker.getWinner() == Game.PLAYER_ONE)
            return -1;
        else
            return 0;
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
