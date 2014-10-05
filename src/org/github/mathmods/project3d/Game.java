package org.github.mathmods.project3d;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

public class Game {

	public static final int width = 1440;
	public static final int height = 700;
	
	int x, y;
	boolean isLdown, isRdown = false;
	
	public void start(){
		try {
			
			//Creating a Display(May cause a error)
			
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		} catch (LWJGLException e) {
			
			//Exiting by unknown error
			
			e.printStackTrace();
			System.exit(0);
		}
		
		boolean wantToClose = false;
		
		while(!Display.isCloseRequested() && !wantToClose){
			
			//All the rendering
			
			x = Mouse.getX();
			y = Mouse.getY();
			isLdown = Mouse.isButtonDown(0);
			isRdown = Mouse.isButtonDown(1);
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				wantToClose = true;
			}
			
			//Updating the screen
			
			Display.update();
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args){
		Game g = new Game();
		g.start();
	}
	
}
