package GUImodule;

import processing.core.PApplet;
import processing.core.PImage;

public class SunSet extends PApplet{

	PImage img;
	
	public SunSet() {
		// TODO Auto-generated constructor stub
	}

	public void setup(){//setup()only run once
		size(400,400);
		background(255); 
		//stroke(0);
		img = loadImage("http://cseweb.ucsd.edu/~minnes/palmTrees.jpg", "jpg");
		img.resize(0, height);
		image(img,0,0);
	}
	
	public void draw(){//draw()runs continuously in loop
		
		int[] color = sunColorSec(second()); //calculate color code for sun
		fill(color[0],color[1],color[2]); //set sun color
		ellipse(width/4,height/5,width/5,height/5); //draw sun
		
	}
	
	public int[] sunColorSec(float seconds){
		int[] rgb = new int[3];
		//scale the brightness of yellow based on the seconds. 0 is black, 30 is bright yellow
		float diff30 = Math.abs(30-seconds);
		float ratio = diff30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
}
