package org.github.mathmods.project3d;

import java.util.Random;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Game {

	public static final int width = 1220;
	public static final int height = 700;
	
	float rotation = 0;
	float x = 0, y = 0, z = 0;
	
	int vievy = 25, vievx = 25;
	
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
		while(Keyboard.next()){
			if(getEventKeyState()){
				if(getEventKey() == KEY_W){
					
				}else if(getEventKey() == KEY_A){
					
				}else if(getEventKey() == KEY_S){
					
				}else if(getEventKey() == KEY_D){
					
				}
			}else{
				if(getEventKey() == KEY_W){
					
				}else if(getEventKey() == KEY_A){
					
				}else if(getEventKey() == KEY_S){
					
				}else if(getEventKey() == KEY_D){
					
				}
			}
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
		
		glViewport(vievx, vievy, Display.getWidth(), Display.getHeight());
		
		
		// Clear The Screen And The Depth Buffer
		glClearColor(0f, 0f, 0f, 0.5f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		glTranslatef(x, y, -6.0f);
		glRotatef(rotation, 0, rand.nextInt(5), 0);
		glRotatef(rotation, rand.nextInt(5), 0, 0);
		glRotatef(rotation, 0, 0, rand.nextInt(5));
		draw1MCube();
		glTranslatef(-x, -y, 6.0f);
	}
	
	public void draw1MCube(){
		glBegin(GL_QUADS);
		{
			//Sides
			glColor3f(0f, 0f, 1f);
			glVertex3f(-1.0f, -1.0f, 1.0f);
			glVertex3f(1.0f, -1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(-1.0f, 1.0f, 1.0f);
			
			glColor3f(0f, 1f, 0f);
			glVertex3f(1.0f, -1.0f, -1.0f);
			glVertex3f(1.0f, -1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, -1.0f);
			
			glColor3f(1f, 0f, 0f);
			glVertex3f(1.0f, -1.0f, -1.0f);
			glVertex3f(-1.0f, -1.0f, -1.0f);
			glVertex3f(-1.0f, 1.0f, -1.0f);
			glVertex3f(1.0f, 1.0f, -1.0f);
			
			glColor3f(0f, 1f, 1f);
			glVertex3f(-1.0f, -1.0f, 1.0f);
			glVertex3f(-1.0f, -1.0f, -1.0f);
			glVertex3f(-1.0f, 1.0f, -1.0f);
			glVertex3f(-1.0f, 1.0f, 1.0f);
			
			//Top
			glColor3f(1f, 1f, 0f);
			glVertex3f(-1.0f, 1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, 1.0f);
			glVertex3f(1.0f, 1.0f, -1.0f);
			glVertex3f(-1.0f, 1.0f, -1.0f);
			
			//Bottom
			glColor3f(1f, 0f, 1f);
			glVertex3f(1.0f, -1.0f, 1.0f);
			glVertex3f(-1.0f, -1.0f, 1.0f);
			glVertex3f(-1.0f, -1.0f, -1.0f);
			glVertex3f(1.0f, -1.0f, -1.0f);
		}
		glEnd();
	}
	
	public void initGL() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		GLU.gluPerspective(45.5F, Display.getWidth() / Display.getHeight(), 1.0F, 1000.0F);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		glClearDepth(1.0f);
		
		glDepthFunc(GL_LEQUAL);
		glEnable(GL_DEPTH_TEST);
		glShadeModel(GL_SMOOTH);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}
	
	public static void main(String[] args){
		Game g = new Game();
		g.start();
	}
	
}
