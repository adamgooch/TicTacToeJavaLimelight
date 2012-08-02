require 'java'
require File.expand_path(File.dirname(__FILE__) + '/production')

class GuiIo
  include Java::gooch.tictactoe.IO

  attr_accessor :user_has_moved

  def initialize (prod, board)
    @board = board
    @production = prod
    @user_has_moved = false
  end

  def displayBoard()
    9.times do |i|
      mark = @board.getMarkInSquare(i)
      square = @production.scene.find(i)
      if mark == Production::X || mark == Production::O 
        square.text = mark.chr
      else
        square.text = ""
      end
    end
  end

  def displayMessage message
    message_prop = @production.scene.find(:message)
    child_props = message_prop.find_by_name("message")
    if child_props.length == 0
      message_prop.build do
        __install 'partials/message_label.rb', :text => message
      end
    else
      child_props[0].text = message
    end
    @production.play_audio_message message_prop
  end

  def getPlayerMove player_mark
    until @user_has_moved
      sleep 0.5
    end
    @user_has_moved = false
  end

  def clear_board
    9.times do |i|
      @board.removeMarkInSquare(i)
    end
    displayBoard
  end

  def remove_message
    message = @production.scene.find(:message)
    message.remove_all
  end

end
