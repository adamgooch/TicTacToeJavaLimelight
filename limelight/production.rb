require 'java'

import 'gooch.tictactoe.Board'
import 'gooch.tictactoe.MiniMaxAI'
import 'gooch.tictactoe.BoardAnalyzer'

module Production
  X = 88
  O = 79
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

  def move_production_forward(scene, id)
      board.putMarkInSquare(X, id.to_i)
      if !game_over 
        ai.move(O)
      end
      drawboard(scene)
      if game_over
        show_message(scene) 
      end
  end

  def drawboard scene
    9.times do |i|
      mark = board.getMarkInSquare(i)
      if mark == X || mark == O 
        scene.find(i).text = mark.chr
      end
    end
  end

  def game_over
    analyzer.thereIsAWinner() || 
      board.countSquaresAvailable() == 0 
  end

  def show_message scene
    message = get_message
    scene.build do
      __install 'partials/message_label.rb', :text => message
    end
  end

  def get_message
    if analyzer.getWinner() == X  
      return "I Can't Believe It!"
    elsif analyzer.getWinner() == O
      return "I Win!!"
    else
      return "Draw."
    end
  end



end
