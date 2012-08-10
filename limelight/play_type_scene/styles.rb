play_type_scene {
  background_color :black
  horizontal_alignment :center
  vertical_alignment :center
  width "100%"
  height "100%"
}

type_button {
  height 100
  width 300
  margin 10
  horizontal_alignment :center
  vertical_alignment :center
  border_color Colors::PLAY_TYPE_BORDER
  border_width 2
  background_color Colors::PLAY_TYPE_INACTIVE
  secondary_background_color Colors::PLAY_TYPE_ACTIVE
  gradient :on
  rounded_corner_radius 30
  font_size 28
}

