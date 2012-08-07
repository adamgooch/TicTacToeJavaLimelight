require File.expand_path(File.dirname(__FILE__) + '/gui_io')

module Production

  attr_accessor :scene

  def name
    return "Tic Tac Toe"
  end

  def production_opened
    stage = theater.stages[0]
    @scene = stage.current_scene
    @io = GuiIo.new(self)
    gameMaker = GameMaker.new(@io)
  end

  def io
    @io
  end

end
