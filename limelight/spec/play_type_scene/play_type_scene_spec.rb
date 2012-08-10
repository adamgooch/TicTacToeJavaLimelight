require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")

describe "Play Type Scene" do

  uses_limelight :scene => "play_type_scene", :hidden => true

  it "should have three buttons to choose from" do
    scene.children.size.should == 3
  end

end
