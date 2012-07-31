require 'java'

import 'gooch.tictactoe.Board'

module TttSquare

  X = 88
  O = 79

  def mouse_entered e
    if !production.game_over && self.text == ""
      self.style.background_color = "#999"
    end
  end

  def mouse_exited e
    self.style.background_color = "#444"
  end

  def mouse_clicked (event)
    if self.text == "" && !production.game_over
      id = self.id
      production.board.putMarkInSquare(X, id.to_i)
      if !production.game_over 
        production.ai.move(O)
      end
      production.drawboard(scene)
      if production.game_over
        production.show_message(scene) 
      end
    end
  end

end
