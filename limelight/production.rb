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
  X_WINS_MSG = "I Can't Believe It!"
  O_WINS_MSG = "I Win!!"
  NOBODY_WINS_MSG = "Draw."

  def name
    return "Tic Tac Toe"
  end

  def production_opening
    @board = Board.new()
    @ai = MiniMaxAI.new(@board)
    @analyzer = BoardAnalyzer.new(@board)
  end

  def move_production_forward(scene, id)
    @scene = scene
    @board.putMarkInSquare(X, id.to_i)
    if !game_over?
      @ai.move(O)
    end
    draw_board
    if game_over?
      show_message scene
      play_audio_message(@scene.find(id))
      highlight_win
    end
  end

  def game_over?
    @analyzer.thereIsAWinner() ||
      @board.countSquaresAvailable() == 0
  end

  def clear_board
    9.times do |i|
      @board.removeMarkInSquare(i)
      @scene.find(i).text = ""
      @scene.find(i).style.background_color = "#444444ff"
    end
    message = @scene.find(:message)
    message.remove_all
  end

  def draw_board
    9.times do |i|
      mark = @board.getMarkInSquare(i)
      if mark == X || mark == O 
        @scene.find(i).text = mark.chr
      end
    end
  end

  def show_message scene
    message = get_message
    @scene.find(:message).build do
      __install 'partials/message_label.rb', :text => message
    end
  end

  def get_message
    if @analyzer.getWinner() == X
      return X_WINS_MSG
    elsif @analyzer.getWinner() == O
      return O_WINS_MSG
    else
      return NOBODY_WINS_MSG
    end
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

  def highlight_win
    if @analyzer.thereIsAWinner()
      winning_squares = @analyzer.getWinningSquares()
      3.times do |i|
        @scene.find(winning_squares[i]).style.background_color = "#999999ff"
      end
    end
  end

end

