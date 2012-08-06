require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")

describe "Default Scene" do

  uses_limelight :scene => "default_scene", :hidden => true

  before(:each) do
    production.production_opened
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
    square.text.should == "X"
  end

  it "should display a message when the game is over" do
    scene.find(:message_label).should be_nil
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
    message = scene.find(:message_label)
    message.text.should == "O WINS!"
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

  it "should remove the message if it exists" do
    scene.find(3).mouse_clicked nil
    scene.find(6).mouse_clicked nil
    scene.find(4).mouse_clicked nil
    scene.find(:start_over).mouse_clicked nil
    scene.find(:message_label).should be_nil
  end

  it "should highlight the winning squares" do
    scene.find(3).mouse_clicked nil
    scene.find(6).mouse_clicked nil
    scene.find(4).mouse_clicked nil
    scene.find(0).style.background_color.should == "#999999ff"
    scene.find(1).style.background_color.should == "#999999ff"
    scene.find(2).style.background_color.should == "#999999ff"
  end

end
