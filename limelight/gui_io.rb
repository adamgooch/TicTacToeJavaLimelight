require 'java'
require File.expand_path(File.dirname(__FILE__) + '/production')

import 'gooch.tictactoe.Board'

class GuiIo
  include Java::gooch.tictactoe.IO

  attr_accessor :user_has_moved

  def initialize (prod, board)
    @board = board
    @production = prod
    @user_has_moved = false
  end

  def displayBoard()
    Board::NUMBER_OF_SQUARES.times do |square|
      put_text_in_square square
    end
  end

  def put_text_in_square square
    mark = @board.getMarkInSquare(square)
    square = @production.scene.find(square)
    if mark == Production::X || mark == Production::O
      square.text = mark.chr
    else
      square.text = ""
    end
  end

  def displayMessage message
    if message_is_not_displayed
      message_prop.build do
        __install 'partials/message_label.rb', :text => message
      end
    else
      child_props[0].text = message
    end
    @production.play_audio_message message_prop
  end

  def message_is_not_displayed
    message_prop = @production.scene.find(:message)
    child_props = message_prop.find_by_name("message")
    child_props.length == 0
  end

  def getPlayerMove player_mark
    until @user_has_moved
      sleep 0.5
    end
    @user_has_moved = false
  end

end
