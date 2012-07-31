require 'java'

import 'gooch.tictactoe.Board'

board = Board.new()
puts board.asString()
char_attempt = 'X'[0].to_java :char
int_attempt = 4.to_java :int
puts char_attempt 
puts int_attempt
board.putMarkInSquare(88, 4)
puts board.asString()
