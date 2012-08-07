require File.expand_path(File.dirname(__FILE__) + '/gui_io')

module Production

  attr_accessor :scene

  def name
    return "Tic Tac Toe"
  end

  def production_opened
    stage = theater.stages[0]
    if stage.current_scene != nil
      @scene = stage.current_scene
    end
    @io = GuiIo.new(self)
    GameMaker.makeGame(@io)
  end

  def io
    @io
  end

end
