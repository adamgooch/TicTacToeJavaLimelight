start_over_button :id => :start_over,
  :text => "Start Over",
  :on_mouse_entered => "style.background_color = :red",
  :on_mouse_exited => "style.background_color = '#550000'"
row do
  ttt_square :id => 0
  ttt_square :id => 1
  ttt_square :id => 2
end
row do
  ttt_square :id => 3
  ttt_square :id => 4
  ttt_square :id => 5
end
row do
  ttt_square :id => 6
  ttt_square :id => 7
  ttt_square :id => 8
end
