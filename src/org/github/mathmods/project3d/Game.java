package org.github.mathmods.project3d;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;

public class Game {

	public static final int width = 1220;
	public static final int height = 700;
	
	public static final float blockHeight = 1f;
	public static final float blockWidth = (1f/5f)*3f;
	
	float rotation = 0;
	float x = 0, y = 0, z = 0;
	
	int viewy = 25, viewx = 25;
	
	//int radius = 7;
	
	float camerax = 3, cameray = 5, cameraz = 7;
	
	float lookx = camerax, looky = cameray, lookz = cameraz-5;
	
	double degreessurface = 90;
	double degreesup = 0;
	
	int mousex, mousey;
	boolean isLdown = false, isRdown = false;
	boolean wantToClose = false;
	
	private long lastFrame;
	private int fps;
	private long lastTime;
	
	World world;
	
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
		setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
		world = new World(10);
		
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
		if(mousex != Display.getWidth()/2){
			if(mousex < Display.getWidth()/2){
				
			}else{
				
			}
			setCursorPosition(Display.getWidth()/2, mousey);
		}
		if(mousey != Display.getHeight()/2){
			if(mousey < Display.getHeight()/2){
				
			}else{
				
			}
			setCursorPosition(getX(), Display.getHeight()/2);
		}
		if(isKeyDown(KEY_ESCAPE)){
			wantToClose = true;
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
		
		//Doesn't lag but isn't a full world
		Block b = new BlockGrass();
		glTranslatef(b.x-.5f, b.y-.5f, b.z-.5f);
		draw1MCube(b);
		glTranslatef(-b.x+.5f, -b.y+.5f, -b.z+.5f);
		Block d = new BlockDirt();
		glTranslatef(d.x-.5f, d.y-.5f, d.z-.5f);
		draw1MCube(d);
		glTranslatef(-d.x+.5f, -d.y+.5f, -d.z+.5f);
		/* LAGGING AS IF IT WAS NO SENSE PLAYING D:*\
		for(int cx = 0;cx<10;cx++){
			for(int cy = 0;cy<10;cy++){
				for(int x = 0;x<16;x++){
					for(int z = 0;z<16;z++){
						for(int y = 0;y<52;y++){
							Block b = world.spawnChunks[cx][cy].getBlock(x, y, z);
							if(b!=null){
								glTranslatef(world.spawnChunks[cx][cy].x + x, y,
										world.spawnChunks[cx][cy].z + z);
								draw1MCube(b);
								glTranslatef(-world.spawnChunks[cx][cy].x - x, -y,
										-world.spawnChunks[cx][cy].z + -z);
							}
						}
					}
				}
			}
		}/*OH NO*/
		
		Display.sync(60);
	}
	
	public void draw1MCube(Block b){
		Texture t = b.getIcon(3, 0);
		t.bind();
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			//Front
			glTexCoord2f(1, 1);
			glVertex3f(blockWidth, 0f, blockWidth);
			glTexCoord2f(0, 1);
			glVertex3f(0f, 0f, blockWidth);
			glTexCoord2f(0, 0);
			glVertex3f(0f, blockHeight, blockWidth);
			glTexCoord2f(1, 0);
			glVertex3f(blockWidth, blockHeight, blockWidth);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		t = b.getIcon(5, 0);
		t.bind();
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			//Right
			glTexCoord2f(1, 1);
			glVertex3f(blockWidth, 0f, 0f);
			glTexCoord2f(0, 1);
			glVertex3f(blockWidth, 0f, blockWidth);
			glTexCoord2f(0, 0);
			glVertex3f(blockWidth, blockHeight, blockWidth);
			glTexCoord2f(1, 0);
			glVertex3f(blockWidth, blockHeight, 0f);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		t = b.getIcon(4, 0);
		t.bind();
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			//Back
			glTexCoord2f(1, 1);
			glVertex3f(blockWidth, 0f, 0f);
			glTexCoord2f(0, 1);
			glVertex3f(0f, 0f, 0f);
			glTexCoord2f(0, 0);
			glVertex3f(0f, blockHeight, 0f);
			glTexCoord2f(1, 0);
			glVertex3f(blockWidth, blockHeight, 0f);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		t = b.getIcon(2, 0);
		t.bind();
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			//Left
			glTexCoord2f(1, 1);
			glVertex3f(0f, 0f, blockWidth);
			glTexCoord2f(0, 1);
			glVertex3f(0f, 0f, 0f);
			glTexCoord2f(0, 0);
			glVertex3f(0f, blockHeight, 0f);
			glTexCoord2f(1, 0);
			glVertex3f(0f, blockHeight, blockWidth);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		t = b.getIcon(1, 0);
		t.bind();
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			//Top
			glTexCoord2f(1, 1);
			glVertex3f(0f, blockHeight, blockWidth);
			glTexCoord2f(0, 1);
			glVertex3f(blockWidth, blockHeight, blockWidth);
			glTexCoord2f(0, 0);
			glVertex3f(blockWidth, blockHeight, 0f);
			glTexCoord2f(1, 0);
			glVertex3f(0f, blockHeight, 0f);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
		t = b.getIcon(0, 0);
		t.bind();
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		{
			//Bottom
			glTexCoord2f(1, 1);
			glVertex3f(blockWidth, 0f, blockWidth);
			glTexCoord2f(0, 1);
			glVertex3f(0f, 0f, blockWidth);
			glTexCoord2f(0, 0);
			glVertex3f(0f, 0f, 0f);
			glTexCoord2f(1, 0);
			glVertex3f(blockWidth, 0f, 0f);
		}
		glEnd();
		glDisable(GL_TEXTURE_2D);
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
	
	/*private void updatePosition(){
		
		double radiansup = Math.toRadians(degreesup);
		cameray = (float)(y + radius * Math.sin(radiansup));
		
		double radians = Math.toRadians(degreessurface);
		camerax = (float)(x + radius * Math.cos(radians));
		cameraz = (float)(z + radius * Math.sin(radians));
	}*///Updates the position of the camera, UNEEDED;
	
	public static void main(String[] args){
		Game g = new Game();
		g.start();
	}
	
}
