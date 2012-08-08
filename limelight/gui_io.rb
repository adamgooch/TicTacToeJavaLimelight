require 'java'
require File.expand_path(File.dirname(__FILE__) + '/colors')

import 'gooch.tictactoe.Board'
import 'gooch.tictactoe.Game'
import 'gooch.tictactoe.PlayType'
import 'gooch.tictactoe.InputListener'

class GuiIo < Java::gooch.tictactoe.InputListener
  include Java::gooch.tictactoe.IO

  X = 88
  O = 79

  X_WIN_SOUND = 'sounds/reverseit.au'
  O_WIN_SOUND = 'sounds/yougetnothing.au'
  DRAW_SOUND = 'sounds/journeysover.au'

  attr_accessor :production

  def initialize (board)
    @board = board
  end

  def displayBoard
    Board::NUMBER_OF_SQUARES.times do |square|
      put_text_in_square(square)
    end
  end

  def put_text_in_square (square)
    mark = @board.getMarkInSquare(square)
    square = @production.scene.find(square)
    if mark == X || mark == O
      square.text = mark.chr
    else
      square.text = ""
    end
  end

  def displayMessage (message)
    message_container = @production.scene.find(:message)
    if message_is_not_displayed(message_container)
      build_message(message_container, message)
    else
      child_props[0].text = message
    end
  end

  def message_is_not_displayed (message_container)
    child_props = message_container.find_by_name("message")
    child_props.length == 0
  end

  def build_message (message_container, message)
    message_container.build do
      __install 'partials/message_label.rb', :text => message
    end
  end

  def getPlayerMove (player_mark)
  end

  def getPlayType
    PlayType::PLAYER_VS_AI
  end

  def playAudioMessage(message)
    if message == Game::PLAYER_ONE_WINS
      @production.scene.play_sound(X_WIN_SOUND)
    elsif message == Game::PLAYER_TWO_WINS
      @production.scene.play_sound(O_WIN_SOUND)
    else
      @production.scene.play_sound(DRAW_SOUND)
    end
  end

  def highlightWin(winning_squares)
    winning_squares.each do |i|
      square = @production.scene.find(i)
      square.style.background_color = Colors::SQUARE_ACTIVE
    end
  end

  def reset_game
    reset_square_color
    @production.production_opened
    remove_message
  end

  def reset_square_color
    Board::NUMBER_OF_SQUARES.times do |i|
      square = @production.scene.find(i)
      square.style.background_color = Colors::SQUARE_INACTIVE
    end
  end

  def remove_message
    message = @production.scene.find(:message)
    message.remove_all
  end

  def button_clicked
    notifyListeners()
  end

end
