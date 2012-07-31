require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")

describe "Default Scene" do

  uses_limelight :scene => "default_scene", :hidden => true

  before(:each) do
    production.production_opening
  end

  it "should have 9 buttons with no text" do
    9.times do |i|
      scene.find(i).text.should == ""
    end
  end

  it "should put an X in a clicked square that is empty" do
    square = scene.find("0")
    square.text.should == ""
    square.mouse_clicked nil
    square.text.should == "X"
  end

  it "should put an O in the middle square when X moves in a corner" do
    square = scene.find("0")
    square.mouse_clicked nil
    scene.find(4).text.should == 'O' 
  end

  it "should display a message when the game is over" do
    scene.children.size.should == 3 
    scene.find(3).mouse_clicked nil
    scene.find(4).mouse_clicked nil
    scene.find(6).mouse_clicked nil
    scene.find(7).mouse_clicked nil
    scene.children.size.should == 4
  end

  it "should display I Win!! when the computer has won" do
    scene.find(3).mouse_clicked nil
    scene.find(6).mouse_clicked nil
    scene.find(4).mouse_clicked nil
  end

end
