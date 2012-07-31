require 'java'

import 'gooch.tictactoe.Board'
import 'gooch.tictactoe.MiniMaxAI'
import 'gooch.tictactoe.BoardAnalyzer'

module Production
  attr_accessor :board
  attr_accessor :ai
  attr_accessor :analyzer

  def name
    return "Tic Tac Toe"
  end

  def production_opening
    @board = Board.new()
    @ai = MiniMaxAI.new(@board)
    @analyzer = BoardAnalyzer.new(@board)
  end

end
