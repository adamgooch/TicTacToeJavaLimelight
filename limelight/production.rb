require 'java'
require File.expand_path(File.dirname(__FILE__) + '/gui_io')
require File.expand_path(File.dirname(__FILE__) + '/colors')

import 'gooch.tictactoe.Board'
import 'gooch.tictactoe.MiniMaxAI'
import 'gooch.tictactoe.NineSquareChecker'
import 'gooch.tictactoe.Game'
import 'gooch.tictactoe.PlayType'

module Production

  attr_accessor :game
  attr_accessor :scene

  X = 88
  O = 79
  X_WIN_SOUND = 'sounds/reverseit.au'
  O_WIN_SOUND = 'sounds/yougetnothing.au'
  DRAW_SOUND = 'sounds/journeysover.au'

  def name
    return "Tic Tac Toe"
  end

#  def production_opening
#  end

  def production_opened
    stage = theater.stages[0]
    @scene = stage.current_scene
    @board = Board.new()
    @io = GuiIo.new(self, @board)
    @ai = MiniMaxAI.new(@board)
    @checker = NineSquareChecker.new(@board)
    @game = Game.new(@ai, @io, @checker, PlayType::PLAYER_VS_AI)
  end

  def move_production_forward(id)
    @game.movesMade += 1
    @board.putMarkInSquare(X, id.to_i)
    if !@game.gameOver
      @ai.move(O)
      @game.movesMade += 1
    end
    @io.displayBoard
    if @game.gameOver
      @io.displayMessage(@game.getWinnerMessage)
      highlight_win
    end
  end

  def clear_board
    @game.movesMade = 0
    9.times do |i|
      @board.removeMarkInSquare(i)
      @scene.find(i).text = ""
      @scene.find(i).style.background_color = Colors::SQUARE_INACTIVE
    end
    message = @scene.find(:message)
    message.remove_all
  end

  def play_audio_message prop
    if @checker.getWinner() == X
      prop.play_sound(X_WIN_SOUND)
    elsif @checker.getWinner() == O
      prop.play_sound(O_WIN_SOUND)
    else
      prop.play_sound(DRAW_SOUND)
    end
  end

  def highlight_win
    if @checker.thereIsAWinner()
      winning_squares = @checker.getWinningSquares()
      3.times do |i|
        @scene.find(winning_squares[i]).style.background_color =
            Colors::SQUARE_ACTIVE
      end
    end
  end

end

