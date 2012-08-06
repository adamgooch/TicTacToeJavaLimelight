require File.expand_path(File.dirname(__FILE__) + '/../../colors')

module TttSquare

  def mouse_clicked e
    if active?
      self.style.background_color = Colors::SQUARE_INACTIVE
      production.move_production_forward(self.id)
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
