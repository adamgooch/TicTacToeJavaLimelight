require 'java'
require File.expand_path(File.dirname(__FILE__) + '/gui_io')

import 'gooch.tictactoe.Board'
import 'gooch.tictactoe.MiniMaxAI'
import 'gooch.tictactoe.BoardAnalyzer'
import 'gooch.tictactoe.Game'

module Production
  X = 88
  O = 79
  X_WIN_SOUND = 'sounds/reverseit.au'
  O_WIN_SOUND = 'sounds/yougetnothing.au'
  DRAW_SOUND = 'sounds/journeysover.au'

  attr_accessor :scene

  def name
    return "Tic Tac Toe"
  end

  def production_opening
    @board = Board.new()
    @ai = MiniMaxAI.new(@board)
    @analyzer = BoardAnalyzer.new(@board)
    @gui_io = GuiIo.new(self, @board)
    @game = Game.new(@ai, @gui_io, @board, 1)
  end

  def production_opened
    stage = theater.stages[0]
    @scene = stage.current_scene
    @game.play
  end

  def move_production_forward(id)
    @board.putMarkInSquare(X, id.to_i)
    @gui_io.user_has_moved = true
  end

  def game_over?
    @game.game_over
  end

  def play_audio_message prop
    if @analyzer.getWinner() == X
      prop.play_sound(X_WIN_SOUND)
    elsif @analyzer.getWinner() == O
      prop.play_sound(O_WIN_SOUND)
    else
      prop.play_sound(DRAW_SOUND)
    end
  end

  def gui_io
    @gui_io
  end

end
