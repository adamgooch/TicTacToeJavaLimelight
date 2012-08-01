module StartOverButton

  def mouse_clicked e
    production.clear_board(scene)
  end

  def mouse_entered e
    self.style.background_color = :red
  end

  def mouse_exited e
    self.style.background_color = "#550000" 
  end

end
