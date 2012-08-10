require File.expand_path(File.dirname(__FILE__) + '/gui_io')

module Production

  attr_accessor :scene
  attr_reader :io
  attr_reader :game
  attr_reader :board
  attr_reader :stage

  def name
    return "Tic Tac Toe"
  end

  def production_opened
    @stage = theater.stages[0]
    if @stage.current_scene != nil
      @scene = stage.current_scene
    end
  end

  def start_game selection
    @board = Board.new(3)
    @io = GuiIo.new(board)
    @io.production = self
    @io.play_type = selection
    @game = Game.new(@io, @board)
    @game.begin()
  end

end
