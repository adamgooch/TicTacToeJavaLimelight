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
    scene.children.size.should == 4 #there are 3 rows and a button
    scene.find(3).mouse_clicked nil
    scene.find(4).mouse_clicked nil
    scene.find(6).mouse_clicked nil
    scene.find(7).mouse_clicked nil
    scene.children.size.should == 5 #the message is the fifth child
  end

  it "should display I Win!! when the computer has won" do
    scene.find(3).mouse_clicked nil
    scene.find(6).mouse_clicked nil
    scene.find(4).mouse_clicked nil
    message = scene.find(9)
    message.text.should == "I Win!!"
  end

  it "should have a start over button" do
    button = scene.find(:start_over)
    button.should_not be_nil
    button.text.should == "Start Over"
  end

  it "should clear the board when the start over button is clicked" do
    scene.find(0).mouse_clicked nil
    scene.find(5).mouse_clicked nil
    scene.find(:start_over).mouse_clicked nil
    scene.find(0).text.should == ""
    scene.find(5).text.should == ""
  end

end
