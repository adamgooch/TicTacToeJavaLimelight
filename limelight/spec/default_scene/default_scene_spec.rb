require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")

describe "Default Scene" do

  uses_limelight :scene => "default_scene", :hidden => true

  before(:each) do
    production.production_opening
    production.scene = scene
  end

  it "should have 9 buttons with no text" do
    9.times do |i|
      scene.find(i).text.should == ""
    end
  end

  it "should put an X in a clicked square that is empty" do
    square = scene.find(0)
    square.text.should == ""
    square.mouse_clicked nil
    production.gui_io.displayBoard
    square.text.should == "X"
  end

end
