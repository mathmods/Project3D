package org.github.mathmods.project3d;

import java.util.Random;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;

public class Game {

	public static final int width = 1220;
	public static final int height = 700;
	
	float rotation = 0;
	float x = 0, y = 0, z = 0;
	
	int viewy = 25, viewx = 25;
	
	int radius = 7;
	
	float camerax = 0, cameray = 0, cameraz = radius;
	
	double degreessurface = 90;
	double degreesup = 0;
	
	int mousex, mousey;
	boolean isLdown = false, isRdown = false;
	boolean wantToClose = false;
	
	private long lastFrame;
	private int fps;
	private long lastTime;
	
	Random rand = new Random(135653224);
	
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
		
		//Initialize OpenGL
		initGL();
		getDelta();
		lastTime = getTime();
		
		//The game loop
		while(!Display.isCloseRequested() && !wantToClose){
			
			int delta = getDelta();
			
		//All the rendering
			
			renderGL();
			update(delta);
			
		//Updating the screen
			
			//Checking the inputs
			inputs();
			
			//Update the screen
			Display.update();
		}
		
		//Exiting screen after close requested
		Display.destroy();
		System.exit(0);
	}
	
	public void inputs(){
		mousex = getX();
		mousey = getY();
		isLdown = isButtonDown(0);
		isRdown = isButtonDown(1);
		if(isKeyDown(KEY_ESCAPE)){
			wantToClose = true;
		}
		if(isKeyDown(KEY_W)){
			degreesup += 1;
			if(degreesup > 360){
				degreesup -= 360;
			}
			updatePositionY();
		}
		
		if(isKeyDown(KEY_A)){
			degreessurface += 1;
			if(degreessurface > 360){
				degreessurface -= 360;
			}
			updatePositionSurface();
		}
		
		if(isKeyDown(KEY_S)){
			
		}
		
		if(isKeyDown(KEY_D)){
			degreessurface -= 1;
			if(degreessurface < 0){
				degreessurface += 360;
			}
			updatePositionSurface();
		}
	}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
 
	    return delta;
	}
	
	public long getTime() {
	    return System.currentTimeMillis();
	}
	
	public void updateFPS() {
	    if (getTime() - lastTime > 1000) {
	        Display.setTitle("FPS: " + fps);
	        fps = 0;
			lastTime = getTime();
	    }
	    fps++;
	}
	
	public void update(int delta) {
		rotation += 0.15f * delta;
 
		updateFPS(); // update FPS Counter
	}
	
	public void renderGL() {
		
		glViewport(viewx, viewy, Display.getWidth(), Display.getHeight());
		
		// Clear The Screen And The Depth Buffer
		glClearColor(0f, 0f, 0f, 0.5f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		gluLookAt(camerax, cameray, cameraz, x, y, z, 0, 1, 0);
		
		glTranslatef(x-.5f, y-.5f, z-.5f);

		draw1MCube();
		glTranslatef(-x+.5f, -y+.5f, -z+.5f);
		
		Display.sync(60);
	}
	
	public void draw1MCube(){
		glBegin(GL_QUADS);
		{
			//Front
			glColor3f(0f, 0f, 1f);
			glVertex3f(0f, 0f, 1.0f);
			glVertex3f(1.0f, 0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(0f, 1.0f, 1.0f);
			
			//Right
			glColor3f(0f, 1f, 0f);
			glVertex3f(1.0f, 0f, 0f);
			glVertex3f(1.0f, 0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 0f);
			
			//Back
			glColor3f(1f, 0f, 0f);
			glVertex3f(1.0f, 0f, 0f);
			glVertex3f(0f, 0f, 0f);
			glVertex3f(0f, 1.0f, 0f);
			glVertex3f(1.0f, 1.0f, 0f);
			
			//Left
			glColor3f(0f, 1f, 1f);
			glVertex3f(0f, 0f, 1.0f);
			glVertex3f(0f, 0f, 0f);
			glVertex3f(0f, 1.0f, 0f);
			glVertex3f(0f, 1.0f, 1.0f);
			
			//Top
			glColor3f(1f, 1f, 0f);
			glVertex3f(0f, 1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 0f);
			glVertex3f(0f, 1.0f, 0f);
			
			//Bottom
			glColor3f(1f, 0f, 1f);
			glVertex3f(1.0f, 0f, 1.0f);
			glVertex3f(0f, 0f, 1.0f);
			glVertex3f(0f, 0f, 0f);
			glVertex3f(1.0f, 0f, 0f);
		}
		glEnd();
	}
	
	public void initGL() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		gluPerspective(45.5F, Display.getWidth() / Display.getHeight(), 1.0F, 1000.0F);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		glClearDepth(1.0f);
		
		glDepthFunc(GL_LEQUAL);
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_SMOOTH);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}
	
	private void updatePositionSurface(){
		double radians = Math.toRadians(degreessurface);
		camerax = (float)(x + radius * Math.cos(radians));
		cameraz = (float)(z + radius * Math.sin(radians));
	}
	
	private void updatePositionY(){
		double radians = Math.toRadians(degreesup);
		cameray = (float)(y + radius * Math.sin(radians));
	}
	
	public static void main(String[] args){
		Game g = new Game();
		g.start();
	}
	
}
