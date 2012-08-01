require 'java'

import 'gooch.tictactoe.Board'
import 'gooch.tictactoe.MiniMaxAI'
import 'gooch.tictactoe.BoardAnalyzer'

module Production
  X = 88
  O = 79

  def name
    return "Tic Tac Toe"
  end

  def production_opening
    @board = Board.new()
    @ai = MiniMaxAI.new(@board)
    @analyzer = BoardAnalyzer.new(@board)
  end

  def move_production_forward(scene, id)
      @board.putMarkInSquare(X, id.to_i)
      if !game_over
        @ai.move(O)
      end
      draw_board(scene)
      if game_over
        show_message(scene)
        play_audio_message(scene, id)
      end
  end

  def game_over
    @analyzer.thereIsAWinner() ||
      @board.countSquaresAvailable() == 0
  end

  def clear_board scene
    9.times do |i|
      @board.removeMarkInSquare(i)
      scene.find(i).text = ""
    end
    message = scene.find(:message)
    message.remove_all
  end

  def draw_board scene
    9.times do |i|
      mark = @board.getMarkInSquare(i)
      if mark == X || mark == O 
        scene.find(i).text = mark.chr
      end
    end
  end

  def show_message scene
    message = get_message
    scene.find(:message).build do
      __install 'partials/message_label.rb', :text => message
    end
  end

  def get_message
    if @analyzer.getWinner() == X
      return "I Can't Believe It!"
    elsif @analyzer.getWinner() == O
      return "I Win!!"
    else
      return "Draw."
    end
  end

  def play_audio_message (scene, id)
    prop = scene.find(id)
    if @analyzer.getWinner() == X
      prop.play_sound('sounds/reverseit.au')
    elsif @analyzer.getWinner() == O
      prop.play_sound('sounds/yougetnothing.au')
    else
      prop.play_sound('sounds/journeysover.au')
    end
  end

end
