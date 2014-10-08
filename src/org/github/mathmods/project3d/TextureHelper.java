package org.github.mathmods.project3d;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureHelper extends TextureLoader {

	public Texture getTexture(String name){
		try {
			return getTexture("png", ResourceLoader.getResourceAsStream("res/textures/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
