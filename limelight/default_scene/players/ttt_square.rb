require File.expand_path(File.dirname(__FILE__) + '/../../gui_io')

module TttSquare

  def mouse_clicked e
    if active?
      self.style.background_color = Colors::SQUARE_INACTIVE
      GameMaker::board.putMarkInSquare(GuiIo::X, self.id.to_i)
      GameMaker::game.inputReceived()
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
    self.text == "" && !GameMaker::game.gameOver
  end

end
