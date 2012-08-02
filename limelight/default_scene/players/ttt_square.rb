module TttSquare

  def mouse_clicked e
    if active?
      self.style.background_color = "#444"
      production.move_production_forward(self.id)
    end
  end
  
  def mouse_entered e
    if active?
      self.style.background_color = "#999"
    end
  end

  def mouse_exited e
    self.style.background_color = "#444"
  end

  def active?
    self.text == "" && !production.game_over?
  end

end
