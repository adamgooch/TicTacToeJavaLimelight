require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")

describe "Default Scene" do

  uses_limelight :scene => "default_scene", :hidden => true

  before(:each) do
    production.production_opening
    production.scene = scene
  end

  it "should have 9 buttons with no text" do
    9.times do |id|
      scene.find(id).text.should == ""
    end
  end

  it "should put an X in a clicked square that is empty" do
    square = scene.find(0)
    square.text.should == ""
    square.mouse_clicked nil
    production.gui_io.displayBoard
    square.text.should == "X"
  end

  it "should add a message prop at the end of a game" do
    message_prop = scene.find(:message)
    child_props  = message_prop.find_by_name("message")
    child_props.length.should == 0
    production.gui_io.displayMessage "testing"
    child_props  = message_prop.find_by_name("message")
    child_props.length.should == 1
  end

end
