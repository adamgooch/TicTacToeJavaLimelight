default_scene {
  background_color :black
  horizontal_alignment :center
  padding 10
  width "100%"
  height "100%"
}

row {
  width "100%"
  horizontal_alignment :center
}

ttt_square {
  height 100
  width 100
  border_color "#aaa"
  border_width 2
  background_color Colors::SQUARE_INACTIVE
  horizontal_alignment :center
  vertical_alignment :center
  margin 5
  font_size 48
}

start_over_button {
  height 80
  width 250
  margin 10
  horizontal_alignment :center
  vertical_alignment :center
  background_color Colors::START_OVER_ACTIVE
  secondary_background_color Colors::START_OVER_INACTIVE
  gradient :on
  rounded_corner_radius 30
  font_size 40
  text_color :white
}
