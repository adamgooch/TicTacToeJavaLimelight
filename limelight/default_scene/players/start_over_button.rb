require File.expand_path(File.dirname(__FILE__) + '/../../colors')

module StartOverButton

  def mouse_clicked e
    production.io.clear_board
  end

  def mouse_entered e
    self.style.background_color = :red
  end

  def mouse_exited e
    self.style.background_color = Colors::START_OVER_ACTIVE
  end

end
