module TttSquare

  def mouse_clicked e
    if self.text == "" && !production.game_over
      id = self.id
      self.style.background_color = "#444"
      production.move_production_forward(scene, id)
    end
  end
  
  def mouse_entered e
    if !production.game_over && self.text == ""
      self.style.background_color = "#999"
    end
  end

  def mouse_exited e
    self.style.background_color = "#444"
  end

end
