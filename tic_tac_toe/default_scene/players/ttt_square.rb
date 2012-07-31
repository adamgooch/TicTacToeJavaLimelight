require 'java'

import 'gooch.tictactoe.Board'

module TttSquare

  X = 88
  O = 79

  def mouse_clicked (event)
    if self.text == "" && !game_over
      id = self.id
      production.board.putMarkInSquare(X, id.to_i)
      game_over ? show_message : production.ai.move(O)
      drawboard
      if game_over
        show_message 
      end
    end
  end

  def drawboard
    9.times do |i|
      mark = production.board.getMarkInSquare(i)
      if mark == X || mark == O 
        scene.find(i).text = mark.chr
      end
    end
  end

  def game_over
    production.analyzer.thereIsAWinner() || production.board.countSquaresAvailable() == 0 
  end

  def show_message
    message = get_message
    scene.build do
      __install 'partials/message_label.rb', :text => message
    end
  end

  def get_message
    if production.analyzer.getWinner() == X  
      return "I Can't Believe It!"
    elsif production.analyzer.getWinner() == O
      return "I Win!!"
    else
      return "Draw."
    end
  end

end
