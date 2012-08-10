require File.expand_path(File.dirname(__FILE__) + '/../../gui_io')

module TttSquare

  def mouse_clicked e
    if active?
      self.style.background_color = Colors::SQUARE_INACTIVE
      production.board.putMarkInSquare(production.io.mark, self.id.to_i)
      production.io.notifyListeners()
    end
  end

  def mouse_entered e
    if active?
      self.style.background_color = Colors::SQUARE_ACTIVE
    end
  end

  def mouse_exited e
    if active?
      self.style.background_color = Colors::SQUARE_INACTIVE
    end
  end

  def active?
    self.text == "" && !production.game.gameOver
  end

end
