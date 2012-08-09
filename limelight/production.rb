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
    @board = Board.new(3)
    @io = GuiIo.new(board)
    @io.production = self
    @game = Game.new(@io, @board)
    game.begin()
  end

  def io
    @io
  end

  def game
    @game
  end

  def board
    @board
  end

end
