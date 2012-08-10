require File.expand_path(File.dirname(__FILE__) + '/../../gui_io')

module TypeButton

  def mouse_clicked e
    production.producer.open_scene("default_scene", production.stage)
    production.scene = production.stage.current_scene
    if self.id == "player_player"
      type = PlayType::PLAYER_VS_PLAYER
    elsif self.id == "player_computer"
      type = PlayType::PLAYER_VS_AI
    else
      type = PlayType::AI_VS_AI
    end
    production.start_game type
  end

  def mouse_entered e
      self.style.background_color = Colors::PLAY_TYPE_ACTIVE
  end

  def mouse_exited e
      self.style.background_color = Colors::PLAY_TYPE_INACTIVE
  end

end
