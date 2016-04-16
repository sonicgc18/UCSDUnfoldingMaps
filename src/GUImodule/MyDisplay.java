package GUImodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{

	public MyDisplay() {
		// TODO Auto-generated constructor stub
	}

	public void setup(){
		size(400,600);
		background(255,200,200);
	}
	
	public void draw(){
		fill(255,255,0);
		ellipse(width/2,height/2, 200,200);
		fill(0,0,0);
		ellipse(width/2-40,height/2-30, 30,40);
		ellipse(width/2+40,height/2-30, 30,40);

		noFill();
		arc(width/2,height/2+40, 50,50,0,PI);
	}
	
}
